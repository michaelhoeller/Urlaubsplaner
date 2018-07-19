package com.itraccoon.database.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.itraccoon.database.ConnectionManager;
import com.itraccoon.database.device.UserDevice;

public class UserManagement {
    
    private final static Logger logger = Logger.getLogger(UserManagement.class);
    
    public static void insertNewUser(String name, Integer daysSpent, Integer daysRemaining, Integer daysPerYear, Integer userRole) {
        // TODO: null handling
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(UserDevice.getInsertNewUser());
            stmt.setString(1, name);
            stmt.setInt(2, daysSpent);
            stmt.setInt(3, daysRemaining);
            stmt.setInt(4, daysPerYear);
            stmt.setInt(5, userRole);
            
            stmt.execute();
            conn.commit();
        }
        catch (SQLException e) {
            logger.error("Could not insert User", e);
        }
        finally {
            ConnectionManager.close(null, stmt, conn);
        }
        logger.info("User " + name + " successfully inserted");
    }
    
    /**
     * Deletes a user and all respective holidays and absences
     * 
     * @param id
     *            userId
     */
    public static void deleteUserById(Integer id) {
        
        HolidayManagement.deleteHolidaysByUserId(id);
        AbsenceManagement.deleteAbsencessByUserId(id);
        
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(UserDevice.getDeleteUserById());
            stmt.setInt(1, id);
            
            stmt.execute();
            
            conn.commit();
        }
        catch (SQLException e) {
            logger.error("Could not delete User", e);
        }
        finally {
            ConnectionManager.close(null, stmt, conn);
        }
        logger.info("User successfully deleted");
    }
    
}
