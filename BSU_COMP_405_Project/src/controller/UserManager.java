package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import userAccounts.User;

public class UserManager
{
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
	
	public static ArrayList<User> getUsers() {
		return users;
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
}