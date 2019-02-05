package com.axmor.service;

import com.axmor.dao.CommentDAO;
import com.axmor.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommentService {
    private static Logger log = LoggerFactory.getLogger(CommentService.class);
    private static CommentDAO commentDAO = new CommentDAO();

    public static List<Comment> getByUserId(int userId){

        List<Comment> commentList = commentDAO.getAll();
        List<Comment> resultList = new ArrayList<>();

        for(Comment comment : commentList){
            if (comment.getIssueId() == userId){
                resultList.add(comment);
            }
        }

        return resultList;
    }

    public static void create(Map<String, String> params){
        //int userId = Integer.valueOf(params.get("userid"));
        int issueId = Integer.valueOf(params.get("issueid"));
        String status = params.get("status");
        String text = params.get("text");

        Comment comment = new Comment();

        //comment.setId(33);
        comment.setUserId(44);
        comment.setIssueId(issueId);

        int intStatus = 0;

        switch(status){
            //case "wip" : intStatus = 7; break;
            case "resolved" : intStatus = 88; break;
            case "closed" : intStatus = 999; break;

            default: intStatus = 7;
        }

        comment.setStatusId(intStatus);
        comment.setText(text);
        comment.setDate(new Date());

        log.info("Create comment " + comment);

        commentDAO.create(comment);
    }

    /*
    public static void update(Map<String, String> params){
        log.info("Update id = " + params.get("id"));
        String title = params.get("title");
        String description = params.get("description");
        String id = params.get("id");

        int intId = Integer.valueOf(id);

        Issue issue = issueDAO.get(intId);

        log.info("Issue" + issue);
        issue.setTitle(title);
        issue.setDescription(description);
        log.info("Updated Issue" + issue);

        issueDAO.update(issue);
    }*/

    /*
    public static void delete(String strId){
        int id = Integer.valueOf(strId);
        log.info("Delete id = " + id);

        issueDAO.delete(id);
    }
    */

}
