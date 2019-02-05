package com.axmor.dao;

import com.axmor.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class CommentDAO {
    private Logger log = LoggerFactory.getLogger(CommentDAO.class);
    private List<Comment> commentList = new ArrayList<>();

    public List<Comment> getAll(){
        return commentList;
    }

    public Comment get(int id){
        return commentList.get(id);
    }

    public void create(Comment issue){
        commentList.add(issue);
        issue.setId(commentList.indexOf(issue));
    }

    public void update(Comment issue){
        commentList.add(issue.getId(), issue);
    }

    public void delete(int id){
        commentList.remove(id);
    }
}
