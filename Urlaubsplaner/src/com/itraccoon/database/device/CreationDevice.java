package com.itraccoon.database.device;

public class CreationDevice {

	public static String getCreateBeroTable() {
		String statement = "CREATE TABLE USERROLE("
				+ "USRO_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ "USRO_ROLE VARCHAR(50),"
				+ "PRIMARY KEY(USRO_ID))";
		return statement;
	}

	public static String getCreateUserTable() {
		String statement = "CREATE TABLE USERS("
				+ "USER_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ "USER_NAME VARCHAR(50) NOT NULL,"
				+ "USER_DAYS_SPENT INTEGER,"
				+ "USER_DAYS_REMAINING INTEGER,"
				+ "USER_DAYS_PER_YEAR INTEGER,"
				+ "PRIMARY KEY (USER_ID),"
				+ "USER_USRO_ID INTEGER CONSTRAINT USERROLE_FOREIGN_KEY REFERENCES USERROLE(USRO_ID))";
		return statement;
	}

	public static String getCreateHolidayTable() {
		String statement = "CREATE TABLE HOLIDAYS("
				+ "HOLI_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ "HOLI_START DATE,"
				+ "HOLI_END DATE,"
				+ "HOLI_AMOUNT_OF_DAYS INTEGER,"
				+ "HOLI_AMOUNT_OF_REALDAYS INTEGER,"
				+ "HOLI_IS_HOLIDAY BOOLEAN,"
				+ "PRIMARY KEY (HOLI_ID),"
				+ "HOLI_USER_ID INTEGER CONSTRAINT HOLI_USERS_FOREIGN_KEY REFERENCES USERS(USER_ID))";
		return statement;
	}

	public static String getCreateAbsenceTable() {
		String statment = "CREATE TABLE ABSENCE("
				+ "ABSE_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ "ABSE_START DATE,"
				+ "ABSE_END DATE,"
				+ "ABSE_AMOUNT_OF_DAYS INTEGER,"
				+ "ABSE_AMOUNT_OF_REALDAYS INTEGER,"
				+ "ABSE_IS_HOLIDAY BOOLEAN,"
				+ "PRIMARY KEY (ABSE_ID),"
				+ "ABSE_USER_ID INTEGER CONSTRAINT ABSE_USERS_FOREIGN_KEY REFERENCES USERS(USER_ID))";
		return statment;
	}

	public static String getCreateLoginTable() {
		String statement = "CREATE TABLE LOGIN("
				+ "LOGI_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ "LOGI_LAST_LOGIN TIMESTAMP NOT NULL,"
				+ "PRIMARY KEY(LOGI_ID))";
		return statement;
	}

}
