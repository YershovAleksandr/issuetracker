package com.axmor.dao;

import com.axmor.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static Logger log = LoggerFactory.getLogger(UserDAO.class);
    private List<User> userList = new ArrayList<>();

    public List<User> getAll(){
        return userList;
    }

    public User get(int id){
        return userList.get(id);
    }

    public void create(User user){
        userList.add(user);
        user.setId(userList.indexOf(user));

        log.info("Created user " + user);
    }

    public void update(User user){
        userList.add(user.getId(), user);
    }

    public void delete(int id){
        userList.remove(id);
    }
}