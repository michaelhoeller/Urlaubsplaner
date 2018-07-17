package com.itraccoon.main;

import java.text.ParseException;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;
import com.itraccoon.gui.MainWindow;
import com.itraccoon.test.TesterClass;

public class Main {
    
    static Logger logger;
    
    public static void main(String[] args) throws ParseException {
        System.setProperty("logfileLocation", Constants.LOGFILE_LOCATION);
        System.setProperty("derby.system.home", Constants.DATABASE_LOCATION);
        
        logger = Logger.getLogger(Main.class);
        
        logger.info("System start");
        SystemBoot.getInstance();
        
        // Main program
        new MainWindow();
        TesterClass.printAllLogins();
        
    }
    
}
