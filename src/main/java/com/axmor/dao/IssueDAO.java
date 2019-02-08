package com.axmor.dao;

import com.axmor.model.Issue;
import com.axmor.model.Status;
import com.axmor.model.User;
import com.axmor.service.StatusService;
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

    public List<Issue> getAll(){
        List<Issue> issueList = new ArrayList<>();

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM issue JOIN user ON user.id = issue.userid JOIN status ON issue.statusid = status.id");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Issue issue = new Issue();

                issue.setId(rs.getInt(1));

                User user = new User();
                user.setId(rs.getInt("userid"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                issue.setUser(user);

                issue.setTitle(rs.getString("title"));
                issue.setDescription(rs.getString("description"));
                issue.setDate(rs.getTimestamp("date"));
                issue.setStatus(StatusService.getStatusByStatus(rs.getString("status")));

                issueList.add(issue);
            }
        }catch(SQLException e){
            log.error("Error", e);
        }

        return issueList;
    }

    public Issue get(int id){
        Issue issue = null;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM issue JOIN user ON user.id = issue.userid JOIN status ON issue.statusid = status.id WHERE issue.id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                issue = new Issue();

                issue.setId(rs.getInt(1));

                User user = new User();
                user.setId(rs.getInt("userid"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                issue.setUser(user);

                issue.setTitle(rs.getString("title"));
                issue.setDescription(rs.getString("description"));
                issue.setDate(rs.getTimestamp("date"));
                issue.setStatus(StatusService.getStatusByStatus(rs.getString("status")));

                Status status = new Status();
                status.setId(0);
                status.setStatus("Created");
                issue.setStatus(status);

                issue.setStatus(new Status());
            }
        }catch(SQLException e){
            log.error("Error", e);
        }

        return issue;
    }

    public int create(Issue issue){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO issue(userid, title, description, date, statusid) values(?, ?, ?, ?, ?)");

            ps.setInt(1, issue.getUser().getId());
            ps.setString(2, issue.getTitle());
            ps.setString(3, issue.getDescription());
            ps.setTimestamp(4, new java.sql.Timestamp(issue.getDate().getTime()));
            ps.setInt(5, issue.getStatus().getId());

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        log.info("Created issue " + issue);

        return rez;
    }

    public int update(Issue issue){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE issue SET title = ?, description = ? WHERE id = ?");

            ps.setString(1, issue.getTitle());
            ps.setString(2, issue.getDescription());
            ps.setInt(3, issue.getId());

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        return rez;
    }

    public int delete(int id){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM issue WHERE id = ?");

            ps.setInt(1, id);

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        return rez;
    }
}
