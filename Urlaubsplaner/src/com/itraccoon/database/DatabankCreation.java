package com.itraccoon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;
import com.itraccoon.database.device.AbsenceTypeDevice;
import com.itraccoon.database.device.CreationDevice;
import com.itraccoon.database.device.UserRoleDevice;
import com.itraccoon.gui.dialogue.MessageDialogue;

public class DatabankCreation {
    
    private final Logger logger = Logger.getLogger(DatabankCreation.class);
    
    public DatabankCreation() {
        try {
            DriverManager.getConnection(Constants.DATABANK_URL + ";create=true");
            createTables();
            logger.info("Tables successfully created");
            initUsros();
            logger.info("Default user roles successfully created");
            initAbtys();
            logger.info("Default absence types successfully created");
        }
        catch (SQLException e) {
            new MessageDialogue("Fatal error during databank creation. Please seek immediate shelter!\nAlso: Please check the logfile", "Fatal Error");
            logger.fatal("Fatal error during databank creation. Please seek immediate shelter!", e);
        }
    }
    
    private void createTables() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Statement stmt = null;
        
        try {
            stmt = conn.createStatement();
            stmt.execute(CreationDevice.getCreateBeroTable());
            stmt.execute(CreationDevice.getCreateUserTable());
            stmt.execute(CreationDevice.getCreateAbsenceTypeTable());
            stmt.execute(CreationDevice.getCreateHolidayTable());
            stmt.execute(CreationDevice.getCreateAbsenceTable());
            stmt.execute(CreationDevice.getCreateLoginTable());
            conn.commit();
        }
        catch (SQLException e) {
            logger.error("Error during table creation", e);
        }
        finally {
            ConnectionManager.close(null, stmt, conn);
        }
    }
    
    private void initUsros() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(UserRoleDevice.getInsertIntoUserRole());
            
            stmt.setString(1, Constants.DEFAULT_ROLE_1);
            stmt.execute();
            
            stmt.setString(1, Constants.DEFAULT_ROLE_2);
            stmt.execute();
            
            stmt.setString(1, Constants.DEFAULT_ROLE_3);
            stmt.execute();
            
            stmt.setString(1, Constants.DEFAULT_ROLE);
            stmt.execute();
            
            conn.commit();
        }
        catch (SQLException e) {
            logger.error("Error during inital user role creation", e);
        }
        finally {
            ConnectionManager.close(null, stmt, conn);
        }
    }
    
    private void initAbtys() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(AbsenceTypeDevice.getInsertIntoAbsenceType());
            
            stmt.setString(1, Constants.DEFAULT_ABTY_1);
            stmt.execute();
            
            stmt.setString(1, Constants.DEFAULT_ABTY_2);
            stmt.execute();
            
            stmt.setString(1, Constants.DEFAULT_ABTY_3);
            stmt.execute();
            
            conn.commit();
        }
        catch (SQLException e) {
            logger.error("Error during inital absence type creation", e);
        }
        finally {
            ConnectionManager.close(null, stmt, conn);
        }
        
    }
}
