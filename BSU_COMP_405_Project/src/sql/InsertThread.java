package sql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InsertThread extends SQLThread
{
	/*
	 * constructor should remain protected to prevent multiple instances of this class
	 * being created without permission
	 */
	protected InsertThread()
	{
		
	}
	
	public boolean run(int insertType, Object[] data)
	{
		switch(insertType)
		{
			case NEW_FLIGHT:
				return insertNewFlight(data);
		}
		
		return false;
	}

	private boolean insertNewFlight(Object[] data)
	{
		if(data.length == 8)
		{
			sql = "INSERT INTO Flight"
					+ "(departureDate, arrivalDate, departureTime,"
					+ "arrivalTime, departure, arrival, price)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		}
		else
		{
			return false;
		}
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// flight id is autoincremented
			stmt.setDate(1, Date.valueOf(convertDateToZonedDateTime(data[1]).toLocalDate()));
			stmt.setDate(2, Date.valueOf(convertDateToZonedDateTime(data[2]).toLocalDate()));
			stmt.setTime(3, Time.valueOf(convertDateToZonedDateTime(data[3]).toLocalTime()));
			stmt.setTime(4, Time.valueOf(convertDateToZonedDateTime(data[4]).toLocalTime()));
			stmt.setInt(5, (int) data[5]);
			stmt.setInt(6, (int) data[6]);
			stmt.setDouble(7, ((Double) data[7]).doubleValue());
			
			stmt.execute();
			
			return true;
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			return false;
		}
	}
	
	private ZonedDateTime convertDateToZonedDateTime(Object d)
	{
		return ((java.util.Date) d).toInstant().atZone(ZoneId.of("America/New_York"));
	}
}
