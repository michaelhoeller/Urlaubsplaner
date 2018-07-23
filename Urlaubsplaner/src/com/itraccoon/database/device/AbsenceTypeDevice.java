package com.itraccoon.database.device;

public class AbsenceTypeDevice {
    
    /**
     * String abty_type
     */
    public static String getInsertIntoAbsenceType() {
        String statement = "INSERT INTO ABSENCE_TYPE (ABTY_TYPE) VALUES (?)";
        return statement;
    }
    
    /**
     * Integer abty_id
     */
    public static String getSelectUserRoleById() {
        String statement = "SELECT ABTY_TYPE FROM ABSENCE_TYPE WHERE ABTY_ID = ?";
        return statement;
    }
    
    /**
     * Index 1(String): ABTY_TYPE</br>
     * Index 2(Integer): ABTY_ID
     */
    public static String getModifiyUserRoleById() {
        String statement = "UPDATE ABSENCE_TYPE SET ABTY_TYPE = ? WHERE ABTY_ID = ?";
        return statement;
    }
    
    public static String getAllRoles() {
        String statement = "SELECT * FROM ABSENCE_TYPE";
        return statement;
    }
}
