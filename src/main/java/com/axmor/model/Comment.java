package com.axmor.model;

import java.util.Date;

public class Comment {
    private int id;
    private User user;
    private Issue issue;
    private int status;
    private String text;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment {" +
                "id=" + id +
                ", user=" + user +
                ", issue=" + issue +
                ", statusId=" + status +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
