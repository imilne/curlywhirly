// Copyright 2009-2014 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package curlywhirly.opengl;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.*;
import javax.media.opengl.glu.*;
import javax.swing.*;
import javax.vecmath.*;

import curlywhirly.data.*;
import curlywhirly.gui.*;
import curlywhirly.gui.viewer.*;

import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;

import static javax.media.opengl.GL2.*;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.*;

// TODO: Check what exactly needs to be done with the animators at various points
public class OpenGLPanel extends GLJPanel implements GLEventListener
{
	public static int CANVAS_WIDTH = 800;
	public static int CANVAS_HEIGHT = 600;

	// The animator which updates the display at the desired framerate
	private Animator animator;

	private GLU glu;

	private Scene scene;

	private CanvasMouseListener mouseListener;

	// Collision detection variables
	private Point mousePoint = new Point(0, 0);
	private CollisionDetection detector;

	private final CloseOverlay closeOverlay;
	private final MovieCaptureEventListener movieCapture;

	public OpenGLPanel(WinMain winMain, GLCapabilities caps)
	{
		super(caps);

		closeOverlay = new CloseOverlay(winMain);
		movieCapture = new MovieCaptureEventListener(this);

		addGLEventListener(this);
		addGLEventListener(closeOverlay);
		addGLEventListener(movieCapture);

		ToolTipManager.sharedInstance().setInitialDelay(0);
	}

	public void setDataSet(DataSet dataSet)
	{
        Rotation rotation = new Rotation();
        int perspectiveAngle = 45;
        scene = new Scene(dataSet, rotation, perspectiveAngle, (float)CANVAS_WIDTH / CANVAS_HEIGHT);
		mouseListener = new CanvasMouseListener(this, rotation);

		detector = new CollisionDetection();

		scene.reset();
	}

	// Starts animation, this should be called when you want rendering to start
	public void startAnimator()
	{
		if (animator != null)
			animator.remove(this);

		animator = new Animator(this);
//		animator.setUpdateFPSFrames(200, System.out);
		animator.setPrintExceptions(true);
		animator.start();
	}

	// Stop the animator in situations where you need the display to pause
	// updating and to prevent unpredictable animation behaviour
	public void stopAnimator()
	{
		if (animator != null)
		{
			animator.stop();
			animator.pause();
		}
	}

	@Override
	public void init(GLAutoDrawable drawable)
	{
		// Our basic GL setup configuration
		GL2 gl = drawable.getGL().getGL2();

		glu = new GLU();
		gl.glClearDepth(1.0f);
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthMask(true);
		gl.glDepthRangef(0f, 1f);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		gl.glShadeModel(GL_SMOOTH);
		gl.glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
		gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		gl.glLineWidth(1.5f);
		gl.setSwapInterval(1);
		gl.glEnable(GL_RESCALE_NORMAL);
		gl.glEnable(GL_CULL_FACE);

        scene.init(gl);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) { }

	@Override
	public void display(GLAutoDrawable drawable)
	{
		// Don't try to render when the animator is paused
		if (animator.isPaused())
			return;

		// Get the graphics context
		GL2 gl = drawable.getGL().getGL2();
        scene.render(gl);
		drawTooltip(gl);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		// Get the graphics context
		GL2 gl = drawable.getGL().getGL2();

		height = height == 0 ? 1 : height;
		float aspect = (float)width / height;

        scene.setAspect(aspect);

		CANVAS_WIDTH = width;
		CANVAS_HEIGHT = height;

		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		// Setup perspective projection, with aspect ratio matching viewport
		scene.setPerspective(gl);

		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();

		mouseListener.initialiseArcBall(CANVAS_WIDTH, CANVAS_HEIGHT);
		animator.start();
    }

	// Uses GLPanel's method of getting a screenshot on the EDT, other methods
    // of getting screenshots could clutter up the rendering code.
	public BufferedImage getScreenShot()
        throws Exception
	{
        // Create an image of the correct proportions
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        // "Print" the current state of the panel to that image
        setupPrint(1, 1, 1, getWidth(), getHeight());
        printAll(g);
        image.flush();
        // Called to release the panel from the EDT.
        releasePrint();

        return image;
	}

	// Screenshot capture for exporting movies, not screenshots (which utilise
	// the JOGL EDT Screenshot method)
	public BufferedImage snapshot(GL gl, AWTGLReadBufferUtil readBufferUtil, File imageDir)
	{
		gl.glFinish();

		return readBufferUtil.readPixelsToBufferedImage(gl, true);
	}

	public void setMousePoint(Point point)
	{
		this.mousePoint = point;
	}

	private Ray getRay(GL2 gl)
	{
		int[] view = new int[4];
		float[] model = new float[16];

        float[] proj = scene.getProj();

		gl.glGetIntegerv(GL.GL_VIEWPORT, view, 0);
		// Get the current model view matrix
		gl.glGetFloatv(GL_MODELVIEW_MATRIX, model, 0);
		float winX = mousePoint.x;
		// Adjust into opengl y space
		float winY = CANVAS_HEIGHT - (float)mousePoint.y -1;

		// Used to store the result of gluUnproject for the near clipping plane
		float[] near = new float[4];
		glu.gluUnProject(winX, winY, 0, model, 0, proj, 0, view, 0, near, 0);
		Vector3f nVec = new Vector3f(near);

		// Used to store the result of gluUnproject for the far clipping plane
		float[] far = new float[4];
		glu.gluUnProject(winX, winY, 1, model, 0, proj, 0, view, 0, far, 0);
		Vector3f fVec = new Vector3f(far);

		// Subtract the near clipping plane vector from the far clipping plane
		// vector to establish the direction of our ray
		Vector3f dir = new Vector3f(fVec.x - nVec.x, fVec.y - nVec.y, fVec.z - nVec.z);
		Vector3f eye = new Vector3f(0, 0, 200);

		Ray ray = new Ray(dir, eye);

		return ray;
	}

	private void drawTooltip(GL2 gl)
	{
		// Mouse over code looking for sphere's under the mouse
		Ray ray = getRay(gl);
		DataPoint found = detector.findSphereRayIntersection(ray, scene.getPointSize());

		// If we have found a spehre, display this sphere's name in a tooltip
		String text = found != null ? found.getName() : null;
		setToolTipText(text);
	}

	public CloseOverlay getCloseOverlay()
		{ return closeOverlay; }

	public MovieCaptureEventListener getMovieCapture()
		{ return movieCapture; }

    public Scene getScene()
        { return scene; }
}