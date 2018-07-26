package com.itraccoon.database.device;

import com.itraccoon.constants.Constants;

public class SystemValueDevice {
    
    public static String getInsertVersion() {
        String statement = "UPDATE SYSTEM_VALUES SET"
            + "SYSV_VERSION = ?"
            + "WHERE SYSV_MAN_ID = " + Constants.MANUAL_VERSION_ID;
        return statement;
    }
    
    public static String getSelectVersion() {
        String statement = "SELECT SYSV_VERSION"
            + "WHERE SYSV_MAN_ID = " + Constants.MANUAL_VERSION_ID;
        return statement;
    }
}
