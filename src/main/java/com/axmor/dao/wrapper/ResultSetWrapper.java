package com.axmor.dao.wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetWrapper implements AutoCloseable{
    private ResultSet resultSet;

    public ResultSetWrapper(int parameterIndex, String parameter, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(parameterIndex, parameter);

        resultSet = preparedStatement.executeQuery();
    }

    public ResultSetWrapper(int parameterIndex, int parameter, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(parameterIndex, parameter);

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