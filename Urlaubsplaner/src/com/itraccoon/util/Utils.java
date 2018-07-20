package com.itraccoon.util;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.itraccoon.constants.Constants;
import com.itraccoon.gui.dialogue.MessageDialogue;
import com.itraccoon.main.Runtime;
import com.itraccoon.object.Holiday;
import com.itraccoon.object.User;

public class Utils {
    
    // Clipboard
    public static String getClipboard() throws HeadlessException, UnsupportedFlavorException, IOException {
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }
    
    public static void setClipboard(String contents) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(contents), new StringSelection(contents));
    }
    
    // Id
    public static Integer getHighestUserId() {
        Integer highestId = 0;
        for (User user : Runtime.getInstance().getUserlist()) {
            if (user.getId() > highestId) {
                highestId = user.getId();
            }
        }
        return highestId;
    }
    
    // Get Key from Value
    public static Integer getKeyFromValue(Map<Integer, String> hm, Object value) {
        for (Integer o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
    
    // Local Date to Date
    public static Date localToDate(LocalDate ld) {
        return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public static void deleteFileStructure() {
        try {
            FileUtils.deleteDirectory(new File(Constants.MAINFOLDER));
        }
        catch (IOException e) {
            System.out.println("Couldn't delete main folder");
        }
    }
    
    // Calculate Holiday length
    // To Do:
    public static Integer calculateLength(Holiday holiday) {
        int length = 0;
        long diff = holiday.getEnd().getTime() - holiday.getStart().getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        length = (int) diffDays;
        return length;
    }
    
    // Todo: move to Database
    public static User findUserById(Integer id) {
        List<User> userList = Runtime.getInstance().getUserlist();
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        new MessageDialogue("User with id " + id + " not found!", "Error");
        return null;
    }
    
}
