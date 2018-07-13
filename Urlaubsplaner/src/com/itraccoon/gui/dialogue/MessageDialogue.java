package com.itraccoon.gui.dialogue;

import javax.swing.JOptionPane;

public class MessageDialogue {
    
    public MessageDialogue(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
