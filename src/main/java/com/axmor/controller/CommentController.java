package com.axmor.controller;

import com.axmor.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class CommentController {
    private static Logger log = LoggerFactory.getLogger(CommentController.class);

    public static Route CreateComment = (request, response) -> {
        log.info("Create new comment at issue id = " + request.params(":id"));

        Map<String, Object> map= new HashMap<>();
        //TODO fix this shit
        //map.put("userid", request.queryParams("userid"));

        map.put("issueid", request.params(":id"));
        map.put("status", request.queryParams("status"));
        map.put("text", request.queryParams("text"));
        map.put("user", request.session().attribute("user"));

        CommentService.create(map);

        response.redirect(request.url());

        return null;
    };
}
