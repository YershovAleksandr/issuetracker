package com.axmor;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Application entry point
 */
public class Main {
    public static void main(String[] args) {

        staticFiles.location("/web");
        port(8080);

        get("/x/:id", (request, response) -> {
            return "Hello: " + request.params(":id") + " Buz = " + request.queryParams("buz");
        });

        get("/", (Request req, Response res) -> index());
        //get("/viewissues", (Request req, Response res) -> {res.redirect("/index.html");});
        get("/create", (Request req, Response res) -> create());
        get("/comment", (Request req, Response res) -> comment());

        /*
        *
        * path("/api", () -> {
            before("/*", (q, a) -> log.info("Received api call"));
            path("/email", () -> {
                post("/add",       EmailApi.addEmail);
                put("/change",     EmailApi.changeEmail);
                delete("/remove",  EmailApi.deleteEmail);
            });
            path("/username", () -> {
                post("/add",       UserApi.addUsername);
                put("/change",     UserApi.changeUsername);
                delete("/remove",  UserApi.deleteUsername);
            });
        });
        *
        * */

        Map map = new HashMap();
        map.put("foo", "bar!!!!");

        get("/hello", (rq, rs) -> new ModelAndView(map, "index"), new ThymeleafTemplateEngine());


        notFound("<html><body><h1>Fuck off!</h1></body></html>");

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
