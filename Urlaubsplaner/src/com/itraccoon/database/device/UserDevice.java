package com.itraccoon.database.device;

public class UserDevice {
    
    /**
     * Integer user_id
     */
    public static String getSelectUserById() {
        String statement = "SELECT * FROM USERS WHERE USER_ID = ?";
        return statement;
    }
    
    public static String getSelectAllUsers() {
        String statement = "SELECT * FROM USERS";
        return statement;
    }
    
    /**
     * 1 String username </br>
     * 2 Integer days_spent </br>
     * 3 Integer days_remaining </br>
     * 4 Integer days_per_year </br>
     * 5 Integer USRO_ID </br>
     * 6 Integer user_id
     */
    public static String getUpdateUserById() {
        String statement = "UPDATE USERS SET" +
            "USER_NAME = ?," +
            "USER_DAYS_SPENT = ?," +
            "USER_DAYS_REMAINING = ?," +
            "USER_DAYS_PER_YEAR = ?," +
            "USER_USRO_ID = ?" +
            "WHERE USER_ID = ?";
        return statement;
    }
    
    /**
     * 1 String username </br>
     * 2 Integer days_spent </br>
     * 3 Integer days_remaining </br>
     * 4 Integer days_per_year </br>
     * 5 Integer USRO_ID
     */
    public static String getInsertNewUser() {
        String statement = "INSERT INTO USERS" +
            "(USER_NAME, USER_DAYS_SPENT, USER_DAYS_REMAINING, USER_DAYS_PER_YEAR, USER_USRO_ID)" +
            "VALUES " +
            "(?,?,?,?,?)";
        return statement;
    }
    
    /**
     * Integer user_id
     */
    public static String getDeleteUserById() {
        String statement = "DELETE FROM USERS WHERE USER_ID = ?";
        return statement;
    }
    
}
