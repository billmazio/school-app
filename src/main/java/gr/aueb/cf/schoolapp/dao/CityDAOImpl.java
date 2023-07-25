package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityDAOImpl implements ICityDAO {

//    @Override
//    public List<City> getByCityName(String name) throws CityDAOException {
//        String sql = "SELECT * FROM CITIES";
//        List<City> cities = new ArrayList<>();
//        ResultSet rs = null;
//
//        try (Connection connection = DBUtil.getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                City city = new City(rs.getInt("ID"), rs.getString("NAME"));
//                cities.add(city);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new CityDAOException("SQL Error in City GetAll");
//        } finally {
//            try {
//                if (rs != null) rs.close();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        }
//        return cities;
//    }
@Override
public Optional<List<City>> getByCityName(String name) throws CityDAOException {
    String sql = "SELECT * FROM CITIES WHERE NAME = ?";
    List<City> cities = new ArrayList<>();
    ResultSet rs = null;

    try (Connection connection = DBUtil.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setString(1, name);
        rs = ps.executeQuery();

        while (rs.next()) {
            City city = new City(rs.getInt("ID"), rs.getString("NAME"));
            cities.add(city);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new CityDAOException("SQL Error in City GetByCityName", e);
    } finally {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    if (cities.isEmpty()) {
        return Optional.empty();
    } else {
        return Optional.of(cities);
    }
}


    @Override
    public City getById(int id) throws CityDAOException {
        String sql = "SELECT * FROM CITIES WHERE ID =  ?";
        City city = null;
        ResultSet rs = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                city = new City(rs.getInt("ID"), rs.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("SQL Error in City GetById" + id);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return city;
    }

    @Override
    public City insert(City city) throws CityDAOException {
        String sql = "INSERT INTO CITIES(NAME) VALUES (?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {

            String name = city.getName();
            ps.setString(1, name);

            int n = ps.executeUpdate();
            if (n >= 1) {
                try  (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        city.setId(generatedKeys.getInt(1));
                    }
                }
                JOptionPane.showMessageDialog(null, n + " rows affected",  " Successful Insert", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("SQL Error in City insert" + city);
        }
        return city;
    }

    @Override
    public City update(City city) throws CityDAOException {
        String sql = "UPDATE CITIES SET NAME = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = city.getId();
            String name = city.getName();

            ps.setString(1, name);
            ps.setInt(2, id);

            int n = ps.executeUpdate();
            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Successful Update", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("SQL Error in City Update" + city);
        }
        return city;
    }

    @Override
    public void delete(int id) throws CityDAOException {
        String sql = "DELETE FROM CITIES WHERE ID = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("SQL Error in City with id: " + id);
        }
    }

    @Override
    public List<City> getAllCities() throws CityDAOException {
        String sql = "SELECT * FROM CITIES";
        List<City> cities = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            rs = ps.executeQuery();

            while (rs.next()) {
                City city = new City(rs.getInt("ID"), rs.getString("NAME"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("SQL Error in City GetAll");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return cities;
    }

}