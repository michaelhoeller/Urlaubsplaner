package com.itraccoon.gui;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itraccoon.constants.Constants;
import com.itraccoon.database.management.UserManagement;
import com.itraccoon.database.management.UserRoleManagement;
import com.itraccoon.object.User;
import com.itraccoon.util.WindowUtils;
import com.itraccoon.util.frameSize;

public class MainWindow {
    
    private static final int WIDTH_CORRECTOR  = 35;
    private static final int HEIGHT_CORRECTOR = 90;
    private JFrame           frame;
    
    public JFrame getFrame() {
        return frame;
    }
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
    private JPanel            calendarPanel;
    private JTable            userTable;
    private JTable            calTable;
    private DefaultTableModel userModel;
    private DefaultTableModel calModel;
    private JScrollPane       userScrollPane;
    private JScrollPane       calScrollPane;
    private JMenuItem         mntmNewUser;
    private JMenuItem         mntmChangeUser;
    private JMenuItem         mntmDeleteUser;
    
    public MainWindow() {
        initialize();
        frame.setVisible(true);
    }
    
    private void initialize() {
        frame = new JFrame();
        setFrame(frame);
        
        WindowUtils.setWindowProperties(frame, "Urlaubsplaner - Übersicht", frameSize.FULL_SCREEN.size(), true, null);
        
        // Test impl
        // TODO: remove me
        frame.setSize((Constants.SCREENWIDTH / 1) - 50, (Constants.SCREENHEIGHT / 1) - 50);
        
        // Components
        frame.getContentPane().add(getUserScrollPane());
        frame.getContentPane().add(getCalendarPanel());
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("User Management");
        menuBar.add(mnNewMenu);
        
        mnNewMenu.add(getMntmNewUser());
        mnNewMenu.add(getMntmChangeUser());
        mnNewMenu.add(getMntmDeleteUser());
        
        // Window rezize listenter
        frame.addComponentListener(new ComponentAdapter() {
            
            public void componentResized(ComponentEvent evt) {
                refresh();
            }
        });
    }
    
    protected void refresh() {
        getUserScrollPane().setSize(getFrame().getSize().width - WIDTH_CORRECTOR, 200);
        getCalendarPanel().setBounds(10, 220, getFrame().getSize().width - WIDTH_CORRECTOR, getFrame().getSize().height - getUserScrollPane().getSize().height - HEIGHT_CORRECTOR);
    }
    
    private JPanel getCalendarPanel() {
        if (calendarPanel == null) {
            calendarPanel = new JPanel();
            calendarPanel.setLayout(null);
            calendarPanel.setBackground(new Color(255, 160, 122));
            calendarPanel.setBounds(10, 220, getFrame().getSize().width - WIDTH_CORRECTOR, getFrame().getSize().height - getUserScrollPane().getSize().height - HEIGHT_CORRECTOR);
            
            calScrollPane = getcalScrollPane();
            calendarPanel.add(calScrollPane);
        }
        return calendarPanel;
    }
    
    private JScrollPane getUserScrollPane() {
        if (userScrollPane == null) {
            userScrollPane = new JScrollPane();
            userScrollPane.setBounds(10, 10, 10, 10);
            userScrollPane.setSize(getFrame().getSize().width - WIDTH_CORRECTOR, 200);
            userScrollPane.setViewportView(getUserTable());
        }
        return userScrollPane;
    }
    
    private JScrollPane getcalScrollPane() {
        if (calScrollPane == null) {
            calScrollPane = new JScrollPane();
            calScrollPane.setSize(getCalendarPanel().getSize());
            calScrollPane.setViewportView(getCalTable());
        }
        return calScrollPane;
    }
    
    private JTable getUserTable() {
        if (userTable == null) {
            userTable = new JTable();
            userTable.addMouseListener((new MouseAdapter() {
                
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int temp = userTable.getSelectedRow();
                        if (temp == -1) {
                            System.err.println("No row selected");
                        }
                        else {
                            // new ChangeUserWindow(getMainWindowHolder(),
                            // Utils.findUserById(Integer.parseInt(userTable.getValueAt(temp,
                            // 0).toString())));
                        }
                    }
                }
            }));
            userTable.setModel(getUserTableModel());
        }
        return userTable;
    }
    
    private JTable getCalTable() {
        if (calTable == null) {
            calTable = new JTable();
        }
        calTable.setModel(getCalTableModel());
        return calTable;
    }
    
    protected DefaultTableModel getUserTableModel() {
        if (userModel == null) {
            userModel = new userTableModel();
        }
        return userModel;
    }
    
    private DefaultTableModel getCalTableModel() {
        if (calModel == null) {
            calModel = new calTableModel();
        }
        return calModel;
    }
    
    void reloadUserTable(DefaultTableModel model) {
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            model.removeRow(0);
        }
        populateUserTabel(model);
    }
    
    void reloadCalTable(DefaultTableModel model) {
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            model.removeRow(0);
        }
        populateCalTabel(model);
    }
    
    void populateUserTabel(DefaultTableModel model) {
        List<User> userList = UserManagement.getAllUsers();
        for (User user : userList) {
            model.addRow(
                new Object[] { user.getName(), UserRoleManagement.getUsroByUsroId(user.getUserRole()), user.getDaysRemaining(), user.getDaysSpent(), user.getDaysPerYear() });
        }
    }
    
    public void populateCalTabel(DefaultTableModel model) {
        // TODO populate the table
    }
    
    class userTableModel extends DefaultTableModel {
        
        private static final long serialVersionUID = 1L;
        private String[]          columnNames      = Constants.USER_COLUMNNAMES;
        
        public userTableModel() {
            for (int i = 0; i < columnNames.length; i++) {
                this.addColumn(columnNames[i]);
            }
            populateUserTabel(this);
        }
        
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        
    }
    
    class calTableModel extends DefaultTableModel {
        
        private static final long serialVersionUID = 1L;
        private String[]          columnNames      = Constants.CALENDAR_COLUMNAMES;
        
        public calTableModel() {
            for (int i = 0; i < columnNames.length; i++) {
                this.addColumn(columnNames[i]);
            }
            populateCalTabel(this);
        }
        
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        
    }
    
    private JMenuItem getMntmNewUser() {
        if (mntmNewUser == null) {
            mntmNewUser = new JMenuItem("New User");
            mntmNewUser.addActionListener(e -> new CreateUserWindow(this));
        }
        return mntmNewUser;
    }
    
    private JMenuItem getMntmChangeUser() {
        if (mntmChangeUser == null) {
            mntmChangeUser = new JMenuItem("Change User");
        }
        return mntmChangeUser;
    }
    
    private JMenuItem getMntmDeleteUser() {
        if (mntmDeleteUser == null) {
            mntmDeleteUser = new JMenuItem("Delete Uer");
        }
        return mntmDeleteUser;
    }
}
