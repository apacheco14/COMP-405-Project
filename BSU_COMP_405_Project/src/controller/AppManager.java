package controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import customerGUI.Frm_CustomerBooking;
import employeeGUI.Frm_EmployeeMainMenu;
import userAccounts.Frm_LogIn;
import userAccounts.User;

public class AppManager
{
	// only one SqlConnection should be made, should not be changed
	// all requests for data come from AppManager
	private final static SqlConnection sql = new SqlConnection();
	
	public static void startApp()
	{
		//new Frm_Startup();
		AppManager.startLoginProcess();
	}
	
	public static void startLoginProcess()
	{
		UsersManager.createUsers();
		new Frm_LogIn();
	}
	
	public static void startEmployeeVersion(User user)
	{
		new Frm_EmployeeMainMenu(user);
	}
	
	public static void startCustomerVersion()
	{
		new Frm_CustomerBooking();
	}
	
	public static String[] getColumnNames()
	{
		return null;
		//return new String[] {"artist_name", "album_title"};
	}
	
	public static Object[][] searchDatabase(String param)
	{
		return null;
		//return sql.searchDatabase(param);
	}
	
	public static int getAirportId(String param)
	{
		return sql.getAirportId(param);
	}
	
	public static ArrayList<String> getAirportLocations()
	{
		return sql.getAirportLocations();
	}
	
	public static boolean insertNewFlight(Object[] data)
	{
		return sql.insertNewFlight(data);
	}
	
	public static String[] getAvailableSeats(int flightNumber)
	{
		/*return*/ sql.getAvailableSeats(flightNumber);
		
		// need placeholder so combo box works when customer is booking flight
		String[] placeholderArray = {"1A", "1B", "1C", "2A", "2B", "2C"};
		return placeholderArray;
	}
	
	public static void bookFlight(int flightNumber, String seat,
			String email, String firstName, String lastName, Date DOB)
	{
		// enter customer data if necessary
		if(!sql.isCustomerInDatabase(email))
			sql.insertNewCustomer(email, firstName, lastName, DOB);
		
		// enter booking data
		sql.bookFlight(flightNumber, seat, email);
	}
	
	public static Image getIconImage()
	{
		try
		{
			return ImageIO.read(new File("Images/Small Airplane.png"));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args)
	{
		AppManager.startApp();
	}
}