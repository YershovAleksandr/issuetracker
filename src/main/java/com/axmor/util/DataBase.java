package com.axmor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.axmor.dao.SQLConstants.*;

public class DataBase {
    private static Logger log = LoggerFactory.getLogger(DataBase.class);

    public static void init(){
        createUserSchema();
        createIssueSchema();
        createCommentSchema();
    }

    private static void createUserSchema(){
        log.info("User schema");

        try (Connection cn = DataSource.getConnection()){
            Statement st = cn.createStatement();

            st.executeUpdate(CREATE_USER_TABLE);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createIssueSchema(){
        log.info("Issue schema");

        try (Connection cn = DataSource.getConnection()){
            Statement st = cn.createStatement();

            st.executeUpdate(CREATE_ISSUE_TABLE);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createCommentSchema(){
        log.info("Comment schema");

        try (Connection cn = DataSource.getConnection()){
            Statement st = cn.createStatement();

            st.executeUpdate(CREATE_COMMENT_TABLE);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}