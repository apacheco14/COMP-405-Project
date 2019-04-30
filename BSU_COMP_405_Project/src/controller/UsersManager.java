package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import userAccounts.User;

public class UsersManager
{
	public static final String MESSAGE_LOG_ON = "Log on";
	public static final String MESSAGE_LOG_OFF = "Log off";
	public static final String MESSAGE_NEW_FNAME = "First name changed";
	public static final String MESSAGE_NEW_LNAME = "Last name changed";
	public static final String MESSAGE_ADDED_FLIGHT = "Added new flight";
	public static final String MESSAGE_BOOKED_FLIGHT = "Booked flight";
	
	private static ArrayList<User> users = new ArrayList<User>();
	
	public static void createUsers()
	{
		try
		{
			Scanner reader = new Scanner(new File("Resources/User List.txt"));
			
			while(reader.hasNextLine())
			{
				users.add(new User(reader.nextLine(), reader.nextLine(), reader.nextLine(),
						reader.nextLine(), reader.nextInt()));
				if(reader.hasNextLine())
					reader.nextLine();	//skip remainder of integer line
			}
			
			reader.close();
		}
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(
					null, "File not found.\n" + e.toString(), "File Error",
					JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}
	}
	
	public static boolean isUserNameAvailable(String userName)
	{
		for(User u : users)
			if(userName.equals(u.getUserName()))
				return false;
		
		return true;
	}

	public static void addUser(User user)
	{
		users.add(user);
		
		try {
			PrintWriter writer = new PrintWriter(new File("Resources/User List.txt"));
			
			for(int index = 0; index < users.size(); index++)
			{
				if(index != 0)
					writer.println();
				writer.println(users.get(index).getUserName());
				writer.println(users.get(index).getFirstName());
				writer.println(users.get(index).getLastName());
				writer.println(users.get(index).getPassword());
				writer.print(users.get(index).getPermissionLevel());
				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateUserInfo()
	{
		try {
			PrintWriter writer = new PrintWriter(new File("Resources/User List.txt"));
			
			for(int index = 0; index < users.size(); index++)
			{
				if(index != 0)
					writer.println();
				writer.println(users.get(index).getUserName());
				writer.println(users.get(index).getFirstName());
				writer.println(users.get(index).getLastName());
				writer.println(users.get(index).getPassword());
				writer.print(users.get(index).getPermissionLevel());
				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean attemptLogIn(String userName, String password)
	{
		for(int index = 0; index < users.size(); index++)
		{
			if(userName.equals(users.get(index).getUserName()) && users.get(index).logOn(password))
			{
				User loggedInUser = users.get(index);
				
				switch(loggedInUser.getPermissionLevel())
				{
					case User.CUSTOMER:
						AppManager.startCustomerVersion();
						break;
					case User.EMPLOYEE:
					case User.MANAGER:
						AppManager.startEmployeeVersion(loggedInUser);
						break;
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	public static void writeToUserLog(User user, String messageType)
	{
		String message = LocalDate.now() + " " + LocalTime.now() + " ";
		switch(messageType)
		{
			case MESSAGE_LOG_ON:
			case MESSAGE_LOG_OFF:
			case MESSAGE_ADDED_FLIGHT:
			case MESSAGE_BOOKED_FLIGHT:
				message = message + messageType;
				break;
			case MESSAGE_NEW_FNAME:
			case MESSAGE_NEW_LNAME:
				message = message + messageType + user.getFirstName() + " " + user.getLastName();
				break;
		}
		
		File userLog;
		FileWriter writer;
		
		userLog = new File("UserLogs/" + user.getUserName() + ".txt");
		try
		{
			writer = new FileWriter(userLog, true);
			writer.write(message + "\n");
			writer.flush();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}