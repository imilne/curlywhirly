/*
 * DataLoadingDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package graphviewer3d.gui;

/**
 *
 * @author  __USER__
 */
public class DataLoadingDialog extends javax.swing.JDialog
{
	
	/** Creates new form DataLoadingDialog */
	public DataLoadingDialog(java.awt.Frame parent, boolean modal)
	{
		super(parent, modal);
		initComponents();
	}
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{
		
		jProgressBar1 = new javax.swing.JProgressBar();
		jLabel1 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Data loading");
		setLocationByPlatform(true);
		
		jProgressBar1.setIndeterminate(true);
		
		jLabel1.setText("Data loading -- please wait ...");
		
		jButton1.setText("Cancel");
		
		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
						layout.createSequentialGroup().add(61, 61, 61).add(
										layout.createParallelGroup(
														org.jdesktop.layout.GroupLayout.CENTER).add(
														jButton1).add(
														jProgressBar1,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														199,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
														jLabel1)).addContainerGap(
										63, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
						layout.createSequentialGroup().addContainerGap().add(jLabel1).addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED).add(
										jProgressBar1,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED).add(
										jButton1).addContainerGap(
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		
		pack();
	}// </editor-fold>
	//GEN-END:initComponents
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[])
	{
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				DataLoadingDialog dialog = new DataLoadingDialog(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter()
				{
					public void windowClosing(java.awt.event.WindowEvent e)
					{
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}
	
	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JProgressBar jProgressBar1;
	// End of variables declaration//GEN-END:variables
	
}
