package com.axmor.service;

import com.axmor.dao.CommentDAO;
import com.axmor.model.Comment;
import com.axmor.model.Issue;
import com.axmor.model.User;
import com.axmor.util.StatusHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CommentService {
    private static Logger log = LoggerFactory.getLogger(CommentService.class);
    private static CommentDAO commentDAO = new CommentDAO();

    public static List<Comment> getCommentByIssueId(String id){
        //
        //int issueId = Integer.valueOf(id);

        //List<Comment> commentList = commentDAO.getAll();
        /*List<Comment> resultList = new ArrayList<>();

        for(Comment comment : commentList){
            if (comment.getIssue().getId() == issueId){
                resultList.add(comment);
            }
        }*/

        return commentDAO.getByIssueId(id);
    }

    public static void createCommentByIssueId(Issue issue, int status, String text, User user){
        Comment comment = new Comment();

        comment.setUser(user);
        comment.setIssue(issue);
        comment.setStatus(status);
        comment.setText(text);
        comment.setDate(new Date());

        log.info("Create comment " + comment);

        commentDAO.create(comment);
    }

    public static void deleteByIssueId(String issueId){
        /*Map<Integer, Comment> commentMap = commentDAO.getAll();

        int intIssueId = Integer.valueOf(issueId);

        log.info("Comments " + commentMap.toString());
        commentMap.entrySet().removeIf(comment -> (comment.getValue().getIssue().getId() == intIssueId));
        log.info("Comments after remove" + commentMap.toString());*/
        /*
        for(Comment comment: commentList){
            if (comment.getIssueId() == intIssueId){
                log.info("Remove " + comment);

                commentList.remove(comment);
            }
        }*/
    }
}