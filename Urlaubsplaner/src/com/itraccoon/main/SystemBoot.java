package com.itraccoon.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;
import com.itraccoon.gui.dialogue.MessageDialogue;
import com.itraccoon.util.Utils;

public class SystemBoot {
	List<String> messeges = new ArrayList<String>();

	Logger logger = Logger.getLogger(SystemBoot.class);

	private static SystemBoot instance;

	private SystemBoot() {
		logger.info("Preparing SystemBoot");
		checkFileStruckture();
		initializeSysProps();
		checkForData();
		// checkNewYear();
	}

	public static SystemBoot getInstance() {
		if (instance == null) {
			instance = new SystemBoot();
		}
		return instance;
	}

	private void initializeSysProps() {
		System.setProperty("logfileLocation", Constants.LOGFILE_LOCATION);
		System.setProperty(Constants.DATABASE_HOME, Constants.DATABASE_LOCATION);

	}

	private void checkFileStruckture() {
		// Check for Folders. Create it if it doesn't exist
		if (!new File(Constants.MAINFOLDER).exists()) {
			new File(Constants.MAINFOLDER).mkdirs();
			logger.info("No system folder found. Creation successful");
			messeges.add("No system folder found. Creation successful");
		}
	}

	private void checkForData() {
		// If no File exists. Set id to 1 and wait for creation.
		if (!new File(Constants.EXPORT_LOCATION).exists()) {
			new MessageDialogue("Bitte Benutzer anlegen", "Keine Nutzer gefunden");
			Runtime.getInstance().setNextUserId(1);
		} else {
			// loadUserList();
			Runtime.getInstance().setNextUserId(Utils.getHighestUserId() + 1);
		}
	}

	private void checkNewYear() {
		// TODO Auto-generated method stub
	}
}
