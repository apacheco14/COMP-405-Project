package controller;

import userAccounts.Frm_LogIn;

public class Main
{
	public static void main(String[] args)
	{
		UserManager.createUsers();
		new Frm_LogIn();
	}
}