package startMenu;

import javax.swing.JMenuItem;

class MenuItem extends JMenuItem
{
	private static final long serialVersionUID = -8148185566682947601L;

	MenuItem(String text, int key, MainMenu mainMenu)
	{
		setText(text);
		setMnemonic(key);
		addActionListener(mainMenu);
	}
}