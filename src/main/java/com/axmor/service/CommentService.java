package com.axmor.service;

import com.axmor.dao.CommentDAO;
import com.axmor.model.Comment;
import com.axmor.model.Status;
import com.axmor.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CommentService {
    private static Logger log = LoggerFactory.getLogger(CommentService.class);
    private static CommentDAO commentDAO = new CommentDAO();

    public static List<Comment> getCommentByIssueId(String id){
        //
        int issueId = Integer.valueOf(id);

        List<Comment> commentList = new ArrayList<>(commentDAO.getAll().values());
        List<Comment> resultList = new ArrayList<>();

        for(Comment comment : commentList){
            if (comment.getIssueId() == issueId){
                resultList.add(comment);
            }
        }

        return resultList;
    }

    public static void createCommentByIssueId(String id, String status, String text, User user){
        Comment comment = new Comment();

        comment.setUser(user);
        //TODO FIX THIS FUCKING SHIT!!!
        comment.setIssueId(Integer.valueOf(id));

        Status st = null;

        switch(status){
            case "resolved" : st = StatusService.getResolvedStatus(); break;
            case "closed" : st = StatusService.getClosedStatus(); break;
            case "duplicated" : st = StatusService.getDuplicatedStatus(); break;
            case "reopened" : st = StatusService.getReopenedStatus(); break;

            default: st = StatusService.getCreatedStatus(); //Created;
        }

        comment.setStatus(st);
        comment.setText(text);
        comment.setDate(new Date());

        IssueService.updateStatusById(id, st);

        log.info("Create comment " + comment);

        commentDAO.create(comment);
    }


    public static void deleteByIssueId(String issueId){
        Map<Integer, Comment> commentMap = commentDAO.getAll();

        int intIssueId = Integer.valueOf(issueId);

        log.info("Comments " + commentMap.toString());
        commentMap.entrySet().removeIf(comment -> (comment.getValue().getIssueId() == intIssueId));
        log.info("Comments after remove" + commentMap.toString());
        /*
        for(Comment comment: commentList){
            if (comment.getIssueId() == intIssueId){
                log.info("Remove " + comment);

                commentList.remove(comment);
            }
        }*/
    }


    /*
    public static void delete(String strId){
        int id = Integer.valueOf(strId);
        log.info("Delete id = " + id);

        issueDAO.delete(id);
    }
    */

}
