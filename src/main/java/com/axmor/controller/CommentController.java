package com.axmor.controller;

import com.axmor.model.User;
import com.axmor.util.CommentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

import static com.axmor.Main.commentService;
import static com.axmor.Main.issueService;

public class CommentController {
    private static Logger log = LoggerFactory.getLogger(CommentController.class);

    public static Route CreateComment = (request, response) -> {
        log.info("Create new comment at issue id = {}", request.params(":id"));

        String id = request.params(":id");

        if (!issueService.isIssueExistsById(id)){
            log.warn("Issue id not valid");
            response.redirect("/");
            return null;
        }

        User user = request.session().attribute("user");

        if (user == null){
            log.warn("Create comment for not authorized user");
            response.redirect("/");
            return null;
        }

        String status = request.queryParams("status");
        String text = request.queryParams("text");

        if (!CommentValidator.isStatusValid(status) || !CommentValidator.isTextValid(text)) {
            log.warn("Comment status or text not valid");
        } else {
            commentService.createCommentByIssueId(issueService.getIssueById(id), status, text, user);
            issueService.updateStatusById(id, status);
        }

        response.redirect(request.url());

        return null;
    };
}