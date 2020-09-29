package framework.driver;

import static framework.utils.StringUtils.toMultiOS;

import framework.driver.exception.WrongWebDriverException;
import framework.utils.PropertyManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {

    public static WebDriver getBrowser(String browserName) {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "firefox":
                return BrowserOptions.getFirefoxDriver();
            case "chrome":
                return BrowserOptions.getChromeDriver();
            default:
                throw new WrongWebDriverException("Didn't find framework.driver " + browserName);
        }
    }
}