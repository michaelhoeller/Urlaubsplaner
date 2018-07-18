package com.itraccoon.database.device;

public class AbsencesDevice {
	public static String getDeleteAbsencesOfUser() {
		String statement = "DELETE FROM ABCENSE WHERE ABSE_USER_ID = ?";
		return statement;
	}
}
