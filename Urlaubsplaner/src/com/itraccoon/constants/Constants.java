package com.itraccoon.constants;

import java.awt.Toolkit;

public class Constants {

	// System Properties
	public static final String SEPARATOR = System.getProperty("file.separator");
	public static final String SYS_USERDIR = System.getProperty("user.dir");
	public static final String SYS_USERHOME = System.getProperty("user.home");
	public static final String SYS_TEMPDIR = System.getProperty("java.io.tmpdir");
	public static final String DATABASE_HOME = System.getProperty("derby.system.home");

	public static final String SYSTEMLOCK = Constants.SYS_TEMPDIR + Constants.SEPARATOR + "SystemLock";

	// Toolkit
	public static final Integer SCREENWIDTH = ((Double) Toolkit.getDefaultToolkit().getScreenSize().getWidth()).intValue();
	public static final Integer SCREENHEIGHT = ((Double) Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue();

	// Database locations
	/**
	 * C:/Users/username/ITRacconUrlaubsplaner
	 */
	public static final String MAINFOLDER = SYS_USERHOME + SEPARATOR + "ITRaccoonUrlaubsplaner";

	// Databank url

	public static final String DATABANK_URL = "jdbc:derby:Urlaubsplaner";
	/**
	 * C:/Users/username/ITRacconUrlaubsplaner/...
	 */
	public static final String DATABASE_LOCATION = MAINFOLDER + SEPARATOR;

	// Export/Import location
	public static final String EXPORT_LOCATION = SYS_USERHOME + SEPARATOR + "Desktop" + SEPARATOR
			+ "Urlaubsplaner_Export.xml";

	// Logfile location
	/**
	 * C:/Users/username/ITRacconUrlaubsplaner/Logfile.log
	 */
	public static final String LOGFILE_LOCATION = DATABASE_LOCATION + "Logfile.log";

	// Date
	public static final String DATEFORMAT = "dd.MM.yyyy";

	// Initial Bero Values
	public static final String DEFAULT_ROLE = "NO ROLE SPECIFIED";
	public static final String DEFAULT_ROLE_1 = "Arzt";
	public static final String DEFAULT_ROLE_2 = "Rezeption";
	public static final String DEFAULT_ROLE_3 = "Assistenz";

	// Tables
	// TODO: Adjust
	public static final String[] USER_COLUMNNAMES = { "Name", "Rolle", "Verfügbare Urlaubstage", "Geplante Urlaubstage", "Urlaubstage pro Jahr" };
	public static final String[] CALENDAR_COLUMNAMES = { "User name", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", };
}
