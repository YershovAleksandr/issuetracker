package com.axmor.dao;

import com.axmor.model.Issue;
import com.axmor.util.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class IssueDAO {
    private Logger log = LoggerFactory.getLogger(IssueDAO.class);
    private HashMap<Integer, Issue> issueMap = new LinkedHashMap<>();

    public List<Issue> getAll(){
        return new ArrayList<>(issueMap.values());
    }

    public Issue get(int id){
        return issueMap.get(id);
    }

    public void create(Issue issue){
        int newId;

        if (issueMap.size() == 0){
            newId = 0;
        } else {
            newId = Collections.max(issueMap.keySet()) + 10;
        }

        issueMap.put(newId, issue);
        issue.setId(newId);

        try (Connection con = DataSource.getConnection()){

            PreparedStatement pst = con.prepareStatement("show tables");

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                log.info(rs.getString(1));
            }


        }catch (SQLException e){
            log.error("Error", e);
        }


        log.info("Create issue " + issue);
    }

    public void update(Issue issue){
        issueMap.put(issue.getId(), issue);
    }

    public void delete(int id){
        issueMap.remove(id);
    }
}
