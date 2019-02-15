package com.axmor.controller;

import com.axmor.model.User;
import com.axmor.util.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static com.axmor.Main.userService;

public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    final static private String ATTRIBUTE_AUTH_FAILED = "authFailed";

    public static Route Login = (request, response) -> {
        log.info("Login ");

        Map<String, Object> map = new HashMap<>();
        map.put(ATTRIBUTE_AUTH_FAILED, request.session().attribute(ATTRIBUTE_AUTH_FAILED));

        return new ThymeleafTemplateEngine().render(new ModelAndView(map, "login"));
    };

    public static Route LoginPost = (request, response) -> {
        log.info("Login post ");

        String login = request.queryParams("login");
        String password = request.queryParams("password");

        String url = "/login";

        if (!UserValidator.isNameValid(login) || !UserValidator.isPasswordValid(password)){
            log.warn("User name or password not valid");
        } else {
            User user = userService.getUserByName(login);

            if (user != null && user.getPassword().equals(password)){
                log.info("User accepted " + user);
                request.session().attribute("user", user);
                request.session().removeAttribute(ATTRIBUTE_AUTH_FAILED);
                url = "/";
            } else {
                log.info("User not accepted [login {} password {}]", login, password);
                request.session().attribute(ATTRIBUTE_AUTH_FAILED, true);

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
        request.session().removeAttribute(ATTRIBUTE_AUTH_FAILED);

        return new ThymeleafTemplateEngine().render(new ModelAndView(new HashMap(), "register"));
    };

    public static Route RegisterPost = (request, response) -> {
        log.info("Create new user");

        String login = request.queryParams("login");
        String password = request.queryParams("password");

        if (!UserValidator.isNameValid(login) || !UserValidator.isPasswordValid(password)){
            log.warn("User name or password not valid");
        } else {
            userService.createNewUser(login, password);
        }

        response.redirect("/login");

        return null;
    };
}