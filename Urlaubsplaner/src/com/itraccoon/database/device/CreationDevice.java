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
            + "USER_DAYS_SPENT FLOAT,"
            + "USER_DAYS_REMAINING FLOAT,"
            + "USER_DAYS_PER_YEAR FLOAT,"
            + "PRIMARY KEY (USER_ID),"
            + "USER_USRO_ID INTEGER CONSTRAINT USERROLE_FOREIGN_KEY REFERENCES USERROLE(USRO_ID) NOT NULL)";
        return statement;
    }
    
    public static String getCreateHolidayTable() {
        String statement = "CREATE TABLE HOLIDAYS("
            + "HOLI_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + "HOLI_START DATE,"
            + "HOLI_END DATE,"
            + "HOLI_AMOUNT_OF_DAYS FLOAT,"
            + "HOLI_AMOUNT_OF_REALDAYS FLOAT,"
            + "PRIMARY KEY (HOLI_ID),"
            + "HOLI_USER_ID INTEGER CONSTRAINT HOLI_USERS_FOREIGN_KEY REFERENCES USERS(USER_ID) NOT NULL)";
        return statement;
    }
    
    public static String getCreateAbsenceTable() {
        String statment = "CREATE TABLE ABSENCE("
            + "ABSE_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + "ABSE_START DATE,"
            + "ABSE_END DATE,"
            + "ABSE_AMOUNT_OF_DAYS FLOAT,"
            + "ABSE_AMOUNT_OF_REALDAYS FLOAT,"
            + "PRIMARY KEY (ABSE_ID),"
            + "ABSE_ABTY_ID INTEGER CONSTRAINT ABSE_ABTY_FOREIGN_KEY REFERENCES ABSENCE_TYPE(ABTY_ID) NOT NULL,"
            + "ABSE_USER_ID INTEGER CONSTRAINT ABSE_USERS_FOREIGN_KEY REFERENCES USERS(USER_ID) NOT NULL)";
        return statment;
    }
    
    public static String getCreateAbsenceTypeTable() {
        String statement = "CREATE TABLE ABSENCE_TYPE("
            + "ABTY_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + "ABTY_TYPE VARCHAR(50),"
            + "PRIMARY KEY (ABTY_ID))";
        return statement;
    }
    
    public static String getCreateLoginTable() {
        String statement = "CREATE TABLE LOGIN("
            + "LOGI_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + "LOGI_LAST_LOGIN TIMESTAMP NOT NULL,"
            + "PRIMARY KEY(LOGI_ID))";
        return statement;
    }
    
    public static String getCreateSystemValueTable() {
        String statement = "CREATE TABLE SYSTEM_VALUES("
            + "SYSV_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + "SYSV_VERSION INTEGER,"
            + "SYSV_MAN_ID INTEGER,"
            + "PRIMARY KEY (SYSV_ID))";
        return statement;
    }
    
}
