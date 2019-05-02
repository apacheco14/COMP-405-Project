package customerGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controller.AppManager;

public class Frm_CustomerBooking extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = -4132683384953637172L;
	
	/*
	 * filter by all flight parameters
	 * after picking flight, show available seats
	 * 
	 * enter email
	 * if new customer, ask for more information
	 * if already in database ask for password
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
	private Pnl_SearchFlights pnl_search = new Pnl_SearchFlights();
	
	// flight options
	private Tbl_Flights tbl_flights = new Tbl_Flights();
	private JScrollPane scr_flightsPane = new JScrollPane(tbl_flights);
	
	// after picking flight and seat
	private Pnl_BookFlight pnl_booking = new Pnl_BookFlight();
	
	private JButton btn_cancel = new JButton("Cancel");
	private JButton btn_confirm = new JButton("Confirm");
	
	public Frm_CustomerBooking()
	{
		super("Find and Book Flights");
		
		contentPane.add(pnl_search);
		contentPane.add(scr_flightsPane);
		contentPane.add(pnl_booking);
		contentPane.add(btn_cancel);
		contentPane.add(btn_confirm);
		
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