package customerGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import org.jdesktop.swingx.JXDatePicker;

import controller.AppManager;

public class Frm_CustomerBooking extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = -4132683384953637172L;
	
	/*
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
	
	
	// copied over from employee new flight panel - not supposed to be used
	private JLabel lbl_flightNum = new JLabel("Flight Number");
	private JLabel lbl_depDate = new JLabel("Departure Date/Time");
	private JLabel lbl_arrDate = new JLabel("Arrival Date/Time");
	private JLabel lbl_depLocation = new JLabel("Departure Location");
	private JLabel lbl_arrLocation = new JLabel("Arrival Location");
	private JLabel lbl_price = new JLabel("Price");
	
	// copied over from employee new flight panel - not supposed to be used
	private JTextField txt_flightNum = new JTextField(15);
	private JXDatePicker dtp_depDate = new JXDatePicker();
	private JXDatePicker dtp_arrDate = new JXDatePicker();
	private JSpinner spn_depTime = new JSpinner(new SpinnerDateModel());
	private JSpinner.DateEditor edt_depTime = new JSpinner.DateEditor(spn_depTime, "hh:mm a");
	private JSpinner spn_arrTime = new JSpinner(new SpinnerDateModel());
	private JSpinner.DateEditor edt_arrTime = new JSpinner.DateEditor(spn_arrTime, "hh:mm a");
	private JComboBox<String> drp_depLocation = new JComboBox<String>();
	private JComboBox<String> drp_arrLocation = new JComboBox<String>();
	private JTextField txt_price = new JTextField(15);
	
	
	
	private JButton btn_cancel = new JButton("Cancel");
	private JButton btn_confirm = new JButton("Confirm");
	
	private JPanel pnl_flights = new JPanel();
	private JScrollPane scr_flightsPane = new JScrollPane();
	
	public Frm_CustomerBooking()
	{
		super("Book Flight");
		setSize(768 + 6, 446 + 52);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setIconImage(AppManager.getIconImage());
		addWindowListener(this);
		
		
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