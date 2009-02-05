package graphviewer3d.data;

import java.io.File;

import javax.swing.JFileChooser;

import graphviewer3d.gui.DataLoadingDialog;
import graphviewer3d.gui.GraphViewerFrame;
import graphviewer3d.gui.Instructions3D;
import graphviewer3d.gui.Preferences;

public class FileLoader extends Thread
{
	GraphViewerFrame frame;
	public boolean done = false;
	File file;
	DataLoadingDialog dataLoadingDialog;

	public FileLoader(GraphViewerFrame frame, File file, DataLoadingDialog dataLoadingDialog)
	{
		this.frame = frame;
		this.file = file;
		this.dataLoadingDialog = dataLoadingDialog;
	}

	public void run()
	{
		frame.loadData(file);
		if (dataLoadingDialog != null)
			dataLoadingDialog.setVisible(false);
		if (frame.dataLoaded)
		{
			if (Preferences.show3DControlInstructions)
			{
				Instructions3D instr = new Instructions3D(frame);
				instr.show3DInstructions(true);
			}
		}
	}

}
