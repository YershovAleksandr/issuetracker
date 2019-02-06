package com.axmor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    public static Route login = (request, response) -> {
        log.info("Login ");

        return null;
    };

    public static Route logout = (request, response) -> {
        log.info("Logout ");

        return null;
    };

    public static Route createUser = (request, response) -> {
        log.info("Create user ");

        return null;
    };

}
