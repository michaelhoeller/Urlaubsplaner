package com.itraccoon.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.itraccoon.database.ConnectionManager;
import com.itraccoon.database.device.SystemPreferencesDevice;

public class SystemPreferencesManagement {
	private final static Logger logger = Logger.getLogger(SystemPreferencesManagement.class);

	public static String getBeroHigh() {
		String returnString = null;
		Connection conn = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SystemPreferencesDevice.getSelectBeroHigh());

			while (rs.next()) {
				returnString = rs.getString(1);
			}
			conn.commit();

		} catch (SQLException e) {
			logger.error("Could not get userrole (high)", e);
		} finally {
			ConnectionManager.close(rs, stmt, conn);
		}
		return returnString;
	}

	public static String getBeroMid() {
		String returnString = null;
		Connection conn = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SystemPreferencesDevice.getSelectBeroMid());

			while (rs.next()) {
				returnString = rs.getString(1);
			}
			conn.commit();

		} catch (SQLException e) {
			logger.error("Could not get userrole (mid)", e);
		} finally {
			ConnectionManager.close(rs, stmt, conn);
		}
		return returnString;
	}

	public static String getBeroLow() {
		String returnString = null;
		Connection conn = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SystemPreferencesDevice.getSelectBeroLow());

			while (rs.next()) {
				returnString = rs.getString(1);
			}
			conn.commit();

		} catch (SQLException e) {
			logger.error("Could not get userrole (low)", e);
		} finally {
			ConnectionManager.close(rs, stmt, conn);
		}
		return returnString;
	}

	public static void setBeroHigh(String value) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SystemPreferencesDevice.getSetBeroHigh());
			stmt.setString(1, value);
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			logger.error("Could not insert userrole (high)", e);
		} finally {
			ConnectionManager.close(null, stmt, conn);
		}
	}

	public static void setBeroMid(String value) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SystemPreferencesDevice.getSetBeroMid());
			stmt.setString(1, value);
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			logger.error("Could not insert userrole (mid)", e);
		} finally {
			ConnectionManager.close(null, stmt, conn);
		}
	}

	public static void setBeroLow(String value) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SystemPreferencesDevice.getSetBeroLow());
			stmt.setString(1, value);
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			logger.error("Could not insert userrole (low)", e);
		} finally {
			ConnectionManager.close(null, stmt, conn);
		}
	}
}
