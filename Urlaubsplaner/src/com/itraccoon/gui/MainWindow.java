package com.itraccoon.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itraccoon.constants.Constants;
import com.itraccoon.filehandling.ExportUserlist;
import com.itraccoon.gui.dialogue.ClosingDialogue;
import com.itraccoon.main.Runtime;
import com.itraccoon.object.User;
import com.itraccoon.util.Utils;
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
    
    private JMenuBar          menuBar;
    private JMenu             mnBenutzerverwaltung;
    private JMenu             mnUrlaubsverwaltung;
    private JMenu             mnSystemeinstellungen;
    private JMenuItem         mntmBeenden;
    private JMenuItem         mntmAnlegen;
    private JScrollPane       scrollPane;
    private JTable            userTable;
    private DefaultTableModel model;
    
    private MainWindow        mainWindowHolder;
    
    public MainWindow() {
        this.setMainWindowHolder(this);
        initialize();
        frame.setVisible(true);
    }
    
    private void initialize() {
        frame = new JFrame();
        WindowUtils.setWindowProperties(frame, "Urlaubsplaner - Übersicht", frameSize.FULL_SCREEN.size(), true);
        
        frame.getContentPane().add(getScrollPane());
        frame.setJMenuBar(getMenuBar());
    }
    
    public MainWindow getMainWindowHolder() {
        return mainWindowHolder;
    }
    
    public void setMainWindowHolder(MainWindow mainWindowHolder) {
        this.mainWindowHolder = mainWindowHolder;
    }
    
    private JMenuBar getMenuBar() {
        if (menuBar == null) {
            menuBar = new JMenuBar();
            menuBar.add(getMnBenutzerverwaltung());
            menuBar.add(getMnUrlaubsverwaltung());
            menuBar.add(getMnSystemeinstellungen());
        }
        return menuBar;
    }
    
    private JMenu getMnBenutzerverwaltung() {
        if (mnBenutzerverwaltung == null) {
            mnBenutzerverwaltung = new JMenu("Benutzerverwaltung");
            mnBenutzerverwaltung.add(getMntmAnlegen());
        }
        return mnBenutzerverwaltung;
    }
    
    private JMenu getMnUrlaubsverwaltung() {
        if (mnUrlaubsverwaltung == null) {
            mnUrlaubsverwaltung = new JMenu("Urlaubsverwaltung");
        }
        return mnUrlaubsverwaltung;
    }
    
    private JMenu getMnSystemeinstellungen() {
        if (mnSystemeinstellungen == null) {
            mnSystemeinstellungen = new JMenu("Systemeinstellungen");
            mnSystemeinstellungen.add(getMntmBeenden());
        }
        return mnSystemeinstellungen;
    }
    
    private JMenuItem getMntmAnlegen() {
        if (mntmAnlegen == null) {
            mntmAnlegen = new JMenuItem("Anlegen");
            mntmAnlegen.addActionListener(e -> createUser(this));
        }
        return mntmAnlegen;
    }
    
    private JMenuItem getMntmBeenden() {
        if (mntmBeenden == null) {
            mntmBeenden = new JMenuItem("Beenden");
            mntmBeenden.addActionListener(new MntmBeendenActionListener());
        }
        return mntmBeenden;
    }
    
    private class MntmBeendenActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            if (new ClosingDialogue().getAnswer() == true) {
                // TODO
            }
        }
    }
    
    private JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 11, 924, 200);
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
                            new ChangeUserWindow(getMainWindowHolder(), Utils.findUserById(Integer.parseInt(userTable.getValueAt(temp, 0).toString())));
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
    
    // Logic
    void createUser(MainWindow parentWindow) {
        new CreateUserWindow(parentWindow);
    }
    
    void saveUserlist() {
        new ExportUserlist();
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
            model.addRow(new Object[] { user.getId(), user.getFullName(), user.getDaysAvailable(), user.getDaysSpent(), user.getDaysRemaining() });
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
        
    };
    
}
