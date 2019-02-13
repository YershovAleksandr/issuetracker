package com.axmor.service;

import com.axmor.dao.CommentDAO;
import com.axmor.model.Comment;
import com.axmor.model.Issue;
import com.axmor.model.Status;
import com.axmor.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CommentService {
    private static Logger log = LoggerFactory.getLogger(CommentService.class);
    private CommentDAO commentDAO = new CommentDAO();

    public List<Comment> getCommentByIssueId(String id){
        return commentDAO.getByIssueId(id);
    }

    public void createCommentByIssueId(Issue issue, Status status, String text, User user){
        Comment comment = new Comment();

        comment.setUser(user);
        comment.setIssue(issue);
        comment.setStatus(status);
        comment.setText(text);
        comment.setDate(new Date());

        log.info("Create comment {}", comment);

        commentDAO.create(comment);
    }
}