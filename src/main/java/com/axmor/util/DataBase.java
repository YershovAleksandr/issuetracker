package com.axmor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

            st.executeUpdate("CREATE TABLE IF NOT EXISTS user (" +
                    "user_id int NOT NULL AUTO_INCREMENT," +
                    "user_name VARCHAR NOT NULL," +
                    "user_password VARCHAR NOT NULL," +
                    "PRIMARY KEY (user_id)) ");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createIssueSchema(){
        log.info("Issue schema");

        try (Connection cn = DataSource.getConnection()){
            Statement st = cn.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS issue (" +
                    "issue_id int NOT NULL AUTO_INCREMENT," +
                    "issue_userid int NOT NULL," +
                    "issue_title VARCHAR NOT NULL," +
                    "issue_description VARCHAR NOT NULL," +
                    "issue_date TIMESTAMP NOT NULL," +
                    "issue_status VARCHAR NOT NULL," +
                    "PRIMARY KEY (issue_id)," +
                    "FOREIGN KEY (issue_userid) REFERENCES user(user_id))");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createCommentSchema(){
        log.info("Comment schema");

        try (Connection cn = DataSource.getConnection()){
            Statement st = cn.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS comment (" +
                    "comment_id int NOT NULL AUTO_INCREMENT," +
                    "comment_userid int NOT NULL," +
                    "comment_issueid int NOT NULL," +
                    "comment_status VARCHAR NOT NULL," +
                    "comment_text VARCHAR NOT NULL," +
                    "comment_date TIMESTAMP NOT NULL," +
                    "PRIMARY KEY (comment_id)," +
                    "FOREIGN KEY (comment_userid) REFERENCES user(user_id)," +
                    "FOREIGN KEY (comment_issueid) REFERENCES issue(issue_id) ON DELETE CASCADE)");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
