package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IMeetingDAO;
import gr.aueb.cf.schoolapp.dao.MeetingDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.service.IMeetingService;
import gr.aueb.cf.schoolapp.service.MeetingServiceImpl;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MeetingsMenu extends JFrame {

    private static final long serialVersionUID = 123456;
    private JPanel contentPane;
    private JTextField roomTxt;
    private String room = "";

    public MeetingsMenu() {
        setTitle("Μενού Συναντήσεων");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 449, 481);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        searchPanel.setBounds(32, 23, 355, 170);
        contentPane.add(searchPanel);
        searchPanel.setLayout(null);

        JLabel roomLbl = new JLabel("Αίθουσα Συναντήσεων");
        roomLbl.setForeground(new Color(128, 0, 0));
        roomLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
        roomLbl.setBounds(89, 11, 180, 35);
        searchPanel.add(roomLbl);

        roomTxt = new JTextField();
        roomTxt.setBounds(66, 46, 216, 35);
        searchPanel.add(roomTxt);
        roomTxt.setColumns(10);

        JButton searchBtn = new JButton("Αναζήτηση");
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                room = roomTxt.getText();
                Main.getMeetingUpdateDeleteForm().setVisible(true);
                Main.getMeetingsSearchForm().setEnabled(false);
                List<Meeting> meetings;
                try {
                    IMeetingDAO meetingDAO = new MeetingDAOImpl();
                    IMeetingService meetingService = new MeetingServiceImpl(meetingDAO);
                    meetings = meetingService.getMeetingsByMeetingRoom(room);

                    // Handle the list of meetings as needed
                    // e.g., display the meetings in another form or dialog
                    if (meetings.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No meetings found with the given room",
                                "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        StringBuilder message = new StringBuilder("Meetings found in room '" + room + "':\n");
                        for (Meeting meeting : meetings) {
                            message.append(meeting.getMeetingRoom());

                        }
                        JOptionPane.showMessageDialog(null, message.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (MeetingDAOException ex) {
                    ex.printStackTrace();
                    // Handle the exception appropriately
                    JOptionPane.showMessageDialog(null, "An error occurred during the search",
                            "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        searchBtn.setForeground(new Color(0, 0, 255));
        searchBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        searchBtn.setBounds(113, 98, 122, 47);
        searchPanel.add(searchBtn);

        JPanel insertPanel = new JPanel();
        insertPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        insertPanel.setBounds(32, 204, 355, 145);
        contentPane.add(insertPanel);
        insertPanel.setLayout(null);

        JButton insertBtn = new JButton("Εισαγωγή");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Main.getAdminInsertMeetingsForm().setVisible(true);
            }
        });
        insertBtn.setForeground(new Color(0, 0, 255));
        insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        insertBtn.setBounds(113, 54, 122, 47);
        insertPanel.add(insertBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getAdminMenu().setEnabled(true);
                setVisible(false);
            }
        });
        closeBtn.setForeground(new Color(0, 0, 255));
        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        closeBtn.setBounds(280, 384, 108, 49);
        contentPane.add(closeBtn);
    }
}
