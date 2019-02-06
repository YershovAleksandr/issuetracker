package com.axmor.dao;

import com.axmor.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class CommentDAO {
    private Logger log = LoggerFactory.getLogger(CommentDAO.class);
    private Map<Integer, Comment> commentMap = new LinkedHashMap<>();

    public Map<Integer, Comment> getAll(){
        return commentMap;
    }

    public Comment get(int id){
        return commentMap.get(id);
    }

    public void create(Comment comment){
        int newId = 0;

        if (commentMap.size() == 0){
            newId = 0;
        } else {
            newId = Collections.max(commentMap.keySet()) + 10;
        }

        commentMap.put(newId, comment);
        comment.setId(newId);

        log.info("Created comment " + comment);

    }

    public void update(Comment issue){
        commentMap.put(issue.getId(), issue);
    }

    public void delete(int id){
        commentMap.remove(id);
    }
}
