package controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import startupGUI.Frm_Startup;
import userAccounts.Frm_LogIn;

public class AppManager
{
	// only one SqlConnection should be made, all requests for data come from AppManager
	private static SqlConnection sql = new SqlConnection();
	
	public static void startApp()
	{
		new Frm_Startup();
	}
	
	public static void startEmployeeVersion()
	{
		UsersManager.createUsers();
		new Frm_LogIn();
	}
	
	public static void startCustomerVersion()
	{
		
	}
	
	public static String[] getColumnNames()
	{
		return new String[] {"artist_name", "album_title"};
	}
	
	public static Object[][] getAllData()
	{
		return sql.searchDatabase("");
	}
	
	public static Object[][] searchDatabase(String query)
	{
		return sql.searchDatabase(query);
	}
	
	public static void insertNewFlightData(Object[] data)
	{
		sql.insertNewFlight(data);
	}
	
	public static ArrayList<String> getAirportLocations()
	{
		return sql.getAirportLocations();
	}
	
	public static int getAirportId(String airportCity)
	{
		return sql.getAirportId(airportCity);
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