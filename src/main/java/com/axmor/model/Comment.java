package com.axmor.model;

import java.util.Date;

public class Comment {
    private int id;
    private User user;
    private int issueId;
    private Status status;
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

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
                ", issueId=" + issueId +
                ", statusId=" + status +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
