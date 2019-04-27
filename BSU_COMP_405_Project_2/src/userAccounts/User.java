package userAccounts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class User
{
	public final static int MANAGER = 0;
	public final static int EMPLOYEE = 1;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private int permissionLevel;
	private boolean loggedIn;
	private File userLog;
	private FileWriter writer;
	
	public User(String userName, String firstName, String lastName, String password, int permissionLevel)
	{
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.permissionLevel = permissionLevel;
		loggedIn = false;
		userLog = new File("UserLogs/" + userName + ".txt");
		try {
			writer = new FileWriter(userLog, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean logOn(String password)
	{
		if(password.equals(this.password))
		{
			try {
				writer.write(LocalDate.now() + " " + LocalTime.now() + " Log on\n");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loggedIn = true;
			return true;
		}
		else
			return false;
	}
	
	public void logOff()
	{
		try {
			writer.write(LocalDate.now() + " " + LocalTime.now() + " Log off\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loggedIn = false;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public int getPermissionLevel() {
		return permissionLevel;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public File getUserLog() {
		return userLog;
	}

	public void setFirstName(String text)
	{
		firstName = text;
		try {
			writer.write(LocalDate.now() + " " + LocalTime.now()
					+ " First name changed to " + firstName + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setLastName(String text)
	{
		lastName = text;
		try {
			writer.write(LocalDate.now() + " " + LocalTime.now()
					+ " Last name changed to " + lastName + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}