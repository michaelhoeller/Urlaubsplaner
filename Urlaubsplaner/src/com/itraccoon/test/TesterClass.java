package com.itraccoon.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.apache.log4j.Logger;

import com.itraccoon.database.ConnectionManager;
import com.itraccoon.object.FDate;

public class TesterClass {
    
    Logger logger = Logger.getLogger(TesterClass.class);
    
    public static void printAllLogins() throws ParseException {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from SYSTEMPREFERENCES");
            
            System.out.println("Begin Test");
            while (rs.next()) {
                System.out.println(rs.getInt("Syst_id"));
                System.out.println(rs.getTimestamp("SYST_LAST_LOGIN"));
                FDate date = new FDate(rs.getTimestamp(2));
                System.out.println("datetest: " + date.getDateAsString());
            }
            System.out.println("End Test");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
