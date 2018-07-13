package com.itraccoon.main;

import java.io.File;

import com.itraccoon.constants.Constants;
import com.itraccoon.filehandling.ImportUserlist;
import com.itraccoon.gui.dialogue.MessageDialogue;
import com.itraccoon.util.Utils;

public class SystemBoot {
    
    private static SystemBoot instance;
    
    private SystemBoot() {
        checkFileStruckture();
        checkForData();
        checkNewYear();
    }
    
    private void checkNewYear() {
        // TODO Auto-generated method stub
    }
    
    public static SystemBoot getInstance() {
        if (instance == null) {
            instance = new SystemBoot();
        }
        return instance;
    }
    
    private void checkFileStruckture() {
        // Check for Folders. Create it if it doesn't exist
        if (!new File(Constants.MAINFOLDER).exists()) {
            new MessageDialogue("Application Folder wurde unter " + Constants.SYS_USERHOME + " angelegt.\nUm Datenverlust zu vermeiden: Finger weg!", "Programverzeichnis nicht gefunden.");
            new File(Constants.MAINFOLDER).mkdirs();
        }
    }
    
    private void checkForData() {
        // If no File exists. Set id to 1 and wait for creation.
        if (!new File(Constants.EXPORT_LOCATION).exists()) {
            new MessageDialogue("Bitte Benutzer anlegen", "Keine Nutzer gefunden");
            Runtime.getInstance().setNextUserId(1);
        }
        else {
            loadUserList();
            Runtime.getInstance().setNextUserId(Utils.getHighestUserId() + 1);
        }
    }
    
    private void loadUserList() {
        new ImportUserlist();
    }
}
