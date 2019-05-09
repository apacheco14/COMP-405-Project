package employeeGUI;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;

import controller.AppManager;

class Pnl_NewFlight extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 4245246633629876362L;
	
	// airplane
	// departure date/time
	// departure location
	// arrival date/time
	// arrival location
	// airplane
	
	private ArrayList<String> availableLocations = AppManager.getAirportLocations();
	private ArrayList<String> airplanes = AppManager.getAirplaneNames();
	
	private JLabel lbl_airplane = new JLabel("Airplane");
	private JLabel lbl_depDate = new JLabel("Departure Date/Time");
	private JLabel lbl_arrDate = new JLabel("Arrival Date/Time");
	private JLabel lbl_depLocation = new JLabel("Departure Location");
	private JLabel lbl_arrLocation = new JLabel("Arrival Location");
	
	private JComboBox<String> drp_airplanes = new JComboBox<String>();
	private JXDatePicker dtp_depDate = new JXDatePicker();
	private JXDatePicker dtp_arrDate = new JXDatePicker();
	private JSpinner spn_depTime = new JSpinner(new SpinnerDateModel());
	private JSpinner.DateEditor edt_depTime = new JSpinner.DateEditor(spn_depTime, "hh:mm a");
	private JSpinner spn_arrTime = new JSpinner(new SpinnerDateModel());
	private JSpinner.DateEditor edt_arrTime = new JSpinner.DateEditor(spn_arrTime, "hh:mm a");
	private JComboBox<String> drp_depLocation = new JComboBox<String>();
	private JComboBox<String> drp_arrLocation = new JComboBox<String>();
	
	private JButton btn_cancel = new JButton("Cancel");
	private JButton btn_confirm = new JButton("Confirm");
	
	private Frm_EmployeeMainMenu window;
	
	Pnl_NewFlight(Frm_EmployeeMainMenu window)
	{
		this.window = window;
		
		this.setLayout(new GridLayout(7, 2, 5, 20));
		
		for(String airplane : airplanes)
		{
			drp_airplanes.addItem(airplane);
		}
		
		dtp_depDate.setDate(Date.valueOf(LocalDate.now()));
		dtp_arrDate.setDate(Date.valueOf(LocalDate.now()));
		
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
		
		this.add(lbl_airplane);
		this.add(drp_airplanes);
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
					this.setVisible(false);
					this.setEnabled(false);
					window.resetMainPanel();
					break;
				case JOptionPane.NO_OPTION:
					break;
			}
		}
		else if(e.getSource().equals(btn_confirm))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("E MM/dd/yyyy");
			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
			
			switch(JOptionPane.showConfirmDialog(
					this, "Are you sure you want to confirm this new flight?\n"
							+ drp_depLocation.getSelectedItem() + " to "
							+ drp_arrLocation.getSelectedItem() + " on\n"
							+ dateFormat.format(dtp_depDate.getDate())
							+ " at " + timeFormat.format(spn_depTime.getValue()),
					"Confirm New Flight Addition", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE))
			{
				case JOptionPane.YES_OPTION:
					insertData();
					break;
				case JOptionPane.NO_OPTION:
					break;
			}
			
			this.setVisible(false);
			this.setEnabled(false);
			window.resetMainPanel();
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
	
	private void insertData()
	{
		String depLocationId = (String) drp_depLocation.getSelectedItem();
		String arrLocationId = (String) drp_arrLocation.getSelectedItem();
		int airplaneId = AppManager.getAirplaneId((String) drp_airplanes.getSelectedItem());
		
		Object[] data =
				{
						airplaneId, dtp_depDate.getDate(), dtp_arrDate.getDate(),
						spn_depTime.getValue(), spn_arrTime.getValue(), depLocationId,
						arrLocationId
				};
		
		boolean insertSuccessful = AppManager.insertNewFlight(data);
		if(insertSuccessful)
		{
			JOptionPane.showMessageDialog(this, "New flight data inserted successfully.",
					"Successful Insert", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
