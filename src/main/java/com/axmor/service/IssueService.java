package com.axmor.service;

import com.axmor.dao.IssueDAO;
import com.axmor.model.Issue;
import com.axmor.model.User;
import com.axmor.util.StatusHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class IssueService {
    private static Logger log = LoggerFactory.getLogger(IssueService.class);
    private IssueDAO issueDAO = new IssueDAO();

    public List<Issue> getAllIssues(){
        return issueDAO.getAll();
    }

    public boolean isIssueExistsById(String id){
        Issue issue = getIssueById(id);

        if (issue != null){
            return true;
        } else {
            return false;
        }
    }

    public Issue getIssueById(String id){
        int intId;

        try {
            intId = Integer.valueOf(id);
        } catch (NumberFormatException e){
            return null;
        }

        return issueDAO.get(intId);
    }

    public void createIssue(User user, String title, String description){
        Issue issue = new Issue();
        issue.setUser(user);
        issue.setTitle(title);
        issue.setDescription(description);
        issue.setDate(new Date());
        issue.setStatus(StatusHelper.getCreatedStatus());

        log.info("Create issue {}", issue);

        issueDAO.create(issue);
    }

    public void updateIssue(String id, String title, String description){
        Issue issue = getIssueById(id);
        issue.setTitle(title);
        issue.setDescription(description);

        log.info("Updated Issue {}", issue);

        issueDAO.update(issue);
    }

    public void deleteById(String strId){
        int id = Integer.valueOf(strId);
        log.info("Delete id = {}", id);

        issueDAO.delete(id);
    }

    public void updateStatusById(String id, String status){
        Issue issue = getIssueById(id);
        issue.setStatus(status);

        log.info("Updated Issue status {}", issue);

        issueDAO.updateStatus(issue);
    }
}