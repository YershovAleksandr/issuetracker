package com.axmor.dao.wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserResultSetWrapper implements AutoCloseable {
    private ResultSet resultSet;

    public UserResultSetWrapper(int parameterIndex, String name, PreparedStatement preparedStatement) throws Exception{
        preparedStatement.setString(parameterIndex, name);

        resultSet = preparedStatement.executeQuery();
    }

    public ResultSet getResultSet(){
        return resultSet;
    }

    @Override
    public void close() throws Exception{
        resultSet.close();
    }

}