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

import java.net.URLEncoder;
import java.util.*;

import static spark.Spark.*;

/**
 * Application entry point
 */

public class Main {
    final static Logger log = LoggerFactory.getLogger(Main.class);

    private static void FillIssue(){

      /*  Issue issue = new Issue();

        issue.setId(1);
        issue.setUserId(11);
        issue.setTitle("Title 1");
        issue.setDescription("Description 1");
        issue.setDate(new Date());
        issue.setStatus(111);

        IssueController.create(issue);

        Issue issue2 = new Issue();

        issue2.setId(2);
        issue2.setUserId(22);
        issue2.setTitle("Title 2");
        issue2.setDescription("Description 2");
        issue2.setDate(new Date());
        issue2.setStatus(222);

        IssueController.create(issue2);*/

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

            List<Issue> issueList = new ArrayList<Issue>(IssueController.getAll().values());

            map1.put("issueList", issueList);

            return new ThymeleafTemplateEngine().render(new ModelAndView(map1, "issues"));
        };

        get("/", routeViewIssues);

        Route routeViewIssue = (request, response) -> {
            log.info("view issue id = " + request.params(":id"));

            Map map1 = new HashMap();

            int intId = Integer.valueOf(request.params(":id"));
            Issue issue = IssueController.get(intId);

            map1.put("issue", issue);

            return new ThymeleafTemplateEngine().render(new ModelAndView(map1, "issue"));
        };

        get("/issue/:id", routeViewIssue);

        Route routeUpdateIssue =  (request, response) -> {
            log.info("update issue id = " + request.params(":id"));

            Map map1 = new HashMap();

            int intId = Integer.valueOf(request.params(":id"));
            Issue issue = IssueController.get(intId);

            map1.put("foo", "bar");
            map1.put("id", request.params(":id"));
            map1.put("issue", issue);

            return new ThymeleafTemplateEngine().render(new ModelAndView(map1, "update"));
        };

        get("/update/:id", routeUpdateIssue);

        Route routeUpdateIssuePost = (request, response) -> {
            log.info("Update issue");

            Map<String, String> map= new HashMap<>();

            map.put("id", request.queryParams("id"));
            map.put("title", request.queryParams("title"));
            map.put("description", request.queryParams("description"));

            IssueController.update(map);
            response.redirect("/");

            return null;
        };

        post("/updateissue", routeUpdateIssuePost);

        Route routeDeleteIssue = (request, response) -> {
            log.info("delete issue id = " + request.params(":id"));

            IssueController.delete(request.params(":id"));

            response.redirect("/");

            //return routeViewIssues.handle(request, response);
            return null;
        };

        get("/delete/:id", routeDeleteIssue);

        Route routeCreateIssue = (request, response) -> {
            log.info("Create issue");
            Map map1 = new HashMap();
            map1.put("foo", "bar");

            //response.redirect("/");

            //return null;
            return new ThymeleafTemplateEngine().render(new ModelAndView(map1, "createissueform"));
        };

        get("/create", routeCreateIssue);

        Route routeCreateIssuePost = (request, response) -> {
            log.info("Create issue post");

            Map<String, String> map= new HashMap<>();

            map.put("title", request.queryParams("title"));
            map.put("description", request.queryParams("description"));

            IssueController.create(map);
            response.redirect("/");

            return null;
        };

        post("/createissue", routeCreateIssuePost);

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
