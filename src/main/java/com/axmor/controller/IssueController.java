package com.axmor.controller;

import com.axmor.dao.IssueDAO;
import com.axmor.model.Issue;

import java.util.List;

public class IssueController {
    private static IssueDAO issueDAO = new IssueDAO();

    public static List<Issue> getAll(){
        return issueDAO.getAll();
    }

    public static Issue get(int id){
        return issueDAO.get(id);
    }

    public static void create(Issue issue){
        issueDAO.create(issue);
    }

    public static void update(Issue issue){
        issueDAO.update(issue);
    }

    public static void delete(int id){
        issueDAO.delete(id);
    }
}
