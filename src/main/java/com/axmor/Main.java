package com.axmor;

import com.axmor.controller.IssueController;
import com.axmor.model.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Application entry point
 */

public class Main {
    final static Logger log = LoggerFactory.getLogger(Main.class);

    private static void FillIssue(){

        Issue issue = new Issue();

        issue.setId(1);
        issue.setTitle("Title 1");
        issue.setDescription("Description 1");

        IssueController.create(issue);
    }

    public static void main(String[] args) {

        FillIssue();

        //TODO fix this shit
        staticFiles.location("/web");

        port(8080);

        Route routeViewIssues = (request, response) -> {
            Map map1 = new HashMap();
            map1.put("foo", "bar");
            log.info("view all issues");

            List<Issue> issueList = IssueController.getAll();

            map1.put("issueList", issueList);

            return new ThymeleafTemplateEngine().render(new ModelAndView(map1, "issues"));
        };

        get("/", routeViewIssues);

        Route routeViewIssue = (request, response) -> {
            Map map1 = new HashMap();
            map1.put("id", request.params(":id"));
            log.info("view issue id = " + request.params(":id"));

            return new ThymeleafTemplateEngine().render(new ModelAndView(map1, "issue"));
        };

        get("/issue/:id", routeViewIssue);

        Route routeUpdateIssue =  (request, response) -> {
            Map map1 = new HashMap();
            map1.put("id", request.params(":id"));

            log.info("update issue id = " + request.params(":id"));

            return new ThymeleafTemplateEngine().render(new ModelAndView(map1, "update"));
        };

        get("/update/:id", routeUpdateIssue);

        Route routeDeleteIssue = (request, response) -> {
            log.info("delete issue id = " + request.params(":id"));

            response.redirect("/");

            //return routeViewIssues.handle(request, response);
            return null;
        };

        get("/delete/:id", routeDeleteIssue);

        Route routeCreateIssue = (request, response) -> {
            log.info("Create new issue");

            response.redirect("/");

            return null;
        };

        post("/createissue", routeCreateIssue);

        Route routeCreateComment = (request, response) -> {
            log.info("Create new comment at issue id = " + request.params(":id"));


            //log.info("url " + request.url() + " queryString " + request.queryString());
            response.redirect(request.url());

            return null;

            //return routeViewIssue.handle(request, response);
        };

        post("/issue/:id", routeCreateComment);

        notFound("<html><body><h1>Fuck off!</h1></body></html>");
    }
}
