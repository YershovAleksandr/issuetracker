package com.axmor.controller;

import com.axmor.service.CommentService;
import com.axmor.service.IssueService;
import com.axmor.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.*;

@SuppressWarnings("unchecked")
public class IssueController {
    private static Logger log = LoggerFactory.getLogger(IssueController.class);

    public static Route ViewIssues = (request, response) -> {
        log.info("View issues");

        Map map = new HashMap();
        map.put("issueList", IssueService.getAllIssues());
        map.put("user", request.session().attribute("user"));

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "issues"));
    };

    public static Route ViewIssue = (request, response) -> {
        log.info("View issue id = " + request.params(":id"));

        String strId = request.params(":id");

        if (!IssueService.isIssueExistsById(strId)){
            log.warn("Issue id not valid");

            //TODO show IssueIdNotValid page?
            response.redirect("/");

            return null;
        }

        Map map = new HashMap();
        map.put("issue", IssueService.getIssueById(strId));
        map.put("commentList", CommentService.getCommentByIssueId(strId));
        map.put("statusList", StatusService.getAllStatus());
        map.put("user", request.session().attribute("user"));

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "issue"));
    };

    public static Route CreateIssue = (request, response) -> {
        log.info("Create issue");

        if (request.session().attribute("user") == null){
            log.warn("Create issue for not authorized user");

            //TODO show NotAuthorizesUser page?
            response.redirect("/");

            return null;
        }

        return new ThymeleafTemplateEngine().render(new ModelAndView(new HashMap(), "createissueform"));
    };

    public static Route CreateIssuePost = (request, response) -> {
        log.info("Create issue post");

        Map<String, Object> map= new HashMap<>();
        map.put("title", request.queryParams("title"));
        map.put("description", request.queryParams("description"));
        //TODO fix this shit

        map.put("user", request.session().attribute("user"));

        IssueService.create(map);
        response.redirect("/");

        return null;
    };

    public static Route UpdateIssue =  (request, response) -> {
        log.info("update issue id = " + request.params(":id"));

        Map map = new HashMap();
        map.put("issue", IssueService.getIssueById(request.params(":id")));

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
        CommentService.deleteByIssueId(request.params(":id"));
        response.redirect("/");

        return null;
    };
}