package com.itraccoon.gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

import org.apache.log4j.Logger;

import com.itraccoon.constants.Constants;
import com.itraccoon.database.management.UserManagement;
import com.itraccoon.database.management.UserRoleManagement;
import com.itraccoon.exceptions.SpecificUserRoleException;
import com.itraccoon.object.User;
import com.itraccoon.util.Utils;
import com.itraccoon.util.WindowUtils;
import com.itraccoon.util.frameSize;

public class CreateUserWindow {
    
    private final static Logger logger = Logger.getLogger(CreateUserWindow.class);
    
    private MainWindow          parentFrame;
    private JFrame              frame;
    
    public JFrame getFrame() {
        return frame;
    }
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
    public MainWindow getParentFrame() {
        return parentFrame;
    }
    
    public void setParentFrame(MainWindow parentFrame) {
        this.parentFrame = parentFrame;
    }
    
    // VARS
    private JLabel     lblUsername;
    private JTextField txtUsernameInput;
    private JSpinner   spinnerDaysPerYear;
    private JSpinner   spinnerUserRole;
    private JLabel     lblFreeDaysPer;
    private JLabel     lblUserRole;
    private JButton    btnSave;
    
    public CreateUserWindow(MainWindow parentFrame) {
        this.parentFrame = parentFrame;
        
        initialize();
        frame.setVisible(true);
    }
    
    private void initialize() {
        parentFrame.getFrame().setEnabled(false);
        frame = new JFrame();
        
        WindowUtils.setWindowProperties(frame, "Urlaubsplaner - New User", frameSize.QUARTER_SCREEN.size(), false, getParentFrame().getFrame());
        
        frame.setSize(Constants.SCREENWIDTH / 4, Constants.SCREENHEIGHT / 4);
        
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(getLblUsername());
        frame.getContentPane().add(getLblFreeDaysPer());
        frame.getContentPane().add(getLblUserRole());
        frame.getContentPane().add(getTxtUsernameInput());
        frame.getContentPane().add(getSpinnerDaysPerYear());
        frame.getContentPane().add(getSpinnerUserRole());
        frame.getContentPane().add(getBtnSave());
    }
    
    private JLabel getLblUsername() {
        if (lblUsername == null) {
            lblUsername = new JLabel("Username");
            lblUsername.setBounds(10, 11, 100, 20);
        }
        return lblUsername;
    }
    
    private JLabel getLblFreeDaysPer() {
        if (lblFreeDaysPer == null) {
            lblFreeDaysPer = new JLabel("Free days per year");
            lblFreeDaysPer.setBounds(10, 42, 100, 20);
        }
        return lblFreeDaysPer;
    }
    
    private JLabel getLblUserRole() {
        if (lblUserRole == null) {
            lblUserRole = new JLabel("User role");
            lblUserRole.setBounds(10, 73, 100, 20);
        }
        return lblUserRole;
    }
    
    private JTextField getTxtUsernameInput() {
        if (txtUsernameInput == null) {
            txtUsernameInput = new JTextField();
            txtUsernameInput.setBounds(150, 11, 304, 20);
            txtUsernameInput.setColumns(10);
        }
        return txtUsernameInput;
    }
    
    private JSpinner getSpinnerDaysPerYear() {
        if (spinnerDaysPerYear == null) {
            spinnerDaysPerYear = new JSpinner();
            spinnerDaysPerYear.setModel(new SpinnerNumberModel(new Float(25), new Float(0), new Float(100), new Float(0.5)));
            spinnerDaysPerYear.setBounds(150, 42, 304, 20);
        }
        return spinnerDaysPerYear;
    }
    
    private JSpinner getSpinnerUserRole() {
        List<String> userRoles = Utils.getValuesAsArray(UserRoleManagement.getAllUserroles());
        if (spinnerUserRole == null) {
            spinnerUserRole = new JSpinner();
            spinnerUserRole.setModel(new SpinnerListModel(userRoles));
            spinnerUserRole.setBounds(150, 73, 304, 20);
        }
        return spinnerUserRole;
    }
    
    private JButton getBtnSave() {
        if (btnSave == null) {
            btnSave = new JButton("Save");
            btnSave.setBounds(304, 225, 150, 25);
            btnSave.addActionListener(e -> saveUser());
        }
        return btnSave;
    }
    
    private void saveUser() {
        Float daysPerYear = (Float) getSpinnerDaysPerYear().getValue();
        Integer selectedUsro = null;
        try {
            selectedUsro = UserRoleManagement.getSpecificUserRoleByValue(getSpinnerUserRole().getValue().toString());
            User userToSave = new User(getTxtUsernameInput().getText(), 0.0f, daysPerYear, daysPerYear, selectedUsro);
            UserManagement.insertNewUser(userToSave);
            logger.info("New user saved to databank");
            getParentFrame().reloadUserTable(getParentFrame().getUserTableModel());
            WindowUtils.backToParrentWindow(getFrame(), getParentFrame().getFrame());
        }
        catch (SpecificUserRoleException e) {
            logger.error("Could not get USRO id while trying to save a new user", e);
        }
    }
}
