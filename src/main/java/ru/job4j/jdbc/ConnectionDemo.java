package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
        Config config = new Config("data/app.properties");
        config.load();
        String driver = config.value("hibernate.connection.driver_class");
        String urlAppProp = config.value("hibernate.connection.url");
        String loginAppProp = config.value("hibernate.connection.username");
        String passwordAppProp = config.value("hibernate.connection.password");
        System.out.println(driver);
        System.out.println(urlAppProp);
        System.out.println(loginAppProp);
        System.out.println(passwordAppProp);
    }
}