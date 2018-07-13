package com.itraccoon.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Iterator;
import java.util.List;

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

public class ChangeUserWindow {
    
    private MainWindow       parentWindow;
    private ChangeUserWindow thisWindow;
    private JFrame           frame;
    private JButton          btnSave;
    private JButton          btnBack;
    private JTextField       nameInput;
    private JTextField       lastNameInput;
    private JLabel           name;
    private JLabel           lastName;
    private JLabel           days;
    private JSpinner         daysSpinner;
    private User             user;
    private JButton          btnDelete;
    private JLabel           lblBereitsGebucht;
    private JLabel           lblVerfgbar;
    private JLabel           lblSpentDays;
    private JLabel           lblFreeDays;
    private JButton          btnNewHoliday;
    private JLabel           lblUrlaubstageProJahr;
    private JSpinner         spinner;
    
    public ChangeUserWindow(MainWindow parentWindow, User user) {
        this.setParentWindow(parentWindow);
        this.setThisWindow(this);
        this.user = user;
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
        frame.setTitle("Mitarbeiter " + user.getFullName() + " ändern");
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
        frame.getContentPane().add(getBtnDelete());
        frame.getContentPane().add(getLblBereitsGebucht());
        frame.getContentPane().add(getLblVerfgbar());
        frame.getContentPane().add(getLblSpentDays());
        frame.getContentPane().add(getLblFreeDays());
        frame.getContentPane().add(getBtnNewHoliday());
        frame.getContentPane().add(getLblUrlaubstageProJahr());
        frame.getContentPane().add(getSpinnerPerYear());
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
            nameInput.setText(user.getName());
        }
        return nameInput;
    }
    
    protected JTextField getLastNameInput() {
        if (lastNameInput == null) {
            lastNameInput = new JTextField();
            lastNameInput.setColumns(10);
            lastNameInput.setBounds(204, 103, 250, 25);
            lastNameInput.setText(user.getLastName());
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
            days = new JLabel("Urlaubstage verf\u00FCgbar");
            days.setBounds(10, 144, 196, 25);
        }
        return days;
    }
    
    JSpinner getDaysSpinner() {
        if (daysSpinner == null) {
            daysSpinner = new JSpinner();
            daysSpinner.setModel(new SpinnerNumberModel(20, 20, 40, 1));
            daysSpinner.setBounds(404, 144, 50, 25);
            daysSpinner.setValue(user.getDaysAvailable());
        }
        return daysSpinner;
    }
    
    private class BtnBackActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            getParentWindow().getFrame().setEnabled(true);
            frame.dispose();
        }
    }
    
    private class BtnSaveActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            saveOldUser(getNameInput().getText(), getLastNameInput().getText(), ((Integer) getDaysSpinner().getValue()), ((Integer) getSpinnerPerYear().getValue()), user.getId());
            getParentWindow().reloadTable(getParentWindow().getUserTableModel());
            getParentWindow().getFrame().setEnabled(true);
            frame.dispose();
        }
    }
    
    private class BtnDeleteActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            deleteUserById(user.getId());
            getParentWindow().reloadTable(getParentWindow().getUserTableModel());
            getParentWindow().getFrame().setEnabled(true);
            frame.dispose();
        }
    }
    
    private class BtnNewHolidayActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new CreateHolidayWindow(parentWindow, user);
        }
    }
    
    private JButton getBtnDelete() {
        if (btnDelete == null) {
            btnDelete = new JButton("L\u00F6schen");
            btnDelete.addActionListener(new BtnDeleteActionListener());
            btnDelete.setBounds(10, 406, 120, 25);
        }
        return btnDelete;
    }
    
    private JLabel getLblBereitsGebucht() {
        if (lblBereitsGebucht == null) {
            lblBereitsGebucht = new JLabel("Bereits gebucht");
            lblBereitsGebucht.setBounds(10, 216, 100, 25);
        }
        return lblBereitsGebucht;
    }
    
    private JLabel getLblVerfgbar() {
        if (lblVerfgbar == null) {
            lblVerfgbar = new JLabel("Verf\u00FCgbar");
            lblVerfgbar.setBounds(10, 252, 100, 25);
        }
        return lblVerfgbar;
    }
    
    private JLabel getLblSpentDays() {
        if (lblSpentDays == null) {
            lblSpentDays = new JLabel("");
            lblSpentDays.setBounds(204, 216, 250, 25);
            lblSpentDays.setText(user.getDaysSpent().toString());
        }
        return lblSpentDays;
    }
    
    private JLabel getLblFreeDays() {
        if (lblFreeDays == null) {
            lblFreeDays = new JLabel("");
            lblFreeDays.setBounds(204, 252, 250, 25);
            lblFreeDays.setText(((Integer) (user.getDaysAvailable() - user.getDaysSpent())).toString());
        }
        return lblFreeDays;
    }
    
    private JButton getBtnNewHoliday() {
        if (btnNewHoliday == null) {
            btnNewHoliday = new JButton("Urlaub beantragen");
            btnNewHoliday.addActionListener(new BtnNewHolidayActionListener());
            btnNewHoliday.setBounds(204, 369, 250, 25);
        }
        return btnNewHoliday;
    }
    
    public ChangeUserWindow getThisWindow() {
        return thisWindow;
    }
    
    public void setThisWindow(ChangeUserWindow thisWindow) {
        this.thisWindow = thisWindow;
    }
    
    // Logic
    public void saveOldUser(String name, String lastName, int daysAvailable, int daysPerYear, int userId) {
        List<User> userList = Runtime.getInstance().getUserlist();
        deleteUserById(userId);
        User newUser = new User(name, lastName, daysAvailable, daysPerYear);
        newUser.setId(Runtime.getInstance().getNextUserId());
        userList.add(newUser);
    }
    
    public void deleteUserById(int id) {
        List<User> userList = Runtime.getInstance().getUserlist();
        for (Iterator<User> iter = userList.listIterator(); iter.hasNext();) {
            User a = iter.next();
            if (a.getId() == id) {
                iter.remove();
            }
        }
    }
    
    private JLabel getLblUrlaubstageProJahr() {
        if (lblUrlaubstageProJahr == null) {
            lblUrlaubstageProJahr = new JLabel("Urlaubstage pro Jahr");
            lblUrlaubstageProJahr.setBounds(10, 180, 196, 25);
        }
        return lblUrlaubstageProJahr;
    }
    
    private JSpinner getSpinnerPerYear() {
        if (spinner == null) {
            spinner = new JSpinner();
            spinner.setModel(new SpinnerNumberModel(20, 20, 40, 1));
            spinner.setBounds(404, 182, 50, 25);
            spinner.setValue(user.getDaysPerYear());
        }
        return spinner;
    }
}
