package com.axmor.model;

import java.util.Date;

public class Comment {
    private int id;
    private int userId;
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
                ", userId=" + userId +
                ", issueId=" + issueId +
                ", statusId=" + status +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
