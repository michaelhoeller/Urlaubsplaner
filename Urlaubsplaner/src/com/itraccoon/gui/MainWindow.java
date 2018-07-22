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

	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private JScrollPane userScrollPane;
	private JTable userTable;
	private DefaultTableModel userModel;
	private DefaultTableModel calModel;
	private JPanel calendarPanel;
	private JScrollPane calScrollPane;
	private JTable calTable;

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
		frame.getContentPane().add(getUserScrollPane());
		frame.getContentPane().add(getCalendarPanel());

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmTest = new JMenuItem("test");
		mnNewMenu.add(mntmTest);

		frame.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent evt) {
				refresh();
			}
		});
	}

	protected void refresh() {
		getUserScrollPane().setSize(getFrame().getSize().width - 20, 200);
		getCalendarPanel().setBounds(10, 220, getFrame().getSize().width - 20, getFrame().getSize().height - getUserScrollPane().getSize().height - 75);
	}

	private JPanel getCalendarPanel() {
		if (calendarPanel == null) {
			calendarPanel = new JPanel();
			calendarPanel.setLayout(null);
			calendarPanel.setBackground(new Color(255, 160, 122));
			calendarPanel.setBounds(10, 220, getFrame().getSize().width - 20, getFrame().getSize().height - getUserScrollPane().getSize().height - 75);

			calScrollPane = getcalScrollPane();
			calendarPanel.add(calScrollPane);
		}
		return calendarPanel;
	}

	private JScrollPane getUserScrollPane() {
		if (userScrollPane == null) {
			userScrollPane = new JScrollPane();
			userScrollPane.setBounds(10, 10, 10, 10);
			userScrollPane.setSize(getFrame().getSize().width - 20, 200);
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
						} else {
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

	void reloadTable(DefaultTableModel model) {
		int rowCount = model.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			model.removeRow(0);
		}
		populateUserTabel(model);
	}

	void populateUserTabel(DefaultTableModel model) {
		List<User> userList = UserManagement.getAllUsers();
		for (User user : userList) {
			model.addRow(
					new Object[] { user.getName(), UserRoleManagement.getUsroByUsroId(user.getUserRole()), user.getDaysRemaining(), user.getDaysSpent(), user.getDaysPerYear() });
		}
	}

	class userTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;
		private String[] columnNames = Constants.USER_COLUMNNAMES;

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
		private String[] columnNames = Constants.CALENDAR_COLUMNAMES;

		public calTableModel() {
			for (int i = 0; i < columnNames.length; i++) {
				this.addColumn(columnNames[i]);
			}
			populateUserTabel(this);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}
}
