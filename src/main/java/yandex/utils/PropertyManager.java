package yandex.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static Properties properties;

    public static void readProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(Helper.toMultiOS("src/main/resources/yandex.properties"))) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
