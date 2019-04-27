package startupGUI;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.AppManager;

public class Frm_Startup extends JFrame implements MouseListener
{
	private static final long serialVersionUID = -6212417862601644877L;
	
	private Point screenCenter = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	private static final int WINDOW_SIZE_X = 200;
	private static final int WINDOW_SIZE_Y = 150;
	
	private JLabel lbl_title = new JLabel("Select user type");
	private JButton btn_employee = new JButton("Employee");
	private JButton btn_customer = new JButton("Customer");

	public Frm_Startup()
	{
		btn_employee.addMouseListener(this);
		btn_customer.addMouseListener(this);
		
		this.setLayout(new FlowLayout());
		
		this.add(lbl_title);
		this.add(btn_employee);
		this.add(btn_customer);
		
		this.setLocation(screenCenter.x-WINDOW_SIZE_X/2, screenCenter.y-WINDOW_SIZE_Y/2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(btn_employee))
		{
			AppManager.startEmployeeVersion();
			this.dispose();
		}
		else if(e.getSource().equals(btn_customer))
		{
			AppManager.startCustomerVersion();
			this.dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)	{	}

	@Override
	public void mouseExited(MouseEvent e)	{	}

	@Override
	public void mousePressed(MouseEvent e)	{	}

	@Override
	public void mouseReleased(MouseEvent e)	{	}
}
