package com.itraccoon.constants;

import java.awt.Toolkit;

public class Constants {
    
    // System Properties
    public static String   SEPARATOR       = System.getProperty("file.separator");
    public static String   SYS_USERDIR     = System.getProperty("user.dir");
    public static String   SYS_USERHOME    = System.getProperty("user.home");
    public static String   SYS_TEMPDIR     = System.getProperty("java.io.tmpdir");
    
    // Toolkit
    public static Double   SCREENWIDTH     = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static Double   SCREENHEIGHT    = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    // File Locations
    public static String   MAINFOLDER      = SYS_USERHOME + SEPARATOR + "ITRaccoonUrlaubsplaner";
    public static String   USERFOLDER      = MAINFOLDER + SEPARATOR + "users";
    public static String   USERVERZEICHNIS = MAINFOLDER + SEPARATOR + "userdir.xml";
    public static String   USERFILES       = USERFOLDER + SEPARATOR;
    
    // Date
    public static String   DATEFORMAT      = "dd.MM.yyyy";
    
    // Tables
    public static String[] COLUMNNAMES     = { "Nutzer ID", "Name", "Urlaubstage", "Geplante Urlaubstage", "Freie Urlaubstage" };
}
