package com.axmor.dao;

public class SQLConstants {

    //Tables

    final public static String TABLEUSER = "user";
    final public static String TABLEISSUE = "issue";
    final public static String TABLECOMMENT = "comment";

    //User

    final public static String TABLEUSER_COLUMN_ID = "user_id";
    final public static String TABLEUSER_COLUMN_NAME = "user_name";
    final public static String TABLEUSER_COLUMN_PASSWORD = "user_password";

    //Issue

    final public static String TABLEISSUE_COLUMN_ID = "issue_id";
    final public static String TABLEISSUE_COLUMN_USERID = "issue_userid";
    final public static String TABLEISSUE_COLUMN_TITLE = "issue_title";
    final public static String TABLEISSUE_COLUMN_DESCRIPTION = "issue_description";
    final public static String TABLEISSUE_COLUMN_DATE = "issue_date";
    final public static String TABLEISSUE_COLUMN_STATUS = "issue_status";

    //Comment

    final public static String TABLECOMMENT_COLUMN_ID = "comment_id";
    final public static String TABLECOMMENT_COLUMN_USERID = "comment_userid";
    final public static String TABLECOMMENT_COLUMN_ISSUEID = "comment_issueid";
    final public static String TABLECOMMENT_COLUMN_STATUS = "comment_status";
    final public static String TABLECOMMENT_COLUMN_TEXT = "comment_text";
    final public static String TABLECOMMENT_COLUMN_DATE = "comment_date";

    final public static String CREATE_USER_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (" +
            "%s int NOT NULL AUTO_INCREMENT," +
            "%s VARCHAR NOT NULL," +
            "%s VARCHAR NOT NULL," +
            "PRIMARY KEY (%s))",
            TABLEUSER,
            TABLEUSER_COLUMN_ID,
            TABLEUSER_COLUMN_NAME,
            TABLEUSER_COLUMN_PASSWORD,
            TABLEUSER_COLUMN_ID);

    final public static String CREATE_ISSUE_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (" +
            "%s int NOT NULL AUTO_INCREMENT," +
            "%s int NOT NULL," +
            "%s VARCHAR NOT NULL," +
            "%s VARCHAR NOT NULL," +
            "%s TIMESTAMP NOT NULL," +
            "%s VARCHAR NOT NULL," +
            "PRIMARY KEY (%s)," +
            "FOREIGN KEY (%s) REFERENCES %s(%s))",
            TABLEISSUE,
            TABLEISSUE_COLUMN_ID,
            TABLEISSUE_COLUMN_USERID,
            TABLEISSUE_COLUMN_TITLE,
            TABLEISSUE_COLUMN_DESCRIPTION,
            TABLEISSUE_COLUMN_DATE,
            TABLEISSUE_COLUMN_STATUS,
            TABLEISSUE_COLUMN_ID,
            TABLEISSUE_COLUMN_USERID,
            TABLEUSER,
            TABLEUSER_COLUMN_ID);

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
            TABLECOMMENT,
            TABLECOMMENT_COLUMN_ID,
            TABLECOMMENT_COLUMN_USERID,
            TABLECOMMENT_COLUMN_ISSUEID,
            TABLECOMMENT_COLUMN_STATUS,
            TABLECOMMENT_COLUMN_TEXT,
            TABLECOMMENT_COLUMN_DATE,
            TABLECOMMENT_COLUMN_ID,
            TABLECOMMENT_COLUMN_USERID,
            TABLEUSER,
            TABLEUSER_COLUMN_ID,
            TABLECOMMENT_COLUMN_ISSUEID,
            TABLEISSUE,
            TABLEISSUE_COLUMN_ID);

    //Queries for User Table

    final public static String INSERT_INTO_USER_VALUES = String.format("INSERT INTO %s (%s, %s) values(?, ?)",
            TABLEUSER,
            TABLEUSER_COLUMN_NAME,
            TABLEUSER_COLUMN_PASSWORD);
    final public static String SELECT_FROM_USER_BY_NAME = String.format("SELECT * FROM %s WHERE %s = ?",
            TABLEUSER,
            TABLEUSER_COLUMN_NAME);

    //Queries for Issue Table

    final public static String INSERT_INTO_ISSUE_VALUES = String.format("INSERT INTO %s(%s, %s, %s, %s, %s) values(?, ?, ?, ?, ?)",
            TABLEISSUE,
            TABLEISSUE_COLUMN_USERID,
            TABLEISSUE_COLUMN_TITLE,
            TABLEISSUE_COLUMN_DESCRIPTION,
            TABLEISSUE_COLUMN_DATE,
            TABLEISSUE_COLUMN_STATUS);

    final public static String SELECT_FROM_ISSUE_JOIN_USER_ON_USERID_EQUALS_ISSUE_USERID = String.format("SELECT * FROM %s JOIN %s ON %s = %s",
            TABLEISSUE,
            TABLEUSER,
            TABLEUSER_COLUMN_ID,
            TABLEISSUE_COLUMN_USERID);

    final public static String SELECT_FROM_ISSUE_JOIN_USER_ON_USERID_EQUALS_ISSUE_USERID_BY_ISSUEID = String.format("SELECT * FROM %s JOIN %s ON %s = %s WHERE %s = ?",
            TABLEISSUE,
            TABLEUSER,
            TABLEUSER_COLUMN_ID,
            TABLEISSUE_COLUMN_USERID,
            TABLEISSUE_COLUMN_ID);

    final public static String UPDATE_ISSUE_TITLE_AND_DESCRIPTION_BY_ISSUEID = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
            TABLEISSUE,
            TABLEISSUE_COLUMN_TITLE,
            TABLEISSUE_COLUMN_DESCRIPTION,
            TABLEISSUE_COLUMN_ID);

    final public static String UPDATE_ISSUE_STATUS_BY_ISSUEID = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
            TABLEISSUE,
            TABLEISSUE_COLUMN_STATUS,
            TABLEISSUE_COLUMN_ID);

    final public static String DELETE_FROM_ISSUE_BY_ISSUEID = String.format("DELETE FROM %s WHERE %s = ?",
            TABLEISSUE,
            TABLEISSUE_COLUMN_ID);

    //Queries for Comment Table

    final public static String INSERT_INTO_COMMENT_VALUES = String.format("INSERT INTO %s(%s, %s, %s, %s, %s) values(?, ?, ?, ?, ?)",
            TABLECOMMENT,
            TABLECOMMENT_COLUMN_USERID,
            TABLECOMMENT_COLUMN_ISSUEID,
            TABLECOMMENT_COLUMN_STATUS,
            TABLECOMMENT_COLUMN_TEXT,
            TABLECOMMENT_COLUMN_DATE);

    final public static String SELECT_FROM_COMMENT_JOIN_USER_ON_COMMENT_USERID_EQUALS_USER_ID_BY_COMMENT_USERID = String.format("SELECT * FROM %s JOIN %s ON %s = %s WHERE %s = ?",
            TABLECOMMENT,
            TABLEUSER,
            TABLECOMMENT_COLUMN_USERID,
            TABLEUSER_COLUMN_ID,
            TABLECOMMENT_COLUMN_ISSUEID);
}