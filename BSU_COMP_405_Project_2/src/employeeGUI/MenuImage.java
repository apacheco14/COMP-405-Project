package employeeGUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class MenuImage extends JPanel
{
	private static final long serialVersionUID = -6083595224838252423L;
	private BufferedImage image;
	
	MenuImage()
	{
		try
		{
			image = ImageIO.read(new File("Images/Airplane.jpg"));
		}
		catch (IOException e)
		{
			
		}
		
		setSize(768, 446);
		setLocation(0, 0);
		setLayout(null);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image.getScaledInstance(768, 446, Image.SCALE_FAST), 0, 0, null);
	}
}