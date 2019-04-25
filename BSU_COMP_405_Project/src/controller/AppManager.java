package controller;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import sql.InsertThread;
import sql.SelectThread;
import sql.ThreadFactory;
import userAccounts.User;

public class AppManager
{
	private static SelectThread select = ThreadFactory.getSelectThread();
	private static InsertThread insert = ThreadFactory.getInsertThread();
	
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
	
	public static void insertData(String data)
	{
		while(insert.isAlive())
		{
			// wait for thread to finish?
		}
		
		System.out.println("Inserting " + data + " into database...");
		
		// TODO start an InsertThread to add data into the database
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