package customerGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

import controller.AppManager;

public class Frm_CustomerBooking extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = -4132683384953637172L;
	
	/*
	 * filter by all flight parameters
	 * after picking flight, show available seats
	 * 
	 * 
	 * Flight number
	 * Depart location
	 * arriving location
	 * date/times
	 * seat
	 * class
	 * price
	 * 
	 * 
	 * Name
	 * DOB
	 * email address
	 * payment info (address and credit card)
	 */
	
	
	private JPanel contentPane = new JPanel();
	
	// flight search parameters
	private JPanel pnl_search = new JPanel();
	
	private JLabel lbl_depDate = new JLabel("Departure Date");
	private JLabel lbl_arrDate = new JLabel("Arrival Date");
	private JLabel lbl_depLocation = new JLabel("Departure Location");
	private JLabel lbl_arrLocation = new JLabel("Arrival Location");
	private JLabel lbl_price = new JLabel("Price");
	
	private JXDatePicker dtp_depDate = new JXDatePicker();
	private JXDatePicker dtp_arrDate = new JXDatePicker();
	private JComboBox<String> drp_depLocation = new JComboBox<String>();
	private JComboBox<String> drp_arrLocation = new JComboBox<String>();
	private JSlider sld_price = new JSlider(); 
	
	
	// flight options
	private Tbl_Flights tbl_flights = new Tbl_Flights();
	private JScrollPane scr_flightsPane = new JScrollPane(tbl_flights);
	
	
	// after picking flight and seat
	private JPanel pnl_booking = new JPanel();
	
	private JLabel lbl_fname = new JLabel("First Name");
	private JLabel lbl_lname = new JLabel("Last Name");
	private JLabel lbl_email = new JLabel("Email Address");
	private JLabel lbl_DOB = new JLabel("Date of Birth");
	
	private JTextField txt_fname = new JTextField();
	private JTextField txt_lname = new JTextField();
	private JTextField txt_email = new JTextField();
	private JXDatePicker dtp_DOB = new JXDatePicker();
	
	private JButton btn_cancel = new JButton("Cancel");
	private JButton btn_confirm = new JButton("Confirm");
	
	public Frm_CustomerBooking()
	{
		super("Book Flight");
		
		pnl_search.setLayout(new GridLayout(2, 5, 10, 5));
		pnl_search.add(lbl_depDate);
		pnl_search.add(lbl_arrDate);
		pnl_search.add(lbl_depLocation);
		pnl_search.add(lbl_arrLocation);
		pnl_search.add(lbl_price);
		pnl_search.add(dtp_depDate);
		pnl_search.add(dtp_arrDate);
		pnl_search.add(drp_depLocation);
		pnl_search.add(drp_arrLocation);
		pnl_search.add(sld_price);
		
		
		pnl_booking.setLayout(new GridLayout(3, 4, 20, 5));
		pnl_booking.add(lbl_fname);
		pnl_booking.add(lbl_lname);
		pnl_booking.add(lbl_email);
		pnl_booking.add(lbl_DOB);
		pnl_booking.add(txt_fname);
		pnl_booking.add(txt_lname);
		pnl_booking.add(txt_email);
		pnl_booking.add(dtp_DOB);
		pnl_booking.add(btn_cancel);
		pnl_booking.add(btn_confirm);
		
		contentPane.add(pnl_search);
		contentPane.add(scr_flightsPane);
		contentPane.add(pnl_booking);
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);
		setSize(768 + 6, 446 + 52);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setIconImage(AppManager.getIconImage());
		addWindowListener(this);
	}
	
	private void refreshTable()
	{
		tbl_flights.updateTable(AppManager.searchDatabase(""));
	}
	
	@Override
	public void windowOpened(WindowEvent e)	{	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		switch(JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit?\n"
				+ "Any unsaved changes will be lost.", "Exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
		{
		case JOptionPane.YES_OPTION:
			dispose();
			System.exit(0);
		default:
			break;
		}
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
	
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}