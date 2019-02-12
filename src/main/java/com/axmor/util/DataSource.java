package com.axmor.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final String PROPERTIES = "application.properties";

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static DataBaseConfig dataBaseConfig = new DataBaseConfig(PROPERTIES);

    static {
        config.setJdbcUrl(dataBaseConfig.getUrl());
        config.setUsername(dataBaseConfig.getUsername());
        config.setPassword(dataBaseConfig.getPassword());

        ds = new HikariDataSource(config);
    }

    private DataSource(){}

    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
}