package com.itraccoon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;
import com.itraccoon.database.device.CreationDevice;
import com.itraccoon.gui.dialogue.MessageDialogue;

public class DatabankCreation {
    
    private final Logger logger = Logger.getLogger(DatabankCreation.class);
    
    public DatabankCreation() {
        try {
            DriverManager.getConnection(Constants.DATABANK_URL + ";create=true");
            createTables();
            logger.info("Tables successfully created");
        }
        catch (SQLException e) {
            new MessageDialogue("Fatal error during databank creation. Please seek immediate shelter!\nAlso: Please check the logfile", "Fatal Error");
            logger.fatal("Fatal error during databank creation. Please seek immediate shelter!", e);
        }
    }
    
    private void createTables() throws SQLException {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        Statement stmt = conn.createStatement();
        stmt.execute(CreationDevice.getCreateUserTable());
        ConnectionManager.close(null, stmt, null);
        
        stmt = conn.createStatement();
        stmt.execute(CreationDevice.getCreateHolidayTable());
        ConnectionManager.close(null, stmt, null);
        
        stmt = conn.createStatement();
        stmt.execute(CreationDevice.getCreateSystemTable());
        ConnectionManager.close(null, stmt, conn);
    }
}
