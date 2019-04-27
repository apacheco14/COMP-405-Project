package controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import sql.InsertThread;
import sql.SQLThread;
import sql.SelectThread;
import sql.SqlConnection;
import sql.ThreadFactory;
import startupGUI.Frm_Startup;
import userAccounts.Frm_LogIn;

public class AppManager
{
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
		Object[][] data = null;	// sql.run(select.SIMPLE_SELECT, "");
		
		return data;
	}
	
	public static Object[][] searchDatabase(String query)
	{
		Object[][] data = sql.run(sql.SIMPLE_SELECT, query, null);
		
		return data;
	}
	
	public static void insertNewFlightData(Object[] data)
	{
		sql.insertNewFlight(data);
	}
	
	public static ArrayList<String> getAirportLocations()
	{
		ArrayList<String> data = sql.getAirportLocations();
		
		return data;
	}
	
	public static int getAirportId(String airportCity)
	{
		return sql.getAirportId(airportCity);
	}
	
	public static Image getIconImage()
	{
		try {
			return ImageIO.read(new File("Images/CallButtons.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}