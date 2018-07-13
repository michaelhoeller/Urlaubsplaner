package com.itraccoon.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.itraccoon.gui.dialogue.ClosingDialogue;
import com.itraccoon.main.Runtime;
import com.itraccoon.object.User;
import com.itraccoon.util.Utils;

public class CreateUserWindow {
    
    private MainWindow parentWindow;
    private JFrame     frame;
    private JButton    btnSave;
    private JButton    btnBack;
    private JTextField nameInput;
    private JTextField lastNameInput;
    private JLabel     name;
    private JLabel     lastName;
    private JLabel     days;
    private JSpinner   daysSpinner;
    private JLabel     lblUrlaubstageProJahr;
    private JSpinner   daysPerYearSpinner;
    
    public CreateUserWindow(MainWindow parentWindow) {
        this.setParentWindow(parentWindow);
        initialize();
        frame.setVisible(true);
    }
    
    public MainWindow getParentWindow() {
        return parentWindow;
    }
    
    public void setParentWindow(MainWindow parentWindow) {
        this.parentWindow = parentWindow;
    }
    
    private void initialize() {
        getParentWindow().getFrame().setEnabled(false);
        frame = new JFrame();
        frame.setTitle("Mitarbeiter anlegen");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(Utils.getQuarterWidth(), Utils.getQuarterHeight());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(getBtnSave());
        frame.getContentPane().add(getBtnBack());
        frame.getContentPane().add(getNameInput());
        frame.getContentPane().add(getLastNameInput());
        frame.getContentPane().add(getDaysSpinner());
        frame.getContentPane().add(getName());
        frame.getContentPane().add(getLastName());
        frame.getContentPane().add(getDays());
        frame.getContentPane().add(getLblUrlaubstageProJahr());
        frame.getContentPane().add(getDaysPerYearSpinner());
        frame.addWindowListener(new WindowAdapter() {
            
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (new ClosingDialogue().getAnswer() == true) {
                    getParentWindow().getFrame().setEnabled(true);
                    frame.dispose();
                }
            }
        });
    }
    
    private JButton getBtnSave() {
        if (btnSave == null) {
            btnSave = new JButton("Speichern");
            btnSave.addActionListener(new BtnSaveActionListener());
            btnSave.setBounds(334, 405, 120, 25);
        }
        return btnSave;
    }
    
    private JButton getBtnBack() {
        if (btnBack == null) {
            btnBack = new JButton("Zur\u00FCck");
            btnBack.addActionListener(new BtnBackActionListener());
            btnBack.setBounds(204, 405, 120, 25);
        }
        return btnBack;
    }
    
    protected JTextField getNameInput() {
        if (nameInput == null) {
            nameInput = new JTextField();
            nameInput.setBounds(204, 67, 250, 25);
            nameInput.setColumns(10);
        }
        return nameInput;
    }
    
    protected JTextField getLastNameInput() {
        if (lastNameInput == null) {
            lastNameInput = new JTextField();
            lastNameInput.setColumns(10);
            lastNameInput.setBounds(204, 103, 250, 25);
        }
        return lastNameInput;
    }
    
    private JLabel getName() {
        if (name == null) {
            name = new JLabel("Vorname");
            name.setBounds(10, 67, 100, 25);
        }
        return name;
    }
    
    private JLabel getLastName() {
        if (lastName == null) {
            lastName = new JLabel("Nachname");
            lastName.setBounds(10, 108, 100, 25);
        }
        return lastName;
    }
    
    private JLabel getDays() {
        if (days == null) {
            days = new JLabel("Urlaubstage dieses Jahr");
            days.setBounds(10, 144, 200, 25);
        }
        return days;
    }
    
    private class BtnBackActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            getParentWindow().getFrame().setEnabled(true);
            frame.dispose();
        }
    }
    
    private class BtnSaveActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            saveNewUser();
            getParentWindow().reloadTable(getParentWindow().getUserTableModel());
            resetInputs(getDaysSpinner(), getDaysPerYearSpinner(), getNameInput(), getLastNameInput());
        }
    }
    
    private JLabel getLblUrlaubstageProJahr() {
        if (lblUrlaubstageProJahr == null) {
            lblUrlaubstageProJahr = new JLabel("Urlaubstage pro Jahr");
            lblUrlaubstageProJahr.setBounds(10, 180, 200, 25);
        }
        return lblUrlaubstageProJahr;
    }
    
    JSpinner getDaysSpinner() {
        if (daysSpinner == null) {
            daysSpinner = new JSpinner();
            daysSpinner.setModel(new SpinnerNumberModel(20, 20, 40, 1));
            daysSpinner.setBounds(404, 144, 50, 25);
        }
        return daysSpinner;
    }
    
    private JSpinner getDaysPerYearSpinner() {
        if (daysPerYearSpinner == null) {
            daysPerYearSpinner = new JSpinner();
            daysPerYearSpinner.setModel(new SpinnerNumberModel(20, 20, 40, 1));
            daysPerYearSpinner.setBounds(404, 180, 50, 25);
        }
        return daysPerYearSpinner;
    }
    // Logic
    
    void saveNewUser() {
        Integer daysAv = (Integer) daysSpinner.getValue();
        Integer daysPerYear = (Integer) daysPerYearSpinner.getValue();
        
        User newUser = new User(nameInput.getText(), lastNameInput.getText(), daysAv, daysPerYear);
        Runtime.getInstance().addUserToList(newUser);
    }
    
    public void resetInputs(JSpinner jSpinner, JSpinner jSpinner2, JTextField jTextField, JTextField jTextField2) {
        jSpinner.setValue(20);
        jSpinner2.setValue(20);
        jTextField.setText("");
        jTextField2.setText("");
    }
    
}
