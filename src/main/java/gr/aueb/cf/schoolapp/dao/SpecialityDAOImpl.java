package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapp.model.Speciality;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static gr.aueb.cf.schoolapp.service.util.DBUtil.getConnection;


public class SpecialityDAOImpl implements ISpecialityDAO {

    @Override
    public Speciality insert(Speciality speciality) throws SpecialityDAOException {
        String sql = "INSERT INTO SPECIALITIES(SPECIALITY) VALUES (?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, speciality.getSpeciality());

            int n = ps.executeUpdate();
            if (n >= 1) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        speciality.setId(generatedKeys.getInt(1));
                    }
                }
                JOptionPane.showMessageDialog(null, n + " row(s) affected", "Successful Insert", JOptionPane.PLAIN_MESSAGE);
                return speciality;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("SQL Error in Speciality insert" + speciality);
        }
    }

    @Override
    public Speciality update(Speciality speciality) throws SpecialityDAOException {
        String sql = "UPDATE SPECIALITIES SET SPECIALITY = ? WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, speciality.getSpeciality());
            ps.setInt(2, speciality.getId());

            int n = ps.executeUpdate();
            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " row(s) affected", "Successful Update", JOptionPane.PLAIN_MESSAGE);
                return speciality;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("SQL Error in Speciality Update" + speciality);
        }
    }

    @Override
    public void delete(int id) throws SpecialityDAOException {
        String sql = "DELETE FROM SPECIALITIES WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("SQL Error in Speciality with id: " + id);
        }
    }


//
//    @Override
//    public List<Speciality> getBySpeciality(String specialityTitle) throws SpecialityDAOException {
//        String sql = "SELECT * FROM SPECIALITIES";
//        List<Speciality> specialities = new ArrayList<>();
//        ResultSet rs = null;
//
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Speciality speciality = new Speciality(
//                        rs.getInt("ID"),
//                        rs.getString("SPECIALITY")
//                );
//                specialities.add(speciality);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new SpecialityDAOException("SQL Error in Speciality GetAll");
//        } finally {
//            try {
//                if (rs != null) rs.close();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        }
//        return specialities;
//    }
//
@Override
public Optional<Speciality> getBySpeciality(String specialityTitle) throws SpecialityDAOException {
    String sql = "SELECT * FROM SPECIALITIES WHERE SPECIALITY = ?";
    Speciality speciality = null;
    ResultSet rs = null;

    try (Connection connection = getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setString(1, specialityTitle);
        rs = ps.executeQuery();

        if (rs.next()) {
            speciality = new Speciality(
                    rs.getInt("ID"),
                    rs.getString("SPECIALITY")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SpecialityDAOException("SQL Error in Speciality getBySpeciality");
    } finally {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    return Optional.ofNullable(speciality);
}

    @Override
    public Speciality getById(int id) throws SpecialityDAOException {
        String sql = "SELECT * FROM SPECIALITIES WHERE ID = ?";
        Speciality speciality = null;
        ResultSet rs = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                speciality = new Speciality(
                        rs.getInt("ID"),
                        rs.getString("SPECIALITY")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("SQL Error in Speciality Get with id: " + id);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return speciality;
    }

    @Override
    public List<Speciality> getAllSpecialities() throws SpecialityDAOException {
        String sql = "SELECT * FROM SPECIALITIES";
        List<Speciality> specialities = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            rs = ps.executeQuery();

            while (rs.next()) {
                Speciality speciality = new Speciality(rs.getInt("ID"), rs.getString("SPECIALITY"));
                specialities.add(speciality);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("SQL Error in Speciality GetAll");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return specialities;
    }

}
