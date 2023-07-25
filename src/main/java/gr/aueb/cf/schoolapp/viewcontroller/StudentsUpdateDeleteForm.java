
package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.service.CityServiceImpl;
import gr.aueb.cf.schoolapp.service.ICityService;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.StudentServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.CityNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.StudentNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentsUpdateDeleteForm extends JFrame {
    private JPanel contentPane;
    private JTextField idTxt;
    private JTextField firstnameTxt;
    private JTextField lastnameTxt;
    private JTextField genderTxt;
    private JTextField birthdateTxt;
    private JTextField cityTxt;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JButton nextBtn;
    private JButton previousBtn;
    private JButton firstBtn;
    private JButton lastBtn;
    private JButton closeBtn;

    private IStudentDAO dao = new StudentDAOImpl();
    private IStudentService studentService = new StudentServiceImpl(dao);

    private ICityDAO cityDAO = new CityDAOImpl();
    private ICityService cityService = new CityServiceImpl(cityDAO);

    private List<Student> students;
    private List<City> cities;
    private int listPosition;
    private int listSize;

    public StudentsUpdateDeleteForm() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("eduv2.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        addWindowListener(new WindowAdapter() {


                @Override
                public void windowActivated(WindowEvent e) {
                    setTitle("Ενημέρωση/Απαλοιφή");
                    try {
                        students = studentService.getStudentsByLastname(Main.getStudentSearchForm().getLastname());
                        listSize = students.size();

                        // Fetching cities associated with each student
                        cities = new ArrayList<>();
                        for (Student student : students) {
                            try {
                                cities.add(cityService.getCityById(student.getCityId())); // assuming getCityId() gets the city id of the student
                            } catch (CityNotFoundException ex) {
                                // handle the exception, maybe log a warning and skip to the next student
                            }
                        }

                        if (listSize == 0 || cities.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No Students or Cities Found", "Info", JOptionPane.ERROR_MESSAGE);
                            dispose();
                            Main.getStudentSearchForm().setEnabled(true);
                            Main.getStudentsUpdateDeleteForm().setVisible(false);
                            return;
                        }

                        listPosition = 0;
                        idTxt.setText(Integer.toString(students.get(listPosition).getId()));
                        firstnameTxt.setText(students.get(listPosition).getFirstname());
                        lastnameTxt.setText(students.get(listPosition).getLastname());

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedBirthdate = dateFormat.format(students.get(listPosition).getBirthdate());
                        birthdateTxt.setText(formattedBirthdate);

                        cityTxt.setText(cities.get(listPosition).getName());
                        genderTxt.setText(students.get(listPosition).getGender());
                    } catch (StudentDAOException e1) {
                        String message = e1.getMessage();
                        JOptionPane.showMessageDialog(null, message, "Error in getting students", JOptionPane.ERROR_MESSAGE);
                    } catch (CityDAOException e2) {
                        String message = e2.getMessage();
                        JOptionPane.showMessageDialog(null, message, "Error in getting cities", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel idLbl = new JLabel("ID");
        idLbl.setBounds(65, 35, 76, 14);
        contentPane.add(idLbl);

        idTxt = new JTextField();
        idTxt.setForeground(new Color(128, 0, 0));
        idTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
        idTxt.setBounds(151, 32, 144, 20);
        contentPane.add(idTxt);
        idTxt.setColumns(10);
        idTxt.setEditable(false);

        JLabel nameLbl = new JLabel("Firstname");
        nameLbl.setBounds(65, 93, 76, 14);
        contentPane.add(nameLbl);

        firstnameTxt = new JTextField();
        firstnameTxt.setBounds(151, 90, 144, 20);
        contentPane.add(firstnameTxt);
        firstnameTxt.setColumns(10);

        JLabel lastnameLbl = new JLabel("Lastname");
        lastnameLbl.setBounds(65, 122, 76, 14);
        contentPane.add(lastnameLbl);

        lastnameTxt = new JTextField();
        lastnameTxt.setBounds(151, 119, 144, 20);
        contentPane.add(lastnameTxt);
        lastnameTxt.setColumns(10);

        JLabel genderLbl = new JLabel("Gender");
        genderLbl.setBounds(65, 152, 76, 14);
        contentPane.add(genderLbl);


        genderTxt = new JTextField();
        genderTxt.setBounds(151, 149, 144, 20);
        contentPane.add(genderTxt);
        genderTxt.setColumns(10);
        genderTxt.setEditable(false);

        JLabel birthdateLbl = new JLabel("Birthdate");
        birthdateLbl.setBounds(65, 182, 76, 14);
        contentPane.add(birthdateLbl);

        birthdateTxt = new JTextField();
        birthdateTxt.setBounds(151, 179, 144, 20);
        contentPane.add(birthdateTxt);
        birthdateTxt.setColumns(10);
        birthdateTxt.setEditable(false);

        JLabel cityLbl = new JLabel("City");
        cityLbl.setBounds(65, 212, 76, 14);
        contentPane.add(cityLbl);

        cityTxt = new JTextField();
        cityTxt.setBounds(151, 209, 144, 20);
        contentPane.add(cityTxt);
        cityTxt.setColumns(10);
        cityTxt.setEditable(false);

        deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int response;
                    String idStr = idTxt.getText();
                    int id = Integer.parseInt(idStr);

                    response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        studentService.deleteStudent(id);
                        JOptionPane.showMessageDialog(null, "Student was deleted successfully", "DELETE", JOptionPane.INFORMATION_MESSAGE);
                        idTxt.setText("");
                        firstnameTxt.setText("");
                        lastnameTxt.setText("");
                        genderTxt.setText("");
                        birthdateTxt.setText("");
                        cityTxt.setText("");
                    }
                } catch (StudentDAOException | StudentNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "DELETE", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteBtn.setForeground(Color.BLUE);
        deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        deleteBtn.setBounds(180, 236, 89, 23);
        contentPane.add(deleteBtn);

        updateBtn = new JButton("Update");
        updateBtn.setForeground(new Color(0, 0, 255));
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTxt.getText().trim();
                String lastname = lastnameTxt.getText().trim();
                String firstname = firstnameTxt.getText().trim();

                if (lastname.equals("") || firstname.equals("") || id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Not valid input", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    StudentUpdateDTO studentDTO = new StudentUpdateDTO();
                    studentDTO.setId(Integer.parseInt(id));
                    studentDTO.setFirstname(firstname);
                    studentDTO.setLastname(lastname);

                    studentDTO.setGender(students.get(listPosition).getGender());
                    studentDTO.setCityId(students.get(listPosition).getCityId());
                    studentDTO.setBirthdate(students.get(listPosition).getBirthdate());

                    Student student = studentService.updateStudent(studentDTO);
                    JOptionPane.showMessageDialog(null, "Student with id " + student.getId() + " was updated", "UPDATE", JOptionPane.PLAIN_MESSAGE);
                } catch (StudentDAOException | StudentNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        updateBtn.setBounds(80, 236, 89, 23);
        contentPane.add(updateBtn);

        nextBtn = new JButton("");
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition <= listSize - 2) {
                    listPosition++;

                    idTxt.setText(String.valueOf(students.get(listPosition).getId()));
                    lastnameTxt.setText(students.get(listPosition).getLastname());
                    firstnameTxt.setText(students.get(listPosition).getFirstname());
                    genderTxt.setText(students.get(listPosition).getGender());
                    cityTxt.setText(cities.get(listPosition).getName());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedBirthdate = dateFormat.format(students.get(listPosition).getBirthdate());
                    birthdateTxt.setText(formattedBirthdate);
                }
            }
        });
        nextBtn.setForeground(Color.BLUE);
        nextBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        nextBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Next_track.png"))));
        nextBtn.setBounds(310, 110, 100, 30);
        contentPane.add(nextBtn);

        previousBtn = new JButton("");
        previousBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition > 0) {
                    listPosition--;

                    idTxt.setText(String.valueOf(students.get(listPosition).getId()));
                    lastnameTxt.setText(students.get(listPosition).getLastname());
                    firstnameTxt.setText(students.get(listPosition).getFirstname());
                    genderTxt.setText(students.get(listPosition).getGender());
                    cityTxt.setText(cities.get(listPosition).getName());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedBirthdate = dateFormat.format(students.get(listPosition).getBirthdate());
                    birthdateTxt.setText(formattedBirthdate);
                }
            }
        });
        previousBtn.setForeground(Color.BLUE);
        previousBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        previousBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png"))));
        previousBtn.setBounds(310, 70, 100, 30);
        contentPane.add(previousBtn);

        firstBtn = new JButton();
        firstBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listPosition = 0;

                idTxt.setText(String.valueOf(students.get(listPosition).getId()));
                lastnameTxt.setText(students.get(listPosition).getLastname());
                firstnameTxt.setText(students.get(listPosition).getFirstname());
                genderTxt.setText(students.get(listPosition).getGender());
                cityTxt.setText(cities.get(listPosition).getName());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedBirthdate = dateFormat.format(students.get(listPosition).getBirthdate());
                birthdateTxt.setText(formattedBirthdate);
            }
        });
        firstBtn.setForeground(Color.BLUE);
        firstBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        firstBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("First record.png"))));
        firstBtn.setBounds(310, 30, 100, 30);
        contentPane.add(firstBtn);

        lastBtn = new JButton();
        lastBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listPosition = students.size() - 1;

                idTxt.setText(String.valueOf(students.get(listPosition).getId()));
                lastnameTxt.setText(students.get(listPosition).getLastname());
                firstnameTxt.setText(students.get(listPosition).getFirstname());
                genderTxt.setText(students.get(listPosition).getGender());
                cityTxt.setText(cities.get(listPosition).getName());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedBirthdate = dateFormat.format(students.get(listPosition).getBirthdate());
                birthdateTxt.setText(formattedBirthdate);
            }
        });
        lastBtn.setForeground(Color.BLUE);
        lastBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        lastBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
        lastBtn.setBounds(310, 150, 100, 30);
        contentPane.add(lastBtn);

        closeBtn = new JButton("Return");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        closeBtn.setForeground(new Color(0, 0, 255));
        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        closeBtn.setBounds(310, 236, 103, 40);
        contentPane.add(closeBtn);
    }
}
