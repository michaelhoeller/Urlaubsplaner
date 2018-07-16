package com.itraccoon.database.device;

public class CreationDevice {
    
    public static String getCreateUserTable() {
        String statement = "CREATE TABLE USERS("
            + "USER_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + "USER_NAME VARCHAR(50) NOT NULL,"
            + "USER_DAYS_SPENT INTEGER,"
            + "USER_DAYS_REMAINING INTEGER,"
            + "USER_DAYS_PER_YEAR INTEGER,"
            + "PRIMARY KEY (USER_ID))";
        return statement;
    }
    
    public static String getCreateHolidayTable() {
        String statement = "CREATE TABLE HOLIDAYS("
            + "HOLI_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + "HOLI_START DATE,"
            + "HOLI_END DATE,"
            + "HOLI_AMOUNT_OF_DAYS INTEGER,"
            + "HOLI_AMOUNT_OF_REALDAYS INTEGER,"
            + "PRIMARY KEY (HOLI_ID),"
            + "HOLI_USER_ID INTEGER CONSTRAINT USERS_FOREIGN_KEY REFERENCES USERS(USER_ID))";
        return statement;
    }
    
    public static String getCreateSystemTable() {
        String statement = "CREATE TABLE SYSTEMPREFERENCES("
            + "SYST_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + "SYST_LAST_LOGIN DATE NOT NULL,"
            + "PRIMARY KEY(SYST_ID))";
        return statement;
    }
    
}
