package com.axmor.service;

import com.axmor.dao.UserDAO;
import com.axmor.model.User;

import java.util.List;

public class UserService {
    private static UserDAO userDAO = new UserDAO();

    public static User getUserByName(String name){
        List<User> userList = userDAO.getAll();

        for (User user : userList){
            if (user.getName().equals(name)){
                return user;
            }
        }

        return null;
    }

    public static void createNewUser(String login, String password){
        User user = new User();
        user.setName(login);
        user.setPassword(password);

        userDAO.create(user);
    }
}
