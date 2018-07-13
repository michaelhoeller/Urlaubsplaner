package com.itraccoon.gui.dialogue;

import javax.swing.JOptionPane;

public class QuestionDialogue {
    
    private boolean answer;
    
    public QuestionDialogue(String title, String question) {
        setAnswer(askForClosing(title, question));
    }
    
    private boolean askForClosing(String title, String question) {
        return JOptionPane.showConfirmDialog(null, question, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }
    
    public boolean getAnswer() {
        return answer;
    }
    
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
