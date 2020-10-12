package utils;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class TestData {

    private static final JsonSettingsFile file = new JsonSettingsFile("test.data.json");

    public static Object getValue(String value) {
        return file.getValue(value);
    }

    public static String getUsername() {
        return getValue("/username").toString();
    }

    public static String getPassword() {
        return getValue("/password").toString();
    }
}
