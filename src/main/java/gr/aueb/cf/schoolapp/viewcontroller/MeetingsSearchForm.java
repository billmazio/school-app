package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;


public class MeetingsSearchForm extends JFrame {
    private static final long serialVersionUID = 123456;
    private JPanel contentPane;
    private JTextField roomTxt;
    private String room = "";

    public MeetingsSearchForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                roomTxt.setText("");
            }
        });
        setTitle("Εισαγωγή / Αναζήτηση Συναντήσεων");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("eduv2.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 530, 451);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 255, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        roomTxt = new JTextField();
        roomTxt.setBounds(142, 89, 201, 37);
        contentPane.add(roomTxt);
        roomTxt.setColumns(10);

        JLabel lblRoom = new JLabel("Αίθουσα Συναντήσεων");
        lblRoom.setBounds(170, 58, 160, 20);
        lblRoom.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRoom.setForeground(new Color(160, 82, 45));
        contentPane.add(lblRoom);

        JButton searchBtn = new JButton("Αναζήτηση");
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                room = roomTxt.getText();
                Main.getMeetingUpdateDeleteForm().setVisible(true);
                Main.getAdminInsertMeetingsForm().setEnabled(false);
            }
        });
        searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchBtn.setForeground(new Color(0, 0, 255));
        searchBtn.setBounds(186, 137, 113, 37);
        contentPane.add(searchBtn);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(102, 40, 289, 161);
        contentPane.add(panel);

        JButton insertBtn = new JButton("Εισαγωγή");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getAdminInsertMeetingsForm().setVisible(true);
                Main.getMeetingsSearchForm().setEnabled(false);
            }
        });
        insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        insertBtn.setForeground(new Color(0, 0, 255));
        insertBtn.setBounds(186, 238, 113, 37);
        contentPane.add(insertBtn);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(102, 212, 289, 99);
        contentPane.add(panel_1);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getAdminMenu().setEnabled(true);
                Main.getMeetingsSearchForm().setVisible(false);
            }
        });
        closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        closeBtn.setForeground(new Color(0, 0, 255));
        closeBtn.setBounds(302, 345, 89, 37);
        contentPane.add(closeBtn);
    }

    public String getByRoom() {
        return room;
    }
}

