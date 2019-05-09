package customerGUI;

import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import controller.AppManager;

class Frm_CustomerCredentials extends JFrame implements WindowListener, MouseListener, FocusListener
{
	private static final long serialVersionUID = -4814860646995801896L;
	
	private JLabel lbl_email = new JLabel("Email");
	private JLabel lbl_firstName = new JLabel("First Name");
	private JLabel lbl_lastName = new JLabel("Last Name");
	private JLabel lbl_seat = new JLabel("Seat");
	
	private JTextField txt_email = new JTextField();
	private JTextField txt_firstName = new JTextField();
	private JTextField txt_lastName = new JTextField();
	private JComboBox<String> drp_seat = new JComboBox<String>();
	
	private JButton btn_cancel = new JButton("Cancel");
	private JButton btn_confirm = new JButton("Confirm");
	
	private int flightNumber;
	
	Frm_CustomerCredentials(int flightNumber)
	{
		super("Customer Information");
		this.flightNumber = flightNumber;
		
		add(lbl_email);
		txt_email.addFocusListener(this);
		add(txt_email);
		
		add(lbl_firstName);
		add(txt_firstName);
		
		add(lbl_lastName);
		add(txt_lastName);
		
		add(lbl_seat);
		ArrayList<String> availableSeats = AppManager.getAvailableSeats(flightNumber);
		for(String seat : availableSeats)
			drp_seat.addItem(seat);
		add(drp_seat);
		
		btn_cancel.addMouseListener(this);
		add(btn_cancel);
		btn_confirm.addMouseListener(this);
		add(btn_confirm);
		
		setSize(450, 250);
		setResizable(false);
		setLayout(new GridLayout(6, 2, 5, 10));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setIconImage(AppManager.getIconImage());
		addWindowListener(this);
	}
	
	private void setCustomerDetails(String email)
	{
		if(AppManager.isCustomerInDatabase(email))
		{
			txt_firstName.setText(AppManager.getCustomerFirstName(email));
			txt_firstName.setEditable(false);
			
			txt_lastName.setText(AppManager.getCustomerLastName(email));
			txt_lastName.setEditable(false);
		}
		else
		{
			txt_firstName.setEditable(true);
			txt_lastName.setEditable(true);
		}
	}
	
	private void executeExitSequence()
	{
		switch(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?\n"
				+ "Your flight booking will be cancelled.", "Exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
		{
		case JOptionPane.YES_OPTION:
			dispose();
			System.exit(0);
		default:
			break;
		}
	}
	
	private void confirmBooking()
	{
		String email = txt_email.getText();
		String firstName = txt_firstName.getText();
		String lastName = txt_lastName.getText();
		String seat = (String) drp_seat.getSelectedItem();
		
		switch(JOptionPane.showConfirmDialog(
				this, "Are you sure you want to book this flight?", "Confirm Booking",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE))
		{
			case JOptionPane.YES_OPTION:
				AppManager.bookFlight(flightNumber, seat, email, firstName, lastName);
				dispose();
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION:
				break;
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0)	{	}

	@Override
	public void windowClosed(WindowEvent arg0)	{	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		executeExitSequence();
	}

	@Override
	public void windowDeactivated(WindowEvent e)	{	}

	@Override
	public void windowDeiconified(WindowEvent e)	{	}

	@Override
	public void windowIconified(WindowEvent e)	{	}

	@Override
	public void windowOpened(WindowEvent e)	{	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(btn_cancel))
		{
			executeExitSequence();
		}
		else if(e.getSource().equals(btn_confirm))
		{
			confirmBooking();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)	{	}

	@Override
	public void mouseExited(MouseEvent e)	{	}

	@Override
	public void mousePressed(MouseEvent e)	{	}

	@Override
	public void mouseReleased(MouseEvent e)	{	}
	
	@Override
	public void focusGained(FocusEvent e)	{	}

	@Override
	public void focusLost(FocusEvent e)
	{
		setCustomerDetails(txt_email.getText());
	}
}