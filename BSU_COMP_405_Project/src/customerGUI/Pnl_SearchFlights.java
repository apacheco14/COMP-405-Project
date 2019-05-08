package customerGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.JXDatePicker;

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
	private JSlider sld_maxPrice = new JSlider(0, 1000, 500);
	
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
		this.add(sld_maxPrice);
		
		dtp_depDate.addActionListener(this);
		dtp_arrDate.addActionListener(this);
		drp_depLocation.addActionListener(this);
		drp_arrLocation.addActionListener(this);
		sld_maxPrice.addChangeListener(this);
		
		sld_maxPrice.setMajorTickSpacing(250);
		sld_maxPrice.setMinorTickSpacing(50);
		sld_maxPrice.setSnapToTicks(true);
		sld_maxPrice.setPaintTicks(true);
		sld_maxPrice.setPaintLabels(true);
	}
	
	Object[] getSearchCriteria()
	{
		return new Object[] {dtp_depDate.getDate(), dtp_arrDate.getDate(),
				drp_depLocation.getSelectedItem(), drp_arrLocation.getSelectedItem(),
				sld_maxPrice.getValue()};
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		window.refreshTable();
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		window.refreshTable();
	}
}
