package com.axmor.dao;

public class SQLConstants {
    final public static String USERDB =                 "user";
    final public static String USERDB_COLUMN_ID =       "user_id";
    final public static String USERDB_COLUMN_NAME =     "user_name";
    final public static String USERDB_COLUMN_PASSWORD = "user_password";

    final public static String SELECT_FROM_USER_BY_NAME = String.format("SELECT * FROM %s WHERE %s = ?",
            USERDB,
            USERDB_COLUMN_NAME);
    final public static String INSERT_INTO_USER_VALUES = String.format("INSERT INTO %s (%s, %s) values(?, ?)",
            USERDB,
            USERDB_COLUMN_NAME,
            USERDB_COLUMN_PASSWORD);

    final public static String ISSUEDB =                    "issue";
    final public static String ISSUEDB_COLUMN_ID =          "issue_id";
    final public static String ISSUEDB_COLUMN_USERID =      "issue_userid";
    final public static String ISSUEDB_COLUMN_TITLE =       "issue_title";
    final public static String ISSUEDB_COLUMN_DESCRIPTION = "issue_description";
    final public static String ISSUEDB_COLUMN_DATE =        "issue_date";
    final public static String ISSUEDB_COLUMN_STATUS =      "issue_status";

    final public static String SELECT_FROM_ISSUE_JOIN_USER_ON_USERID_EQUALS_ISSUE_USERID = String.format("SELECT * FROM %s JOIN %s ON %s = %s",
            ISSUEDB,
            USERDB,
            USERDB_COLUMN_ID,
            ISSUEDB_COLUMN_USERID);

    final public static String SELECT_FROM_ISSUE_JOIN_USER_ON_USERID_EQUALS_ISSUE_USERID_BY_ISSUEID = String.format("SELECT * FROM %s JOIN %s ON %s = %s WHERE %s = ?",
            ISSUEDB,
            USERDB,
            USERDB_COLUMN_ID,
            ISSUEDB_COLUMN_USERID,
            ISSUEDB_COLUMN_ID);

    final public static String INSERT_INTO_ISSUE_VALUES = String.format("INSERT INTO %s(%s, %s, %s, %s, %s) values(?, ?, ?, ?, ?)",
            ISSUEDB,
            ISSUEDB_COLUMN_USERID,
            ISSUEDB_COLUMN_TITLE,
            ISSUEDB_COLUMN_DESCRIPTION,
            ISSUEDB_COLUMN_DATE,
            ISSUEDB_COLUMN_STATUS);

    final public static String UPDATE_ISSUE_TITLE_AND_DESCRIPTION_BY_ISSUEID = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
            ISSUEDB,
            ISSUEDB_COLUMN_TITLE,
            ISSUEDB_COLUMN_DESCRIPTION,
            ISSUEDB_COLUMN_ID);

    final public static String UPDATE_ISSUE_STATUS_BY_ISSUEID = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
            ISSUEDB,
            ISSUEDB_COLUMN_STATUS,
            ISSUEDB_COLUMN_ID);

    final public static String DELETE_FROM_ISSUE_BY_ISSUEID = String.format("DELETE FROM %s WHERE %s = ?",
            ISSUEDB,
            ISSUEDB_COLUMN_ID);

    final public static String COMMENTDB =                 "comment";
    final public static String COMMENTDB_COLUMN_ID =       "comment_id";
    final public static String COMMENTDB_COLUMN_USERID =   "comment_userid";
    final public static String COMMENTDB_COLUMN_ISSUEID =  "comment_issueid";
    final public static String COMMENTDB_COLUMN_STATUS =   "comment_status";
    final public static String COMMENTDB_COLUMN_TEXT =     "comment_text";
    final public static String COMMENTDB_COLUMN_DATE =     "comment_date";

    final public static String SELECT_FROM_COMMENT_JOIN_USER_ON_COMMENT_USERID_EQUALS_USER_ID_BY_COMMENT_USERID = String.format("SELECT * FROM %s JOIN %s ON %s = %s WHERE %s = ?",
            COMMENTDB,
            USERDB,
            COMMENTDB_COLUMN_USERID,
            USERDB_COLUMN_ID,
            COMMENTDB_COLUMN_ISSUEID);

    final public static String INSERT_INTO_COMMENT_VALUES = String.format("INSERT INTO %s(%s, %s, %s, %s, %s) values(?, ?, ?, ?, ?)",
            COMMENTDB,
            COMMENTDB_COLUMN_USERID,
            COMMENTDB_COLUMN_ISSUEID,
            COMMENTDB_COLUMN_STATUS,
            COMMENTDB_COLUMN_TEXT,
            COMMENTDB_COLUMN_DATE);
}