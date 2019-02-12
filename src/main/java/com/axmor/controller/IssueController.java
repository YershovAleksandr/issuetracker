package com.axmor.controller;

import com.axmor.model.User;
import com.axmor.util.IssueValidator;
import com.axmor.util.StatusHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.*;

import static com.axmor.Main.commentService;
import static com.axmor.Main.issueService;

@SuppressWarnings("unchecked")
public class IssueController {
    private static Logger log = LoggerFactory.getLogger(IssueController.class);

    public static Route ViewIssues = (request, response) -> {
        log.info("View issues");

        Map map = new HashMap();
        map.put("issueList", issueService.getAllIssues());
        map.put("user", request.session().attribute("user"));

        log.info("user " + request.session().attribute("user"));

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "issues"));
    };

    public static Route ViewIssue = (request, response) -> {
        log.info("View issue id = {}", request.params(":id"));

        String strId = request.params(":id");

        if (!issueService.isIssueExistsById(strId)){
            log.warn("Issue id not valid");
            response.redirect("/");
            return null;
        }

        Map map = new HashMap();
        map.put("issue", issueService.getIssueById(strId));
        map.put("commentList", commentService.getCommentByIssueId(strId));
        map.put("statusList", StatusHelper.getStatusList());
        map.put("user", request.session().attribute("user"));

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "issue"));
    };

    public static Route CreateIssue = (request, response) -> {
        log.info("Create issue");

        if (request.session().attribute("user") == null){
            log.warn("Create issue for not authorized user");
            response.redirect("/");
            return null;
        }

        return new ThymeleafTemplateEngine().render(new ModelAndView(new HashMap(), "createissueform"));
    };

    public static Route CreateIssuePost = (request, response) -> {
        log.info("Create issue post");

        if (request.session().attribute("user") == null){
            log.warn("Create issue post for not authorized user");
            response.redirect("/");
            return null;
        }

        String title = request.queryParams("title");
        String description = request.queryParams("description");

        if (!IssueValidator.isTitleValid(title) || !IssueValidator.isDescriptionValid(description)) {
            log.warn("Issue title or description not valid");
        } else {
            issueService.createIssue(request.session().attribute("user"), title, description);
        }

        response.redirect("/");
        return null;
    };

    public static Route UpdateIssue =  (request, response) -> {
        log.info("Update issue id = {}", request.params(":id"));

        String id = request.params(":id");

        if (!issueService.isIssueExistsById(id)){
            log.warn("Issue id not valid");
            response.redirect("/");
            return null;
        }

        User user = request.session().attribute("user");

        if (user == null){
            log.warn("Create issue post for not authorized user");
            response.redirect("/");
            return null;
        }

        if (!issueService.getIssueById(id).getUser().equals(user)){
            log.warn("Update issue for foreign user");
            response.redirect("/");
            return null;
        }

        Map map = new HashMap();
        map.put("issue", issueService.getIssueById(id));
        map.put("user", user);

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "update"));
    };

    public static Route UpdateIssuePost = (request, response) -> {
        log.info("Update issue post id = {}", request.queryParams("id"));

        String id = request.queryParams("id");

        if (!issueService.isIssueExistsById(id)){
            log.warn("Issue id not valid");
            response.redirect("/");
            return null;
        }

        User user = request.session().attribute("user");

        if (user == null){
            log.warn("Update issue post for not authorized user");
            response.redirect("/");
            return null;
        }

        if (!issueService.getIssueById(id).getUser().equals(user)){
            log.warn("Update issue for foreign user");
            response.redirect("/");
            return null;
        }

        String title = request.queryParams("title");
        String description = request.queryParams("description");

        if (!IssueValidator.isTitleValid(title) || !IssueValidator.isDescriptionValid(description)) {
            log.warn("Issue title or description not valid");
        } else {
            issueService.updateIssue(id, title, description);
        }

        response.redirect("/");
        return null;
    };

    public static Route DeleteIssue = (request, response) -> {
        log.info("Delete issue id = {}", request.params(":id"));

        String id = request.params(":id");

        if (!issueService.isIssueExistsById(id)){
            log.warn("Issue id not valid");
            response.redirect("/");
            return null;
        }

        User user = request.session().attribute("user");

        if (user == null){
            log.warn("Delete issue post for not authorized user");
            response.redirect("/");
            return null;
        }

        if (!issueService.getIssueById(id).getUser().equals(user)){
            log.warn("Delete issue for foreign user");
            response.redirect("/");
            return null;
        }

        issueService.deleteById(id);
        response.redirect("/");
        return null;
    };
}