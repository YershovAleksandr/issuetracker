package com.axmor.dao;

import com.axmor.dao.wrapper.CommentResultSetWrapper;
import com.axmor.model.Comment;
import com.axmor.model.Status;
import com.axmor.model.User;
import com.axmor.util.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.*;

import static com.axmor.Main.issueService;
import static com.axmor.dao.SQLConstants.*;

public class CommentDAO {
    private Logger log = LoggerFactory.getLogger(CommentDAO.class);

    public List<Comment> getByIssueId(String issueid){
        List<Comment> commentList = new ArrayList<>();

        try(Connection con = DataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_FROM_COMMENT_JOIN_USER_ON_COMMENT_USERID_EQUALS_USER_ID_BY_COMMENT_USERID);
            CommentResultSetWrapper rsWrapper = new CommentResultSetWrapper(1, issueid, ps)){

            while(rsWrapper.getResultSet().next()) {
                Comment comment = new Comment();

                comment.setId(rsWrapper.getResultSet().getInt(TABLE_COMMENT_COLUMN_ID));

                User user = new User();
                user.setId(rsWrapper.getResultSet().getInt(TABLE_COMMENT_COLUMN_USERID));
                user.setName(rsWrapper.getResultSet().getString(TABLE_USER_COLUMN_NAME));
                user.setPassword(rsWrapper.getResultSet().getString(TABLE_USER_COLUMN_PASSWORD));
                comment.setUser(user);

                comment.setIssue(issueService.getIssueById(String.valueOf(rsWrapper.getResultSet().getInt(TABLE_COMMENT_COLUMN_ISSUEID))));
                comment.setStatus(Status.valueOf(rsWrapper.getResultSet().getString(TABLE_COMMENT_COLUMN_STATUS)));
                comment.setText(rsWrapper.getResultSet().getString(TABLE_COMMENT_COLUMN_TEXT));
                comment.setDate(rsWrapper.getResultSet().getTimestamp(TABLE_COMMENT_COLUMN_DATE));

                commentList.add(comment);

                log.info("Select comment status {}", comment.getStatus());
            }
        }catch(SQLException e){
            log.error("Error", e);
        }

        return commentList;
    }

    public int create(Comment comment){
        int rez = 0;

        try(Connection con = DataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_INTO_COMMENT_VALUES)){

            ps.setInt(1, comment.getUser().getId());
            ps.setInt(2, comment.getIssue().getId());
            ps.setString(3, comment.getStatus().name());
            ps.setString(4, comment.getText());
            ps.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));

            rez = ps.executeUpdate();

        }catch(SQLException e){
            log.error("Error", e);
        }

        log.info("Created comment {}", comment);

        return rez;
    }
}