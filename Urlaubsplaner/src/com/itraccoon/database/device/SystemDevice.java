package com.itraccoon.database.device;

public class SystemDevice {
    
    public static String getInsertLogin() {
        String statement = "INSERT INTO SYSTEMPREFERENCES"
            + "(SYST_LAST_LOGIN)"
            + "VALUES"
            + "(CURRENT_TIMESTAMP)";
        return statement;
    }
    
    public static String getShutdown() {
        String statement = "";
        return statement;
    }
}
