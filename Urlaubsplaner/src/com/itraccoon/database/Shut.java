package com.itraccoon.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.itraccoon.gui.dialogue.MessageDialogue;

public class Shut {

	private final static Logger logger = Logger.getLogger(Shut.class);
	private final static Integer SQL_ERRORCODE = 45000;
	private final static String SQL_STATE_ERROR = "08006";

	/**
	 * Last method in programm that should be called
	 */
	public static void down() {
		logger.info("Starting shutdown process");
		Connection conn = null;
		try {
			conn = ConnectionManager.getInstance().getShutdownConnection();
		} catch (SQLException e) {
			if (((e.getErrorCode() == SQL_ERRORCODE) && (SQL_STATE_ERROR.equals(e.getSQLState())))) {
				logger.info("Databank shut down normally");
			} else {
				logger.warn("Databank did not shut down normally", e);
				new MessageDialogue("Databank did not shut down normally", "Error");
			}
		} finally {
			ConnectionManager.close(null, null, conn);
		}

		logger.info("Shutdown complete");
		logger.info("------------------------------------------------------------------------------------");
		System.exit(0);
	}
}
