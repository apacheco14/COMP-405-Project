package customerGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.JXDatePicker;

import controller.AppManager;

class Pnl_SearchFlights extends JPanel implements ActionListener, ChangeListener
{
	private static final long serialVersionUID = 3465972594815333475L;
	
	private Frm_CustomerBooking window;
	
	private JLabel lbl_depDate = new JLabel("Departure Date");
	private JLabel lbl_arrDate = new JLabel("Arrival Date");
	private JLabel lbl_depLocation = new JLabel("Departure Location");
	private JLabel lbl_arrLocation = new JLabel("Arrival Location");
	private JLabel lbl_maxPrice = new JLabel("Max Price");
	
	private JXDatePicker dtp_depDate = new JXDatePicker();
	private JXDatePicker dtp_arrDate = new JXDatePicker();
	private JComboBox<String> drp_depLocation = new JComboBox<String>();
	private JComboBox<String> drp_arrLocation = new JComboBox<String>();
	private JSpinner spn_maxPrice = new JSpinner(new SpinnerNumberModel(100,50,Integer.MAX_VALUE,25));
	
	Pnl_SearchFlights(Frm_CustomerBooking window)
	{
		this.window = window;
		
		this.setLayout(new GridLayout(2, 5, 10, 5));
		this.add(lbl_depDate);
		this.add(lbl_arrDate);
		this.add(lbl_depLocation);
		this.add(lbl_arrLocation);
		this.add(lbl_maxPrice);
		
		this.add(dtp_depDate);
		this.add(dtp_arrDate);
		this.add(drp_depLocation);
		this.add(drp_arrLocation);
		this.add(spn_maxPrice);
		
		dtp_depDate.setDate(Date.valueOf(LocalDate.now()));
		dtp_arrDate.setDate(Date.valueOf(LocalDate.now()));
		
		ArrayList<String> airportLocations = AppManager.getAirportLocations();
		for(String location : airportLocations)
		{
			drp_depLocation.addItem(location);
			drp_arrLocation.addItem(location);
		}
		
		dtp_depDate.addActionListener(this);
		dtp_arrDate.addActionListener(this);
		drp_depLocation.addActionListener(this);
		drp_arrLocation.addActionListener(this);
		spn_maxPrice.addChangeListener(this);
	}
	
	Object[] getSearchCriteria()
	{
		return new Object[] {dtp_depDate.getDate(), dtp_arrDate.getDate(),
				drp_depLocation.getSelectedItem(), drp_arrLocation.getSelectedItem(),
				spn_maxPrice.getValue()};
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Date today = Date.valueOf(LocalDate.now());
		if(dtp_depDate.getDate().before(today))
		{
			dtp_depDate.setDate(today);
		}
		if(dtp_arrDate.getDate().before(today))
		{
			dtp_arrDate.setDate(today);
		}
		if(dtp_arrDate.getDate().before(dtp_depDate.getDate()))
		{
			// add one day
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(dtp_depDate.getDate()); 
			cal.add(Calendar.DATE, 1);
			dtp_arrDate.setDate(cal.getTime());
		}
		window.refreshTable();
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		window.refreshTable();
	}
}
