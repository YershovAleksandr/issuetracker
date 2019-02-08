package com.axmor;

import com.axmor.controller.CommentController;
import com.axmor.controller.IssueController;
import com.axmor.controller.UserController;
import com.axmor.service.UserService;
import com.axmor.util.DataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

/**
 * Application entry point
 */

public class Main {
    final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Configuration();

        staticFiles.location("/web");

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
        DataBase.init();

        UserService.createNewUser("q", "w");
        UserService.createNewUser("qq", "ww");
    }
}