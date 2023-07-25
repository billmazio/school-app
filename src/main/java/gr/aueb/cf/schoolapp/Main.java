//package gr.aueb.cf.schoolapp;
//
//
//import gr.aueb.cf.schoolapp.viewcontroller.*;
//import java.awt.*;
//
//public class Main {
//    private static Login loginForm;
//    private static AdminMenu adminMenu;
//    private static TeachersMenu teachersMenu;
//    private static TeacherSearchForm teacherSearchForm;
//    private static TeachersUpdateDeleteForm teachersUpdateDeleteForm;
//    private static AdminInsertTeachersForm adminInsertTeachersForm;
//    private static SpecialitySearchForm specialitySearchForm;
//    private static AdminInsertCitiesForm adminInsertCitiesForm;
//    private static CitySearchForm citySearchForm;
//    private static StudentsMenu studentsMenu;
//    private static StudentSearchForm studentSearchForm;
//    private static StudentsUpdateDeleteForm studentsUpdateDeleteForm;
//    private static AdminInsertStudentsForm adminInsertStudentsForm;
//    private static AdminInsertSpecialitiesForm adminInsertSpecialitiesForm;
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    loginForm = new Login();
//                    loginForm.setVisible(true);
//
//                    adminMenu = new AdminMenu();
//                    adminMenu.setVisible(false);
//
//                    teachersMenu = new TeachersMenu();
//                    teachersMenu.setVisible(false);
//
//                    adminInsertCitiesForm = new AdminInsertCitiesForm();
//                    adminInsertCitiesForm.setVisible(false);
//
//                    teacherSearchForm = new TeacherSearchForm();
//                    teacherSearchForm.setVisible(false);
//
//                    specialitySearchForm = new SpecialitySearchForm();
//                    specialitySearchForm.setVisible(false);
//
//                    teachersUpdateDeleteForm = new TeachersUpdateDeleteForm();
//                    teachersUpdateDeleteForm.setVisible(false);
//
//                    adminInsertTeachersForm = new AdminInsertTeachersForm();
//                    adminInsertTeachersForm.setVisible(false);
//
//                    citySearchForm = new CitySearchForm();
//                    citySearchForm.setVisible(false);
//
//                    studentsMenu = new StudentsMenu();
//                    studentsMenu.setVisible(false);
//
//                    studentSearchForm = new StudentSearchForm();
//                    studentSearchForm.setVisible(false);
//
//                    studentsUpdateDeleteForm = new StudentsUpdateDeleteForm();
//                    studentsUpdateDeleteForm.setVisible(false);
//
//                    adminInsertStudentsForm = new AdminInsertStudentsForm();
//                    adminInsertStudentsForm.setVisible(false);
//
//                    adminInsertSpecialitiesForm = new AdminInsertSpecialitiesForm();
//                    adminInsertSpecialitiesForm.setVisible(false);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public static AdminMenu getAdminMenu() {
//        return adminMenu;
//    }
//
//    public static TeachersMenu getTeachersMenu() {
//        return teachersMenu;
//    }
//
//    public static TeacherSearchForm getTeacherSearchForm() { return teacherSearchForm;}
//
//    public static AdminInsertCitiesForm getAdminInsertCitiesForm() {
//        return adminInsertCitiesForm;
//    }
//
//    public static CitySearchForm getCitySearchForm() {
//        return citySearchForm;
//    }
//
//    public static Login getLoginForm() {return loginForm;}
//
//    public static TeachersUpdateDeleteForm getTeachersUpdateDeleteForm() {
//        return teachersUpdateDeleteForm;
//    }
//
//    public static AdminInsertTeachersForm getAdminInsertTeachersForm() {
//        return adminInsertTeachersForm;
//    }
//
//    public static AdminInsertSpecialitiesForm getAdminInsertSpecialitiesForm() {
//        return adminInsertSpecialitiesForm;
//    }
//
//    public static SpecialitySearchForm getSpecialitySearchForm() {
//        return specialitySearchForm;
//    }
//
//    public static StudentsMenu getStudentsMenu() {
//        return studentsMenu;
//    }
//
//    public static StudentSearchForm getStudentSearchForm() {
//        return studentSearchForm;
//    }
//
//    public static StudentsUpdateDeleteForm getStudentsUpdateDeleteForm() {
//        return studentsUpdateDeleteForm;
//    }
//
//    public static AdminInsertStudentsForm getAdminInsertStudentsForm() {
//        return adminInsertStudentsForm;
//    }
//}

package gr.aueb.cf.schoolapp;


import gr.aueb.cf.schoolapp.viewcontroller.*;
import java.awt.*;

