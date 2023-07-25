package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.StudentServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class StudentsMenu extends JFrame {
    private static final long serialVersionUID = 123456;
    private JPanel contentPane;
    private JTextField lastnameTxt;
    private String lastname = "";

    public StudentsMenu() {
        setTitle("Μενού Φοιτητών");
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
                Main.getStudentsUpdateDeleteForm().setVisible(true);
                Main.getStudentSearchForm().setEnabled(false);
                List<Student> students;
                try {
                    IStudentDAO studentDAO = new StudentDAOImpl();
                    IStudentService studentService = new StudentServiceImpl(studentDAO);
                    students = studentService.getStudentsByLastname(lastname);
                    // Handle the list of students as needed
                    // e.g., display the students in another form or dialog
                    if (students.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No students found with the given last name",
                                "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        StringBuilder message = new StringBuilder("Students found with the last name '" + lastname + "':\n");
                        for (Student student : students) {
                            message.append(student.getFirstname()).append(" ").append(student.getLastname()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, message.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (StudentDAOException ex) {
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
               Main.getAdminInsertStudentsForm().setVisible(true);
               // Main.getStudentsUpdateDeleteForm().setVisible(true);

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