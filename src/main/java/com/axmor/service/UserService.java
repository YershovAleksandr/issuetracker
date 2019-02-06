package com.axmor.service;

import com.axmor.dao.UserDAO;
import com.axmor.model.User;

import java.util.List;

public class UserService {
    private static UserDAO userDAO = new UserDAO();

    public static List<User> getAll(){
        return userDAO.getAll();
    }

    public static User get(int id){
        return userDAO.get(id);
    }

    public static void create(User user){
        userDAO.create(user);
    }

    public static void update(User user){
        userDAO.update(user);
    }

    public static void delete(int id){
        userDAO.delete(id);
    }
}
