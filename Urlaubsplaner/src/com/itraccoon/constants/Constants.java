package com.itraccoon.constants;

import java.awt.Toolkit;

public class Constants {

	// System Properties
	public static String SEPARATOR = System.getProperty("file.separator");
	public static String SYS_USERDIR = System.getProperty("user.dir");
	public static String SYS_USERHOME = System.getProperty("user.home");
	public static String SYS_TEMPDIR = System.getProperty("java.io.tmpdir");
	public static String DATABASE_HOME = System.getProperty("derby.system.home");

	// Toolkit
	public static Double SCREENWIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static Double SCREENHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	// Database locations
	public static String MAINFOLDER = SYS_USERHOME + SEPARATOR + "ITRaccoonUrlaubsplaner";
	public static String DATABASE_LOCATION = MAINFOLDER + SEPARATOR;

	// Export/Import location
	public static String EXPORT_LOCATION = SYS_USERHOME + SEPARATOR + "Desktop" + SEPARATOR
			+ "Urlaubsplaner_Export.xml";

	// Logfile location
	public static String LOGFILE_LOCATION = DATABASE_LOCATION + "Logfile.log";

	// Date
	public static String DATEFORMAT = "dd.MM.yyyy";

	// Tables
	// TODO: Adjust
	public static String[] COLUMNNAMES = { "Nutzer ID", "Name", "Urlaubstage", "Geplante Urlaubstage",
			"Freie Urlaubstage" };
}
