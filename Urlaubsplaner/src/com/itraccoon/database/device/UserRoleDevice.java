package com.itraccoon.database.device;

public class UserRoleDevice {
	public static String getInsertIntoUserRole() {
		String statement = "INSERT INTO USERROLE (USRO_ROLE) VALUES (?)";
		return statement;
	}

	public static String getSelectUserRoleById() {
		String statement = "SELECT USRO_ROLE FROM USERROLE WHERE USRO_ID = ?";
		return statement;
	}

	/**
	 * Index 1(String): USRO_ROLE</br>
	 * Index 2(Integer): USRO_ID
	 */
	public static String getModifiyUserRoleById() {
		String statement = "UPDATE USERROLE SET USRO_ROLE = ? WHERE USRO_ID = ?";
		return statement;
	}

	public static String getAllRoles() {
		String statement = "SELECT * FROM USERROLE";
		return statement;
	}

}
