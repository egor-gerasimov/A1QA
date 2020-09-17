package framework.driver;

import framework.driver.exception.WrongWebDriverException;
import framework.utils.PropertyManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public static WebDriver getBrowser(String browserName) {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("intl.accept_languages=", PropertyManager.getProperty("lang"));
                return new FirefoxDriver(firefoxOptions);
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--lang=" + PropertyManager.getProperty("lang"));
                return new ChromeDriver(chromeOptions);
            default: {
                try {

                    throw new WrongWebDriverException("Didn't find framework.driver " + browserName);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}