package com.axmor.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:h2:./db");
        config.setUsername("user1");
        config.setPassword("123");

        ds = new HikariDataSource(config);
    }

    private DataSource(){}

    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
}
