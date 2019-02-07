package com.axmor;

import com.axmor.controller.CommentController;
import com.axmor.controller.IssueController;
import com.axmor.controller.UserController;
import com.axmor.model.Status;
import com.axmor.service.StatusService;
import com.axmor.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;

/**
 * Application entry point
 */

public class Main {
    final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
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