package com.axmor.controller;

import com.axmor.model.User;
import com.axmor.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);



/*    public static Filter CheckAuth = (request, response) -> {
        log.info("CheckAuth");

        //Session session = request.session(true);
        log.info("Session.isNew() " + request.session().isNew());

        log.info("session.maxInactiveInterval() " + request.session().maxInactiveInterval());
        //request.session().maxInactiveInterval(10);
        log.info("session.maxInactiveInterval() " + request.session().maxInactiveInterval());
        log.info("request.session().id() " + request.session().id());
        log.info("Request.cookies " + request.cookies());
        log.info("Session.attributes " + request.session().attributes());
        log.info("Session.attribute(user)  " + request.session().attribute("user"));


        //response.cookie("foo", "bar");
        //request.session().attribute("user", "pidor");
        //request.session().attributes()
        //response.cookie("foo", "bar");

        //Map map = new HashMap();

        //request.session().removeAttribute("user");



        return;
    };*/

    public static Route Login = (request, response) -> {
        log.info("Login ");

        return new ThymeleafTemplateEngine().render(new ModelAndView(new HashMap(), "login"));
    };

    public static Route LoginPost = (request, response) -> {
        log.info("Login post ");

        //TODO fix this shit

        Map<String, String> map= new HashMap<>();

        String login = request.queryParams("login");
        String password = request.queryParams("password");

        //request.session().attribute("user", login);

        User user = UserService.getUserByName(login);

        String url = "/";

        if (user != null && user.getPassword().equals(password)){
            log.info("User accepted " + user);

            request.session().attribute("user", user);

            log.info("request.session.attribute(user) " + request.session().attribute("user"));

            //TODO user accepted

        } else {
            log.info("User not accepted " + user);

            //TODO user not accepted
            url = "/login";
        }

        response.redirect(url);

        return null;
    };

    public static Route Logout = (request, response) -> {
        log.info("Logout ");

        //request.session().removeAttribute("user");
        request.session().invalidate();

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

        response.redirect("/login");

        return null;
    };
}
