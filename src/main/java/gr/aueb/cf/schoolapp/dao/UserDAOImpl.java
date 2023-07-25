package gr.aueb.cf.schoolapp.dao;



import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    @Override
    public User insert(User user) throws UserDAOException {
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            String username = user.getUsername();
            String password= user.getPassword();



            ps.setString(1, username);
            ps.setString(2, password);


            int n = ps.executeUpdate();
            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Successful Insert", JOptionPane.PLAIN_MESSAGE);
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL Error in User insert: " + user, e);
        }
    }


    @Override
    public User update(User user) throws UserDAOException {
        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, id);

            int n = ps.executeUpdate();
            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Successful Update", JOptionPane.PLAIN_MESSAGE);
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL Error in Teacher Update" + user);
        }
    }
    @Override
    public void delete(int id) throws UserDAOException {
        String sql = "DELETE FROM USERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL Error in User with id: " + id);
        }
    }



    @Override
    public List<User> getByUsername (String username) throws UserDAOException {
        String sql = "SELECT * FROM USERS WHERE USERNAME = ?";

        List<User> users = new ArrayList<>();
        ResultSet rs = null;

        try
                (Connection connection = DBUtil.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt("ID"), rs.getString("USERNAME"),rs.getString("PASSWORD"));
                users.add(user);
            }

        } catch (SQLException e){
            e.printStackTrace();
            throw new UserDAOException("SQL Error in User Delete with username" + username);
        } finally {
            try {
                if (rs != null) rs.close();
            }catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public User getById (int id) throws UserDAOException{
        String sql = "SELECT * FROM USERS WHERE ID =  ?";
        User user= null;
        ResultSet rs = null;

        try
                (Connection connection = DBUtil.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("ID"), rs.getString("USERNAME"),rs.getString("PASSWORD"));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new UserDAOException("SQL Error in User Get with id: " + id);
        } finally {
            try {
                if (rs != null) rs.close();
            }catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return  user;
    }

}