package com.axmor.dao;

import com.axmor.model.Comment;
import com.axmor.model.User;
import com.axmor.service.IssueService;
import com.axmor.util.DataSource;
import com.axmor.util.StatusHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class CommentDAO {
    private Logger log = LoggerFactory.getLogger(CommentDAO.class);

    public List<Comment> getByIssueId(String issueid){
        List<Comment> commentList = new ArrayList<>();

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM comment JOIN user ON comment_userid = user_id WHERE comment_issueid = ?");

            ps.setString(1, issueid);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Comment comment = new Comment();

                comment.setId(rs.getInt("comment_id"));

                User user = new User();
                user.setId(rs.getInt("comment_userid"));
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                comment.setUser(user);
                comment.setIssue(IssueService.getIssueById(String.valueOf(rs.getInt("comment_issueid"))));
                comment.setStatus(rs.getString("comment_status"));
                comment.setText(rs.getString("comment_text"));
                comment.setDate(rs.getTimestamp("comment_date"));

                commentList.add(comment);

                log.info("Select comment status " + comment.getStatus());
            }
        }catch(SQLException e){
            log.error("Error", e);
        }

        return commentList;
    }

    public int create(Comment comment){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO comment(comment_userid, comment_issueid, comment_status, comment_text, comment_date) values(?, ?, ?, ?, ?)");

            ps.setInt(1, comment.getUser().getId());
            ps.setInt(2, comment.getIssue().getId());
            ps.setString(3, comment.getStatus());
            ps.setString(4, comment.getText());
            ps.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        log.info("Created comment " + comment);

        return rez;
    }
}