package com.axmor.dao;

import com.axmor.model.User;
import com.axmor.util.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.axmor.dao.SQLConstants.*;

public class UserDAO {
    private static Logger log = LoggerFactory.getLogger(UserDAO.class);

    public User getUser(String name){
        User user = null;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement(SELECT_FROM_USER_BY_NAME);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                user = new User();

                user.setId(rs.getInt(USERDB_COLUMN_ID));
                user.setName(rs.getString(USERDB_COLUMN_NAME));
                user.setPassword(rs.getString(USERDB_COLUMN_PASSWORD));
            }

        }catch(SQLException e){
            log.error("Error", e);
        }

        return user;
    }

    public int create(User user){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement(INSERT_INTO_USER_VALUES);

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        log.info("Created user {} password {}", user.getName(), user.getPassword());

        return rez;
    }
}