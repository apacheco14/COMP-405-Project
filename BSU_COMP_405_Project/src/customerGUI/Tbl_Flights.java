package customerGUI;

import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.AppManager;

class Tbl_Flights extends JTable implements TableModelListener
{
	private static final long serialVersionUID = -7111214612644360874L;
	private DefaultTableModel model = new DefaultTableModel();
	
	protected Tbl_Flights()
	{
		this.setFocusable(false);
		this.setVisible(true);
		this.setPreferredScrollableViewportSize(new Dimension(735, 340));
		this.setFillsViewportHeight(true);
		this.setModel(model);
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.updateTable(AppManager.searchFlights(new Object[] {null, null, null, null, 0}));
		model.addTableModelListener(this);
		model.fireTableDataChanged();
	}
	
	public void updateTable(Object[][] newData)
	{
		model.setDataVector(newData, AppManager.getFlightColumnNames());
		model.fireTableDataChanged();
	}
}
