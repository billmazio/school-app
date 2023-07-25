package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class TeachersMenu extends JFrame {
    private static final long serialVersionUID = 123456;
    private JPanel contentPane;
    private JTextField lastnameTxt;
    private String lastname = "";

    public TeachersMenu() {
        setTitle("Μενού Εκπαιδευτών");
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

        JLabel lastnameLbl = new JLabel("Επώνυμο");
        lastnameLbl.setForeground(new Color(128, 0, 0));
        lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
        lastnameLbl.setBounds(139, 11, 70, 35);
        searchPanel.add(lastnameLbl);

        lastnameTxt = new JTextField();
        lastnameTxt.setBounds(66, 46, 216, 35);
        searchPanel.add(lastnameTxt);
        lastnameTxt.setColumns(10);

        JButton searchBtn = new JButton("Αναζήτηση");
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lastname = lastnameTxt.getText();
                Main.getTeachersUpdateDeleteForm().setVisible(true);
                Main.getTeacherSearchForm().setEnabled(false);
                List<Teacher> teachers;
                try {
                    ITeacherDAO teacherDAO = new TeacherDAOImpl();
                    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
                    teachers = teacherService.getTeachersByLastname(lastname);
                    // Handle the list of teachers as needed
                    // e.g., display the teachers in another form or dialog
                    if (teachers.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No teachers found with the given last name",
                                "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        StringBuilder message = new StringBuilder("Teachers found with the last name '" + lastname + "':\n");
                        for (Teacher teacher : teachers) {
                            message.append(teacher.getFirstname()).append(" ").append(teacher.getLastname()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, message.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (TeacherDAOException ex) {
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

        JButton insertbtn = new JButton("Εισαγωγή");
        insertbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getAdminInsertTeachersForm().setVisible(true);
            }
        });
        insertbtn.setForeground(new Color(0, 0, 255));
        insertbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        insertbtn.setBounds(113, 54, 122, 47);
        insertPanel.add(insertbtn);

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
