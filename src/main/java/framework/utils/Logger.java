package framework.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;

public class Logger {

    private static final Deque<String> logs = new LinkedList<>();

    private static String getCurrentDate() {
        return DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())
            .format(System.currentTimeMillis());
    }

    public static boolean clearLog() {
        return new File(PropertyManager.getProperty("log.file")).delete();
    }

    public static void writeLog(String str) {
        logs.add(toLogFormat(str));
        try (PrintWriter pw = new PrintWriter(PropertyManager.getProperty("log.file"))) {
            for (String log : logs) {
                pw.println(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String toLogFormat(String str) {
        return getCurrentDate() + " " + str;
    }
}
