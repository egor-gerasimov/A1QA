package database;

import aquality.selenium.browser.AqualityServices;
import utils.SettingsData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = SettingsData.getStringValue("db.connection.url");
    private static final String USER = SettingsData.getStringValue("db.username");
    private static final String PASSWORD = SettingsData.getStringValue("db.password");
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
