package com.axmor.dao;

import com.axmor.model.User;
import com.axmor.util.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static Logger log = LoggerFactory.getLogger(UserDAO.class);

    public User getUser(String name){
        User user = null;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE name = ?");
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                user = new User();

                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
            }

        }catch(SQLException e){
            log.error("Error", e);
        }

        return user;
    }

    public int create(User user){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO user(name, password) values(?, ?)");

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        log.info("Created user " + user);

        return rez;
    }
}