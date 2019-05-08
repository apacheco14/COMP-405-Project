package userAccounts;

import controller.UsersManager;

public class User
{
	public static final int MANAGER = 0;
	public static final int EMPLOYEE = 1;
	public static final int CUSTOMER = 2;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private int permissionLevel;
	private boolean loggedIn;
	
	public User(String userName, String firstName, String lastName, String password, int permissionLevel)
	{
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.permissionLevel = permissionLevel;
		loggedIn = false;
	}
	
	public boolean logOn(String password)
	{
		if(password.equals(this.password))
		{
			UsersManager.writeToUserLog(this, UsersManager.MESSAGE_LOG_ON);
			loggedIn = true;
			return true;
		}
		else
			return false;
	}
	
	public void logOff()
	{
		UsersManager.writeToUserLog(this, UsersManager.MESSAGE_LOG_OFF);
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

	public void setFirstName(String text)
	{
		firstName = text;
		UsersManager.writeToUserLog(this, UsersManager.MESSAGE_NEW_FNAME);
	}
	
	public void setLastName(String text)
	{
		lastName = text;
		UsersManager.writeToUserLog(this, UsersManager.MESSAGE_NEW_LNAME);
	}
}