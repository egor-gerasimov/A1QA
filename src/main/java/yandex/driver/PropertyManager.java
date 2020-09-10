package yandex.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import yandex.Helper;

public class PropertyManager {

    private static PropertyManager instance;
    private final Properties properties;

    private PropertyManager() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(Helper.toMultiOS("src/main/resources/yandex.properties"))) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getBrowserName() {
        return properties.getProperty("browser.name");
    }

    public String getResourcesPath() {
        return Helper.toMultiOS(properties.getProperty("resources.path"));
    }

    public String getYandexLogin() {
        return Helper.toMultiOS(properties.getProperty("yandex.login"));
    }

    public String getYandexPassword() {
        return Helper.toMultiOS(properties.getProperty("yandex.password"));
    }
}
