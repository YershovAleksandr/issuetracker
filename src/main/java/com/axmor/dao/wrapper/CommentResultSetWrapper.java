package com.axmor.dao.wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentResultSetWrapper implements AutoCloseable{
    private ResultSet resultSet;

    public CommentResultSetWrapper(int parameterIndex, String issueId, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(parameterIndex, issueId);

        resultSet = preparedStatement.executeQuery();
    }

    public ResultSet getResultSet(){
        return resultSet;
    }

    @Override
    public void close() throws SQLException{
        resultSet.close();
    }
}