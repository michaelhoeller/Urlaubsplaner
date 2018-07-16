package com.itraccoon.util;

import java.awt.event.WindowAdapter;

import javax.swing.JFrame;

import com.itraccoon.constants.Constants;
import com.itraccoon.database.Shut;
import com.itraccoon.gui.dialogue.ClosingDialogue;

public class WindowUtils {
    
    /**
     * <p>
     * Sets size, title, layout, location, default closing operation and adds widow closing listener
     * </p>
     * 
     * @param frame
     *            frame to edit
     * @param title
     *            desired title
     * @param size
     *            Use frameSize enum
     * @param ableToShutdown
     *            true when the x button is allowed to shutdown the program
     */
    public static void setWindowProperties(JFrame frame, String title, Integer size, boolean ableToShutdown) {
        
        if (size == frameSize.FULL_SCREEN.size()) {
            frame.setSize((Constants.SCREENWIDTH / size) - 50, (Constants.SCREENHEIGHT / size) - 50);
        }
        else {
            frame.setSize(Constants.SCREENWIDTH / size, Constants.SCREENHEIGHT / size);
        }
        frame.setTitle(title);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        if (ableToShutdown) {
            frame.addWindowListener(new WindowAdapter() {
                
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    if (new ClosingDialogue().getAnswer() == true) {
                        Shut.down();
                    }
                }
            });
        }
    }
    
}
