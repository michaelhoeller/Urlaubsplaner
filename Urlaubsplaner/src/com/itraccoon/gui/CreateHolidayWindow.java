package com.itraccoon.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.itraccoon.gui.dialogue.MessageDialogue;
import com.itraccoon.gui.dialogue.QuestionDialogue;
import com.itraccoon.object.FDate;
import com.itraccoon.object.Holiday;
import com.itraccoon.object.User;
import com.itraccoon.util.Utils;

public class CreateHolidayWindow {
    
    private MainWindow          parentWindow;
    private CreateHolidayWindow thisWindow;
    private User                user;
    private Holiday             holiday;
    private Integer             remainingDays = 0;
    
    public Holiday getHoliday() {
        return holiday;
    }
    
    private JFrame     frame;
    private JButton    btnSave;
    private JButton    btnBack;
    private DatePicker datePickerStart;
    private JPanel     datePanel;
    private DatePicker datePickerEnd;
    private JLabel     lblErsterUrlaubstag;
    private JLabel     lblLetzterUrlaubstag;
    private JLabel     lblVerfgbareTage;
    private JLabel     lblBentigteTage;
    private JLabel     lblVerbleibendeTage;
    private JLabel     lblNeededDays;
    private JLabel     lblAvailableDays;
    private JLabel     lblRemainingDays;
    protected boolean  holidayReady = false;
    
    public MainWindow getParentWindow() {
        return parentWindow;
    }
    
    public User getUser() {
        return user;
    }
    
    public CreateHolidayWindow(MainWindow parentWindow, User user) {
        holiday = new Holiday();
        this.parentWindow = parentWindow;
        this.thisWindow = this;
        this.user = user;
        initialize();
        frame.setVisible(true);
    }
    
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Urlaub beantragen für: " + Utils.getFullName(user));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(Utils.getQuarterWidth(), Utils.getQuarterHeight());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(getBtnSave());
        frame.getContentPane().add(getBtnBack());
        frame.getContentPane().add(getDatePanel());
        frame.getContentPane().add(getLblVerfgbareTage());
        frame.getContentPane().add(getLblBentigteTage());
        frame.getContentPane().add(getLblVerbleibendeTage());
        frame.getContentPane().add(getLblNeededDays());
        frame.getContentPane().add(getLblAvailableDays());
        frame.getContentPane().add(getLblRemainingDays());
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
    
