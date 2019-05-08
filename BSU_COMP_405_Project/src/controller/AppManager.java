package controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import customerGUI.Frm_CustomerBooking;
import employeeGUI.Frm_EmployeeMainMenu;
import startupGUI.Frm_Startup;
import userAccounts.Frm_LogIn;
import userAccounts.User;

public class AppManager
{
	// only one SqlConnection should be made, should not be changed
	// all requests for data come from AppManager
	private final static SqlConnection sql = new SqlConnection();
	
	public static void startApp()
	{
		new Frm_Startup();
		//AppManager.startLoginProcess();
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
	
	public static String getAirportId(String param)
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
	
	public static ArrayList<String> getAirplanes()
	{
		return sql.getAirplanes();
	}
	
	public static String[] getAvailableSeats(int flightNumber)
	{
		// TODO
		/*return*/ sql.getAvailableSeats(flightNumber);
		
		// need placeholder so combo box works when customer is booking flight
		String[] placeholderArray = {"1A", "1B", "1C", "2A", "2B", "2C"};
		return placeholderArray;
	}
	
	public static String[] getFlightColumnNames()
	{
		// TODO
		return null;
		//return new String[] {"artist_name", "album_title"};
	}
	
	public static Object[][] searchFlights(Object[] params)
	{
		// TODO Auto-generated method stub
		// return sql.searchFlights(params);
		return null;
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
	
	public static boolean isCustomerInDatabase(String email)
	{
		return sql.isCustomerInDatabase(email);
	}
	
	public static Object[] getCustomer(String email)
	{
		// TODO Auto-generated method stub
		return sql.getCustomer(email);
	}
	
	public static String getCustomerFirstName(String email)
	{
		// TODO Auto-generated method stub
		// return (String) sql.getCustomer(email)[index];
		return "";
	}
	
	public static String getCustomerLastName(String email)
	{
		// TODO Auto-generated method stub
		// return (String) sql.getCustomer(email)[index];
		return "";
	}
	
	public static Date getCustomerDOB(String email)
	{
		// TODO Auto-generated method stub
		// return (String) sql.getCustomer(email)[index];
		return null;
	}
	
	public static ZonedDateTime convertToZonedDateTime(Object d)
	{
		return ((java.util.Date) d).toInstant().atZone(ZoneId.of("America/New_York"));
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