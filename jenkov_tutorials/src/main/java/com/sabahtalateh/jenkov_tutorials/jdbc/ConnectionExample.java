package com.sabahtalateh.jenkov_tutorials.jdbc;

import java.sql.*;

/**
 * ConnectionExample.
 */
public class ConnectionExample {
    /**
     * @param args args.
     * @throws SQLException           exception.
     * @throws ClassNotFoundException exception.
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/dbname";
        Connection connection = DriverManager.getConnection(url, "user", "password");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users LIMIT 10");

        while (resultSet.next()) {
            String username = resultSet.getString("username");
            System.out.println(username);
        }

        resultSet.close();
        statement.close();

        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println(metaData.getDatabaseProductName());
        System.out.println(metaData.getDatabaseProductVersion());
        System.out.println(metaData.getDatabaseMajorVersion());
        System.out.println(metaData.getDatabaseMinorVersion());

        connection.close();
    }
}
