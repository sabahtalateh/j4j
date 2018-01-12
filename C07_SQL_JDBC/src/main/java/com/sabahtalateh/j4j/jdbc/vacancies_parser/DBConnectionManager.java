package com.sabahtalateh.j4j.jdbc.vacancies_parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBConnectionManager.
 */
public class DBConnectionManager {
    private String url = null;
    private Properties props = new Properties();

    /**
     * @param driver   db driver.
     * @param host     db host.
     * @param dbName   db name.
     * @param user     db user.
     * @param password db password.
     */
    DBConnectionManager(String driver, String host, String dbName, String user, String password) {
        this.url = String.format("jdbc:%s://%s/%s", driver, host, dbName);
        this.props.setProperty("user", user);
        this.props.setProperty("password", password);
    }

    /**
     * @return connection.
     * @throws SQLException exception.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.props);
    }
}
