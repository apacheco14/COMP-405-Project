package customerGUI;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.jdesktop.swingx.JXDatePicker;

class Pnl_SearchFlights extends JPanel
{
	private static final long serialVersionUID = 3465972594815333475L;
	
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
	
	Pnl_SearchFlights()
	{
		this.setLayout(new GridLayout(2, 5, 10, 5));
		this.add(lbl_depDate);
		this.add(lbl_arrDate);
		this.add(lbl_depLocation);
		this.add(lbl_arrLocation);
		this.add(lbl_price);
		this.add(dtp_depDate);
		this.add(dtp_arrDate);
		this.add(drp_depLocation);
		this.add(drp_arrLocation);
		this.add(sld_price);
	}
}
