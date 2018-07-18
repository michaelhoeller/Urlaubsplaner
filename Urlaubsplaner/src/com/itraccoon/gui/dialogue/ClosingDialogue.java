package com.itraccoon.gui.dialogue;

import javax.swing.JOptionPane;

public class ClosingDialogue {

	private boolean answer;

	public ClosingDialogue() {
		setAnswer(askForClosing());
	}

	private boolean askForClosing() {
		return JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
	}

	public boolean getAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
}
