package employeeGUI;

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
	
	private JMenu menu_settings = new JMenu("Settings");
	private JMenuItem mi_systemSettings = new MenuItem("System Settings", KeyEvent.VK_S, this);
	private JMenuItem mi_accountSettings = new MenuItem("Account Settings", KeyEvent.VK_A, this);
	
	private Pnl_MenuImage menuImage = new Pnl_MenuImage();
	private Pnl_NewFlight pnl_newFlight = new Pnl_NewFlight();
	
	private User currentUser;
	
	public Frm_EmployeeMainMenu(User user)
	{
		super("Airline Database Management System");
		setSize(768 + 6, 446 + 52);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setIconImage(AppManager.getIconImage());
		addWindowListener(this);
		
		add(menuImage);
		
		currentUser = user;
		
		menu_user.setMnemonic(KeyEvent.VK_U);
		setAllowable(mi_newUser, User.MANAGER);
		menu_user.add(mi_newUser);
		menu_user.add(mi_logOff);
		menu_user.add(mi_exit);
		menuBar.add(menu_user);
		
		menu_flights.setMnemonic(KeyEvent.VK_F);
		setAllowable(mi_newFlight, User.MANAGER);
		menu_flights.add(mi_newFlight);
		setAllowable(mi_bookFlight, User.EMPLOYEE);
		menu_flights.add(mi_bookFlight);
		menuBar.add(menu_flights);
		
		menu_settings.setMnemonic(KeyEvent.VK_S);
		menu_settings.add(mi_accountSettings);
		setAllowable(mi_systemSettings, User.MANAGER);
		menu_settings.add(mi_systemSettings);
		menuBar.add(menu_settings);
		
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
		else if(e.getSource().equals(mi_exit))
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
		else if(e.getSource().equals(mi_newFlight))
		{
			menuImage.setVisible(false);
			add(pnl_newFlight);
			pnl_newFlight.setVisible(true);
		}
		else if(e.getSource().equals(mi_bookFlight))
		{
			
		}
		else if(e.getSource().equals(mi_accountSettings))
		{
			new Frm_AccountSettings(currentUser);
		}
		else if(e.getSource().equals(mi_systemSettings))
		{
			
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
}