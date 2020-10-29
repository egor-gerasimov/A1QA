package database;

import models.Project;
import models.Test;
import utils.PrintUtils;

import java.sql.Timestamp;
import java.util.List;

public class DB {

    public static void showTestProjectMinTime() {
        List<Test> tests = DataManager.getTestProjectMinTime();
        PrintUtils.printTestProjectMinTime(tests);
    }

    public static void showProjectTestCount() {
        List<Project> tests = DataManager.getProjectTestCount();
        PrintUtils.printProjectTestCount(tests);
    }

    public static void showTestProjectFromDate(Timestamp date) {
        List<Test> tests = DataManager.getTestProjectFromDate(date);
        PrintUtils.printTestProjectFromDate(tests);
    }

    public static void showBrowsersTestCount(String... browsers) {
        List<Integer> counts = DataManager.getBrowsersTestCount(browsers);
        PrintUtils.printBrowsersTestCount(counts);
    }
}
