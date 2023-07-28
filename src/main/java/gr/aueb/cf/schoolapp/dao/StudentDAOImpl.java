package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public Student insert(Student student) throws StudentDAOException {
        String sql = "INSERT INTO STUDENTS(FIRSTNAME, LASTNAME, GENDER, BIRTH_DATE, CITY_ID) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                String firstname = student.getFirstname();
                String lastname = student.getLastname();
                String gender = student.getGender();
                Date date = student.getBirthdate();
                Integer cityId = student.getCityId();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, gender);
            ps.setDate(4, (java.sql.Date) date);

            ps.setInt(5, cityId);

            int n = ps.executeUpdate();
            if (n >= 1) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        student.setId(generatedKeys.getInt(1));
                    }
                }
                return student;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Error in Student insert" + student);
        }
    }

    @Override
    public Student update(Student student) throws StudentDAOException {
        String sql = "UPDATE STUDENTS SET FIRSTNAME = ?, LASTNAME = ?, GENDER = ?, BIRTH_DATE = ?, CITY_ID = ? WHERE ID = ?";

        if (student == null || student.getId() == null) {
            throw new IllegalArgumentException("Invalid student or missing student ID");
        }
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getGender());
            ps.setDate(4, new java.sql.Date(student.getBirthdate().getTime()));
            ps.setInt(5, student.getCityId());
            ps.setInt(6, student.getId());

            int n = ps.executeUpdate();
            if (n >= 1) {
                return student;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Error in Student Update" + student);
        }
    }

//    @Override
//    public Student update(Student student) throws StudentDAOException {
//        String sql = "UPDATE STUDENTS SET FIRSTNAME = ?, LASTNAME = ?, GENDER = ?, BIRTH_DATE = ?, CITY_ID = ? WHERE ID = ?";
//
//        try (Connection connection = DBUtil.getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, student.getFirstname());
//            ps.setString(2, student.getLastname());
//            ps.setString(3, student.getGender());
//            ps.setDate(4, new java.sql.Date(student.getBirthdate().getTime()));
//            java.util.Date utilDate = student.getBirthdate();
//            if (utilDate != null) {
//                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                ps.setDate(4, sqlDate);
//            } else {
//                ps.setNull(4, java.sql.Types.DATE); // or set a default date if needed
//            }
//
//            ps.setInt(5, student.getCityId());
//            ps.setInt(6, student.getId());
//
//
//            int n = ps.executeUpdate();
//            if (n >= 1) {
//                return student;
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new StudentDAOException("SQL Error in Student Update" + student);
//        }
//    }


    @Override
    public void delete(int id) throws StudentDAOException {
        String sql = "DELETE FROM STUDENTS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Error in Student with id: " + id);
        }
    }

    @Override
    public List<Student> getByLastname(String lastname) throws StudentDAOException {
        String sql = "SELECT * FROM STUDENTS WHERE LASTNAME LIKE ?";
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs;

            ps.setString(1, lastname + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("GENDER"),
                        rs.getDate("BIRTH_DATE"),
                        rs.getInt("CITY_ID")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Error in Student Get with lastname" + lastname);
        }

        return students;
    }

    @Override
    public Student getById(int id) throws StudentDAOException {
        String sql = "SELECT * FROM STUDENTS WHERE ID = ?";
        Student student = null;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("GENDER"),
                        rs.getDate("BIRTH_DATE"),
                        rs.getInt("CITY_ID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Error in Student Get with id: " + id);
        }

        return student;
    }
    @Override
    public List<Student> getAllStudents() throws StudentDAOException {
        String sql = "SELECT * FROM STUDENTS";
        List<Student> students = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String firstname = rs.getString("FIRSTNAME");
                String lastname = rs.getString("LASTNAME");
                String gender = rs.getString("GENDER");
                Date birthDate = rs.getDate("BIRTH_DATE");
                int cityId = rs.getInt("CITY_ID");

                // Create a new Student object and add it to the list
                Student student = new Student(id, firstname, lastname, gender, birthDate, cityId);
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Error in Student GetAll");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return students;
    }

}
