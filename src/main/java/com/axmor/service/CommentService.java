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

    public static List<Comment> getByUserId(int userId){

        List<Comment> commentList = new ArrayList<>(commentDAO.getAll().values());
        List<Comment> resultList = new ArrayList<>();

        for(Comment comment : commentList){
            if (comment.getIssueId() == userId){
                resultList.add(comment);
            }
        }

        return resultList;
    }

    public static void create(Map<String, Object> params){
        //int userId = Integer.valueOf(params.get("userid"));
        //TODO FIX THIS FUCKING SHIT!!!!
        int issueId = Integer.valueOf((String)params.get("issueid"));
        String strStatus = (String)params.get("status");
        String text = (String)params.get("text");

        Comment comment = new Comment();

        //comment.setId(33);
        User user = (User)params.get("user");
        comment.setUser(user);
        comment.setIssueId(issueId);

        Status status = null;

        switch(strStatus){
            case "resolved" : status = StatusService.getResolvedStatus(); break;
            case "closed" : status = StatusService.getClosedStatus(); break;
            case "duplicated" : status = StatusService.getDuplicatedStatus(); break;
            case "reopened" : status = StatusService.getReopenedStatus(); break;

            default: status = StatusService.getCreatedStatus(); //Created;
        }

        comment.setStatus(status);
        comment.setText(text);
        comment.setDate(new Date());

        IssueService.updateStatusByIssueId(issueId, status);

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
