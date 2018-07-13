package com.itraccoon.main;

import java.text.ParseException;

import javax.swing.JFrame;
import javax.xml.bind.JAXBException;

import com.itraccoon.gui.MainWindow;
import com.itraccoon.object.Holiday;
import com.itraccoon.object.User;

public class Main {
    
    public static void main(String[] args) throws JAXBException, ParseException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SystemBoot.getInstance();
        new MainWindow();
        for (User user : Runtime.getInstance().getUserlist()) {
            for (Holiday holi : user.getHolidays()) {
                System.out.println(holi.getStart());
                System.out.println(holi.getEnd());
                System.out.println(holi.getAmount());
                System.out.println("-------------------------");
            }
        }
    }
}
