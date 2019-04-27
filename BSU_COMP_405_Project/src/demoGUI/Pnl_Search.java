package demoGUI;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import controller.AppManager;

class Pnl_Search extends demoGUI.Pnl_TabPane
{
	private static final long serialVersionUID = -7515201411110517820L;
	
	protected Pnl_Search()
	{
		this.setName("Search Database");
	}
	
	private void refreshTable()
	{
		Object[][] data = AppManager.searchDatabase(this.txt_userInputField.getText());
		tbl_results.updateTable(data);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(btn_confirm)
				&& !this.txt_userInputField.getText().equals("")
				&& !this.txt_userInputField.getText().equals(null))
		{
			refreshTable();
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		refreshTable();
	}
}