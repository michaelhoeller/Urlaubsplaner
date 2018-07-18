package com.itraccoon.main;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;
import com.itraccoon.management.SystemPreferencesManagement;
import com.itraccoon.util.Utils;

public class Main {

	static Logger logger;

	public static void main(String[] args) throws SQLException, IOException {
		Utils.deleteFileStructure();

		System.setProperty("logfileLocation", Constants.LOGFILE_LOCATION);
		System.setProperty("derby.system.home", Constants.DATABASE_LOCATION);

		logger = Logger.getLogger(Main.class);

		logger.info("System start");
		SystemBoot.getInstance();

		// Main program
		// new MainWindow();
		// TesterClass.printAllLogins();

		System.out.println(SystemPreferencesManagement.getBeroHigh());
		System.out.println(SystemPreferencesManagement.getBeroMid());
		System.out.println(SystemPreferencesManagement.getBeroLow());
		System.out.println("Setting new Values. Print out next");
		SystemPreferencesManagement.setBeroHigh("New High Value");
		SystemPreferencesManagement.setBeroMid("New Bero mid");
		SystemPreferencesManagement.setBeroLow("New Bero low");
		System.out.println(SystemPreferencesManagement.getBeroHigh());
		System.out.println(SystemPreferencesManagement.getBeroMid());
		System.out.println(SystemPreferencesManagement.getBeroLow());
	}

}
