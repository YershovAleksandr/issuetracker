package com.axmor.dao;

import com.axmor.model.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueDAO {
    private List<Issue> issueList = new ArrayList<>();

    public List<Issue> getAll(){
        return issueList;
    }

    public Issue get(int id){
        return issueList.get(id);
    }

    public void create(Issue issue){
        issueList.add(issue);
    }

    public void update(Issue issue){
        issueList.set(issue.getId(), issue);
    }

    public void delete(int id){
        issueList.remove(id);
    }
}
