package employeeGUI;

import javax.swing.*;

public class Pnl_NewFlight extends JPanel
{
	private static final long serialVersionUID = 4245246633629876362L;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel searchPane = new Pnl_Search();
	private JPanel insertPane = new Pnl_Insert();
	
	public Pnl_NewFlight()
	{
		tabbedPane.addTab(searchPane.getName(), searchPane);
		tabbedPane.addTab(insertPane.getName(), insertPane);
		this.add(tabbedPane);
	}
}
