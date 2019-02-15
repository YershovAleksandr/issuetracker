package com.axmor.dao.wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetWrapper implements AutoCloseable {
    private ResultSet resultSet;

    public UserResultSetWrapper(int parameterIndex, String name, PreparedStatement preparedStatement) throws SQLException{
        preparedStatement.setString(parameterIndex, name);

        resultSet = preparedStatement.executeQuery();
    }

    public ResultSet getResultSet(){
        return resultSet;
    }

    @Override
    public void close() throws SQLException {
        resultSet.close();
    }

}