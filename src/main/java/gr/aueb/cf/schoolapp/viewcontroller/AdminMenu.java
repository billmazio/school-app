package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMenu extends JFrame {
    private static final long serialVersionUID = 123456;
    private JPanel contentPane;

    public AdminMenu() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(AdminMenu.class.getResource("/resources/eduv2.png")));
        setTitle("Admin Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 410, 505);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getTeachersMenu().setVisible(true);
            }
        });
        btnNewButton.setBounds(32, 67, 40, 40);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Εκπαιδευτές");
        lblNewLabel.setForeground(new Color(128, 64, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(82, 73, 86, 29);
        contentPane.add(lblNewLabel);

        JButton btnCityManagement = new JButton("");
        btnCityManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getAdminInsertCitiesForm().setVisible(true);

            }
        });
        btnCityManagement.setBounds(32, 124, 40, 40);
        contentPane.add(btnCityManagement);

        JLabel lblCityManagement = new JLabel("Πόλεις");
        lblCityManagement.setForeground(new Color(128, 64, 0));
        lblCityManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCityManagement.setBounds(82, 130, 86, 29);
        contentPane.add(lblCityManagement);

        JButton btnStudent = new JButton("");
        btnStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Student form visibility code here
                Main.getStudentsMenu().setVisible(true);
            }
        });
        btnStudent.setBounds(32, 181, 40, 40);
        contentPane.add(btnStudent);

        JLabel lblStudent = new JLabel("Εκπαιδευόμενοι");
        lblStudent.setForeground(new Color(128, 64, 0));
        lblStudent.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblStudent.setBounds(82, 187, 117, 29);
        contentPane.add(lblStudent);

        JButton btnSpecialties = new JButton("");
        btnSpecialties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getAdminInsertSpecialitiesForm().setVisible(true);

            }
        });


        btnSpecialties.setBounds(32, 238, 40, 40);
        contentPane.add(btnSpecialties);

        JLabel lblSpecialties = new JLabel("Ειδικότητες");
        lblSpecialties.setForeground(new Color(128, 64, 0));
        lblSpecialties.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSpecialties.setBounds(82, 244, 86, 29);
        contentPane.add(lblSpecialties);

        JSeparator separator = new JSeparator();
        separator.setBounds(32, 388, 270, 1);
        contentPane.add(separator);

        JButton btnNewButton_2 = new JButton("Close App");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton_2.setForeground(new Color(0, 0, 255));
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton_2.setBounds(204, 417, 103, 40);
        contentPane.add(btnNewButton_2);

        JButton btnMeeting = new JButton("");
        btnMeeting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Meeting form visibility code here
           Main.getMeetingsMenu().setVisible(true);
            }
        });
        btnMeeting.setBounds(32, 294, 40, 40);
        contentPane.add(btnMeeting);

        JLabel lblMeeting = new JLabel("Συναντήσεις");
        lblMeeting.setForeground(new Color(128, 64, 0));
        lblMeeting.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMeeting.setBounds(82, 294, 117, 29);
        contentPane.add(lblMeeting);
    }
}