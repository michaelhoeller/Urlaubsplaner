package com.itraccoon.database.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.itraccoon.database.ConnectionManager;
import com.itraccoon.database.device.AbsencesDevice;

public class AbsenceManagement {
	private final static Logger logger = Logger.getLogger(AbsenceManagement.class);

	public static void deleteAbsencessByUserId(Integer id) {

		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(AbsencesDevice.getDeleteAbsencesOfUser());
			stmt.setInt(1, id);

			stmt.execute();

			conn.commit();
		} catch (SQLException e) {
			logger.error("Couldnot delete Absences", e);
		} finally {
			ConnectionManager.close(null, stmt, conn);
		}
		logger.info("Absences of user deleted");
	}
}
