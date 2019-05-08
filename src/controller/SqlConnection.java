package controller;

import java.sql.*;
import java.util.ArrayList;

class SqlConnection {
    // Database connectivity
    private Connection connection;
    private String sql;
    private String DB_PATH = SqlConnection.class.getResource("project.sqlite").getFile();

    // constructor should only be called by AppManager
    SqlConnection() {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // protocol (jdbc): subprotocol (sqlite):databaseName (project.sqlite)
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Object[][] searchFlights(Date depDate, Date arrDate, String depLocation,
                             String arrLocation, double price) {
//		if(param.equalsIgnoreCase(""))
//		{
//			sql = "SELECT art.Name AS art_name, alb.Title AS alb_title" +
//					" FROM album alb INNER JOIN artist art USING (ArtistId)" +
//					" ORDER BY art_name, alb_title";
//		}
//		else
//		{
//			sql = "SELECT art.Name AS art_name, alb.Title AS alb_title" +
//					" FROM artist art INNER JOIN album alb USING (ArtistId)" +
//					" WHERE art.Name LIKE ?" +
//					" ORDER BY art_name, alb_title";
//		}
//		
//		try
//		{
//			PreparedStatement stmt = connection.prepareStatement(sql);
//			
//			if(!param.equalsIgnoreCase(""))
//			{
//				stmt.setString( 1, "%" + param + "%" );
//			}
//			
//			// get results
//			ResultSet result = stmt.executeQuery();
//			
//			ArrayList<Object[]> resultList = new ArrayList<Object[]>();
//			int numColumns = 2;
//			while(result.next())
//			{
//				resultList.add(new Object[] {result.getString("art_name"), result.getString("alb_title")});
//			}
//			
//			Object[][] data = new Object[resultList.size()][numColumns];
//			int rowCounter = 0;
//			for(Object row : resultList)
//			{
//				data[rowCounter] = (Object[]) row;
//				rowCounter++;
//			}
//			return data;
//			
//		}
//		catch(Exception e1)
//		{
//			e1.printStackTrace();
//		}

        return null;
    }


    String getAirportId(String param) {
        sql = "SELECT Code FROM Airport WHERE City LIKE ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, param);
            ResultSet result = stmt.executeQuery();

            while (result.next())
                return result.getString(1);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return "";
    }

    ArrayList<String> getAirportLocations() {
        sql = "SELECT City, Code FROM Airport ORDER BY City ASC";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            ArrayList<String> resultList = new ArrayList<String>();
            while (result.next())
                resultList.add(result.getString("City") + " (" + result.getString("Code") + ")");

            return resultList;

        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    ArrayList<String> getAirplaneNames() {
        sql = "SELECT Name FROM Airplane";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            ArrayList<String> resultList = new ArrayList<String>();
            while (result.next())
                resultList.add(result.getString("Name"));

            return resultList;

        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    int getAirplaneId(String name) {
        sql = "SELECT Id FROM Airplane WHERE Name LIKE ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();

            while (result.next())
                return result.getInt(1);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return 0;
    }

    /*
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

        sql = "SELECT Seat, Class, Price FROM Ticket WHERE " +
                "Passenger IS NOT NULL AND Flight LIKE ?";
        try
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(flight));
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
                String seatLabel = String.valueOf(r) + 65 + c;
                seats.add(seatLabel);
            }
        }

        for(String ticket : tickets)
        {
            seats.remove(ticket);
        }

        return seats;
    }
    */
    public ArrayList<String[]> getAvailableSeats(int flight) {
        sql = "SELECT Seat, Class, Price FROM Ticket " +
                "WHERE Passenger IS NULL AND Flight LIKE ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(flight));
            ResultSet result = stmt.executeQuery();
            ArrayList<String[]> tickets = new ArrayList<String[]>();
            while (result.next()) {
                String[] seat = {result.getString(1), result.getString(2), result.getString(3)};
				tickets.add(seat);
            }
            return tickets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    boolean isCustomerInDatabase(String email) {
        sql = "SELECT email FROM Customer WHERE email = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();

            return result.next();    // returns true if there is a first row, false otherwise
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return false;
    }

    ArrayList<Object> getCustomer(String email) {
        sql = "SELECT FirstName, LastName, DoB FROM Customer WHERE Email = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            ArrayList<Object> resultList = new ArrayList<Object>();
            while (result.next()) {
                resultList.add(result.getString("FirstName"));
                resultList.add(result.getString("LastName"));
                resultList.add(result.getString("DoB"));
            }

            return resultList;
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return null;
    }

    void insertNewCustomer(String email, String firstName, String lastName,
                           java.util.Date DOB) {
        sql = "INSERT INTO Customer (Email, FirstName, LastName, DoB) " +
                "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setDate(4, Date.valueOf(AppManager.convertToZonedDateTime(DOB).toLocalDate()));
            stmt.executeQuery();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    void bookFlight(int flightNumber, String seat, String email) {
        sql = "INSERT INTO Ticket (Passenger, Flight, Seat) " +
                "VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, String.valueOf(flightNumber));
            stmt.setString(3, seat);
            stmt.executeQuery();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    Object[][] searchFlights(Object[] params) {
        // TODO

        return null;
    }

    boolean insertNewFlight(Object[] data) {
        if (data.length == 8) {
            sql = "INSERT INTO Flight"
                    + "(airplaneId, departureDate, arrivalDate, departureTime,"
                    + "arrivalTime, departure, arrival, price)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setInt(1, (int) data[0]);
                stmt.setDate(2, Date.valueOf(AppManager.convertToZonedDateTime(data[1]).toLocalDate()));
                stmt.setDate(3, Date.valueOf(AppManager.convertToZonedDateTime(data[2]).toLocalDate()));
                stmt.setTime(4, Time.valueOf(AppManager.convertToZonedDateTime(data[3]).toLocalTime()));
                stmt.setTime(5, Time.valueOf(AppManager.convertToZonedDateTime(data[4]).toLocalTime()));
                stmt.setInt(6, (int) data[5]);
                stmt.setInt(7, (int) data[6]);
                stmt.setDouble(8, (double) data[7]);

                stmt.executeUpdate();

                return true;

            } catch (Exception e1) {
                e1.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}