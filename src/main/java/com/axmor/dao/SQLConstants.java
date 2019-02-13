package com.axmor.dao;

public class SQLConstants {

    //Tables

    final public static String TABLE_USER = "user";
    final public static String TABLE_ISSUE = "issue";
    final public static String TABLE_COMMENT = "comment";

    //User

    final public static String TABLE_USER_COLUMN_ID = "user_id";
    final public static String TABLE_USER_COLUMN_NAME = "user_name";
    final public static String TABLE_USER_COLUMN_PASSWORD = "user_password";

    //Issue

    final public static String TABLE_ISSUE_COLUMN_ID = "issue_id";
    final public static String TABLE_ISSUE_COLUMN_USERID = "issue_userid";
    final public static String TABLE_ISSUE_COLUMN_TITLE = "issue_title";
    final public static String TABLE_ISSUE_COLUMN_DESCRIPTION = "issue_description";
    final public static String TABLE_ISSUE_COLUMN_DATE = "issue_date";
    final public static String TABLE_ISSUE_COLUMN_STATUS = "issue_status";

    //Comment

    final public static String TABLE_COMMENT_COLUMN_ID = "comment_id";
    final public static String TABLE_COMMENT_COLUMN_USERID = "comment_userid";
    final public static String TABLE_COMMENT_COLUMN_ISSUEID = "comment_issueid";
    final public static String TABLE_COMMENT_COLUMN_STATUS = "comment_status";
    final public static String TABLE_COMMENT_COLUMN_TEXT = "comment_text";
    final public static String TABLE_COMMENT_COLUMN_DATE = "comment_date";

    final public static String CREATE_USER_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (" +
            "%s int NOT NULL AUTO_INCREMENT," +
            "%s VARCHAR NOT NULL," +
            "%s VARCHAR NOT NULL," +
            "PRIMARY KEY (%s))",
            TABLE_USER,
            TABLE_USER_COLUMN_ID,
            TABLE_USER_COLUMN_NAME,
            TABLE_USER_COLUMN_PASSWORD,
            TABLE_USER_COLUMN_ID);

    final public static String CREATE_ISSUE_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (" +
            "%s int NOT NULL AUTO_INCREMENT," +
            "%s int NOT NULL," +
            "%s VARCHAR NOT NULL," +
            "%s VARCHAR NOT NULL," +
            "%s TIMESTAMP NOT NULL," +
            "%s VARCHAR NOT NULL," +
            "PRIMARY KEY (%s)," +
            "FOREIGN KEY (%s) REFERENCES %s(%s))",
            TABLE_ISSUE,
            TABLE_ISSUE_COLUMN_ID,
            TABLE_ISSUE_COLUMN_USERID,
            TABLE_ISSUE_COLUMN_TITLE,
            TABLE_ISSUE_COLUMN_DESCRIPTION,
            TABLE_ISSUE_COLUMN_DATE,
            TABLE_ISSUE_COLUMN_STATUS,
            TABLE_ISSUE_COLUMN_ID,
            TABLE_ISSUE_COLUMN_USERID,
            TABLE_USER,
            TABLE_USER_COLUMN_ID);

    final public static String CREATE_COMMENT_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (" +
            "%s int NOT NULL AUTO_INCREMENT," +
            "%s int NOT NULL," +
            "%s int NOT NULL," +
            "%s VARCHAR NOT NULL," +
            "%s VARCHAR NOT NULL," +
            "%s TIMESTAMP NOT NULL," +
            "PRIMARY KEY (%s)," +
            "FOREIGN KEY (%s) REFERENCES %s(%s)," +
            "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE)",
            TABLE_COMMENT,
            TABLE_COMMENT_COLUMN_ID,
            TABLE_COMMENT_COLUMN_USERID,
            TABLE_COMMENT_COLUMN_ISSUEID,
            TABLE_COMMENT_COLUMN_STATUS,
            TABLE_COMMENT_COLUMN_TEXT,
            TABLE_COMMENT_COLUMN_DATE,
            TABLE_COMMENT_COLUMN_ID,
            TABLE_COMMENT_COLUMN_USERID,
            TABLE_USER,
            TABLE_USER_COLUMN_ID,
            TABLE_COMMENT_COLUMN_ISSUEID,
            TABLE_ISSUE,
            TABLE_ISSUE_COLUMN_ID);

