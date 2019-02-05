package com.axmor;

import com.axmor.controller.CommentController;
import com.axmor.controller.IssueController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

/**
 * Application entry point
 */

public class Main {
    final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        //TODO fix this shit
        staticFiles.location("/web");

        port(8080);

        get("/", IssueController.ViewIssues);
        get("/issue/:id", IssueController.ViewIssue);
        get("/update/:id", IssueController.UpdateIssue);
        post("/updateissue", IssueController.UpdateIssuePost);
        get("/delete/:id", IssueController.DeleteIssue);
        get("/create", IssueController.CreateIssue);
        post("/createissue", IssueController.CreateIssuePost);

        post("/issue/:id", CommentController.CreateComment);
        //post("/createcomment", CommentController.CreateCommentPost);

        notFound("<html><body><h1>Fuck off!</h1></body></html>");
    }
}
