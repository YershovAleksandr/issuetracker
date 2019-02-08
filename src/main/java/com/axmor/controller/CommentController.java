package com.axmor.controller;

import com.axmor.model.User;
import com.axmor.service.CommentService;
import com.axmor.service.IssueService;
import com.axmor.util.CommentValidator;
import com.axmor.util.StatusHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

public class CommentController {
    private static Logger log = LoggerFactory.getLogger(CommentController.class);

    public static Route CreateComment = (request, response) -> {
        log.info("Create new comment at issue id = " + request.params(":id"));

        String id = request.params(":id");

        if (!IssueService.isIssueExistsById(id)){
            log.warn("Issue id not valid");

            //TODO show IssueIdNotValid page?
            response.redirect("/");

            return null;
        }

        User user = request.session().attribute("user");

        if (user == null){
            log.warn("Create comment for not authorized user");

            //TODO show NotAuthorizesUser page?
            response.redirect("/");

            return null;
        }

        String status = request.queryParams("status");
        String text = request.queryParams("text");

        if (!CommentValidator.isStatusValid(status) || !CommentValidator.isTextValid(text)) {
            log.warn("Comment status or text not valid");

            //TODO show CommentStatusOrDescriptionNotValid page?
        } else {
            CommentService.createCommentByIssueId(IssueService.getIssueById(id), StatusHelper.getIdByStatus(status), text, user);

            IssueService.updateStatusById(id, StatusHelper.getIdByStatus(status));
        }

        response.redirect(request.url());

        return null;
    };
}