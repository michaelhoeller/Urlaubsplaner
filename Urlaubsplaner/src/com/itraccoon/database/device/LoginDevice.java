package com.itraccoon.database.device;

public class LoginDevice {
    
    public static String getInsertLogin() {
        String statement = "INSERT INTO LOGIN"
            + "(LOGI_LAST_LOGIN)"
            + "VALUES"
            + "(CURRENT_TIMESTAMP)";
        return statement;
    }
    
}
