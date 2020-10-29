package database;

import aquality.selenium.browser.AqualityServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/union_reporting?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1111";
    private static Connection instance;

    public static void createConnection() {
        try {
            instance = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (instance == null) {
            createConnection();
        }
        return instance;
    }

    public static void closeConnection() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }
}
