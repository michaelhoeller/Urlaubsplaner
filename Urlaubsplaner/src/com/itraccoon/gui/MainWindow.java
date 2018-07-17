package com.itraccoon.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itraccoon.constants.Constants;
import com.itraccoon.main.Runtime;
import com.itraccoon.object.User;
import com.itraccoon.util.WindowUtils;
import com.itraccoon.util.frameSize;

public class MainWindow {
    
    private JFrame frame;
    
    public JFrame getFrame() {
        return frame;
    }
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
    private JScrollPane       scrollPane;
    private JTable            userTable;
    private DefaultTableModel model;
    private JPanel            calendarPanel;
    private JTextField        txtKalenderbersicht;
    
    public MainWindow() {
        initialize();
        frame.setVisible(true);
    }
    
    private void initialize() {
        frame = new JFrame();
        WindowUtils.setWindowProperties(frame, "Urlaubsplaner - Übersicht", frameSize.FULL_SCREEN.size(), true);
        
        // Test impl
        frame.setSize((Constants.SCREENWIDTH / 1) - 50, (Constants.SCREENHEIGHT / 1) - 50);
        
        // Components
        frame.getContentPane().add(getScrollPane());
        frame.getContentPane().add(getCalendarPanel());
        
        frame.addComponentListener(new ComponentAdapter() {
            
            public void componentResized(ComponentEvent evt) {
                refresh();
            }
        });
    }
    
    protected void refresh() {
        getScrollPane().setSize(getFrame().getSize().width - 20, 200);
        getCalendarPanel().setBounds(10, 220, getFrame().getSize().width - 20, getFrame().getSize().height - getScrollPane().getSize().height - 55);
    }
    
    private JPanel getCalendarPanel() {
        if (calendarPanel == null) {
            calendarPanel = new JPanel();
            calendarPanel.setLayout(null);
            calendarPanel.setBackground(new Color(255, 160, 122));
            calendarPanel.setBounds(10, 220, getFrame().getSize().width - 20, getFrame().getSize().height - getScrollPane().getSize().height - 55);
            calendarPanel.add(getTxtKalenderbersicht());
        }
        return calendarPanel;
    }
    
    private JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 10, 10, 10);
            scrollPane.setSize(getFrame().getSize().width - 20, 200);
            scrollPane.setViewportView(getUserTable());
        }
        return scrollPane;
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
                            // new ChangeUserWindow(getMainWindowHolder(), Utils.findUserById(Integer.parseInt(userTable.getValueAt(temp, 0).toString())));
                        }
                    }
                }
            }));
            userTable.setModel(getUserTableModel());
        }
        return userTable;
    }
    
    protected DefaultTableModel getUserTableModel() {
        if (model == null) {
            model = new userTableModel();
        }
        return model;
    }
    
    void reloadTable(DefaultTableModel model) {
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            model.removeRow(0);
        }
        populateTabel(model);
    }
    
    void populateTabel(DefaultTableModel model) {
        for (User user : Runtime.getInstance().getUserlist()) {
            model.addRow(new Object[] { user.getId(), user.getFullName(), user.getDaysAvailable(), user.getDaysSpent(),
                    user.getDaysRemaining() });
        }
    }
    
    class userTableModel extends DefaultTableModel {
        
        private static final long serialVersionUID = 1L;
        private String[]          columnNames      = Constants.COLUMNNAMES;
        
        public userTableModel() {
            for (int i = 0; i < columnNames.length; i++) {
                this.addColumn(columnNames[i]);
            }
            if (Runtime.getInstance().isDataThere()) {
                populateTabel(this);
            }
        }
        
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        
    }
    
    private JTextField getTxtKalenderbersicht() {
        if (txtKalenderbersicht == null) {
            txtKalenderbersicht = new JTextField();
            txtKalenderbersicht.setFont(new Font("Dialog", Font.BOLD, 99));
            txtKalenderbersicht.setText("Kalender\u00DCbersicht");
            txtKalenderbersicht.setBounds(239, 281, 1114, 266);
            txtKalenderbersicht.setColumns(10);
        }
        return txtKalenderbersicht;
    }
}
