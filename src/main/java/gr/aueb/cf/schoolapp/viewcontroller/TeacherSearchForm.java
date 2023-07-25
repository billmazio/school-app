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


public class TeacherSearchForm extends JFrame {
    private static final long serialVersionUID = 123456;
    private JPanel contentPane;
    private JTextField lastnameTxt;
    private String lastname = "";

    public TeacherSearchForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                lastnameTxt.setText("");
            }
        });
        setTitle("Εισαγωγή / Αναζήτηση Εκπαιδευτή");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("eduv2.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 530, 300); // Adjust the height of the frame
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 255, 250));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add some padding
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

        JButton SearchBtn = new JButton("Αναζήτηση");
        SearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getAdminInsertTeachersForm().setVisible(true);
                Main.getTeacherSearchForm().setEnabled(false);
            }
        });
        SearchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SearchBtn.setForeground(new Color(0, 0, 255));
        SearchBtn.setBounds(190, 140, 150, 40); // Adjust the width and height of the button
        contentPane.add(SearchBtn);

        JButton insertBtn = new JButton("Εισαγωγή");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getAdminInsertTeachersForm().setVisible(true);
                Main.getTeacherSearchForm().setEnabled(false);
            }
        });
        insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        insertBtn.setForeground(new Color(0, 0, 255));
        insertBtn.setBounds(190, 190, 150, 40); // Adjust the width and height of the button
        contentPane.add(insertBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getAdminMenu().setEnabled(true);
                Main.getTeacherSearchForm().setVisible(false);
            }
        });
        closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        closeBtn.setForeground(new Color(0, 0, 255));
        closeBtn.setBounds(390, 210, 100, 40); // Adjust the width and height of the button
        contentPane.add(closeBtn);
    }

    public String getLastname() {
        return lastname;
    }
}
