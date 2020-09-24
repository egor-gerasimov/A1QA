package framework.driver;

import framework.utils.Logger;
import framework.utils.PropertyManager;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;

public class Driver {

    private static WebDriver instance;

    private Driver() {
    }

    public static WebDriver getInstance() {
        if (instance == null) {
            instance = BrowserFactory.getBrowser(PropertyManager.getProperty("browser.name"));
        }
        return instance;
    }

    public static void switchToNextHandle() {
        ArrayList<String> handles = new ArrayList<>(getInstance().getWindowHandles());
        getInstance().switchTo().window(handles.get(handles.size() - 1));
    }

    public static void switchToFirstHandle() {
        ArrayList<String> handles = new ArrayList<>(getInstance().getWindowHandles());
        getInstance().switchTo().window(handles.get(0));
    }

    public static void maximizeWindow() {
        Logger.writeLog("Maximize window");
        getInstance().manage().window().maximize();
    }

    public static void getMainUrl() {
        Logger.writeLog("Get main url");
        getInstance().get(PropertyManager.getProperty("url"));
    }

    public static void quitDriver() {
        if (getInstance() != null) {
            Logger.writeLog("Quit driver");
            getInstance().quit();
        }
    }
}
