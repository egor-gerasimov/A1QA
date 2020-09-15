package yandex.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import yandex.exception.WrongWebDriverException;

public class BrowserFactory {

    public static WebDriver getBrowser(String browserName) {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            default:
            {
                try {
                    throw new WrongWebDriverException("Didn't find driver " + browserName);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}