package com.itraccoon.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.itraccoon.database.ConnectionManager;
import com.itraccoon.database.device.UserDevice;

public class UserManagement {

	private final static Logger logger = Logger.getLogger(UserManagement.class);

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
		} catch (SQLException e) {
			logger.error("Could not delete User", e);
		} finally {
			ConnectionManager.close(null, stmt, conn);
		}
		logger.info("User successfully deleted");
	}
}
