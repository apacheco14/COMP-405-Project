package employeeGUI;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

import org.jdesktop.swingx.JXDatePicker;

import controller.AppManager;

class Pnl_NewFlight extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 4245246633629876362L;
	
	// flight number
	// departure date/time
	// departure location
	// arrival date/time
	// arrival location
	// airplane ?
	// price
	
	private ArrayList<String> availableLocations = AppManager.getAirportLocations();
	
	private JLabel lbl_flightNum = new JLabel("Flight Number");
	private JLabel lbl_depDate = new JLabel("Departure Date/Time");
	private JLabel lbl_arrDate = new JLabel("Arrival Date/Time");
	private JLabel lbl_depLocation = new JLabel("Departure Location");
	private JLabel lbl_arrLocation = new JLabel("Arrival Location");
	private JLabel lbl_price = new JLabel("Price");
	
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
	
	Pnl_NewFlight()
	{
		this.setLayout(new GridLayout(8, 2, 5, 20));
		
		spn_depTime.setEditor(edt_depTime);
		spn_depTime.setValue(Date.valueOf(LocalDate.now()));
		
		spn_arrTime.setEditor(edt_arrTime);
		spn_arrTime.setValue(Date.valueOf(LocalDate.now()));
		
		for(String location : availableLocations)
		{
			drp_depLocation.addItem((String) location);
			drp_arrLocation.addItem((String) location);
		}
		
		btn_cancel.addMouseListener(this);
		btn_confirm.addMouseListener(this);
		
		this.add(lbl_flightNum);
		this.add(txt_flightNum);
		this.add(lbl_depDate);
		this.add(lbl_arrDate);
		this.add(dtp_depDate);
		this.add(dtp_arrDate);
		this.add(spn_depTime);
		this.add(spn_arrTime);
		this.add(lbl_depLocation);
		this.add(lbl_arrLocation);
		this.add(drp_depLocation);
		this.add(drp_arrLocation);
		this.add(lbl_price);
		this.add(txt_price);
		this.add(btn_cancel);
		this.add(btn_confirm);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(btn_cancel))
		{
			switch(JOptionPane.showConfirmDialog(
					this, "Are you sure you want to cancel?\nUnsaved changes will be lost.",
					"Cancel New Flight Addition", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
			{
				case JOptionPane.YES_OPTION:
					// TODO cancel
				case JOptionPane.NO_OPTION:
					// do not cancel
			}
		}
		else if(e.getSource().equals(btn_confirm))
		{
			int depLocationId = AppManager.getAirportId((String) drp_depLocation.getSelectedItem());
			int arrLocationId = AppManager.getAirportId((String) drp_arrLocation.getSelectedItem());
			
			Object[] data =
					{
							Integer.parseInt(txt_flightNum.getText()), dtp_depDate.getDate(),
							dtp_arrDate.getDate(), spn_depTime.getValue(), spn_arrTime.getValue(),
							depLocationId, arrLocationId, Double.valueOf(txt_price.getText())
					};
			AppManager.insertNewFlightData(data);
			
			// TODO give confirmation or close window
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
}
