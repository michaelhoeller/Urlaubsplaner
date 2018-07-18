package com.itraccoon.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.itraccoon.database.ConnectionManager;
import com.itraccoon.database.device.HolidayDevice;

public class HolidayManagement {
	private final static Logger logger = Logger.getLogger(HolidayManagement.class);

	public static void deleteHolidaysByUserId(Integer id) {

		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(HolidayDevice.getDeleteHolidaysOfUser());
			stmt.setInt(1, id);

			stmt.execute();

			conn.commit();
		} catch (SQLException e) {
			logger.error("Couldnot delete Holidays", e);
		} finally {
			ConnectionManager.close(null, stmt, conn);
		}
		logger.info("Holidays of user deleted");
	}
}
