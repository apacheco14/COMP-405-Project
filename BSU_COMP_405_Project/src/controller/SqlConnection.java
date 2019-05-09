package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class SqlConnection
{
	// Database connectivity
	private Connection connection;
	private String sql;
	private String DB_PATH = SqlConnection.class.getResource("project.sqlite").getFile();
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat tf = new SimpleDateFormat("hh:mm a");
	
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
			e.printStackTrace();
		}

		// protocol (jdbc): subprotocol (sqlite):databaseName (project.sqlite)
		try
		{
			connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	String getAirportId(String param)
	{
		sql = "SELECT Code FROM Airport WHERE City LIKE ?";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, param);
			ResultSet result = stmt.executeQuery();
			
			while(result.next())
				return result.getString(1);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		return "";
	}

	ArrayList<String> getAirportLocations()
	{
		sql = "SELECT Code FROM Airport ORDER BY Code ASC";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			ArrayList<String> resultList = new ArrayList<String>();
			while(result.next())
				resultList.add(result.getString("Code"));
			
			return resultList;
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			return null;
		}
	}
	
	ArrayList<String> getAirplaneNames()
	{
		sql = "SELECT Name FROM Airplane";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			ArrayList<String> resultList = new ArrayList<String>();
			while(result.next())
				resultList.add(result.getString("Name"));
			
			return resultList;
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			return null;
		}
	}
	
	int getAirplaneId(String name)
	{
		sql = "SELECT Id FROM Airplane WHERE Name LIKE ?";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet result = stmt.executeQuery();
			
			while(result.next())
				return result.getInt(1);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		return 0;
	}
	
	Object getAirplane(int id)
	{
		sql = "SELECT Name FROM Airplane WHERE Id = ?";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			while(result.next())
				return result.getString(1);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		return null;
	}
	
	ArrayList<String> getAvailableSeats(int flightNumber)
	{
		ArrayList<String> tickets = new ArrayList<String>();
		ArrayList<String> seats = new ArrayList<String>();
		
		// get rows and columns
		sql = "SELECT Airplane.Columns, Airplane.Rows FROM Airplane " + 
				"INNER JOIN Flight ON Flight.PlaneId = Airplane.Id " + 
				"WHERE Flight.Number = ?";
		
		int rows = 0, cols = 0;
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, String.valueOf(flightNumber));
			ResultSet result = stmt.executeQuery();
			
			while(result.next())
			{
				rows = result.getInt("Rows");
				cols = result.getInt("Columns");
			}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		// get tickets
		sql = "SELECT Seat, Class, Price FROM Ticket WHERE " +
				"Passenger IS NOT NULL AND Flight LIKE ?";
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, String.valueOf(flightNumber));
			ResultSet result = stmt.executeQuery();
			
			while(result.next())
			{
				tickets.add(result.getString("Seat"));
			}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
			{
				String seatLabel = String.valueOf(r + 1) + (char) (65 + c);
				seats.add(seatLabel);
			}
		}
		
		for(String ticket : tickets)
		{
			seats.remove(ticket);
		}
		
		return seats;
	}
	
	boolean isCustomerInDatabase(String email)
	{
		sql = "SELECT email FROM Customer WHERE email = ?";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet result = stmt.executeQuery();
			
			return result.next();	// returns true if there is a first row, false otherwise
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		return false;
	}
	
	ArrayList<Object> getCustomer(String email)
	{
		sql = "SELECT FirstName, LastName, DoB FROM Customer WHERE Email = ?";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet result = stmt.executeQuery();
			ArrayList<Object> resultList = new ArrayList<Object>();
			while(result.next())
			{
				resultList.add(result.getString("FirstName"));
				resultList.add(result.getString("LastName"));
				resultList.add(result.getString("DoB"));
			}
			
			return resultList;
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		return null;
	}
	
	void insertNewCustomer(String email, String firstName, String lastName)
	{
		sql = "INSERT INTO Customer (Email, FirstName, LastName, DoB) " +
				"VALUES (?, ?, ?, ?)";

		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.execute();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	void bookFlight(int flightNumber, String seat, String email)
	{
		sql = "INSERT INTO Ticket (Passenger, Flight, Seat) " +
				"VALUES (?, ?, ?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, String.valueOf(flightNumber));
			stmt.setString(3, seat);
			stmt.execute();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	Object[][] searchFlights(java.sql.Date depDate, java.sql.Date arrDate, String depLocation,
			String arrLocation)
	{
		boolean isDepDate = false, isArrDate = false, isDepLocation = false, isArrLocation = false;
		
		sql = "SELECT * FROM Flight WHERE ";
		
		if(depDate != null)
		{
			sql = sql + "DepartureDate = ? ";
			isDepDate = true;
		}
		if(arrDate != null)
		{
			sql = sql + "AND ArrivalDate = ? ";
			isArrDate = true;
		}
		if(depLocation != null)
		{
			sql = sql + "AND Departure = ? ";
			isDepLocation = true;
		}
		if(arrLocation != null)
		{
			sql = sql + "AND Arrival = ? ";
			isArrLocation = true;
		}
		
		if(isDepDate || isArrDate || isDepLocation || isArrLocation)
			sql = sql + "ORDER BY Flight.Number ASC";
		else
			sql = sql + "Flight.Number > 0 ORDER BY Flight.Number ASC";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			if(isDepDate)
				stmt.setString(1, df.format(depDate));
			if(isArrDate)
				stmt.setString(2, df.format(arrDate));
			if(isDepLocation)
				stmt.setString(3, depLocation);
			if(isArrLocation)
				stmt.setString(4, arrLocation);
			
			ResultSet result = stmt.executeQuery();
			
			ArrayList<Object[]> resultList = new ArrayList<Object[]>();
			int numColumns = 8;
			while(result.next())
			{
				resultList.add(new Object[] {
						result.getString("Number"),
						result.getString("Departure"),
						result.getString("DepartureDate"),
						result.getString("DepartureTime"),
						result.getString("Arrival"),
						result.getString("ArrivalDate"),
						result.getString("ArrivalTime"),
						result.getInt("PlaneId")});
			}
			
			Object[][] data = new Object[resultList.size()][numColumns];
			int rowCounter = 0;
			for(Object[] row : resultList)
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
	
	boolean insertNewFlight(Object[] data)
	{
		if(data.length == 7)
		{
			sql = "INSERT INTO Flight"
					+ "(Departure, DepartureDate, DepartureTime, Arrival, ArrivalDate,"
					+ "ArrivalTime, PlaneId)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			try
			{
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setInt(7, (int) data[0]);
				stmt.setString(2, df.format(Date.valueOf( AppManager.convertToZonedDateTime( data[1] ).toLocalDate() )));
				stmt.setString(5, df.format(Date.valueOf( AppManager.convertToZonedDateTime( data[2] ).toLocalDate() )));
				stmt.setString(3, tf.format(Time.valueOf( AppManager.convertToZonedDateTime( data[3] ).toLocalTime() )));
				stmt.setString(6, tf.format(Time.valueOf( AppManager.convertToZonedDateTime( data[4] ).toLocalTime() )));
				stmt.setString(1, (String) data[5]);
				stmt.setString(4, (String) data[6]);
				
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
}
