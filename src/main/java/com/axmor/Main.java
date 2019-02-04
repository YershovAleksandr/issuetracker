package com.axmor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.axmor.controller.IssueController.*;
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

        get("/", ViewIssues);
        get("/issue/:id", ViewIssue);
        get("/update/:id", UpdateIssue);
        post("/updateissue", UpdateIssuePost);
        get("/delete/:id", DeleteIssue);
        get("/create", CreateIssue);
        post("/createissue", CreateIssuePost);

        post("/issue/:id", CreateComment);

        notFound("<html><body><h1>Fuck off!</h1></body></html>");
    }
}
