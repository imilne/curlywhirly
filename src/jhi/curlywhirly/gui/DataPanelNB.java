// Copyright 2009-2017 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package jhi.curlywhirly.gui;

import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.RowFilter.*;
import javax.swing.table.*;

import jhi.curlywhirly.data.*;

import scri.commons.gui.*;

public class DataPanelNB extends JPanel implements ActionListener, DocumentListener, MouseListener
{
	private final DataPanel parent;

	private DefaultComboBoxModel<String> filterModel;

    /** Creates new form DataPanelNB */
    public DataPanelNB(DataPanel parent)
	{
        initComponents();

		this.parent = parent;

		RB.setText(filterByLabel, "gui.DataPanelNB.filterBy");

		setupFilterComboBox();
		filterTextField.getDocument().addDocumentListener(this);

		pointsTable.addMouseListener(this);
    }

	private JTable createTable()
	{
		final JTable table = new JTable()
		{
			@Override
			public TableCellRenderer getCellRenderer(int row, int col)
			{
				return ((DataPanelTableModel)getModel()).getCellRenderer(col);
			}
		};

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);

		return table;
	}

	private void setupFilterComboBox()
	{
		filterModel = new DefaultComboBoxModel<String>();
		for (int i=1; i < 8; i++)
			filterModel.addElement(RB.getString("gui.DataPanelNB.filterCombo" + i));

		filterCombo.setModel(filterModel);
		filterCombo.setSelectedIndex(Prefs.guiDataPanelFilter);
		filterCombo.addActionListener(this);
	}

	void toggleEnabled(boolean enabled)
	{
		lblPoints.setEnabled(enabled);
		pointsTable.setEnabled(enabled);
		filterByLabel.setEnabled(enabled);
		filterTextField.setEnabled(enabled);
		filterCombo.setEnabled(enabled);
	}

	private void filter()
	{
		RowFilter<DataPanelTableModel, Object> rf = null;
		DecimalFormat nf = (DecimalFormat) DecimalFormat.getInstance();

		try
		{
			if (Prefs.guiDataPanelFilter == 0)
				rf = RowFilter.regexFilter(filterTextField.getText(), 0);

			else if (Prefs.guiDataPanelFilter == 1)
			{
				float number = nf.parse(filterTextField.getText()).floatValue();
				rf = RowFilter.numberFilter(ComparisonType.AFTER, number, 1);
			}

			else if (Prefs.guiDataPanelFilter == 2)
			{
				float number = nf.parse(filterTextField.getText()).floatValue();
				rf = RowFilter.numberFilter(ComparisonType.BEFORE, number, 1);
			}

			else if (Prefs.guiDataPanelFilter == 3)
			{
				float number = nf.parse(filterTextField.getText()).floatValue();
				rf = RowFilter.numberFilter(ComparisonType.AFTER, number, 2);
			}

			else if (Prefs.guiDataPanelFilter == 4)
			{
				float number = nf.parse(filterTextField.getText()).floatValue();
				rf = RowFilter.numberFilter(ComparisonType.BEFORE, number, 2);
			}

			else if (Prefs.guiDataPanelFilter == 5)
			{
				float number = nf.parse(filterTextField.getText()).floatValue();
				rf = RowFilter.numberFilter(ComparisonType.AFTER, number, 3);
			}

			else if (Prefs.guiDataPanelFilter == 6)
			{
				float number = nf.parse(filterTextField.getText()).floatValue();
				rf = RowFilter.numberFilter(ComparisonType.BEFORE, number, 3);
			}
		}
		catch (ParseException e) { e.printStackTrace(); }

		parent.setTableFilter(rf);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == filterCombo)
		{
			Prefs.guiDataPanelFilter = filterCombo.getSelectedIndex();

			filterTextField.setText("");
			filterTextField.requestFocus();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		filter();
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		filter();
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		filter();
	}

	void clearFilter()
	{
		filterTextField.setText("");
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		int row = pointsTable.rowAtPoint(e.getPoint());

		if (row != -1)
		{
			DataPoint point = (DataPoint) pointsTable.getValueAt(row, 1);
			point.toggleSelection();
			pointsTable.repaint();
			lblPoints.setText(parent.getTitle());
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
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

        lblPoints = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pointsTable = createTable();
        filterByLabel = new javax.swing.JLabel();
        filterCombo = new javax.swing.JComboBox<String>();
        filterTextField = new javax.swing.JTextField();

        lblPoints.setText("Selected points: 0");

        pointsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        jScrollPane1.setViewportView(pointsTable);

        filterByLabel.setText("Filter by:");

        filterCombo.setActionCommand("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterTextField)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lblPoints)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterByLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPoints)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterByLabel)
                    .addComponent(filterCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JLabel filterByLabel;
    javax.swing.JComboBox<String> filterCombo;
    javax.swing.JTextField filterTextField;
    private javax.swing.JScrollPane jScrollPane1;
    javax.swing.JLabel lblPoints;
    javax.swing.JTable pointsTable;
    // End of variables declaration//GEN-END:variables

}