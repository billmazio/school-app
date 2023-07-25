package gr.aueb.cf.schoolapp.viewcontroller;



import gr.aueb.cf.schoolapp.dao.ISpecialityDAO;
import gr.aueb.cf.schoolapp.dao.SpecialityDAOImpl;
import gr.aueb.cf.schoolapp.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolapp.service.ISpecialityService;
import gr.aueb.cf.schoolapp.service.SpecialityServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminInsertSpecialitiesForm extends JFrame {
    private JPanel contentPane;
    private JTextField nameTxt;
    private ISpecialityService specialityService;

    public AdminInsertSpecialitiesForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setTitle("Admin Insert Specialties");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Specialty Name");
        lblNewLabel.setBounds(54, 56, 100, 14);
        contentPane.add(lblNewLabel);

        nameTxt = new JTextField();
        nameTxt.setBounds(164, 53, 207, 20);
        contentPane.add(nameTxt);
        nameTxt.setColumns(10);

        JButton insertBtn = new JButton("Insert");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertSpecialty();
            }
        });
        insertBtn.setForeground(Color.BLUE);
        insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        insertBtn.setBounds(164, 145, 89, 23);
        contentPane.add(insertBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        closeBtn.setForeground(Color.BLUE);
        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        closeBtn.setBounds(282, 145, 89, 23);
        contentPane.add(closeBtn);

        ISpecialityDAO dao = new SpecialityDAOImpl();
        specialityService = new SpecialityServiceImpl(dao);
    }

    private void insertSpecialty() {
        String speciality = nameTxt.getText().trim();

        if (speciality.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SpecialityInsertDTO dto = new SpecialityInsertDTO();
        dto.setSpeciality(speciality);

        try {
            specialityService.insertSpeciality(dto);
            JOptionPane.showMessageDialog(null, "Specialty inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred during specialty insertion.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
