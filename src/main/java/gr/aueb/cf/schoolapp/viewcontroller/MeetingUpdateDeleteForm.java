package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.*;
import gr.aueb.cf.schoolapp.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolapp.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.*;
import gr.aueb.cf.schoolapp.service.exceptions.MeetingNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MeetingUpdateDeleteForm extends JFrame {
    private JPanel contentPane;
    private JTextField idTxt;
    private JTextField teacherIdTxt;
    private JTextField studentIdTxt;
    private JTextField teacherTxt;
    private JTextField studentTxt;
    private JTextField meetingRoomTxt;
    private JTextField meetingDateTxt;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JButton nextBtn;
    private JButton previousBtn;
    private JButton firstBtn;
    private JButton lastBtn;
    private JButton closeBtn;

    private IMeetingDAO dao = new MeetingDAOImpl();
    private IMeetingService meetingService = new MeetingServiceImpl(dao);

    private ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    private IStudentDAO studentDAO = new StudentDAOImpl();
    private IStudentService studentService = new StudentServiceImpl(studentDAO);

    private List<Meeting> meetings;
    private List<Teacher> teachers;
    private List<Student> students;
    private int listPosition;
    private int listSize;

    public MeetingUpdateDeleteForm() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("eduv2.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        addWindowListener(new WindowAdapter() {


            @Override
            public void windowActivated(WindowEvent e) {
                setTitle("Ενημέρωση / Διαγραφή");
                try {
                    meetings = meetingService.getMeetingsByMeetingRoom(
                            Main.getMeetingsSearchForm().getByRoom());
                    listSize = meetings.size();

                    teachers = new ArrayList<>();
                    for (Meeting meeting : meetings) {
                        try {
                            teachers.add(teacherService.getTeacherById(meeting.getTeacherId()));
                        } catch (TeacherNotFoundException e1) {
                            // Handle exception
                        }
                    }

                    students = new ArrayList<>();
                    for (Meeting meeting : meetings) {
                        try {
                            students.add(studentService.getStudentById(meeting.getStudentId()));
                        } catch (StudentNotFoundException e1) {
                            // Handle exception
                        } catch (StudentDAOException ex) {

                        }
                    }

                    if (listSize == 0 || teachers.isEmpty() || students.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Meetings Found", "Info", JOptionPane.ERROR_MESSAGE);
                        dispose();
                        Main.getMeetingsSearchForm().setEnabled(true);
                        Main.getMeetingUpdateDeleteForm().setVisible(false);
                        return;
                    }
                    listPosition = 0;

                    idTxt.setText(Integer.toString(meetings.get(listPosition).getId()));
//// Corrected the meeting date formatting
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedMeetingDate = dateFormat.format(meetings.get(listPosition).getMeetingDate());
                //    teacherTxt.setText(teachers.get(listPosition).getLastname());
                   // studentTxt.setText(students.get(listPosition).getLastname());
                    meetingDateTxt.setText(formattedMeetingDate);
                } catch (MeetingDAOException | TeacherDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error in getting meetings", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel idLbl = new JLabel("Meeting ID");
        idLbl.setBounds(65, 15, 76, 14);
        contentPane.add(idLbl);

        idTxt = new JTextField();
        idTxt.setForeground(new Color(128, 0, 0));
        idTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
        idTxt.setBounds(151, 15, 144, 20);
        contentPane.add(idTxt);
        idTxt.setColumns(10);
        idTxt.setEditable(false);

        JLabel teacherIdLbl = new JLabel("Teacher ID");
        teacherIdLbl.setBounds(65, 35, 76, 14);
        contentPane.add(teacherIdLbl);

        teacherIdTxt = new JTextField();
        teacherIdTxt.setForeground(new Color(128, 0, 0));
        teacherIdTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
        teacherIdTxt.setBounds(151, 32, 144, 20);
        contentPane.add(teacherIdTxt);
        teacherIdTxt.setColumns(10);
        teacherIdTxt.setEditable(true);

        JLabel studentIdLbl = new JLabel("Student ID");
        studentIdLbl.setBounds(65, 65, 76, 14);
        contentPane.add(studentIdLbl);

        studentIdTxt = new JTextField();
        studentIdTxt.setBounds(151, 62, 144, 20);
        contentPane.add(studentIdTxt);
        studentIdTxt.setColumns(10);
        studentIdTxt.setEditable(true);

        JLabel meetingRoomLbl = new JLabel("Meeting Room");
        meetingRoomLbl.setBounds(65, 95, 76, 14);
        contentPane.add(meetingRoomLbl);

        meetingRoomTxt = new JTextField();
        meetingRoomTxt.setBounds(151, 92, 144, 20);
        contentPane.add(meetingRoomTxt);
        meetingRoomTxt.setColumns(10);

        JLabel meetingDateLbl = new JLabel("Meeting Date");
        meetingDateLbl.setBounds(65, 125, 76, 14);
        contentPane.add(meetingDateLbl);

        meetingDateTxt = new JTextField();
        meetingDateTxt.setBounds(151, 122, 144, 20);
        contentPane.add(meetingDateTxt);
        meetingDateTxt.setColumns(10);

        deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int response;
                    String idStr = idTxt.getText();
                    int id = Integer.parseInt(idStr);

                    response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    meetingService.deleteMeeting(id);
                    JOptionPane.showMessageDialog(null, "Meeting was deleted successfully", "DELETE", JOptionPane.INFORMATION_MESSAGE);
                    idTxt.setText("");
                    meetingRoomTxt.setText("");
                    meetingDateTxt.setText("");
                    teacherTxt.setText("");
                    studentTxt.setText("");
                }


                } catch (MeetingDAOException |MeetingNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "DELETE", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        deleteBtn.setForeground(Color.BLUE);
        deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        deleteBtn.setBounds(180, 170, 89, 23);
        contentPane.add(deleteBtn);

        updateBtn = new JButton("Update");
        updateBtn.setForeground(new Color(0, 0, 255));
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTxt.getText().trim();
                String meetingRoom = meetingRoomTxt.getText().trim();


                if (meetingRoom.isEmpty() || id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Not valid input", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                try {
                    MeetingUpdateDTO meetingUpdateDTO = new MeetingUpdateDTO();
                    meetingUpdateDTO.setId(Integer.parseInt(id));
                    meetingUpdateDTO.setMeetingRoom(meetingRoom);



                    meetingUpdateDTO.setStudentId(meetings.get(listPosition).getStudentId());
                    meetingUpdateDTO.setTeacherId(meetings.get(listPosition).getTeacherId());
                    meetingUpdateDTO.setMeetingDate(meetings.get(listPosition).getMeetingDate());

                   Meeting meeting = meetingService.updateMeeting(meetingUpdateDTO);
                    JOptionPane.showMessageDialog(null, "Student with id " + meeting.getId()+ " was updated", "UPDATE", JOptionPane.PLAIN_MESSAGE);
                } catch ( MeetingNotFoundException | MeetingDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        updateBtn.setBounds(80, 170, 89, 23);
        contentPane.add(updateBtn);

        // Additional buttons and their functionalities (nextBtn, previousBtn, firstBtn, lastBtn, closeBtn)
        nextBtn = new JButton("");
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition <= listSize - 2) {
                    listPosition++;

                    idTxt.setText(String.valueOf(meetings.get(listPosition).getId()));
                    teacherIdTxt.setText(String.valueOf(meetings.get(listPosition).getTeacherId()));
                    studentIdTxt.setText(String.valueOf(meetings.get(listPosition).getStudentId()));
                    meetingRoomTxt.setText(meetings.get(listPosition).getMeetingRoom());

                    // Corrected the meeting date formatting
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedMeetingDate = dateFormat.format(meetings.get(listPosition).getMeetingDate());
                    meetingDateTxt.setText(formattedMeetingDate);
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

                    idTxt.setText(String.valueOf(meetings.get(listPosition).getId()));
                    teacherIdTxt.setText(String.valueOf(meetings.get(listPosition).getTeacherId()));
                    studentIdTxt.setText(String.valueOf(meetings.get(listPosition).getStudentId()));
                    meetingRoomTxt.setText(meetings.get(listPosition).getMeetingRoom());

                    // Corrected the meeting date formatting
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedMeetingDate = dateFormat.format(meetings.get(listPosition).getMeetingDate());
                    meetingDateTxt.setText(formattedMeetingDate);
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

                idTxt.setText(String.valueOf(meetings.get(listPosition).getId()));
                teacherIdTxt.setText(String.valueOf(meetings.get(listPosition).getTeacherId()));
                studentIdTxt.setText(String.valueOf(meetings.get(listPosition).getStudentId()));
                meetingRoomTxt.setText(meetings.get(listPosition).getMeetingRoom());

                // Corrected the meeting date formatting
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedMeetingDate = dateFormat.format(meetings.get(listPosition).getMeetingDate());
                meetingDateTxt.setText(formattedMeetingDate);
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
                listPosition = meetings.size() - 1;

                idTxt.setText(String.valueOf(meetings.get(listPosition).getId()));
                teacherIdTxt.setText(String.valueOf(meetings.get(listPosition).getTeacherId()));
                studentIdTxt.setText(String.valueOf(meetings.get(listPosition).getStudentId()));
                meetingRoomTxt.setText(meetings.get(listPosition).getMeetingRoom());

                // Corrected the meeting date formatting
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedMeetingDate = dateFormat.format(meetings.get(listPosition).getMeetingDate());
                meetingDateTxt.setText(formattedMeetingDate);
            }
        });
        lastBtn.setForeground(Color.BLUE);
        lastBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        lastBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
        lastBtn.setBounds(310, 150, 100, 30);
        contentPane.add(lastBtn);

        // Close button
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

