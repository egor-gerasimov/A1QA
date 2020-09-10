package yandex.driver;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriver {

    private static ChromeDriver instance;

    private ChromeWebDriver() {
    }

    public static ChromeDriver getInstance() {
        if (instance == null) {
            instance = new ChromeDriver();
        }
        return instance;
    }
}
