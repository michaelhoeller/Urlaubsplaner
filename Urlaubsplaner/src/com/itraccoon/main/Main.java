package com.itraccoon.main;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;
import com.itraccoon.gui.MainWindow;

public class Main {
    
    static Logger logger;
    
    public static void main(String[] args) throws IOException {
        // Utils.deleteFileStructure();
        
        System.setProperty("logfileLocation", Constants.LOGFILE_LOCATION);
        System.setProperty("derby.system.home", Constants.DATABASE_LOCATION);
        
        logger = Logger.getLogger(Main.class);
        
        logger.info("Start of program");
        
        SystemBoot.getInstance();
        
        // Main program
        new MainWindow();
        // TesterClass.printAllLogins();
        
        // HashMap<Integer, String> test = UserRoleManagement.getAllUserroles();
        //
        // System.out.println(test.toString());
        //
        // UserRoleManagement.insertIntoUserRole("New Role");
        // UserRoleManagement.insertIntoUserRole("Second new Role123123\\\\");
        // UserRoleManagement.updateUserRole(1, "Nix Arzt mehr");
        //
        // HashMap<Integer, String> test2 = UserRoleManagement.getAllUserroles();
        // test2 = UserRoleManagement.getAllUserroles();
        // System.out.println(test2.toString());
        // Shut.down();
        logger.info("End of program");
        // System.out.println(CreationDevice.getCreateBeroTable() + ";");
        // System.out.println(CreationDevice.getCreateUserTable()+ ";");
        // System.out.println(CreationDevice.getCreateAbsenceTypeTable()+ ";");
        // System.out.println(CreationDevice.getCreateHolidayTable()+ ";");
        // System.out.println(CreationDevice.getCreateAbsenceTable()+ ";");
        // System.out.println(CreationDevice.getCreateLoginTable()+ ";");
    }
    
}
