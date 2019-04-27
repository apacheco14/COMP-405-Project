package sql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;

public class InsertThread extends SQLThread
{
	public static final int NEW_FLIGHT = 0;
	
	/*
	 * constructor should remain protected to prevent multiple instances of this class
	 * being created without permission
	 */
	protected InsertThread()
	{
		
	}
	
	public boolean run(int insertType, Object[] data)
	{
		boolean insertSuccessful = false;
		
		switch(insertType)
		{
			case NEW_FLIGHT:
				insertSuccessful = insertNewFlight(data);
				break;
		}
		
		return insertSuccessful;
	}

	private boolean insertNewFlight(Object[] data)
	{
		if(data.length == 8)
		{
			sql = "INSERT INTO Flights"
					+ "(flightNumber, depDate, arrDate, depTime, arrTime, depLocation, arrLocation, price)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		}
		else
		{
			return false;
		}
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, (String) data[0]);
			stmt.setDate(2, (Date) data[1]);
			stmt.setDate(3, (Date) data[2]);
			stmt.setTime(4, (Time) data[3]);
			stmt.setTime(5, (Time) data[4]);
			stmt.setString(6, (String) data[5]);
			stmt.setString(7, (String) data[6]);
			stmt.setDouble(8, ((Double) data[7]).doubleValue());
			
			return true;
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			return false;
		}
	}
}
