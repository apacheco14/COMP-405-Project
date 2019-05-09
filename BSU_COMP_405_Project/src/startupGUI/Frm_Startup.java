package startupGUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.AppManager;

public class Frm_Startup extends JFrame implements MouseListener
{
	private static final long serialVersionUID = -6212417862601644877L;
	
	private JLabel lbl_title = new JLabel("Select user type");
	private JButton btn_employee = new JButton("Employee");
	private JButton btn_customer = new JButton("Customer");

	public Frm_Startup()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, (int) (0.156*screenSize.width), (int) (0.208*screenSize.height));
		setLocationRelativeTo(null);
		setVisible(true);
		setIconImage(AppManager.getIconImage());
		
		btn_employee.addMouseListener(this);
		btn_customer.addMouseListener(this);
		
		this.setLayout(new FlowLayout());
		
		this.add(lbl_title);
		this.add(btn_employee);
		this.add(btn_customer);
		
		this.validate();
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(btn_employee))
		{
			AppManager.startLoginProcess();
			//AppManager.startEmployeeVersion();
			this.dispose();
		}
		else if(e.getSource().equals(btn_customer))
		{
			//AppManager.startLoginProcess();
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
