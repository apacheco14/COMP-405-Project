package employeeGUI;

import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.AppManager;

class Tbl_Results extends JTable implements TableModelListener
{
	private static final long serialVersionUID = -7111214612644360874L;
	private DefaultTableModel model = new DefaultTableModel(
			AppManager.getAllData(), AppManager.getColumnNames());
	
	protected Tbl_Results()
	{
		this.setFocusable(false);
		this.setVisible(true);
		this.setPreferredScrollableViewportSize(new Dimension(735, 340));
		this.setFillsViewportHeight(true);
		this.setModel(model);
		model.addTableModelListener(this);
	}
	
	public void updateTable(Object[][] newData)
	{
		model.setDataVector(newData, AppManager.getColumnNames());
		model.fireTableDataChanged();
	}
}
