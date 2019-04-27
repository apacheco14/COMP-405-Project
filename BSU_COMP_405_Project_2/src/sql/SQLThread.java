package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class SQLThread extends Thread
{
	// Database connectivity
	Connection connection;
	String sql;
	String DB_PATH = SQLThread.class.getResource("Chinook_Sqlite_AutoIncrementPKs.sqlite").getFile();
	
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
