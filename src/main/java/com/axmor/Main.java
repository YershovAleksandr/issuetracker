package com.axmor;

import com.axmor.controller.CommentController;
import com.axmor.controller.IssueController;
import com.axmor.controller.UserController;
import com.axmor.model.Status;
import com.axmor.model.User;
import com.axmor.service.StatusService;
import com.axmor.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.template.thymeleaf.ThymeleafTemplateEngine;


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
        //TODO fix this shit
        staticFiles.location("/web");

        Configuration();

        port(8080);

        //before("/*", UserController.CheckAuth);

        get("/login", UserController.Login);
        post("/login", UserController.LoginPost);
        get("/logout", UserController.Logout);

        get("/register", UserController.Register);
        post("/registerpost", UserController.RegisterPost);

        get("/", IssueController.ViewIssues);
        get("/issue/:id", IssueController.ViewIssue);
        post("/issue/:id", CommentController.CreateComment);

        get("/create", IssueController.CreateIssue);
        post("/createissue", IssueController.CreateIssuePost);

        get("/update/:id", IssueController.UpdateIssue);
        post("/updateissue", IssueController.UpdateIssuePost);
        get("/delete/:id", IssueController.DeleteIssue);


        //post("/createcomment", CommentController.CreateCommentPost);

        notFound("<html><body><h1>Fuck off!</h1></body></html>");
    }

    private static void Configuration(){
        //TODO FIX this shit

        List<String> statusStringList = new ArrayList<>(Arrays.asList("Created", "Resolved", "Closed", "Duplicated", "Reopened"));

        for(String statusString : statusStringList) {
            Status status = new Status();
            status.setStatus(statusString);
            StatusService.create(status);
        }

        User user = new User();
        //user.setId(42);
        user.setName("q");
        user.setPassword("w");

        UserService.create(user);

        User user2 = new User();
        //user.setId(42);
        user2.setName("qq");
        user2.setPassword("ww");

        UserService.create(user2);
    }
}
