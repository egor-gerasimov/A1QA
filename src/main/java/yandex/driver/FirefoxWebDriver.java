package yandex.driver;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxWebDriver {

    private static FirefoxDriver instance;

    private FirefoxWebDriver() {
    }

    public static FirefoxDriver getInstance() {
        if (instance == null) {
            instance = new FirefoxDriver();
        }
        return instance;
    }
}
