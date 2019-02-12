package com.axmor.dao;

import com.axmor.model.Comment;
import com.axmor.model.User;
import com.axmor.service.IssueService;
import com.axmor.util.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.axmor.dao.SQLConstants.*;


public class CommentDAO {
    private Logger log = LoggerFactory.getLogger(CommentDAO.class);

    public List<Comment> getByIssueId(String issueid){
        List<Comment> commentList = new ArrayList<>();

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement(SELECT_FROM_COMMENT_JOIN_USER_BY_COMMENT_USERID_EQUALS_USER_ID_BY_COMMENT_USERID);

            ps.setString(1, issueid);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Comment comment = new Comment();

                comment.setId(rs.getInt(SQLConstants.COMMENTDB_COLUMN_ID));

                User user = new User();
                user.setId(rs.getInt(SQLConstants.COMMENTDB_COLUMN_USERID));
                user.setName(rs.getString(SQLConstants.USERDB_COLUMN_NAME));
                user.setPassword(rs.getString(SQLConstants.USERDB_COLUMN_PASSWORD));
                comment.setUser(user);
                comment.setIssue(IssueService.getIssueById(String.valueOf(rs.getInt(SQLConstants.COMMENTDB_COLUMN_ISSUEID))));
                comment.setStatus(rs.getString(SQLConstants.COMMENTDB_COLUMN_STATUS));
                comment.setText(rs.getString(SQLConstants.COMMENTDB_COLUMN_TEXT));
                comment.setDate(rs.getTimestamp(SQLConstants.COMMENTDB_COLUMN_DATE));

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

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement(INSERT_INTO_COMMENT_VALUES);

            ps.setInt(1, comment.getUser().getId());
            ps.setInt(2, comment.getIssue().getId());
            ps.setString(3, comment.getStatus());
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