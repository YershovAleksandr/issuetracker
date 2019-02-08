package com.axmor;

import com.axmor.controller.CommentController;
import com.axmor.controller.IssueController;
import com.axmor.controller.UserController;
import com.axmor.model.Status;
import com.axmor.service.StatusService;
import com.axmor.service.UserService;
import com.axmor.util.DataBase;
import com.axmor.util.DataSource;
import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;

/**
 * Application entry point
 */

public class Main {
    final static Logger log = LoggerFactory.getLogger(Main.class);

    /*private static void db(){

        JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:./db", "user1", "123");

        try (Connection conn = cp.getConnection()) {

            Statement st = conn.createStatement();

            st.execute("create table if not exists user(id integer primary key auto_increment, name varchar(100));");
            st.execute("insert into user(name) values('sss'), ('sss')");

            //st.executeQuery("select 1+1, h2version() ");/
            st.executeQuery("select * from user;");

            ResultSet rs = st.getResultSet();

            while(rs.next()){

                //log.info(String.valueOf(rs.getInt(1)));
                log.info(String.valueOf(rs.getInt("id")));
                log.info(rs.getString("name"));

            }




        }catch (SQLException e){
            log.error("Error", e);
        }

        //conn.close();
        cp.dispose();


        try (Connection cn = DataSource.getConnection()){

            Statement st = cn.createStatement();

            //st.executeQuery("select 1+1, h2version() ");
            //st.executeQuery("show databases");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS issue (" +
                    "id int(10) NOT NULL AUTO_INCREMENT," +
                    "title VARCHAR(10) NOT NULL," +
                    "description VARCHAR(50) NOT NULL," +
                    "PRIMARY key(id)) ");
            //st.executeQuery("select * from user;");

            /*ResultSet rs = st.getResultSet();

            while(rs.next()){

                //log.info(String.valueOf(rs.getInt(1)));
                //log.info(rs.getString(2));
                log.info(rs.getString(1));
              //  log.info(String.valueOf(rs.getInt("id")));
                //log.info(rs.getString("name"));

            }*/
/*
        }catch (SQLException e){
            e.printStackTrace();
        }

       // System.exit(0);
    }*/

    public static void main(String[] args) {

        DataBase.init();

        staticFiles.location("/web");

        Configuration();

        port(8080);

        get("/login", UserController.Login);
        post("/login", UserController.LoginPost);
        get("/logout", UserController.Logout);

        get("/register", UserController.Register);
        post("/register", UserController.RegisterPost);

        get("/", IssueController.ViewIssues);
        get("/issue/:id", IssueController.ViewIssue);
        get("/create", IssueController.CreateIssue);
        post("/createissue", IssueController.CreateIssuePost);

        get("/update/:id", IssueController.UpdateIssue);
        post("/updateissue", IssueController.UpdateIssuePost);
        get("/delete/:id", IssueController.DeleteIssue);

        post("/issue/:id", CommentController.CreateComment);

        notFound("<html><body><h1>Fuck off!</h1></body></html>");
    }

    private static void Configuration(){
         List<String> statusStringList = new ArrayList<>(Arrays.asList("Created", "Resolved", "Closed", "Duplicated", "Reopened"));

        for(String statusString : statusStringList) {
            Status status = new Status();
            status.setStatus(statusString);
            StatusService.create(status);
        }

        UserService.createNewUser("q", "w");
        UserService.createNewUser("qq", "ww");
    }
}