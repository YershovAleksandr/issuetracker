package com.axmor;

import spark.Request;
import spark.Response;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

/**
 * Application entry point
 */
public class Main {
    public static void main(String[] args) {

        staticFiles.location("/web");
        port(8080);
        get("/", (Request req, Response res) -> index());
        //get("/viewissues", (Request req, Response res) -> {res.redirect("/index.html");});
        get("/create", (Request req, Response res) -> create());
        get("/comment", (Request req, Response res) -> comment());
    }

    private static String index(){
        return "<html>" +
                "<body>" +
                "<h1>View Issues!</h1>" +
                "<a href=\"/create\">Create Issue</a>" +
                "<br>" +
                "<a href=\"/create\">Comment Issue</a>" +
                "</body>" +
                "</html>";
    }

    private static String create(){
        return "<html><body><h1>Create Issue</h1></body></html>";
    }

    private static String comment(){
        return "<html><body><h1>Comment Issue</h1></body></html>";
    }
}
