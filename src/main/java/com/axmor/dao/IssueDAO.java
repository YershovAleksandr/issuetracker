package com.axmor.dao;

import com.axmor.model.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class IssueDAO {
    private Logger log = LoggerFactory.getLogger(IssueDAO.class);
    private Map<Integer, Issue> issueMap = new LinkedHashMap<>();

    public Map<Integer, Issue> getAll(){
        return issueMap;
    }

    public Issue get(int id){
        return issueMap.get(id);
    }

    public void create(Issue issue){
        int newId = 0;

        if (issueMap.size() == 0){
            newId = 0;
        } else {
            newId = Collections.max(issueMap.keySet()) + 10;
        }

        issueMap.put(newId, issue);
        issue.setId(newId);

        log.info("Create issue " + issue);
    }

    public void update(Issue issue){
        issueMap.put(issue.getId(), issue);
    }

    public void delete(int id){
        issueMap.remove(id);
    }
}