    //Queries for User Table

    final public static String INSERT_INTO_USER_VALUES = String.format("INSERT INTO %s (%s, %s) values(?, ?)",
            TABLE_USER,
            TABLE_USER_COLUMN_NAME,
            TABLE_USER_COLUMN_PASSWORD);
    final public static String SELECT_FROM_USER_BY_NAME = String.format("SELECT * FROM %s WHERE %s = ?",
            TABLE_USER,
            TABLE_USER_COLUMN_NAME);

    //Queries for Issue Table

    final public static String INSERT_INTO_ISSUE_VALUES = String.format("INSERT INTO %s(%s, %s, %s, %s, %s) values(?, ?, ?, ?, ?)",
            TABLE_ISSUE,
            TABLE_ISSUE_COLUMN_USERID,
            TABLE_ISSUE_COLUMN_TITLE,
            TABLE_ISSUE_COLUMN_DESCRIPTION,
            TABLE_ISSUE_COLUMN_DATE,
            TABLE_ISSUE_COLUMN_STATUS);

    final public static String SELECT_FROM_ISSUE_JOIN_USER_ON_USERID_EQUALS_ISSUE_USERID = String.format("SELECT * FROM %s JOIN %s ON %s = %s",
            TABLE_ISSUE,
            TABLE_USER,
            TABLE_USER_COLUMN_ID,
            TABLE_ISSUE_COLUMN_USERID);

    final public static String SELECT_FROM_ISSUE_JOIN_USER_ON_USERID_EQUALS_ISSUE_USERID_BY_ISSUEID = String.format("SELECT * FROM %s JOIN %s ON %s = %s WHERE %s = ?",
            TABLE_ISSUE,
            TABLE_USER,
            TABLE_USER_COLUMN_ID,
            TABLE_ISSUE_COLUMN_USERID,
            TABLE_ISSUE_COLUMN_ID);

    final public static String UPDATE_ISSUE_TITLE_AND_DESCRIPTION_BY_ISSUEID = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
            TABLE_ISSUE,
            TABLE_ISSUE_COLUMN_TITLE,
            TABLE_ISSUE_COLUMN_DESCRIPTION,
            TABLE_ISSUE_COLUMN_ID);

    final public static String UPDATE_ISSUE_STATUS_BY_ISSUEID = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
            TABLE_ISSUE,
            TABLE_ISSUE_COLUMN_STATUS,
            TABLE_ISSUE_COLUMN_ID);

    final public static String DELETE_FROM_ISSUE_BY_ISSUEID = String.format("DELETE FROM %s WHERE %s = ?",
            TABLE_ISSUE,
            TABLE_ISSUE_COLUMN_ID);

    //Queries for Comment Table

    final public static String INSERT_INTO_COMMENT_VALUES = String.format("INSERT INTO %s(%s, %s, %s, %s, %s) values(?, ?, ?, ?, ?)",
            TABLE_COMMENT,
            TABLE_COMMENT_COLUMN_USERID,
            TABLE_COMMENT_COLUMN_ISSUEID,
            TABLE_COMMENT_COLUMN_STATUS,
            TABLE_COMMENT_COLUMN_TEXT,
            TABLE_COMMENT_COLUMN_DATE);

    final public static String SELECT_FROM_COMMENT_JOIN_USER_ON_COMMENT_USERID_EQUALS_USER_ID_BY_COMMENT_USERID = String.format("SELECT * FROM %s JOIN %s ON %s = %s WHERE %s = ?",
            TABLE_COMMENT,
            TABLE_USER,
            TABLE_COMMENT_COLUMN_USERID,
            TABLE_USER_COLUMN_ID,
            TABLE_COMMENT_COLUMN_ISSUEID);
}