package userAccounts;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import controller.AppManager;
import controller.InputChecker;
import controller.UserManager;

public class Frm_NewUser extends JFrame implements CaretListener, FocusListener, ActionListener
{
	private static final long serialVersionUID = -2416004958642968335L;
	private JLabel lbl_firstName = new JLabel("First Name");
	private JLabel lbl_lastName = new JLabel("Last Name");
	private JLabel lbl_userName = new JLabel("Username");
	private JLabel lbl_password1 = new JLabel("Password (6-24 characters)");
	private JLabel lbl_password2 = new JLabel("Confirm Password");
	private JLabel lbl_permission = new JLabel("Permission Level");
	private JTextField txt_firstName = new JTextField();
	private JTextField txt_lastName = new JTextField();
	private JTextField txt_userName = new JTextField();
	private JPasswordField txt_password1 = new JPasswordField();
	private JPasswordField txt_password2 = new JPasswordField();
	private JComboBox<String> drp_permission = new JComboBox<String>();
	private JLabel lbl_firstNameReq = new JLabel("Field cannot be left blank");
	private JLabel lbl_lastNameReq = new JLabel("Field cannot be left blank");
	private JLabel lbl_userNameReq = new JLabel("Field cannot be left blank");
	private JLabel lbl_password1Req = new JLabel("6-24 characters");
	private JLabel lbl_password2Req = new JLabel("Must match password");
	private JLabel lbl_placeholder1 = new JLabel("");
	private JLabel lbl_placeholder2 = new JLabel("");
	private JButton btn_cancel = new JButton("Cancel");
	private JButton btn_confirm = new JButton("Confirm");
	
	public Frm_NewUser()
	{
		super("BEMS New User");
		setSize(600, 300);
		setLayout(new GridLayout(7, 2, 10, 10));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setIconImage(AppManager.getIconImage());
		
		add(lbl_firstName);
		txt_firstName.addFocusListener(this);
		add(txt_firstName);
		lbl_firstNameReq.setVisible(false);
		add(lbl_firstNameReq);
		
		add(lbl_lastName);
		txt_lastName.addFocusListener(this);
		add(txt_lastName);
		lbl_lastNameReq.setVisible(false);
		add(lbl_lastNameReq);
		
		add(lbl_userName);
		txt_userName.addFocusListener(this);
		add(txt_userName);
		lbl_userNameReq.setVisible(false);
		add(lbl_userNameReq);
		
		add(lbl_password1);
		txt_password1.addFocusListener(this);
		txt_password1.addCaretListener(this);
		add(txt_password1);
		lbl_password1Req.setVisible(false);
		add(lbl_password1Req);
		
		add(lbl_password2);
		txt_password2.addFocusListener(this);
		txt_password2.addCaretListener(this);
		add(txt_password2);
		lbl_password2Req.setVisible(false);
		add(lbl_password2Req);
		
		add(lbl_permission);
		drp_permission.addItem("Guest");
		drp_permission.addItem("User");
		drp_permission.addItem("Administrator");
		drp_permission.setSelectedItem("User");
		add(drp_permission);
		
		lbl_placeholder1.setVisible(false);
		add(lbl_placeholder1);
		
		lbl_placeholder2.setVisible(false);
		add(lbl_placeholder2);
		
		btn_cancel.addActionListener(this);
		add(btn_cancel);
		
		btn_confirm.addActionListener(this);
		add(btn_confirm);
		
		validate();
		repaint();
	}
	
	public void caretUpdate(CaretEvent e)
	{
		if(e.getSource().equals(txt_password1))
		{
			if(txt_password1.getPassword().length >= 6 && txt_password1.getPassword().length <= 24)
			{
				txt_password1.setBackground(Color.GREEN);
				lbl_password1Req.setVisible(false);
			}
			else
			{
				txt_password1.setBackground(Color.PINK);
				lbl_password1Req.setVisible(true);
			}
		}
		
		if(e.getSource().equals(txt_password2))
		{
			if(String.valueOf(txt_password2.getPassword()).equals(String.valueOf(
					txt_password1.getPassword())))
			{
				txt_password2.setBackground(Color.GREEN);
				lbl_password2Req.setVisible(false);
			}
			else
			{
				txt_password2.setBackground(Color.PINK);
				lbl_password2Req.setVisible(true);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		if(e.getSource().equals(txt_firstName))
		{
			if(txt_firstName.getText().isEmpty())
			{
				txt_firstName.setBackground(Color.PINK);
				lbl_firstNameReq.setVisible(true);
			}
			else
			{
				txt_firstName.setBackground(Color.GREEN);
				lbl_firstNameReq.setVisible(false);
			}
		}
		
		if(e.getSource().equals(txt_lastName))
		{
			if(txt_lastName.getText().isEmpty())
			{
				txt_lastName.setBackground(Color.PINK);
				lbl_lastNameReq.setVisible(true);
			}
			else
			{
				txt_lastName.setBackground(Color.GREEN);
				lbl_lastNameReq.setVisible(false);
			}
		}
		
		if(e.getSource().equals(txt_userName))
		{
			if(txt_userName.getText().isEmpty())
			{
				txt_userName.setBackground(Color.PINK);
				lbl_userNameReq.setText("Field cannot be left blank");
				lbl_userNameReq.setVisible(true);
			}
			else
			{
				for(int index = 0; index < UserManager.getUsers().size(); index++)
				{
					if(txt_userName.getText().equals(UserManager.getUsers().get(index).getUserName()))
					{
						txt_userName.setBackground(Color.PINK);
						lbl_userNameReq.setText("This username is unavailable");
						lbl_userNameReq.setVisible(true);
					}
					else
					{
						txt_userName.setBackground(Color.GREEN);
						lbl_userNameReq.setVisible(false);
					}
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btn_cancel))
		{
			this.dispose();
		}
		if(e.getSource().equals(btn_confirm))
		{
			if(!InputChecker.isValid(txt_firstName.getText().toCharArray(), InputChecker.LETTERS_ONLY))
			{
				JOptionPane.showMessageDialog(null, "First name input invalid.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			}
			else if(!InputChecker.isValid(txt_lastName.getText().toCharArray(), InputChecker.LETTERS_ONLY))
			{
				JOptionPane.showMessageDialog(null, "Last name input invalid.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				int permissionLevel = 1;
				if(drp_permission.getSelectedItem().equals("Manager"))
					permissionLevel = User.MANAGER;
				else if(drp_permission.getSelectedItem().equals("Employee"))
					permissionLevel = User.EMPLOYEE;
				
				UserManager.addUser(new User(txt_userName.getText(), txt_firstName.getText(),
						txt_lastName.getText(), String.valueOf(txt_password1.getPassword()),
						permissionLevel));
				
				this.dispose();
			}
		}
	}
}