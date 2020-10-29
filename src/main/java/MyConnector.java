import aquality.selenium.browser.AqualityServices;
import constants.Query;
import managers.ProjectsManager;
import models.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyConnector {

    private static final String url = "jdbc:mysql://localhost:3306/union_reporting?useTimezone=true&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "1111";

    public static List<Test> getTests() throws SQLException {
        ResultSet rs = getResultSet(Query.PROJECT_TEST_MIN_TIME);
        List<Test> tests = new ArrayList<>();
        while (rs.next()) {
            Test test = new Test();
            ProjectsManager projectsManager = new ProjectsManager();
            String projectName = rs.getString(1);
            String testName = rs.getString(2);
            int time = rs.getInt(3);
            test.setProject(projectsManager.getProject(projectName));
            test.setName(testName);
            test.setMinTime(time);
            tests.add(test);
        }
        return tests;
    }

    public static void executeQuery(String query) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getSQLState());
        }
    }
}