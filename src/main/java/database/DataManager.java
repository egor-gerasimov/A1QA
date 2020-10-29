package database;

import aquality.selenium.browser.AqualityServices;
import constants.Query;
import models.Project;
import models.Test;
import models.managers.ProjectsManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static List<Test> getTestProjectMinTime() {
        List<Test> tests = new ArrayList<>();
        try (Statement statement = ConnectionManager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(Query.PROJECT_TEST_MIN_TIME)) {
            tests = getTestProjectMinTime(resultSet);
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return tests;
    }

    public static List<Project> getProjectTestCount() {
        List<Project> projects = new ArrayList<>();
        try (Statement statement = ConnectionManager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(Query.PROJECT_TESTS_COUNT)) {
            projects = getProjectTestCount(resultSet);
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return projects;
    }

    public static List<Test> getTestProjectFromDate(Timestamp datetime) {
        List<Test> tests = new ArrayList<>();
        try (Statement statement = ConnectionManager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(Query.getTestsFromDateTime(datetime))) {
            tests = getTestProjectFromDate(resultSet);
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return tests;
    }

    public static List<Integer> getBrowsersTestCount(String[] browsers) {
        List<Integer> counts = new ArrayList<>();
        try (Statement statement = ConnectionManager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(Query.getBrowsersTestCount(browsers))) {
            counts = getBrowsersTestCount(resultSet);
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return counts;
    }

    private static List<Test> getTestProjectMinTime(ResultSet rs) throws SQLException {
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

    private static List<Project> getProjectTestCount(ResultSet rs) throws SQLException {
        List<Project> projects = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString(1);
            int count = rs.getInt(2);
            Project project = new Project(name);
            project.setTestCount(count);
            projects.add(project);
        }
        return projects;
    }

    private static List<Test> getTestProjectFromDate(ResultSet rs) throws SQLException {
        List<Test> tests = new ArrayList<>();
        while (rs.next()) {
            Test test = new Test();
            ProjectsManager projectsManager = new ProjectsManager();
            String projectName = rs.getString(1);
            String testName = rs.getString(2);
            Timestamp dateTime = rs.getTimestamp(3);
            test.setProject(projectsManager.getProject(projectName));
            test.setName(testName);
            test.setStartTime(dateTime);
            tests.add(test);
        }
        return tests;
    }

    private static List<Integer> getBrowsersTestCount(ResultSet rs) throws SQLException {
        List<Integer> counts = new ArrayList<>();
        while (rs.next()) {
            int count = rs.getInt(1);
            counts.add(count);
        }
        return counts;
    }

}