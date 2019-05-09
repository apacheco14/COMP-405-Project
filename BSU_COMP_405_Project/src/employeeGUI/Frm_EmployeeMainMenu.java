package employeeGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import userAccounts.Frm_AccountSettings;
import userAccounts.Frm_NewUser;
import userAccounts.User;
import controller.AppManager;

public class Frm_EmployeeMainMenu extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 3864620873790717020L;
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menu_user = new JMenu("Users");
	private JMenuItem mi_newUser = new MenuItem("New User", KeyEvent.VK_N, this);
	private JMenuItem mi_logOff = new MenuItem("Log Off", KeyEvent.VK_L, this);
	private JMenuItem mi_exit = new MenuItem("Exit", KeyEvent.VK_E, this);
	
	private JMenu menu_flights = new JMenu("Flights");
	private JMenuItem mi_newFlight = new MenuItem("New Flight", KeyEvent.VK_N, this);
	private JMenuItem mi_bookFlight = new MenuItem("Book Flight", KeyEvent.VK_B, this);
	private JMenuItem mi_cancelFlight = new MenuItem("Cancel Flight", KeyEvent.VK_C, this);
	
	private JMenu menu_settings = new JMenu("Settings");
	private JMenuItem mi_systemSettings = new MenuItem("System Settings", KeyEvent.VK_S, this);
	private JMenuItem mi_accountSettings = new MenuItem("Account Settings", KeyEvent.VK_A, this);
	private JMenuItem mi_editLocations = new MenuItem("Edit Airport Locations", KeyEvent.VK_L, this);
	
	private Pnl_MenuImage pnl_menuImage = new Pnl_MenuImage();
	private Pnl_NewFlight pnl_newFlight = new Pnl_NewFlight(this);
//	private JPanel pnl_cancelFlight = new JPanel();				//placeholder
//	private JPanel pnl_editLocations = new JPanel();			//placeholder
	
	private User currentUser;
	
	public Frm_EmployeeMainMenu(User user)
	{
		super("Airline Database Management System");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, (int) (0.605*screenSize.width), (int) (0.692*screenSize.height));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setIconImage(AppManager.getIconImage());
		addWindowListener(this);
		
		resetMainPanel();
		
		currentUser = user;
		
		menu_user.setMnemonic(KeyEvent.VK_U);
		menu_user.add(mi_newUser);
		menu_user.add(mi_logOff);
		menu_user.add(mi_exit);
		menuBar.add(menu_user);
		
		menu_flights.setMnemonic(KeyEvent.VK_F);
		menu_flights.add(mi_newFlight);
		menu_flights.add(mi_bookFlight);
		menu_flights.add(mi_cancelFlight);
		menuBar.add(menu_flights);
		
		menu_settings.setMnemonic(KeyEvent.VK_S);
		menu_settings.add(mi_accountSettings);
		menu_settings.add(mi_systemSettings);
		menu_settings.add(mi_editLocations);
		menuBar.add(menu_settings);
		
		setAllowable(mi_newUser, User.MANAGER);
		setAllowable(mi_newFlight, User.MANAGER);
		setAllowable(mi_bookFlight, User.EMPLOYEE);
		setAllowable(mi_cancelFlight, User.MANAGER);
		setAllowable(mi_systemSettings, User.MANAGER);
		setAllowable(mi_editLocations, User.MANAGER);
		
		setJMenuBar(menuBar);
		
		repaint();
		validate();
	}
	
	private void setAllowable(JMenuItem mi, int maxPermissionLevel)
	{
		if(currentUser.getPermissionLevel() <= maxPermissionLevel)
			mi.setEnabled(true);
		else
			mi.setEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(mi_newUser))
		{
			new Frm_NewUser();
		}
		else if(e.getSource().equals(mi_logOff))
		{
			executeLogOffSequence();
		}
		else if(e.getSource().equals(mi_exit))
		{
			executeExitSequence();
		}
		else if(e.getSource().equals(mi_newFlight))
		{
			pnl_menuImage.setVisible(false);
			add(pnl_newFlight);
			pnl_newFlight.setVisible(true);
			repaint();
			validate();
		}
		else if(e.getSource().equals(mi_bookFlight))
		{
			AppManager.startCustomerVersion();
			currentUser.logOff();
			this.dispose();
		}
		else if(e.getSource().equals(mi_cancelFlight))
		{
			// TODO
		}
		else if(e.getSource().equals(mi_accountSettings))
		{
			new Frm_AccountSettings(currentUser);
		}
		else if(e.getSource().equals(mi_systemSettings))
		{
			// TODO
		}
		else if(e.getSource().equals(mi_editLocations))
		{
			// TODO
		}
	}

	@Override
	public void windowOpened(WindowEvent e)	{	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		mi_exit.doClick();
	}

	@Override
	public void windowClosed(WindowEvent e)	{	}

	@Override
	public void windowIconified(WindowEvent e)	{	}

	@Override
	public void windowDeiconified(WindowEvent e)	{	}

	@Override
	public void windowActivated(WindowEvent e)	{	}

	@Override
	public void windowDeactivated(WindowEvent e)	{	}
	
	public void remove(Component c)
	{
		
	}
	
	public void resetMainPanel()
	{
		add(pnl_menuImage);
		pnl_menuImage.setVisible(true);
		repaint();
		validate();
	}
	
	private void executeLogOffSequence()
	{
		switch(JOptionPane.showConfirmDialog(null, "Are you sure you wish to log off?", "Log off",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
		{
		case JOptionPane.YES_OPTION:
			currentUser.logOff();
			switch(JOptionPane.showConfirmDialog(null, "Would you like to exit the program?", "Exit",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
			{
			case JOptionPane.YES_OPTION:
				dispose();
				break;
			default:
				AppManager.startApp();
				dispose();
				break;
			}
		default:
			break;
		}
	}
	
	private void executeExitSequence()
	{
		switch(JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit?\n"
				+ "You will be logged off and any unsaved changes will be lost.", "Exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
		{
		case JOptionPane.YES_OPTION:
			currentUser.logOff();
			dispose();
			System.exit(0);
		default:
			break;
		}
	}
}