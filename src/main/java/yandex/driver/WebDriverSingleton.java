package yandex.driver;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import yandex.utils.PropertyManager;

public class WebDriverSingleton {

    private static WebDriver instance;

    private WebDriverSingleton() {
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
        getInstance().manage().window().maximize();
    }
}
