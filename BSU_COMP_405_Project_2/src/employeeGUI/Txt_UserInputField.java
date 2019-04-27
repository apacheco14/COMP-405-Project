package employeeGUI;

import java.awt.event.KeyListener;

import javax.swing.JTextField;

class Txt_UserInputField extends JTextField
{
	private static final long serialVersionUID = 8423465619351873010L;

	protected Txt_UserInputField(KeyListener kl)
	{
		this.setEditable(true);
		this.setColumns(50);
		this.setFocusable(true);
		this.addKeyListener(kl);
	}
}