    private class BtnBackActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            getParentWindow().getFrame().setEnabled(true);
            frame.dispose();
        }
    }
    
    private class BtnSaveActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            if (!holidayReady) {
                new MessageDialogue("Kein gültiger Urlaubszeitraum gewählt", "Error");
            }
            else if (remainingDays <= 0) {
                new MessageDialogue("Nicht genug Urlaubstage verfügbar!", "Error");
            }
            else {
                if (new QuestionDialogue("Sicher?", "Urlaub wird beantragt für: " + Utils.getFullName(user) + "\nVom: " + holiday.getStartAsString() + "\nBis: " + holiday.getEndAsString()).getAnswer()) {
                    user.getHolidays().add(holiday);
                    user.setDaysSpent(user.getDaysSpent() + holiday.getAmount());
                    user.setDaysAvailable(user.getDaysAvailable() - user.getDaysAvailable());
                    new MessageDialogue("Urlaub wurde gespeichert", "Noice");
                }
            }
        }
    }
    
    private JPanel getDatePanel() {
        if (datePanel == null) {
            datePanel = new JPanel();
            datePanel.setBounds(10, 11, 444, 89);
            datePanel.setLayout(null);
            datePanel.add(getDatePickerStart());
            datePanel.add(getDatePickerEnd());
            datePanel.add(getLblErsterUrlaubstag());
            datePanel.add(getLblLetzterUrlaubstag());
        }
        return datePanel;
    }
    
    private DatePicker getDatePickerStart() {
        if (datePickerStart == null) {
            datePickerStart = new DatePicker();
            datePickerStart.setBounds(244, 11, 190, 25);
            datePickerStart.addDateChangeListener(new DateChangeListener() {
                
                @Override
                public void dateChanged(DateChangeEvent event) {
                    if (datePickerStart.getDate() != null) {
                        // Change of startdate
                        holiday.setStart(Utils.localToDate(datePickerStart.getDate()));
                        if (triggerUpdate(thisWindow)) {
                            holidayReady = true;
                            lblNeededDays.setText(holiday.getAmount().toString());
                            
                            // TODO: We and holidaycalc
                            remainingDays = ((Integer) (Utils.getRemainingDays(user) - holiday.getAmount()));
                            lblRemainingDays.setText(remainingDays.toString());
                        }
                    }
                }
                
            });
        }
        return datePickerStart;
    }
    
    private DatePicker getDatePickerEnd() {
        if (datePickerEnd == null) {
            datePickerEnd = new DatePicker();
            datePickerEnd.setBounds(244, 47, 190, 25);
            datePickerEnd.addDateChangeListener(new DateChangeListener() {
                
                @Override
                public void dateChanged(DateChangeEvent event) {
                    if (datePickerEnd.getDate() != null) {
                        // Change of enddate
                        holiday.setEnd(Utils.localToDate(datePickerEnd.getDate()));
                        if (triggerUpdate(thisWindow)) {
                            holidayReady = true;
                            lblNeededDays.setText(holiday.getAmount().toString());
                            
                            // TODO: We and holidaycalc
                            remainingDays = ((Integer) (Utils.getRemainingDays(user) - holiday.getAmount()));
                            lblRemainingDays.setText(remainingDays.toString());
                        }
                    }
                }
            });
        }
        return datePickerEnd;
    }
    
    private JLabel getLblErsterUrlaubstag() {
        if (lblErsterUrlaubstag == null) {
            lblErsterUrlaubstag = new JLabel("Von...");
            lblErsterUrlaubstag.setBounds(10, 11, 150, 25);
        }
        return lblErsterUrlaubstag;
    }
    
    private JLabel getLblLetzterUrlaubstag() {
        if (lblLetzterUrlaubstag == null) {
            lblLetzterUrlaubstag = new JLabel("Bis...");
            lblLetzterUrlaubstag.setBounds(10, 47, 150, 25);
        }
        return lblLetzterUrlaubstag;
    }
    
    private JLabel getLblVerfgbareTage() {
        if (lblVerfgbareTage == null) {
            lblVerfgbareTage = new JLabel("Verf\u00FCgbare Tage:");
            lblVerfgbareTage.setBounds(10, 147, 150, 25);
        }
        return lblVerfgbareTage;
    }
    
    private JLabel getLblBentigteTage() {
        if (lblBentigteTage == null) {
            lblBentigteTage = new JLabel("Ben\u00F6tigte Tage:");
            lblBentigteTage.setBounds(10, 111, 150, 25);
        }
        return lblBentigteTage;
    }
    
    private JLabel getLblVerbleibendeTage() {
        if (lblVerbleibendeTage == null) {
            lblVerbleibendeTage = new JLabel("Verbleibende Tage");
            lblVerbleibendeTage.setBounds(10, 183, 150, 25);
        }
        return lblVerbleibendeTage;
    }
    
    private JLabel getLblNeededDays() {
        if (lblNeededDays == null) {
            lblNeededDays = new JLabel("0");
            lblNeededDays.setBounds(304, 111, 150, 25);
        }
        return lblNeededDays;
    }
    
    private JLabel getLblAvailableDays() {
        if (lblAvailableDays == null) {
            lblAvailableDays = new JLabel("Available Days");
            lblAvailableDays.setText(Utils.getRemainingDaysAsString(user));
            lblAvailableDays.setBounds(304, 147, 150, 25);
        }
        return lblAvailableDays;
    }
    
    private JLabel getLblRemainingDays() {
        if (lblRemainingDays == null) {
            lblRemainingDays = new JLabel("Remaining Days\r\n");
            lblRemainingDays.setBounds(304, 183, 150, 25);
        }
        return lblRemainingDays;
    }
    
    // Logic
    protected Boolean triggerUpdate(CreateHolidayWindow window) {
        Holiday holi = window.getHoliday();
        if (holi.getEnd() != null && holi.getStart() != null) {
            holi.setAmount(Utils.calculateLength(holi));
            try {
                holi.setStartAsString(new FDate(holi.getStart()).getDateAsString());
                holi.setEndAsString(new FDate(holi.getEnd()).getDateAsString());
            }
            catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
