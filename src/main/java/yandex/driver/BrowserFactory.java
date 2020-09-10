package yandex.driver;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;

public class BrowserFactory {

    private static final Map<String, WebDriver> DRIVERS = new HashMap<String, WebDriver>();

    public static WebDriver getBrowser(String browserName) {

        WebDriver driver = null;
        PropertyManager propertyManager = PropertyManager.getInstance();

        switch (browserName) {
            case "firefox":
                driver = DRIVERS.get("firefox");
                if (driver == null) {
                    System.setProperty("webdriver.gecko.driver", propertyManager.getResourcesPath() + "geckodriver.exe");
                    driver = FirefoxWebDriver.getInstance();
                    DRIVERS.put("firefox", driver);
                }
                break;
            case "chrome":
                driver = DRIVERS.get("chrome");
                if (driver == null) {
                    System.setProperty("webdriver.chrome.driver", propertyManager.getResourcesPath() + "chromedriver.exe");
                    driver = ChromeWebDriver.getInstance();
                    DRIVERS.put("chrome", driver);
                }
                break;
        }
        return driver;
    }
}