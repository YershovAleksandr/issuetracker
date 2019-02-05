package com.axmor.model;

import java.util.Date;

public class Comment {
    private int id;
    private int userId;
    private int issueId;
    private int statusId;
    private String text;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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
                ", userId=" + userId +
                ", issueId=" + issueId +
                ", statusId=" + statusId +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
