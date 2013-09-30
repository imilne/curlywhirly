// Copyright 2009-2012 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package curlywhirly.gui.dialog;

import javax.swing.*;

import curlywhirly.gui.*;

import scri.commons.gui.*;

class PreferencesPanelNB extends JPanel
{
	private WinMain winMain;
	private PreferencesDialog parent;

	private DefaultComboBoxModel<String> displayModel;
	private DefaultComboBoxModel<String> updateModel;

    public PreferencesPanelNB(WinMain winMain, PreferencesDialog parent)
    {
		this.winMain = winMain;
		this.parent = parent;

        initComponents();

        GUIUtils.setPanelColor(this, false);
        GUIUtils.setPanelColor(generalPanel, false);
		GUIUtils.setPanelColor(otherPanel, false);

		// Interface settings
		generalPanel.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.PreferencesPanelNB.generalPanelTitle")));
		RB.setText(displayLabel, "gui.PreferencesPanelNB.displayLabel");

        displayModel = new DefaultComboBoxModel<String>();
        displayModel.addElement(RB.getString("gui.PreferencesPanelNB.auto"));
        displayModel.addElement(RB.getString("gui.PreferencesPanelNB.en_GB"));
        displayModel.addElement(RB.getString("gui.PreferencesPanelNB.en_US"));
        displayModel.addElement(RB.getString("gui.PreferencesPanelNB.de_DE"));
        displayModel.addElement(RB.getString("gui.PreferencesPanelNB.es_MX"));
        displayCombo.setModel(displayModel);
		displayCombo.setSelectedIndex(getLocaleIndex());

        // Update settings
        RB.setText(updateLabel, "gui.PreferencesPanelNB.updateLabel");

        updateModel = new DefaultComboBoxModel<String>();
        updateModel.addElement(RB.getString("gui.PreferencesPanelNB.updateNever"));
        updateModel.addElement(RB.getString("gui.PreferencesPanelNB.updateStartup"));
        updateModel.addElement(RB.getString("gui.PreferencesPanelNB.updateDaily"));
        updateModel.addElement(RB.getString("gui.PreferencesPanelNB.updateWeekly"));
        updateModel.addElement(RB.getString("gui.PreferencesPanelNB.updateMonthly"));
        updateCombo.setModel(updateModel);
        updateCombo.setSelectedIndex(Prefs.guiUpdateSchedule);

		RB.setText(chkAntialias, "gui.PreferencesPanelNB.antialiasAxes");
		chkAntialias.setSelected(Prefs.guiAntialiasAxes);

		// Other settings
		otherPanel.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.PreferencesPanelNB.otherPanelTitle")));

		RB.setText(bCustomizeColors, "gui.PreferencesPanelNB.customizeColors");
		bCustomizeColors.addActionListener(parent);
	}

    private int getLocaleIndex()
	{
		if (Prefs.localeText.equals("en_GB"))
			return 1;
		else if (Prefs.localeText.equals("en_US"))
			return 2;
		else if (Prefs.localeText.equals("de_DE"))
			return 3;
		else if (Prefs.localeText.equals("es_MX"))
			return 4;
		else
			return 0;
	}

	public void applySettings()
	{
		switch (displayCombo.getSelectedIndex())
		{
			case 1:  Prefs.localeText = "en_GB"; break;
			case 2:  Prefs.localeText = "en_US"; break;
			case 3:  Prefs.localeText = "de_DE"; break;
			case 4:  Prefs.localeText = "es_MX"; break;
			default: Prefs.localeText = "auto";
		}

		Prefs.guiUpdateSchedule = updateCombo.getSelectedIndex();
		Prefs.guiAntialiasAxes = chkAntialias.isSelected();
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        generalPanel = new javax.swing.JPanel();
        displayCombo = new javax.swing.JComboBox<String>();
        updateCombo = new javax.swing.JComboBox<String>();
        displayLabel = new javax.swing.JLabel();
        updateLabel = new javax.swing.JLabel();
        otherPanel = new javax.swing.JPanel();
        bCustomizeColors = new javax.swing.JButton();
        chkAntialias = new javax.swing.JCheckBox();

        generalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("General options (restart to apply):"));

        displayLabel.setLabelFor(displayCombo);
        displayLabel.setText("Interface display language:");

        updateLabel.setLabelFor(updateCombo);
        updateLabel.setText("Check for newer CurlyWhirly versions:");

        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayLabel)
                    .addComponent(updateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateCombo, 0, 195, Short.MAX_VALUE)
                    .addComponent(displayCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayLabel)
                    .addComponent(displayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateLabel)
                    .addComponent(updateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        otherPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Other options:"));

        bCustomizeColors.setText("Customize colours");

        chkAntialias.setText("Antialias axes");

        javax.swing.GroupLayout otherPanelLayout = new javax.swing.GroupLayout(otherPanel);
        otherPanel.setLayout(otherPanelLayout);
        otherPanelLayout.setHorizontalGroup(
            otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkAntialias)
                    .addComponent(bCustomizeColors))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        otherPanelLayout.setVerticalGroup(
            otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherPanelLayout.createSequentialGroup()
                .addComponent(bCustomizeColors)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAntialias))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(otherPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(otherPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton bCustomizeColors;
    javax.swing.JCheckBox chkAntialias;
    private javax.swing.JComboBox<String> displayCombo;
    private javax.swing.JLabel displayLabel;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JPanel otherPanel;
    private javax.swing.JComboBox<String> updateCombo;
    private javax.swing.JLabel updateLabel;
    // End of variables declaration//GEN-END:variables
}