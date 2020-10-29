package utils;

import aquality.selenium.browser.AqualityServices;
import de.vandermeer.asciitable.AsciiTable;
import models.Project;
import models.Test;

import java.util.List;

public class PrintUtils {

    private static void printToLog(String tableName, AsciiTable asciiTable) {
        String render = asciiTable.render();
        AqualityServices.getLogger().info(tableName + '\n' + render);
    }

    public static void printTestProjectMinTime(List<Test> tests) {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("PROJECT", "TEST", "MIN_WORKING_TIME");
        asciiTable.addRule();
        for (Test test : tests) {
            asciiTable.addRow(test.getProject().getName(), test.getName(), test.getMinTime());
            asciiTable.addRule();
        }
        printToLog("Tests min time", asciiTable);
    }

    public static void printProjectTestCount(List<Project> projects) {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("PROJECT", "TEST_COUNT");
        asciiTable.addRule();
        for (Project project : projects) {
            asciiTable.addRow(project.getName(), project.getTestCount());
            asciiTable.addRule();
        }
        printToLog("Projects test count", asciiTable);
    }

    public static void printTestProjectFromDate(List<Test> tests) {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("PROJECT", "TEST", "DATE");
        asciiTable.addRule();
        for (Test test : tests) {
            String timestamp = String.format("%tF %tT", test.getStartTime(), test.getStartTime());
            asciiTable.addRow(test.getProject().getName(), test.getName(), timestamp);
            asciiTable.addRule();
        }
        printToLog("Tests from date", asciiTable);
    }

    public static void printBrowsersTestCount(List<Integer> counts) {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("BROWSERS");
        asciiTable.addRule();
        for (Integer count : counts) {
            asciiTable.addRow(count);
            asciiTable.addRule();
        }
        printToLog("Browsers test count", asciiTable);
    }
}
