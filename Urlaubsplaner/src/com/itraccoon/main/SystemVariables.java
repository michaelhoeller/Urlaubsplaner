package com.itraccoon.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;

public class SystemVariables {
    
    private Logger logger = Logger.getLogger(SystemVariables.class);
    
    public SystemVariables(String[] args) {
        try {
            if (!args.equals(null)) {
                logger.info("Startup variables given:" + args.length);
                for (String string : args) {
                    if (string.equalsIgnoreCase(Constants.SYS_PAR_DEL_DATABASE)) {
                        deleteDatabase();
                    }
                    if (string.equalsIgnoreCase(Constants.SYS_PAR_REMOVE_LOCK)) {
                        deleteSystemLock();
                    }
                    if (string.equalsIgnoreCase(Constants.SYS_PAR_HELP)) {
                        printHelp();
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            logger.warn("No system start parameters set");
        }
    }
    
    private void deleteDatabase() {
        try {
            FileUtils.deleteDirectory(new File(Constants.DATABANK_FOLDER));
            FileUtils.forceDelete(new File(Constants.DATABANK_LOG));
        }
        catch (IOException e) {
            logger.error("Could not delete databank struckture", e);
        }
        logger.info("Databank deleted");
    }
    
    private void deleteSystemLock() {
        try {
            FileUtils.forceDelete(new File(Constants.SYSTEMLOCK));
            logger.info("System lock removed");
        }
        catch (FileNotFoundException e) {
            logger.info("No lock to remove was found");
        }
        catch (IOException e) {
            logger.error("Could not remove system lock", e);
        }
    }
    
    private void printHelp() {
        logger.info("--------------------------------------------");
        logger.info("Commands for system start:");
        logger.info(">-help<            this message");
        logger.info(">-rmdb<            deletes the databank");
        logger.info(">-rmlock<          removes any system lock");
        logger.info("--------------------------------------------");
    }
    
}
