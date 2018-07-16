package com.itraccoon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;

public class ConnectionManager {
    
    private static ConnectionManager instance;
    private static Logger            logger = Logger.getLogger(ConnectionManager.class);
    
    private ConnectionManager() {
    }
    
    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }
    
    protected Connection connection;
    
    public Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(Constants.DATABANK_URL + ";create=false");
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            logger.error("SQLException was thrown during connection to databank", e);
        }
        
        return connection;
    }
    
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                logger.error("Error during close. Couldn't close resultset", e);
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException e) {
                logger.error("Error during close. Couldn't close statement", e);
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                
                conn.close();
            }
            catch (SQLException e) {
                logger.error("Error during close. Couldn't close connection", e);
            }
            conn = null;
        }
    }
    
}
