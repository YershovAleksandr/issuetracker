package com.axmor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class DataBaseConfig {
    private static Logger log = LoggerFactory.getLogger(DataBaseConfig.class);

    private final String PROPERTIES_DB_URL = "db.url";
    private final String PROPERTIES_DB_USERNAME = "db.username";
    private final String PROPERTIES_DB_PASSWORD = "db.password";

    private String url;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public DataBaseConfig(String propertiesName){
        Properties properties = new Properties();

        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesName)) {
            if (inputStream != null) {
                properties.load(inputStream);

                url = properties.getProperty(PROPERTIES_DB_URL);
                username = properties.getProperty(PROPERTIES_DB_USERNAME);
                password = properties.getProperty(PROPERTIES_DB_PASSWORD);
            }
        }catch (Exception e){
            log.error("Error read properties", e);
        }

        log.info(toString());
    }

    @Override
    public String toString() {
        return "DataBaseConfig {" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
