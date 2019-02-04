package com.axmor.controller;

import com.axmor.model.Issue;
import com.axmor.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.*;

public class IssueController {
    private static Logger log = LoggerFactory.getLogger(IssueController.class);

    public static Route ViewIssues = (request, response) -> {
        log.info("view all issues");

        Map map = new HashMap();
        //TODO fix this shit
        List<Issue> issueList = new ArrayList<Issue>(IssueService.getAll().values());
        map.put("issueList", issueList);

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "issues"));
    };

    public static Route ViewIssue = (request, response) -> {
        log.info("view issue id = " + request.params(":id"));

        Map map = new HashMap();
        //TODO fix this shit
        int intId = Integer.valueOf(request.params(":id"));

        Issue issue = IssueService.get(intId);
        map.put("issue", issue);

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "issue"));
    };

    public static Route UpdateIssue =  (request, response) -> {
        log.info("update issue id = " + request.params(":id"));

        Map map = new HashMap();
        //TODO fix this shit
        int intId = Integer.valueOf(request.params(":id"));

        Issue issue = IssueService.get(intId);
        map.put("issue", issue);

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "update"));
    };

    public static Route UpdateIssuePost = (request, response) -> {
        log.info("Update issue");

        Map<String, String> map= new HashMap<>();
        //TODO fix this shit
        map.put("id", request.queryParams("id"));
        map.put("title", request.queryParams("title"));
        map.put("description", request.queryParams("description"));

        IssueService.update(map);
        response.redirect("/");

        return null;
    };

    public static Route DeleteIssue = (request, response) -> {
        log.info("delete issue id = " + request.params(":id"));

        //TODO fix this shit
        IssueService.delete(request.params(":id"));
        response.redirect("/");

        return null;
    };

    public static Route CreateIssue = (request, response) -> {
        log.info("Create issue");
        Map map = new HashMap();
        //TODO fix this shit
        map.put("foo", "bar");

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "createissueform"));
    };

    public static Route CreateIssuePost = (request, response) -> {
        log.info("Create issue post");

        Map<String, String> map= new HashMap<>();
        map.put("title", request.queryParams("title"));
        map.put("description", request.queryParams("description"));
        //TODO fix this shit
        IssueService.create(map);
        response.redirect("/");

        return null;
    };

    public static Route CreateComment = (request, response) -> {
        log.info("Create new comment at issue id = " + request.params(":id"));

        response.redirect(request.url());

        return null;
    };
}
