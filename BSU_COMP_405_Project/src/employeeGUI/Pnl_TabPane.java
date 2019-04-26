package employeeGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

abstract class Pnl_TabPane extends JPanel implements MouseListener, KeyListener
{
	private static final long serialVersionUID = 3295382959521503686L;
	protected JPanel pnl_userInputArea = new JPanel();
	protected JPanel pnl_resultArea = new JPanel();
	protected Txt_UserInputField txt_userInputField = new Txt_UserInputField(this);
	protected Btn_Confirm btn_confirm = new Btn_Confirm(this);
	protected Tbl_Results tbl_results = new Tbl_Results();
	protected JScrollPane scr_resultsPane = new JScrollPane(tbl_results);
	
	protected Pnl_TabPane()
	{
		pnl_userInputArea.add(txt_userInputField);
		pnl_userInputArea.add(btn_confirm);
		this.add(pnl_userInputArea);
		
		pnl_resultArea.add(scr_resultsPane);
		this.add(pnl_resultArea);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// do nothing
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// do nothing
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// do nothing
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// do nothing
	}
	
	@Override
	public void keyPressed(KeyEvent kEvent)
	{
		// do nothing
	}

	@Override
	public void keyReleased(KeyEvent kEvent)
	{
		// do nothing
	}
	
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// do nothing
	}
}
