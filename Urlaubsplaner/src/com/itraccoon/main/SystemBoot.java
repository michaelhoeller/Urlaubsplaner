package com.itraccoon.main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;
import com.itraccoon.database.ConnectionManager;
import com.itraccoon.database.DatabankCreation;
import com.itraccoon.database.device.LoginDevice;

public class SystemBoot {
    
    List<String>              messages           = new ArrayList<String>();
    static Logger             logger             = Logger.getLogger(SystemBoot.class);
    
    private boolean           databaseExists;
    private boolean           newYearBetweenLogins;
    private static boolean    systemBootComplete = false;
    
    private static SystemBoot instance;
    
    private SystemBoot() {
        logger.info("Preparing SystemBoot");
        checkFileStruckture();
        checkForDatabank();
        if (!databaseExists) {
            new DatabankCreation();
        }
        else {
            checkNewYear();
        }
        
        stateLastLogin();
        
        setLookAndFeel();
        // Last action
        systemBootComplete = true;
        logger.info("SystemBoot completed successfully");
    }
    
    public static SystemBoot getInstance() {
        if (systemBootComplete) {
            logger.warn("SystemBoot was already instanciated and called but was called again");
            return null;
        }
        if (instance == null) {
            instance = new SystemBoot();
        }
        return instance;
    }
    
    private void checkFileStruckture() {
        // Check for Folders. Create it if it doesn't exist
        if (!new File(Constants.MAINFOLDER).exists()) {
            new File(Constants.MAINFOLDER).mkdirs();
            messages.add("No system folder found. Creation successful");
        }
        logger.info("File structure ok");
    }
    
    private void checkForDatabank() {
        // TODO implement Methode
        if (checkDBExistence()) {
            setDatabaseExists(true);
            logger.info("Databank exists and connection was established successfully");
            messages.add("Databank exists and connection was established successfully");
        }
        else {
            setDatabaseExists(false);
            logger.info("No databank found. Initializing creation process");
        }
        
    }
    
    @SuppressWarnings("unused")
    private void checkNewYear() {
        // TODO Auto-generated method stub
        // Check Last Login
        Date date = new Date();
        
        // 31.12 is between last login and date
        if (1 == 2) {
            // Give every user respective holidays
            logger.info("New days have been allocated to all users");
        }
    }
    
    private static boolean checkDBExistence() {
        
        boolean flag = false;
        try {
            String url = Constants.DATABANK_URL;
            DriverManager.getConnection(url);
            flag = true;
        }
        catch (SQLException e) {
            if (e.getSQLState().equals("XJ004")) {
                flag = false;
            }
            else {
                flag = false;
                logger.error("Error in SQLException", e);
            }
            
        }
        logger.info("checkDBExistence() returned " + flag);
        return flag;
    }
    
    private void stateLastLogin() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(LoginDevice.getInsertLogin());
            stmt.execute();
            conn.commit();
        }
        catch (SQLException e) {
            logger.fatal("Couldn't insert login date", e);
        }
        finally {
            ConnectionManager.close(null, stmt, conn);
        }
    }
    
    private void setLookAndFeel() {
        logger.info("Setting up look and feel.");
        
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        }
        catch (ClassNotFoundException e) {
            logger.warn("Look and feel yesn't be set", e);
        }
        catch (InstantiationException e) {
            logger.warn("Look and feel yesn't be set", e);
        }
        catch (IllegalAccessException e) {
            logger.warn("Look and feel yesn't be set", e);
        }
        catch (UnsupportedLookAndFeelException e) {
            logger.warn("Look and feel yesn't be set", e);
        }
        logger.info("Look and feel set successfully.");
    }
    
    public boolean isDatabaseExists() {
        return databaseExists;
    }
    
    public void setDatabaseExists(boolean databaseExists) {
        this.databaseExists = databaseExists;
    }
    
    public boolean isNewYearBetweenLogins() {
        return newYearBetweenLogins;
    }
    
    public void setNewYearBetweenLogins(boolean newYearBetweenLogins) {
        this.newYearBetweenLogins = newYearBetweenLogins;
    }
}
