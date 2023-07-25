
package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.*;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.model.Speciality;
import gr.aueb.cf.schoolapp.service.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminInsertTeachersForm extends JFrame {

    private static final long serialVersionUID = 123456;
    private JPanel contentPane;
    private JTextField firstnameTxt;
    private JTextField lastnameTxt;
    private JTextField ssnTxt;
    private JComboBox<String> specialityComboBox;
    private ITeacherService teacherService;
    private ISpecialityService specialityService;
    private String speciality;
    public AdminInsertTeachersForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 538, 468);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        int labelWidth = 130; // Width for labels
        int inputWidth = 207; // Width for input fields

        // First Name
        JLabel firstnameLbl = new JLabel("Όνομα");
        firstnameLbl.setForeground(new Color(128, 0, 0));
        firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        firstnameLbl.setBounds(68, 75, labelWidth, 17);
        contentPane.add(firstnameLbl);

        firstnameTxt = new JTextField();
        firstnameTxt.setBounds(68 + labelWidth + 5, 73, inputWidth, 20);
        contentPane.add(firstnameTxt);
        firstnameTxt.setColumns(10);

        // Last Name
        JLabel lastnameLbl = new JLabel("Επώνυμο");
        lastnameLbl.setForeground(new Color(128, 0, 0));
        lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        lastnameLbl.setBounds(68, 105, labelWidth, 17);
        contentPane.add(lastnameLbl);

        lastnameTxt = new JTextField();
        lastnameTxt.setColumns(10);
        lastnameTxt.setBounds(68 + labelWidth + 5, 103, inputWidth, 20);
        contentPane.add(lastnameTxt);

        // SSN (ΑΜ Εκπαιδευτικού)
        JLabel ssnLbl = new JLabel("ΑΜ Εκπαιδευτικού");
        ssnLbl.setForeground(new Color(128, 0, 0));
        ssnLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        ssnLbl.setBounds(68, 135, labelWidth, 17);
        contentPane.add(ssnLbl);

        ssnTxt = new JTextField();
        ssnTxt.setColumns(10);
        ssnTxt.setBounds(68 + labelWidth + 5, 133, inputWidth, 20);
        contentPane.add(ssnTxt);

        // Speciality (Ειδικότητα)
        JLabel specialityLbl = new JLabel("Ειδικότητα");
        specialityLbl.setForeground(new Color(128, 0, 0));
        specialityLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        specialityLbl.setBounds(68, 165, labelWidth, 17);
        contentPane.add(specialityLbl);

        specialityComboBox = new JComboBox<>();
        specialityComboBox.setBounds(68 + labelWidth + 5, 163, inputWidth, 22);
        contentPane.add(specialityComboBox);

        loadSpecialities(); // Call this method to populate the specialityComboBox

        JButton insertBtn = new JButton("Εισαγωγή");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertTeacher();
                Main.getAdminMenu().setVisible(true);
                AdminInsertTeachersForm.this.dispose();
            }
        });
        insertBtn.setForeground(new Color(0, 0, 255));
        insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        insertBtn.setBounds(129, 235, 129, 41);
        contentPane.add(insertBtn);

        JButton closeBtn = new JButton("Κλείσιμο");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        closeBtn.setForeground(Color.BLUE);
        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        closeBtn.setBounds(266, 235, 129, 41);
        contentPane.add(closeBtn);

        ITeacherDAO dao = new TeacherDAOImpl();
        teacherService = new TeacherServiceImpl(dao);
    }






    private void loadSpecialities() {
       ISpecialityDAO dao = new SpecialityDAOImpl();
        specialityService = new SpecialityServiceImpl(dao);
        try {
            List<Speciality> specialities = specialityService.getAllSpecialities();
            for (Speciality speciality : specialities) {
                specialityComboBox.addItem(speciality.getSpeciality());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching specialities.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertTeacher() {
        String firstname = firstnameTxt.getText().trim();
        String lastname = lastnameTxt.getText().trim();
        int ssn = Integer.parseInt(ssnTxt.getText().trim());
        String selectedSpecialityTitle = (String) specialityComboBox.getSelectedItem();

        if (firstname.isEmpty() || lastname.isEmpty() || ssnTxt.getText().trim().isEmpty() || selectedSpecialityTitle == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ISpecialityDAO dao = new SpecialityDAOImpl();
        specialityService = new SpecialityServiceImpl(dao);
        try {
            List<Speciality> specialities = specialityService.getAllSpecialities();
            int specialityId = -1;
            for (Speciality speciality : specialities) {
                if (selectedSpecialityTitle.equals(speciality.getSpeciality())){
                    specialityId = speciality.getId();
                    break;
                }
            }

            if (specialityId == -1) {
                // Speciality with the selected title not found, handle this case accordingly
                JOptionPane.showMessageDialog(null, "Selected speciality not found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TeacherInsertDTO dto = new TeacherInsertDTO();
            dto.setFirstname(firstname);
            dto.setLastname(lastname);
            dto.setSsn(ssn);
            dto.setSpecialityId(specialityId);

            teacherService.insertTeacher(dto);
            JOptionPane.showMessageDialog(null, "Teacher inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred during teacher insertion.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
