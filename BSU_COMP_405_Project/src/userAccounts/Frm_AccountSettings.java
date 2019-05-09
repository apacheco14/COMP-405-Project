package userAccounts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import controller.AppManager;
import controller.InputChecker;
import controller.UsersManager;

public class Frm_AccountSettings extends JFrame implements CaretListener, FocusListener, ActionListener
{
	private static final long serialVersionUID = -2416004958642968335L;
	private JLabel lbl_firstName = new JLabel("First Name");
	private JLabel lbl_lastName = new JLabel("Last Name");
	private JLabel lbl_userName = new JLabel("Username");
	private JLabel lbl_newPassword1 = new JLabel("Password (6-24 characters)");
	private JLabel lbl_newPassword2 = new JLabel("Confirm Password");
	private JTextField txt_firstName = new JTextField();
	private JTextField txt_lastName = new JTextField();
	private JTextField txt_userName = new JTextField();
	private JPasswordField txt_newPassword1 = new JPasswordField();
	private JPasswordField txt_newPassword2 = new JPasswordField();
	private JLabel lbl_firstNameReq = new JLabel("Field cannot be left blank");
	private JLabel lbl_lastNameReq = new JLabel("Field cannot be left blank");
	private JLabel lbl_userNameReq = new JLabel("Field cannot be left blank");
	private JLabel lbl_newPassword1Req = new JLabel("6-24 characters");
	private JLabel lbl_newPassword2Req = new JLabel("Must match password");
	private JLabel lbl_placeholder1 = new JLabel("");
	private JButton btn_save = new JButton("Save Changes");
	private User currentUser;
	
	public Frm_AccountSettings(User currentUser)
	{
		super("Airline DBMS User Settings");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, (int) (0.469*screenSize.width), (int) (0.368*screenSize.height));
		setSize(600, 265);
		setLayout(new GridLayout(6, 2, 10, 10));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setIconImage(AppManager.getIconImage());
		
		this.currentUser = currentUser;
		
		add(lbl_firstName);
		txt_firstName.addFocusListener(this);
		txt_firstName.setText(currentUser.getFirstName());
		add(txt_firstName);
		lbl_firstNameReq.setVisible(false);
		add(lbl_firstNameReq);
		
		add(lbl_lastName);
		txt_lastName.addFocusListener(this);
		txt_lastName.setText(currentUser.getLastName());
		add(txt_lastName);
		lbl_lastNameReq.setVisible(false);
		add(lbl_lastNameReq);
		
		add(lbl_userName);
		txt_userName.addFocusListener(this);
		txt_userName.setText(currentUser.getUserName());
		txt_userName.setEnabled(false);
		txt_userName.setDisabledTextColor(Color.BLACK);
		txt_userName.setBackground(Color.LIGHT_GRAY);
		add(txt_userName);
		lbl_userNameReq.setVisible(false);
		add(lbl_userNameReq);
		
		add(lbl_newPassword1);
		txt_newPassword1.addFocusListener(this);
		txt_newPassword1.addCaretListener(this);
		add(txt_newPassword1);
		lbl_newPassword1Req.setVisible(false);
		add(lbl_newPassword1Req);
		
		add(lbl_newPassword2);
		txt_newPassword2.addFocusListener(this);
		txt_newPassword2.addCaretListener(this);
		add(txt_newPassword2);
		lbl_newPassword2Req.setVisible(false);
		add(lbl_newPassword2Req);
		
		lbl_placeholder1.setVisible(false);
		add(lbl_placeholder1);
		
		btn_save.addActionListener(this);
		add(btn_save);
		
		validate();
		repaint();
	}
	
	public void caretUpdate(CaretEvent e)
	{
		if(e.getSource().equals(txt_newPassword1))
		{
			if(txt_newPassword1.getPassword().length >= 6 && txt_newPassword1.getPassword().length <= 24)
			{
				txt_newPassword1.setBackground(Color.GREEN);
				lbl_newPassword1Req.setVisible(false);
			}
			else
			{
				txt_newPassword1.setBackground(Color.PINK);
				lbl_newPassword1Req.setVisible(true);
			}
		}
		
		if(e.getSource().equals(txt_newPassword2))
		{
			if(String.valueOf(txt_newPassword2.getPassword()).equals(String.valueOf(
					txt_newPassword1.getPassword())))
			{
				txt_newPassword2.setBackground(Color.GREEN);
				lbl_newPassword2Req.setVisible(false);
			}
			else
			{
				txt_newPassword2.setBackground(Color.PINK);
				lbl_newPassword2Req.setVisible(true);
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
				if(UsersManager.isUserNameAvailable(txt_userName.getText()))
				{
					txt_userName.setBackground(Color.GREEN);
					lbl_userNameReq.setVisible(false);
				}
				else
				{
					txt_userName.setBackground(Color.PINK);
					lbl_userNameReq.setText("This username is unavailable");
					lbl_userNameReq.setVisible(true);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btn_save))
		{
			if(InputChecker.isValid(txt_firstName.getText().toCharArray(), InputChecker.LETTERS_ONLY))
				currentUser.setFirstName(txt_firstName.getText());
			else
				JOptionPane.showMessageDialog(null, "First name input invalid.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			
			if(InputChecker.isValid(txt_lastName.getText().toCharArray(), InputChecker.LETTERS_ONLY))
				currentUser.setLastName(txt_lastName.getText());
			else
				JOptionPane.showMessageDialog(null, "Last name input invalid.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			
			UsersManager.updateUserInfo();
			
			this.dispose();
		}
	}
}