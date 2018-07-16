package com.itraccoon.database.device;

import java.sql.Date;

public class SystemDevice {
    
    public static String getInsertLogin() {
        Date date = new Date(System.currentTimeMillis());
        String statement = "INSERT INTO SYSTEMPREFERENCES"
            + "(SYST_LAST_LOGIN)"
            + "VALUES"
            + "(" + date + ")";
        return statement;
    }
}
