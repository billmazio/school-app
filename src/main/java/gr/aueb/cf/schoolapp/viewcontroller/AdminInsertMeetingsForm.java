package gr.aueb.cf.schoolapp.viewcontroller;
import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IMeetingDAO;
import gr.aueb.cf.schoolapp.dao.MeetingDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.IMeetingService;
import gr.aueb.cf.schoolapp.service.MeetingServiceImpl;
import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.StudentServiceImpl;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class AdminInsertMeetingsForm extends JFrame {
    private JPanel contentPane;
    private JComboBox<String> teacherComboBox; // Dropdown list for teachers
    private JComboBox<String> studentComboBox; // Dropdown list for students
    private JTextField meetingRoomTxt;
    private JTextField meetingDateTxt;
    private JButton insertBtn;

    private IMeetingService meetingService;
    private IStudentService studentService;
    private ITeacherService teacherService;

    public AdminInsertMeetingsForm() throws StudentDAOException, TeacherDAOException {
        IStudentDAO studentDAO = new StudentDAOImpl();
        studentService = new StudentServiceImpl(studentDAO);

        ITeacherDAO teacherDAO = new TeacherDAOImpl();
        teacherService = new TeacherServiceImpl(teacherDAO);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 538, 468);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        int labelWidth = 150; // Width for labels
        int inputWidth = 207; // Width for input fields

        // Teacher
        JLabel teacherLbl = new JLabel("Καθηγητής");
        teacherLbl.setForeground(new Color(128, 0, 0));
        teacherLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        teacherLbl.setBounds(68, 75, labelWidth, 17);
        contentPane.add(teacherLbl);

        teacherComboBox = new JComboBox<>();
        teacherComboBox.setBounds(68 + labelWidth + 5, 73, inputWidth, 22);
        contentPane.add(teacherComboBox);

        // Student
        JLabel studentLbl = new JLabel("Μαθητής");
        studentLbl.setForeground(new Color(128, 0, 0));
        studentLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        studentLbl.setBounds(68, 105, labelWidth, 17);
        contentPane.add(studentLbl);

        studentComboBox = new JComboBox<>();
        studentComboBox.setBounds(68 + labelWidth + 5, 103, inputWidth, 22);
        contentPane.add(studentComboBox);

        // Meeting Room
        JLabel meetingRoomLbl = new JLabel("Αίθουσα Συνάντησης");
        meetingRoomLbl.setForeground(new Color(128, 0, 0));
        meetingRoomLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        meetingRoomLbl.setBounds(68, 135, labelWidth, 17);
        contentPane.add(meetingRoomLbl);

        meetingRoomTxt = new JTextField();
        meetingRoomTxt.setColumns(10);
        meetingRoomTxt.setBounds(68 + labelWidth + 5, 133, inputWidth, 20);
        contentPane.add(meetingRoomTxt);

        // Meeting Date
        JLabel meetingDateLbl = new JLabel("Ημερομηνία Συνάντησης (yyyy-MM-dd)");
        meetingDateLbl.setForeground(new Color(128, 0, 0));
        meetingDateLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        meetingDateLbl.setBounds(68, 165, labelWidth, 17);
        contentPane.add(meetingDateLbl);

        meetingDateTxt = new JTextField();
        meetingDateTxt.setColumns(10);
        meetingDateTxt.setBounds(68 + labelWidth + 5, 163, inputWidth, 20);
        contentPane.add(meetingDateTxt);

        insertBtn = new JButton("Εισαγωγή");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    insertMeeting();
                } catch (StudentNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (TeacherNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (StudentDAOException ex) {
                    throw new RuntimeException(ex);
                } catch (TeacherDAOException ex) {
                    throw new RuntimeException(ex);
                }
                Main.getAdminMenu().setVisible(true);
                AdminInsertMeetingsForm.this.dispose();
            }
        });
        insertBtn.setForeground(new Color(0, 0, 255));
        insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        insertBtn.setBounds(129, 205, 129, 41);
        contentPane.add(insertBtn);

        JButton closeBtn = new JButton("Κλείσιμο");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        closeBtn.setForeground(Color.BLUE);
        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        closeBtn.setBounds(266, 205, 129, 41);
        contentPane.add(closeBtn);

        IMeetingDAO meetingDAO = new MeetingDAOImpl();
        meetingService = new MeetingServiceImpl(meetingDAO);

        try {
            loadTeachers(); // Call this method to populate the teacherComboBox
            loadStudents(); // Call this method to populate the studentComboBox
        } catch (TeacherNotFoundException | StudentNotFoundException | TeacherDAOException | StudentDAOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTeachers() throws TeacherNotFoundException, TeacherDAOException {
        List<Teacher> teachers = teacherService.getAllTeachers();
        for (Teacher teacher : teachers) {
            teacherComboBox.addItem(teacher.getLastname()); // Add teacher last name to the dropdown
        }
    }

    private void loadStudents() throws StudentNotFoundException, StudentDAOException {
        List<Student> students = studentService.getAllStudents();
        for (Student student : students) {
            studentComboBox.addItem(student.getLastname()); // Add student last name to the dropdown
        }
    }

    private void insertMeeting() throws StudentNotFoundException, TeacherNotFoundException, StudentDAOException, TeacherDAOException {
        int teacherId = -1;
        int studentId = -1;

        String selectedTeacherName = (String) teacherComboBox.getSelectedItem();
        String selectedStudentName = (String) studentComboBox.getSelectedItem();
        String meetingRoom = meetingRoomTxt.getText().trim();
        String meetingDateString = meetingDateTxt.getText().trim();

        if (meetingRoom.isEmpty() || meetingDateString.isEmpty() || selectedStudentName == null || selectedTeacherName == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Get the Teacher and Student objects based on the selected names
            List<Teacher> selectedTeachers = teacherService.getTeachersByLastname(selectedTeacherName);
            List<Student> selectedStudents = studentService.getStudentsByLastname(selectedStudentName);

            if (selectedTeachers.isEmpty()) {
                throw new TeacherNotFoundException("Selected teacher not found.");
            }
            if (selectedStudents.isEmpty()) {
                throw new StudentNotFoundException("Selected student not found.");
            }

            // For simplicity, we'll assume there's only one matching teacher and student in the list
            Teacher selectedTeacher = selectedTeachers.get(0);
            Student selectedStudent = selectedStudents.get(0);

            teacherId = selectedTeacher.getId();
            studentId = selectedStudent.getId();

            // Parse meeting date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date meetingDate = dateFormat.parse(meetingDateString);

            // Create a MeetingInsertDTO object with the retrieved IDs and other details
            MeetingInsertDTO meeting = new MeetingInsertDTO();
            meeting.setTeacherId(teacherId);
            meeting.setStudentId(studentId);
            meeting.setMeetingRoom(meetingRoom);

            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlMeetingDate = new java.sql.Date(meetingDate.getTime());
            meeting.setMeetingDate(sqlMeetingDate);

            // Insert the meeting into the database
            meetingService.insertMeeting(meeting);
            JOptionPane.showMessageDialog(null, "Meeting inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid meeting date format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (TeacherNotFoundException | StudentNotFoundException | MeetingDAOException e) {
            JOptionPane.showMessageDialog(null, "Error occurred during meeting insertion.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

//
//package gr.aueb.cf.schoolapp.viewcontroller;
//
//import gr.aueb.cf.schoolapp.Main;
//import gr.aueb.cf.schoolapp.dao.*;
//import gr.aueb.cf.schoolapp.dto.MeetingInsertDTO;
//import gr.aueb.cf.schoolapp.model.Teacher;
//import gr.aueb.cf.schoolapp.service.*;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class AdminInsertMeetingsForm extends JFrame {
//
//    private static final long serialVersionUID = 123456;
//    private JPanel contentPane;
//    private JTextField idTxt;
//    private JTextField teacherTxt;
//    private JTextField studentTxt;
//    private JTextField roomTxt;
//    private JTextField dateTxt;
//    private ITeacherService teacherService;
//    private IStudentService studentService;
//    private IMeetingService meetingService;
//    private int teacherId;
//    private int studentId;
//
//    private int id;
//
//
//    public AdminInsertMeetingsForm() {
//        IMeetingDAO meetingDAO = new MeetingDAOImpl();
//        meetingService = new MeetingServiceImpl(meetingDAO);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 538, 300);
//        setLocationRelativeTo(null);
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setContentPane(contentPane);
//        contentPane.setLayout(null);
//
//        JLabel roomLbl = new JLabel("Αίθουσα Συναντήσεων");
//        roomLbl.setForeground(new Color(128, 0, 0));
//        roomLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
//        roomLbl.setBounds(25, 75, 150, 20);
//        contentPane.add(roomLbl);
//
//        roomTxt = new JTextField();
//        roomTxt.setBounds(180, 75, 187, 20);
//        contentPane.add(roomTxt);
//        roomTxt.setColumns(10);
//
//        JLabel teacherLbl = new JLabel("Teacher ID");
//        teacherLbl.setForeground(new Color(128, 0, 0));
//        teacherLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
//        teacherLbl.setBounds(25, 105, 150, 20);
//        contentPane.add(teacherLbl);
//
//        teacherTxt = new JTextField();
//        teacherTxt.setBounds(180, 105, 187, 20);
//        contentPane.add(teacherTxt);
//        teacherTxt.setColumns(10);
//
//        JLabel studentLbl = new JLabel("Student ID");
//        studentLbl.setForeground(new Color(128, 0, 0));
//        studentLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
//        studentLbl.setBounds(25, 135, 150, 20);
//        contentPane.add(studentLbl);
//
//        studentTxt = new JTextField();
//        studentTxt.setBounds(180, 135, 187, 20);
//        contentPane.add(studentTxt);
//        studentTxt.setColumns(10);
//
//        JLabel dateLbl = new JLabel("Ημερομηνία Συνάντησης (YYYY-MM-DD HH:mm:ss)");
//        dateLbl.setForeground(new Color(128, 0, 0));
//        dateLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
//        dateLbl.setBounds(25, 165, 290, 20);
//        contentPane.add(dateLbl);
//
//        dateTxt = new JTextField();
//        dateTxt.setColumns(10);
//        dateTxt.setBounds(25, 190, 290, 20);
//        contentPane.add(dateTxt);
//
//        JLabel idLbl = new JLabel("Meeting ID");
//        idLbl.setForeground(new Color(128, 0, 0));
//        idLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
//        idLbl.setBounds(25, 45, 150, 20);
//        contentPane.add(idLbl);
//
//        idTxt = new JTextField();
//        idTxt.setBounds(180, 45, 187, 20);
//        contentPane.add(idTxt);
//        idTxt.setColumns(10);
//
//
//        JButton insertBtn = new JButton("Εισαγωγή");
//        insertBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                insertMeeting();
//                Main.getAdminMenu().setVisible(true);
//                AdminInsertMeetingsForm.this.dispose();
//            }
//        });
//        insertBtn.setForeground(new Color(0, 0, 255));
//        insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
//        insertBtn.setBounds(160, 200, 129, 41);
//        contentPane.add(insertBtn);
//
//        JButton closeBtn = new JButton("Κλείσιμο");
//        closeBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//            }
//        });
//        closeBtn.setForeground(Color.BLUE);
//        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
//        closeBtn.setBounds(297, 200, 129, 41);
//        contentPane.add(closeBtn);
//
//        IMeetingDAO dao = new MeetingDAOImpl();
//        meetingService = new MeetingServiceImpl(dao);
//    }
//
//    private void insertMeeting() {
//        // Generate a unique ID programmatically
//        int id = generateUniqueMeetingID();
//
//        int teacherId = Integer.parseInt(teacherTxt.getText().trim());
//        int studentId = Integer.parseInt(studentTxt.getText().trim());
//        String room = roomTxt.getText().trim();
//        String meetingDateStr = dateTxt.getText().trim();
//
//        if (room.isEmpty() || meetingDateStr.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        java.sql.Date meetingDate;
//        MeetingInsertDTO dto = new MeetingInsertDTO();
//
//        java.sql.Date sqlDate;
//        try {
//            Date utilDate = dateFormat.parse(meetingDateStr);
//            sqlDate = new java.sql.Date(utilDate.getTime());
//
//        } catch (ParseException e) {
//            JOptionPane.showMessageDialog(null, "Invalid birthdate format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        dto.setId(id); // Set the generated unique ID in the DTO
//        dto.setTeacherId(teacherId);
//        dto.setStudentId(studentId);
//        dto.setMeetingRoom(room);
//        dto.setMeetingDate(sqlDate); // Set the parsed meetingDate in the DTO
//
//        try {
//            meetingService.insertMeeting(dto);
//            JOptionPane.showMessageDialog(null, "Meeting inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error occurred during meeting insertion.", "Error", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//        }
//    }
//
//    // Method to generate a unique ID for the meeting
//    private int generateUniqueMeetingID() {
//        // You can implement a logic here to generate a unique ID, such as using a database sequence or UUID.
//        // For simplicity, let's assume we are just incrementing a static counter for demonstration purposes.
//        // In a real application, you should use a proper unique ID generation mechanism.
//        return ++id;
//    }
//
//}