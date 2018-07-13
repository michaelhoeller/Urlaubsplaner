package com.itraccoon.util;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.itraccoon.constants.Constants;
import com.itraccoon.filehandling.ExportUserlist;
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
    
    // System exit
    public static void SystemExit() {
        if (Runtime.getInstance().isDataThere()) {
            new ExportUserlist();
        }
        else {
            new MessageDialogue("No data to save.", "Warning");
        }
        System.exit(0);
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
    
    // User full name
    public static String getFullName(User user) {
        if (user.getName() == null || user.getLastName() == null) {
            return "No data available";
        }
        return user.getName() + " " + user.getLastName();
    }
    
    // User remaining days
    public static String getRemainingDaysAsString(User user) {
        return getRemainingDays(user).toString();
    }
    
    public static Integer getRemainingDays(User user) {
        return user.getDaysAvailable() - user.getDaysSpent();
    }
    
    // Local Date to Date
    public static Date localToDate(LocalDate ld) {
        return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
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
    
    // Window size
    public static Integer getQuarterWidth() {
        return (int) (Constants.SCREENWIDTH / 4);
    }
    
    public static Integer getHalfWidth() {
        return (int) (Constants.SCREENWIDTH / 2);
    }
    
    public static Integer getQuarterHeight() {
        return (int) (Constants.SCREENWIDTH / 4);
    }
    
    public static Integer getHalfHeight() {
        return (int) (Constants.SCREENHEIGHT / 2);
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
