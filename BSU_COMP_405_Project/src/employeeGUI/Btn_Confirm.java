package employeeGUI;

import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.*;

class Btn_Confirm extends JButton
{
	private static final long serialVersionUID = 6891010776217668184L;

	protected Btn_Confirm(MouseListener ml)
	{
		this.setEnabled(true);
		this.setText("Confirm");
		this.setToolTipText("Start running SQL code");
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(90, 30));
		this.setVisible(true);
		this.addMouseListener(ml);
	}
}
