package com.itraccoon.database.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.itraccoon.database.ConnectionManager;
import com.itraccoon.database.device.UserDevice;

public class SystemValueManagement {
    
    private final static Logger logger = Logger.getLogger(SystemValueManagement.class);
    
    public static Integer getVersion() {
        Integer version = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.prepareStatement(UserDevice.getSelectUserById());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                version = rs.getInt("SYSV_VERSION");
                
            }
            conn.commit();
        }
        catch (SQLException e) {
            logger.error("Could not version", e);
        }
        finally {
            ConnectionManager.close(rs, stmt, conn);
        }
        
        return version;
    }
    
    public static void setVersion(Integer version) {
        
    }
}
