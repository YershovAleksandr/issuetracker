package com.axmor.service;

import com.axmor.dao.UserDAO;
import com.axmor.model.User;

public class UserService {
    private static UserDAO userDAO = new UserDAO();

    public User getUserByName(String name){
        return userDAO.getUser(name);
    }

    public void createNewUser(String login, String password){
        User user = new User();
        user.setName(login);
        user.setPassword(password);

        userDAO.create(user);
    }
}