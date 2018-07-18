package com.itraccoon.database.device;

public class HolidayDevice {

	public static String getDeleteHolidaysOfUser() {
		String statement = "DELETE FROM HOLIDAYS WHERE HOLI_USER_ID = ?";
		return statement;
	}
}
