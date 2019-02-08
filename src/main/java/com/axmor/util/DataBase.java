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
        createStatusSchema();
        createIssueSchema();
        createCommentSchema();
    }

    private static void createUserSchema(){
        log.info("User schema");

        try (Connection cn = DataSource.getConnection()){
            Statement st = cn.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS user (" +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR NOT NULL," +
                    "password VARCHAR NOT NULL," +
                    "PRIMARY KEY (id)) ");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createStatusSchema(){
        log.info("Status schema");

        try (Connection cn = DataSource.getConnection()){
            Statement st = cn.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS status (" +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "status VARCHAR NOT NULL," +
                    "PRIMARY KEY (id)) ");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createIssueSchema(){
        log.info("Issue schema");

        try (Connection cn = DataSource.getConnection()){
            Statement st = cn.createStatement();

            //TODO TIMESTAMP
            st.executeUpdate("CREATE TABLE IF NOT EXISTS issue (" +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "userid int NOT NULL," +
                    "title VARCHAR NOT NULL," +
                    "description VARCHAR NOT NULL," +
                    "date TIMESTAMP NOT NULL," +
                    "statusid int NOT NULL," +
                    "PRIMARY KEY (id)," +
                    "FOREIGN KEY (userid) REFERENCES User(id))");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createCommentSchema(){
        log.info("Comment schema");

    }


}
