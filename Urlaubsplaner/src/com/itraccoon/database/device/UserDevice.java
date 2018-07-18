package com.itraccoon.database.device;

public class UserDevice {

	// TODO Get user by name
	// TODO Get user by USRO_ID
	// TODO Get all users

	// TODO Update user by USER_ID

	// TODO Insert new user

	// TODO Delete User by ID
	public static String getDeleteUserById() {
		String statement = "DELETE FROM USERS WHERE USER_ID = ?";
		return statement;
	}

}
