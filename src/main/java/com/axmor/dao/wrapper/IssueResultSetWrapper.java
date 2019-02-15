package com.axmor.dao.wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueResultSetWrapper implements AutoCloseable {
    private ResultSet resultSet;

    public IssueResultSetWrapper(int parameterIndex, int id, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(parameterIndex, id);

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