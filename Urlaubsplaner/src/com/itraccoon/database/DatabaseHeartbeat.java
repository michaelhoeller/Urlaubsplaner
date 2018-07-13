package com.itraccoon.database;

import com.itraccoon.constants.Constants;
import com.itraccoon.util.Check;

public class DatabaseHeartbeat {
    
    public DatabaseHeartbeat() {
        // Check derby home
        if (Check.isNotNullOrEmpty(Constants.DATABASE_HOME)) {
            
        }
        
    }
}
