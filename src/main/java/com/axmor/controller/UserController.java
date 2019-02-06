package com.axmor.controller;

import com.axmor.model.User;
import com.axmor.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    //public static Route Login = (request, response) -> {
    public static ModelAndView Login (Request request, Response response){
        log.info("Login ");

        log.info("Request.headers " + request.headers());
        log.info("Request.cookies " + request.cookies());

        //response.cookie("foo", "bar");
        //request.session(true);
        log.info("request.session " + request.session().attributes());
        //request.session().attribute("user", "pidor");
        //request.session().attributes()

        //TODO FIX
        Map map = new HashMap();

        //return new ThymeleafTemplateEngine().render(new ModelAndView(map, "login"));
        return new ModelAndView(map, "login");
    };

    public static Route Logout = (request, response) -> {
        log.info("Logout ");

        //response.cookie("foo", "bar");
        log.info("request.session " + request.session().attribute("user"));
        //Map map = new HashMap();

        request.session().removeAttribute("user");

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

            request.session().attribute("user", user.getName()+user.getPassword());

            log.info("request.session.attribute(user) " + request.session().attribute("user"));

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
