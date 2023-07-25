package gr.aueb.cf.schoolapp.service;


import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp.dto.UserUpdateDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import javax.swing.*;
import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User insertUser(UserInsertDTO dto) throws UserDAOException {
        if (dto == null) return null;
        User user;
        try {
            user = map(dto);
            return userDAO.insert(user);

        } catch (UserDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public User updateUser(UserUpdateDTO dto) throws UserDAOException, UserNotFoundException {
        if (dto == null) return null;
        User user;
        try {
            user = map(dto);

            if (userDAO.getById(user.getId()) == null) {
                throw new UserNotFoundException(user);
            }
            return userDAO.update(user);
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteUser(int id) throws UserDAOException, UserNotFoundException {
        User user;
        try {
            user = userDAO.getById(id);

            if (user == null) {
                throw new UserNotFoundException("User with id: " + id + " was not found");
            }
            userDAO.delete(id);
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<User> getUsersByUsername(String username) throws UserDAOException {
        List<User> users;

        try {
            users = userDAO.getByUsername(username);
            return users;
        } catch (UserDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public User getUserById(int id) throws UserDAOException, UserNotFoundException {
        User user;
        try {
            user = userDAO.getById(id);
            if (user == null) {
                throw new UserNotFoundException("Search Error: User with id: " + id + " was not found");
            }
            return user;
        } catch (UserDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private User map(UserInsertDTO dto) {
        return new User(null, dto.getUsername(), dto.getPassword());
    }

    private User map(UserUpdateDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword());
    }



}
