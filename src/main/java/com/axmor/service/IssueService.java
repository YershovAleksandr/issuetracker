package com.axmor.service;

import com.axmor.dao.IssueDAO;
import com.axmor.model.Issue;
import com.axmor.model.Status;
import com.axmor.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

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
        issue.setStatus(StatusService.getStatusByStatus("Created"));

        log.info("Create issue " + issue);

        issueDAO.create(issue);
    }

    public static void updateIssue(String id, String title, String description){
        Issue issue = getIssueById(id);
        issue.setTitle(title);
        issue.setDescription(description);

        log.info("Updated Issue" + issue);

        issueDAO.update(issue);
    }

    public static void deleteById(String strId){
        int id = Integer.valueOf(strId);
        log.info("Delete id = " + id);

        issueDAO.delete(id);
    }

    public static void updateStatusById(String id, Status status){
        Issue issue = getIssueById(id);

        if (issue != null) {
            issue.setStatus(status);
        }
    }
}
