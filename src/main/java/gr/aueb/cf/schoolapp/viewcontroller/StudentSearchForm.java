

package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class StudentSearchForm extends JFrame {

    private JPanel contentPane;
    private JTextField lastnameTxt;
    private String lastname = "";

    public StudentSearchForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                lastnameTxt.setText("");
            }
        });
        setTitle("Εισαγωγή / Αναζήτηση Φοιτητή");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("eduv2.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 530, 300); // Adjust the height of the frame
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 255, 250));
        contentPane.setBorder(new EmptyBorder(20, 20, 40, 20)); // Add some padding
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLastname = new JLabel("Επώνυμο");
        lblLastname.setBounds(180, 60, 90, 20);
        lblLastname.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblLastname.setForeground(new Color(160, 82, 45));
        contentPane.add(lblLastname);

        lastnameTxt = new JTextField();
        lastnameTxt.setBounds(95, 90, 320, 30); // Adjust the width of the text field
        contentPane.add(lastnameTxt);
        lastnameTxt.setColumns(10);

        JButton searchBtn = new JButton("Αναζήτηση");
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getStudentsUpdateDeleteForm().setVisible(true);
                Main.getAdminInsertStudentsForm().setEnabled(false);
            }
        });
        searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchBtn.setForeground(new Color(0, 0, 255));
        searchBtn.setBounds(190, 140, 150, 40); // Adjust the width and height of the button
        contentPane.add(searchBtn);

        JButton insertStudentBtn = new JButton("Εισαγωγή");
        insertStudentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getAdminInsertStudentsForm().setVisible(true);
                Main.getStudentSearchForm().setEnabled(false);
            }
        });
        insertStudentBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        insertStudentBtn.setForeground(new Color(0, 0, 255));
        insertStudentBtn.setBounds(190, 190, 150, 40); // Adjust the width and height of the button
        contentPane.add(insertStudentBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getAdminMenu().setEnabled(true);
                Main.getStudentSearchForm().setVisible(false);
            }
        });
        closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        closeBtn.setForeground(new Color(0, 0, 255));
        closeBtn.setBounds(390, 230, 100, 40); // Adjust the width and height of the button
        contentPane.add(closeBtn);
    }

    public String getLastname() {
        return lastname;
    }
}
