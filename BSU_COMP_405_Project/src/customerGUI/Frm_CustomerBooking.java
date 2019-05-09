package customerGUI;

import java.awt.Dimension;
import java.awt.Toolkit;
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
	 * if already in database ask for password		--- maybe not
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
	private Pnl_SearchFlights pnl_search = new Pnl_SearchFlights(this);
	
	// flight options
	private Tbl_Flights tbl_flights = new Tbl_Flights();
	private JScrollPane scr_flightsPane = new JScrollPane(tbl_flights);
	
	// id customer and confirm booking
	private JButton btn_confirm = new JButton("Book Flight");
	
	public Frm_CustomerBooking()
	{
		super("Find and Book Flights");
		
		contentPane.add(pnl_search);
		contentPane.add(scr_flightsPane);
		contentPane.add(btn_confirm);
		
		btn_confirm.addActionListener(this);
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, (int) (0.605*screenSize.width), (int) (0.692*screenSize.height));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setIconImage(AppManager.getIconImage());
		addWindowListener(this);
	}
	
	void refreshTable()
	{
		tbl_flights.updateTable(AppManager.searchFlights(pnl_search.getSearchCriteria()));
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
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btn_confirm))
		{
			if(tbl_flights.getSelectedRow() == -1)
			{
				JOptionPane.showMessageDialog(this, "No flight selected.\n"
						+ "Select a flight in the table.", "Booking Error",
						JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				int row = tbl_flights.getSelectedRow();
				int col = 0;		// TODO need a way to make sure that this is right
				new Frm_CustomerCredentials((Integer.valueOf((String) tbl_flights.getValueAt(row, col))));
			}
		}
	}
}