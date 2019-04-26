package userAccounts;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.AppManager;
import controller.UsersManager;

public class Frm_LogIn extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = -2735021839072947289L;
	private JLabel lbl_userName = new JLabel("Username: ");
	private JTextField txt_userName = new JTextField();
	private JLabel lbl_password = new JLabel("Password: ");
	private JPasswordField txt_password = new JPasswordField();
	private JButton btn_login = new JButton("Log in");
	private GridBagConstraints c = new GridBagConstraints();
	
	public Frm_LogIn()
	{
		super("Airline DBMS Login");
		setSize(300, 150);
		setLayout(new GridBagLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setIconImage(AppManager.getIconImage());
		
		c.weighty = 1.0;
		c.insets = new Insets(5, 5, 5, 5);
		
		c.gridx = 0;
		c.weightx = 1.0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		add(lbl_userName, c);
		
		c.gridx = 1;
		c.weightx = 10.0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		add(txt_userName, c);
		
		c.gridx = 0;
		c.weightx = 1.0;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		add(lbl_password, c);
		
		txt_password.addKeyListener(this);
		c.gridx = 1;
		c.weightx = 10.0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		add(txt_password, c);
		
		btn_login.addActionListener(this);
		btn_login.addKeyListener(this);
		c.gridx = 0;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		add(btn_login, c);
		
		txt_userName.requestFocusInWindow();
		
		repaint();
		validate();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		boolean loggedInSuccessfully = UsersManager.attemptLogIn(
				txt_userName.getText(), String.valueOf(txt_password.getPassword()));
		
		if(loggedInSuccessfully)
		{
			this.dispose();
		}
		else
		{
			JOptionPane.showMessageDialog(
					null, "Username or password is incorrect. Please try again.", "Log In Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			btn_login.doClick();
		}
	}

	@Override
	public void keyTyped(KeyEvent e)	{	}

	@Override
	public void keyReleased(KeyEvent e)	{	}
}