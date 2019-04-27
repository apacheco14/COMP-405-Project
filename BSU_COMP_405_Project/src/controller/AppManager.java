package controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import sql.InsertThread;
import sql.SelectThread;
import sql.ThreadFactory;
import startupGUI.Frm_Startup;
import userAccounts.Frm_LogIn;

public class AppManager
{
	private static SelectThread select = ThreadFactory.getSelectThread();
	private static InsertThread insert = ThreadFactory.getInsertThread();
	
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
		while(select.isAlive())
		{
			// wait for thread to finish?
		}
		
		Object[][] data = select.run("");
		
		return data;
	}
	
	public static Object[][] searchDatabase(String query)
	{
		while(select.isAlive())
		{
			// wait for thread to finish?
		}
		
		System.out.println("Searching database for " + query + "...");
		
		Object[][] data = select.run(query);
		
		return data;
	}
	
	public static void insertNewFlightData(Object[] data)
	{
		while(insert.isAlive())
		{
			// wait for thread to finish?
		}
		
		insert.run(insert.NEW_FLIGHT, data);
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