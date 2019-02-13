package com.axmor.dao;

import com.axmor.model.Issue;
import com.axmor.model.Status;
import com.axmor.model.User;
import com.axmor.util.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.axmor.dao.SQLConstants.*;

public class IssueDAO {
    private Logger log = LoggerFactory.getLogger(IssueDAO.class);

    public List<Issue> getAll(){
        List<Issue> issueList = new ArrayList<>();

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement(SELECT_FROM_ISSUE_JOIN_USER_ON_USERID_EQUALS_ISSUE_USERID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Issue issue = new Issue();

                issue.setId(rs.getInt(1));

                User user = new User();
                user.setId(rs.getInt(TABLE_USER_COLUMN_ID));
                user.setName(rs.getString(TABLE_USER_COLUMN_NAME));
                user.setPassword(rs.getString(TABLE_USER_COLUMN_PASSWORD));
                issue.setUser(user);

                issue.setTitle(rs.getString(TABLE_ISSUE_COLUMN_TITLE));
                issue.setDescription(rs.getString(TABLE_ISSUE_COLUMN_DESCRIPTION));
                issue.setDate(rs.getTimestamp(TABLE_ISSUE_COLUMN_DATE));
                issue.setStatus(Status.valueOf(rs.getString(TABLE_ISSUE_COLUMN_STATUS)));

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
            PreparedStatement ps = con.prepareStatement(SELECT_FROM_ISSUE_JOIN_USER_ON_USERID_EQUALS_ISSUE_USERID_BY_ISSUEID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                issue = new Issue();

                issue.setId(rs.getInt(1));

                User user = new User();
                user.setId(rs.getInt(TABLE_USER_COLUMN_ID));
                user.setName(rs.getString(TABLE_USER_COLUMN_NAME));
                user.setPassword(rs.getString(TABLE_USER_COLUMN_PASSWORD));
                issue.setUser(user);

                issue.setTitle(rs.getString(TABLE_ISSUE_COLUMN_TITLE));
                issue.setDescription(rs.getString(TABLE_ISSUE_COLUMN_DESCRIPTION));
                issue.setDate(rs.getTimestamp(TABLE_ISSUE_COLUMN_DATE));
                issue.setStatus(Status.valueOf(rs.getString(TABLE_ISSUE_COLUMN_STATUS)));
            }
        }catch(SQLException e){
            log.error("Error", e);
        }

        return issue;
    }

    public int create(Issue issue){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement(INSERT_INTO_ISSUE_VALUES);

            ps.setInt(1, issue.getUser().getId());
            ps.setString(2, issue.getTitle());
            ps.setString(3, issue.getDescription());
            ps.setTimestamp(4, new java.sql.Timestamp(issue.getDate().getTime()));
            ps.setString(5, issue.getStatus().name());

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        log.info("Created issue {}", issue);

        return rez;
    }

    public int update(Issue issue){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement(UPDATE_ISSUE_TITLE_AND_DESCRIPTION_BY_ISSUEID);

            ps.setString(1, issue.getTitle());
            ps.setString(2, issue.getDescription());
            ps.setInt(3, issue.getId());

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        return rez;
    }

    public int updateStatus(Issue issue){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement(UPDATE_ISSUE_STATUS_BY_ISSUEID);

            ps.setString(1, issue.getStatus().name());
            ps.setInt(2, issue.getId());

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        return rez;
    }


    public int delete(int id){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement(DELETE_FROM_ISSUE_BY_ISSUEID);

            ps.setInt(1, id);

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        return rez;
    }
}