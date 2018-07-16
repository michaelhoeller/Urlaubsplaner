package com.itraccoon.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;
import com.itraccoon.database.ConnectionManager;

public class Main {
    
    static Logger logger;
    
    public static void main(String[] args) {
        System.setProperty("logfileLocation", Constants.LOGFILE_LOCATION);
        System.setProperty("derby.system.home", Constants.DATABASE_LOCATION);
        logger = Logger.getLogger(Main.class);
        
        logger.info("System start");
        SystemBoot.getInstance();
        
        // Main program
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from SYSTEMPREFERENCES");
            
            System.out.println("Begin Test");
            while (rs.next()) {
                System.out.println(rs.getInt("Syst_id"));
                System.out.println(rs.getDate("SYST_LAST_LOGIN"));
            }
            System.out.println("End Test");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
