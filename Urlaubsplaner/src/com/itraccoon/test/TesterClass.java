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

	static final Logger logger = Logger.getLogger(TesterClass.class);

	public static void printAllLogins() throws ParseException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from LOGIN");

			logger.info("---------------------------------------------");
			logger.info("Begin Test");
			while (rs.next()) {
				logger.info("Eintrag with id: " + rs.getInt("logi_id"));
				logger.info("Respective timestamp: " + rs.getTimestamp("logi_LAST_LOGIN"));
				logger.info("Conversion to String test: " + new FDate(rs.getTimestamp(2)).getDateAsString());
				logger.info("------------------");
			}
			logger.info("End Test");
			logger.info("---------------------------------------------");
		} catch (SQLException e) {
			logger.error("Error during TesterClass.", e);
		}
	}
}
