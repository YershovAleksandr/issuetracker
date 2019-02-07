package com.axmor.service;

import com.axmor.dao.IssueDAO;
import com.axmor.model.Issue;
import com.axmor.model.Status;
import com.axmor.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class IssueService {
    private static Logger log = LoggerFactory.getLogger(IssueService.class);
    private static IssueDAO issueDAO = new IssueDAO();

    public static List<Issue> getAllIssues(){
        return issueDAO.getAll();
    }

    public static boolean isIssueExistsById(String id){
        Issue issue = getIssueById(id);

        if (issue != null){
            return true;
        } else {
            return false;
        }
    }

    public static Issue getIssueById(String id){
        int intId;

        try {
            intId = Integer.valueOf(id);
        } catch (NumberFormatException e){
            return null;
        }

        return issueDAO.get(intId);
    }

    public static void createIssue(User user, String title, String description){
        Issue issue = new Issue();
        issue.setUser(user);
        issue.setTitle(title);
        issue.setDescription(description);
        issue.setDate(new Date());
        issue.setStatus(StatusService.getCreatedStatus());

        log.info("Create issue " + issue);

        issueDAO.create(issue);
    }

    public static void update(Map<String, String> params){
        log.info("Update id = " + params.get("id"));
        String title = params.get("title");
        String description = params.get("description");
        String id = params.get("id");

        Issue issue = getIssueById(id);

        log.info("Issue" + issue);
        issue.setTitle(title);
        issue.setDescription(description);
        log.info("Updated Issue" + issue);

        issueDAO.update(issue);
    }

    public static void delete(String strId){
        int id = Integer.valueOf(strId);
        log.info("Delete id = " + id);

        issueDAO.delete(id);
    }

    public static void updateStatusByIssueId(String id, Status status){
        Issue issue = getIssueById(id);

        //TODO check for null;
        issue.setStatus(status);
    }
}
