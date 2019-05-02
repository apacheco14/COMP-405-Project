package customerGUI;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

class Pnl_BookFlight extends JPanel
{
	private static final long serialVersionUID = 8171855664711935192L;
	
	private JLabel lbl_fname = new JLabel("First Name");
	private JLabel lbl_lname = new JLabel("Last Name");
	private JLabel lbl_email = new JLabel("Email Address");
	private JLabel lbl_DOB = new JLabel("Date of Birth");
	
	private JTextField txt_fname = new JTextField();
	private JTextField txt_lname = new JTextField();
	private JTextField txt_email = new JTextField();
	private JXDatePicker dtp_DOB = new JXDatePicker();
	
	Pnl_BookFlight()
	{
		this.setLayout(new GridLayout(2, 4, 20, 5));
		this.add(lbl_fname);
		this.add(lbl_lname);
		this.add(lbl_email);
		this.add(lbl_DOB);
		this.add(txt_fname);
		this.add(txt_lname);
		this.add(txt_email);
		this.add(dtp_DOB);
	}
}
