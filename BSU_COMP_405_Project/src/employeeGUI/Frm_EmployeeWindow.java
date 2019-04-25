package employeeGUI;

import javax.swing.*;

public class Frm_EmployeeWindow extends JFrame
{
	private static final long serialVersionUID = 4245246633629876362L;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel searchPane = new Pnl_Search();
	private JPanel insertPane = new Pnl_Insert();
	
	public Frm_EmployeeWindow()
	{
		tabbedPane.addTab(searchPane.getName(), searchPane);
		tabbedPane.addTab(insertPane.getName(), insertPane);
		this.getContentPane().add(tabbedPane);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 450);
		this.setVisible(true);
	}
}
