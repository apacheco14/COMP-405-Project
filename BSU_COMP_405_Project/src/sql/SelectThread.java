package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SelectThread extends SQLThread
{
	/*
	 * constructor should remain protected to prevent multiple instances of this class
	 * being created without permission
	 */
	protected SelectThread()
	{
		
	}
	
	public Object[][] run(int selectType, String param)
	{
		switch(selectType)
		{
			case AIRPORT_LOCATIONS:
				return getAirportLocations();
			case AIRPORT_ID:
				return getAirportId(param);
			case SIMPLE_SELECT:
				return simpleSelect(param);
		}
		
		return null;
	}

	private Object[][] simpleSelect(String param)
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
	

	private Object[][] getAirportId(String param)
	{
		sql = "SELECT Id FROM Airport WHERE City LIKE ?";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, param);
			
			ResultSet result = stmt.executeQuery();
			
			Object data[][] = new Object[1][1];
			data[0][0] = result.getInt(1);
			
			return data;
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		return null;
	}

	private Object[][] getAirportLocations()
	{
		sql = "SELECT City FROM Airport ORDER BY City ASC";
		
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet result = stmt.executeQuery();
			
			ArrayList<String> resultList = new ArrayList<String>();
			
			while(result.next())
			{
				resultList.add(result.getString("City"));
			}
			
			Object[][] data = new Object[1][resultList.size()];
			int columnCounter = 0;
			for(Object row : resultList)
			{
				data[0][columnCounter] = row;
				columnCounter++;
			}
			return data;
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		return null;
	}
}