public class Main {
    private static Login loginForm;
    private static AdminMenu adminMenu;
    private static TeachersMenu teachersMenu;
    private static MeetingsMenu meetingsMenu;
    private static TeacherSearchForm teacherSearchForm;
    private static TeachersUpdateDeleteForm teachersUpdateDeleteForm;
    private static AdminInsertTeachersForm adminInsertTeachersForm;
    private static AdminInsertCitiesForm adminInsertCitiesForm;
    private static AdminInsertMeetingsForm adminInsertMeetingsForm;
    private static StudentsMenu studentsMenu;
    private static StudentSearchForm studentSearchForm;
    private static MeetingsSearchForm meetingsSearchForm;
    private static StudentsUpdateDeleteForm studentsUpdateDeleteForm;
    private static MeetingUpdateDeleteForm meetingUpdateDeleteForm;
    private static AdminInsertStudentsForm adminInsertStudentsForm;
    private static AdminInsertSpecialitiesForm adminInsertSpecialitiesForm;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    loginForm = new Login();
                    loginForm.setVisible(true);

                    adminMenu = new AdminMenu();
                    adminMenu.setVisible(false);

                    teachersMenu = new TeachersMenu();
                    teachersMenu.setVisible(false);

                    meetingsMenu = new MeetingsMenu();
                    meetingsMenu.setVisible(false);

                    adminInsertMeetingsForm = new AdminInsertMeetingsForm();
                    adminInsertMeetingsForm.setVisible(false);

                    adminInsertCitiesForm = new AdminInsertCitiesForm();
                    adminInsertCitiesForm.setVisible(false);

                    teacherSearchForm = new TeacherSearchForm();
                    teacherSearchForm.setVisible(false);

                    meetingsSearchForm = new MeetingsSearchForm();
                    meetingsSearchForm.setVisible(false);

                    meetingUpdateDeleteForm = new MeetingUpdateDeleteForm();
                    meetingUpdateDeleteForm.setVisible(false);

                    teachersUpdateDeleteForm = new TeachersUpdateDeleteForm();
                    teachersUpdateDeleteForm.setVisible(false);

                    adminInsertTeachersForm = new AdminInsertTeachersForm();
                    adminInsertTeachersForm.setVisible(false);

                    studentsMenu = new StudentsMenu();
                    studentsMenu.setVisible(false);

                    studentSearchForm = new StudentSearchForm();
                    studentSearchForm.setVisible(false);

                    studentsUpdateDeleteForm = new StudentsUpdateDeleteForm();
                    studentsUpdateDeleteForm.setVisible(false);

                    adminInsertStudentsForm = new AdminInsertStudentsForm();
                    adminInsertStudentsForm.setVisible(false);

                    adminInsertSpecialitiesForm = new AdminInsertSpecialitiesForm();
                    adminInsertSpecialitiesForm.setVisible(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static AdminMenu getAdminMenu() {
        return adminMenu;
    }

    public static TeachersMenu getTeachersMenu() {
        return teachersMenu;
    }

    public static TeacherSearchForm getTeacherSearchForm() { return teacherSearchForm;}

    public static AdminInsertCitiesForm getAdminInsertCitiesForm() {
        return adminInsertCitiesForm;
    }

    public static Login getLoginForm() {
        return loginForm;
    }

    public static TeachersUpdateDeleteForm getTeachersUpdateDeleteForm() {
        return teachersUpdateDeleteForm;
    }

    public static AdminInsertTeachersForm getAdminInsertTeachersForm() {
        return adminInsertTeachersForm;
    }

    public static StudentsMenu getStudentsMenu() {
        return studentsMenu;
    }

    public static StudentSearchForm getStudentSearchForm() {
        return studentSearchForm;
    }

    public static StudentsUpdateDeleteForm getStudentsUpdateDeleteForm() {
        return studentsUpdateDeleteForm;
    }

    public static AdminInsertStudentsForm getAdminInsertStudentsForm() {
        return adminInsertStudentsForm;
    }

    public static AdminInsertMeetingsForm getAdminInsertMeetingsForm() {
        return adminInsertMeetingsForm;
    }

    public static MeetingsSearchForm getMeetingsSearchForm() {
        return meetingsSearchForm;
    }

    public static MeetingUpdateDeleteForm getMeetingUpdateDeleteForm() {
        return meetingUpdateDeleteForm;
    }

    public static AdminInsertSpecialitiesForm getAdminInsertSpecialitiesForm() {
        return adminInsertSpecialitiesForm;
    }

    public static MeetingsMenu getMeetingsMenu() {
        return meetingsMenu;
    }
}

