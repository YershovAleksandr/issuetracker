package com.axmor.controller;

import com.axmor.model.User;
import com.axmor.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    public static Route Login = (request, response) -> {
        log.info("Login ");

        //TODO FIX
        Map map = new HashMap();

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "login"));
    };

    public static Route Logout = (request, response) -> {
        log.info("Logout ");

        //Map map = new HashMap();

        UserService.logout();

        response.redirect("/");

        return null;
    };

    public static Route Register = (request, response) -> {
        log.info("Register user ");

        Map map = new HashMap();


        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "register"));
    };

    public static Route RegisterPost = (request, response) -> {
        log.info("Create new user");

        String login = request.queryParams("login");
        String password = request.queryParams("password");

        User user = new User();
        user.setName(login);
        user.setPassword(password);

        UserService.create(user);

        response.redirect("/");

        return null;
    };

    public static Route LoginPost = (request, response) -> {
        log.info("Login post ");

        //TODO fix this shit

        Map<String, String> map= new HashMap<>();

        String login = request.queryParams("login");
        String password = request.queryParams("password");

        User user = UserService.getUserByName(login);

        if (user != null && user.getPassword().equals(password)){
            log.info("User accepted " + user);

            //TODO user accepted
            //response.redirect("/");
        } else {
            log.info("User not accepted " + user);

            //TODO user not accepted
            //response.redirect("/");
        }

        response.redirect("/");

        return null;
    };
}
