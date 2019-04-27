package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLThread extends Thread
{
	public final int NEW_FLIGHT = 0;
	public final int AIRPORT_LOCATIONS = 1;
	public final int AIRPORT_ID = 2;
	public final int SIMPLE_SELECT = 3;
	
	// Database connectivity
	Connection connection;
	String sql;
	String DB_PATH = SQLThread.class.getResource("project.sqlite").getFile();
	
	protected SQLThread()
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

		// protocol (jdbc): subprotocol (sqlite):databaseName (Chinook_Sqlite_AutoIncrementPKs.sqlite)
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
}
