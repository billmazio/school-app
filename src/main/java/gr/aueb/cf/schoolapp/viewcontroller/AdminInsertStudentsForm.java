
package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.service.CityServiceImpl;
import gr.aueb.cf.schoolapp.service.ICityService;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.StudentServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminInsertStudentsForm extends JFrame {

    private static final long serialVersionUID = 123456;
    private JPanel contentPane;
    private JTextField firstnameTxt;
    private JTextField lastnameTxt;
    private JTextField genderTxt;
    private JTextField birthdateTxt;
    private JComboBox<String> cityComboBox; // Dropdown list for cities
    private IStudentService studentService;
    private ICityService cityService;
    private JComboBox<String> genderComboBox;

    public AdminInsertStudentsForm() {
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



        // Gender
        JLabel genderLbl = new JLabel("Φύλο");
        genderLbl.setForeground(new Color(128, 0, 0));
        genderLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        genderLbl.setBounds(68, 135, labelWidth, 17);
        contentPane.add(genderLbl);

        String[] genderOptions = { "F", "M" }; // Female and Male options
        genderComboBox = new JComboBox<>(genderOptions); // Initialize genderComboBox
        genderComboBox.setBounds(68 + labelWidth + 5, 133, inputWidth, 22);
        contentPane.add(genderComboBox);

        // Birthdate
        JLabel birthdateLbl = new JLabel("Ημερομηνία Γέννησης (yyyy-MM-dd)");
        birthdateLbl.setForeground(new Color(128, 0, 0));
        birthdateLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        birthdateLbl.setBounds(68, 165, labelWidth, 17);
        contentPane.add(birthdateLbl);

        birthdateTxt = new JTextField();
        birthdateTxt.setColumns(10);
        birthdateTxt.setBounds(68 + labelWidth + 5, 163, inputWidth, 20);
        contentPane.add(birthdateTxt);

        // City
        JLabel cityLbl = new JLabel("Πόλη");
        cityLbl.setForeground(new Color(128, 0, 0));
        cityLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        cityLbl.setBounds(68, 195, labelWidth, 17);
        contentPane.add(cityLbl);

        cityComboBox = new JComboBox<>();
        cityComboBox.setBounds(68 + labelWidth + 5, 193, inputWidth, 22);
        contentPane.add(cityComboBox);

        loadCities(); // Call this method to populate the cityComboBox

        JButton insertBtn = new JButton("Εισαγωγή");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertStudent();
                Main.getAdminMenu().setVisible(true);
                AdminInsertStudentsForm.this.dispose();
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

        IStudentDAO dao = new StudentDAOImpl();
        studentService = new StudentServiceImpl(dao);
    }

    private void loadCities() {
        ICityDAO dao = new CityDAOImpl();
        cityService = new CityServiceImpl(dao);
        try {
            List<City> cities = cityService.getAllCities();
            for (City city : cities) {
                cityComboBox.addItem(city.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching cities.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertStudent() {
        String firstname = firstnameTxt.getText().trim();
        String lastname = lastnameTxt.getText().trim();
        String gender = (String) genderComboBox.getSelectedItem();
        String birthdateString = birthdateTxt.getText().trim();
        String selectedCityName = (String) cityComboBox.getSelectedItem();

        if (firstname.isEmpty() || lastname.isEmpty() || gender.isEmpty() || birthdateString.isEmpty()
                || selectedCityName == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate;
        try {
            birthdate = dateFormat.parse(birthdateString);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid birthdate format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ICityDAO dao = new CityDAOImpl();
        cityService = new CityServiceImpl(dao);
        try {
            List<City> cities = cityService.getCitiesByCityName(selectedCityName);
            int cityId = -1;
            for (City city : cities) {
                if (selectedCityName.equals(city.getName())) {
                    cityId = city.getId();
                    break;
                }
            }

            if (cityId == -1) {
                // City with the selected name not found, handle this case accordingly
                JOptionPane.showMessageDialog(null, "Selected city not found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StudentInsertDTO student = new StudentInsertDTO();
            student.setFirstname(firstname);
            student.setLastname(lastname);
            student.setGender(gender);
            student.setCityId(cityId);

            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlBirthdate = new java.sql.Date(birthdate.getTime());
            student.setBirthdate(sqlBirthdate);

            try {
                studentService.insertStudent(student);
                JOptionPane.showMessageDialog(null, "Student inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error occurred during student insertion.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching cities.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
