package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

class SqlConnection
{
	// Database connectivity
	private Connection connection;
	private String sql;
	private String DB_PATH = SqlConnection.class.getResource("project.sqlite").getFile();
	
	// constructor should only be called by AppManager
	SqlConnection()
	{
		// load the sqlite-JDBC driver using the current class loader
		try
		{
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// protocol (jdbc): subprotocol (sqlite):databaseName (project.sqlite)
		try
		{
			connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Object[][] searchDatabase(String param)
	{
		if(param.equalsIgnoreCase(""))
		{
			sql = "SELECT art.Name AS art_name, alb.Title AS alb_title" +
					" FROM album alb INNER JOIN artist art USING (ArtistId)" +
					" ORDER BY art_name, alb_title";
		}
		else
		{
			sql = "SELECT art.Name AS art_name, alb.Title AS alb_title" +
					" FROM artist art INNER JOIN album alb USING (ArtistId)" +
					" WHERE art.Name LIKE ?" +
					" ORDER BY art_name, alb_title";
		}
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			if(!param.equalsIgnoreCase(""))
			{
				stmt.setString( 1, "%" + param + "%" );
			}
			
			// get results
			ResultSet result = stmt.executeQuery();
			
			ArrayList<Object[]> resultList = new ArrayList<Object[]>();
			int numColumns = 2;
			while(result.next())
			{
				resultList.add(new Object[] {result.getString("art_name"), result.getString("alb_title")});
			}
			
			Object[][] data = new Object[resultList.size()][numColumns];
			int rowCounter = 0;
			for(Object row : resultList)
			{
				data[rowCounter] = (Object[]) row;
				rowCounter++;
			}
			return data;
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		return null;
	}
	

	int getAirportId(String param)
	{
		sql = "SELECT Id FROM Airport WHERE City LIKE ?";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, param);
			ResultSet result = stmt.executeQuery();
			
			return result.getInt(1);
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			return 0;
		}
	}

	ArrayList<String> getAirportLocations()
	{
		sql = "SELECT City, Code FROM Airport ORDER BY City ASC";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			ArrayList<String> resultList = new ArrayList<String>();
			while(result.next())
				resultList.add(result.getString("City") + " (" + result.getString("Code") + ")");
			
			return resultList;
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			return null;
		}
	}
	
	boolean insertNewFlight(Object[] data)
	{
		if(data.length == 8)
		{
			sql = "INSERT INTO Flight"
					+ "(departureDate, arrivalDate, departureTime,"
					+ "arrivalTime, departure, arrival, price)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			try
			{
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				// flight id is autoincremented
				stmt.setDate(1, Date.valueOf( convertToZonedDateTime( data[1] ).toLocalDate() ));
				stmt.setDate(2, Date.valueOf( convertToZonedDateTime( data[2] ).toLocalDate() ));
				stmt.setTime(3, Time.valueOf( convertToZonedDateTime( data[3] ).toLocalTime() ));
				stmt.setTime(4, Time.valueOf( convertToZonedDateTime( data[4] ).toLocalTime() ));
				stmt.setInt(5, (int) data[5]);
				stmt.setInt(6, (int) data[6]);
				stmt.setDouble(7, (double) data[7]);
				
				stmt.executeUpdate();
				
				return true;
				
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	private ZonedDateTime convertToZonedDateTime(Object d)
	{
		return ((java.util.Date) d).toInstant().atZone(ZoneId.of("America/New_York"));
	}
}
