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
	
	public static String getAirportCode(String param)
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
	
	public static ArrayList<String> getAirplaneNames()
	{
		return sql.getAirplaneNames();
	}
	
	public static int getAirplaneId(String name)
	{
		return sql.getAirplaneId(name);
	}
	
	public static ArrayList<String> getAvailableSeats(int flightNumber)
	{
		return sql.getAvailableSeats(flightNumber);
		
		// need placeholder so combo box works when customer is booking flight
		//String[] placeholderArray = {"1A", "1B", "1C", "2A", "2B", "2C"};
		//return placeholderArray;
	}
	
	public static String[] getFlightColumnNames()
	{
		return new String[] {"Number", "Departure", "DepartureDate", "DepartureTime",
				"Arrival", "ArrivalDate", "ArrivalTime", "PlaneId"};
	}
	
	public static Object[][] searchFlights(Object[] criteria)
	{
		java.util.Date depDate = (Date) criteria[0];
		java.util.Date arrDate = (Date) criteria[1];
		String depLocation = (String) criteria[2];
		String arrLocation = (String) criteria[3];
		
		java.sql.Date depDateSQL = null;
		java.sql.Date arrDateSQL = null;
		if(depDate != (null))
			depDateSQL = java.sql.Date.valueOf(convertToZonedDateTime(depDate).toLocalDate());
		if(arrDate != (null))
			arrDateSQL = java.sql.Date.valueOf(convertToZonedDateTime(arrDate).toLocalDate());
		
		return sql.searchFlights(depDateSQL, arrDateSQL, depLocation, arrLocation);
	}
	
	public static void bookFlight(int flightNumber, String seat,
			String email, String firstName, String lastName)
	{
		// enter customer data if necessary
		if(!sql.isCustomerInDatabase(email))
			sql.insertNewCustomer(email, firstName, lastName);
		
		// enter booking data
		sql.bookFlight(flightNumber, seat, email);
	}
	
	public static boolean isCustomerInDatabase(String email)
	{
		return sql.isCustomerInDatabase(email);
	}
	
	public static ArrayList<Object> getCustomer(String email)
	{
		return sql.getCustomer(email);
	}
	
	public static String getCustomerFirstName(String email)
	{
		return (String) sql.getCustomer(email).get(0);
	}
	
	public static String getCustomerLastName(String email)
	{
		return (String) sql.getCustomer(email).get(1);
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
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args)
	{
		AppManager.startApp();
	}
}