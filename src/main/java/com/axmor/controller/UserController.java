package com.axmor.controller;

import com.axmor.model.User;
import com.axmor.service.UserService;
import com.axmor.util.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    public static Route Login = (request, response) -> {
        log.info("Login ");

        return new ThymeleafTemplateEngine().render(new ModelAndView(new HashMap(), "login"));
    };

    public static Route LoginPost = (request, response) -> {
        log.info("Login post ");

        String login = request.queryParams("login");
        String password = request.queryParams("password");

        String url = "/login";

        if (!UserValidator.isNameValid(login) || !UserValidator.isPasswordValid(password)){
            log.warn("User name or password not valid");

            //TODO show UserLoginOrPasswordNotValid page?
        } else {
            User user = UserService.getUserByName(login);

            if (user != null && user.getPassword().equals(password)){
                log.info("User accepted " + user);

                request.session().attribute("user", user);

                url = "/";
            } else {
                log.info("User not accepted [login " + login + " password " + password + "]");

                url = "/login";
            }

        }

        response.redirect(url);

        return null;
    };

    public static Route Logout = (request, response) -> {
        log.info("Logout ");

        request.session().invalidate();
        response.redirect("/");

        return null;
    };

    public static Route Register = (request, response) -> {
        log.info("Register user ");

        return new ThymeleafTemplateEngine().render(new ModelAndView(new HashMap(), "register"));
    };

    public static Route RegisterPost = (request, response) -> {
        log.info("Create new user");

        String login = request.queryParams("login");
        String password = request.queryParams("password");

        if (!UserValidator.isNameValid(login) || !UserValidator.isPasswordValid(password)){
            log.warn("User name or password not valid");

            //TODO show UserLoginOrPasswordNotValid page?
        } else {
            UserService.createNewUser(login, password);
        }

        response.redirect("/login");

        return null;
    };
}