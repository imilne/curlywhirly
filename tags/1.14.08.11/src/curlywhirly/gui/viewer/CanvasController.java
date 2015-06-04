// Copyright 2009-2014 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package curlywhirly.gui.viewer;

import java.awt.Color;
import java.util.*;

import curlywhirly.data.*;
import curlywhirly.gui.*;

public class CanvasController
{
	private final WinMain winMain;

	public CanvasController(WinMain winMain)
	{
		this.winMain = winMain;
	}

	//update the current scene graph with new settings
	public void updateSelected(ArrayList<String> selected)
	{
		if (selected.size() < 0)
			return;

		for (Category category : winMain.getDataSet().getCurrentCategoryGroup())
			category.setSelected(selected.contains(category.getName()));
	}

		//changes the background colour
	public void setBackgroundColour(int newColour)
	{
		Color bgColor = Color.BLACK;
		switch (newColour)
		{
			case 0:
				bgColor = Color.BLACK;
				break;
			case 1:
				bgColor = Color.DARK_GRAY;
				break;
			case 2:
				bgColor = Color.LIGHT_GRAY;
				break;
			case 3:
				bgColor = Color.WHITE;
				break;
		}

		ColorPrefs.setColor("User.OpenGLPanel.background", bgColor);
	}
}