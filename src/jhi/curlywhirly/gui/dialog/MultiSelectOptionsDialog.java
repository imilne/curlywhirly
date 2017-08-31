// Copyright 2009-2017 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package jhi.curlywhirly.gui.dialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import jhi.curlywhirly.gui.*;
import jhi.curlywhirly.util.*;
import jhi.curlywhirly.util.ColorPrefs.*;

import scri.commons.gui.*;

public class MultiSelectOptionsDialog extends JDialog implements ActionListener
{
	public MultiSelectOptionsDialog()
	{
		super(
			CurlyWhirly.winMain,
			RB.getString("gui.dialog.MultiSelectOptionsDialog.title"),
			true
		);

		initComponents();
		initComponents2();
		getContentPane().setBackground(Color.WHITE);

		getRootPane().setDefaultButton(bClose);
		SwingUtils.addCloseHandler(this, bClose);

		pack();
		setLocationRelativeTo(CurlyWhirly.winMain);
		setResizable(false);
		setVisible(true);
	}

	private void initComponents2()
	{
		RB.setText(bClose, "gui.text.close");
		bClose.addActionListener(this);

		RB.setText(lblColours, "gui.dialog.MultiSelectOptionsDialog.customiseColours");

		RB.setText(chkAxes, "gui.dialog.MultiSelectOptionsDialog.chkAxes");
		chkAxes.setSelected(Prefs.guiChkSelectionAxes);
		chkAxes.addActionListener(this);

		RB.setText(chkSelectOrigin, "gui.dialog.MultiSelectOptionsDialog.chkSelectOrigin");
		chkSelectOrigin.setSelected(Prefs.guiChkAnchorPoints);
		chkSelectOrigin.addActionListener(this);

		DefaultListModel<ColorPref> colourModel = new DefaultListModel<>();
		colourModel.addElement(ColorPrefs.get("User.OpenGLPanel.multiSelectColor"));
		colourModel.addElement(ColorPrefs.get("User.OpenGLPanel.multiSelectSphereColor"));
		colourModel.addElement(ColorPrefs.get("User.OpenGLPanel.multiSelectLineColor"));
		colourModel.addElement(ColorPrefs.get("User.OpenGLPanel.multiSelectAxesColor"));
		lstColour.setModel(colourModel);
		lstColour.setCellRenderer(new ColorListRenderer());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bClose)
			setVisible(false);

		else if (e.getSource() == chkAxes)
			Prefs.guiChkSelectionAxes = !Prefs.guiChkSelectionAxes;

		else if (e.getSource() == chkSelectOrigin)
			Prefs.guiChkAnchorPoints = !Prefs.guiChkAnchorPoints;
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        dialogPanel1 = new scri.commons.gui.matisse.DialogPanel();
        bClose = new javax.swing.JButton();
        chkSelectOrigin = new javax.swing.JCheckBox();
        chkAxes = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstColour = new javax.swing.JList<ColorPref>();
        lblColours = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bClose.setText("Close");
        dialogPanel1.add(bClose);

        chkSelectOrigin.setText("Anchor points to selection origin");

        chkAxes.setText("Show axes in selection circle");

        jScrollPane1.setViewportView(lstColour);

        lblColours.setText("Customise colours:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dialogPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblColours)
                            .addComponent(chkAxes)
                            .addComponent(chkSelectOrigin))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblColours)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSelectOrigin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAxes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dialogPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JCheckBox chkAxes;
    private javax.swing.JCheckBox chkSelectOrigin;
    private scri.commons.gui.matisse.DialogPanel dialogPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblColours;
    private javax.swing.JList<ColorPref> lstColour;
    // End of variables declaration//GEN-END:variables

}