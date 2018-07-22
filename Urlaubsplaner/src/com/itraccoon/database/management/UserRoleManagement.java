package com.itraccoon.database.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;
import com.itraccoon.database.ConnectionManager;
import com.itraccoon.database.device.UserRoleDevice;
import com.itraccoon.exceptions.DefaultUserRoleException;
import com.itraccoon.util.Utils;

public class UserRoleManagement {

	private final static Logger logger = Logger.getLogger(UserRoleManagement.class);

	public static String getUsroByUsroId(Integer usroid) {
		String returnString = null;
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(UserRoleDevice.getSelectUserRoleById());
			stmt.setInt(1, usroid);
			rs = stmt.executeQuery();

			while (rs.next()) {
				returnString = rs.getString(1);
				System.out.println(returnString);
			}
			conn.commit();

		} catch (SQLException e) {
			logger.error("Could not get userrole by id: " + usroid, e);
		} finally {
			ConnectionManager.close(rs, stmt, conn);
		}
		logger.info("Userrole " + returnString + " with id " + usroid + " successfully retrived");
		return returnString;
	}

	public static void insertIntoUserRole(String userrole) {

		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(UserRoleDevice.getInsertIntoUserRole());
			stmt.setString(1, userrole);
			stmt.execute();

			conn.commit();
		} catch (SQLException e) {
			logger.error("Could not insert: " + userrole, e);
		} finally {
			ConnectionManager.close(null, stmt, conn);
		}
		logger.info("Userrole " + userrole + " successfully inserted");
	}

	public static void updateUserRole(Integer id, String newUserRole) {

		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(UserRoleDevice.getModifiyUserRoleById());
			stmt.setString(1, newUserRole);
			stmt.setInt(2, id);
			stmt.execute();

			conn.commit();
		} catch (SQLException e) {
			logger.error("Could not update userrole with id: " + id, e);
		} finally {
			ConnectionManager.close(null, stmt, conn);
		}
		logger.info("Userrole with id " + id + " changed to " + newUserRole);
	}

	public static HashMap<Integer, String> getAllUserroles() {
		HashMap<Integer, String> returnMap = new HashMap<>();
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(UserRoleDevice.getAllRoles());
			rs = stmt.executeQuery();

			while (rs.next()) {
				returnMap.put(rs.getInt("USRO_ID"), rs.getString("USRO_ROLE"));
			}
			conn.commit();

		} catch (SQLException e) {
			logger.error("Could not get userroles", e);
		} finally {
			ConnectionManager.close(rs, stmt, conn);
		}
		logger.info("All userroles retrived");
		return returnMap;
	}

	public static Integer getDefaultUserRole() throws DefaultUserRoleException {
		HashMap<Integer, String> allRoles = getAllUserroles();
		Integer defaultUserRoleId = Utils.getKeyFromValue(allRoles, Constants.DEFAULT_ROLE);

		if (defaultUserRoleId == null) {
			throw new DefaultUserRoleException();
		}
		return defaultUserRoleId;
	}

}
