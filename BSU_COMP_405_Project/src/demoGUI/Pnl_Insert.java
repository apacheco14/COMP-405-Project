package demoGUI;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import controller.AppManager;

class Pnl_Insert extends demoGUI.Pnl_TabPane
{
	private static final long serialVersionUID = 4646320130197860976L;
	
	protected Pnl_Insert()
	{
		this.setName("Insert New Data");
	}
	
	private void refreshTable()
	{
		//AppManager.insertData(this.txt_userInputField.getText());
		tbl_results.updateTable(AppManager.searchDatabase(""));
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(btn_confirm)
				&& !this.txt_userInputField.getText().equals("")
				&& !this.txt_userInputField.getText().equals(null))
		{
			refreshTable();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER
				&& !this.txt_userInputField.getText().equals("")
				&& !this.txt_userInputField.getText().equals(null))
		{
			refreshTable();
		}
	}
}
