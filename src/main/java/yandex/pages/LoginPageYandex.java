package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yandex.utils.PropertyManager;
import yandex.utils.Waiter;
import yandex.driver.WebDriverSingleton;

public class LoginPageYandex {

    private static final By LOGIN = By.id("passp-field-login");
    private static final By PASSWORD = By.cssSelector("#passp-field-passwd");
    private static final By BUTTON_ENTER_LOGIN = By.xpath("//button[@data-t='button:action']");

    private final WebDriver driver = WebDriverSingleton.getInstance();

    public void login() {
        Waiter.fluentWait(LOGIN);
        driver.findElement(LOGIN).click();
        driver.findElement(LOGIN).sendKeys(PropertyManager.getProperty("yandex.login"));
        driver.findElement(LOGIN).submit();
        driver.findElement(PASSWORD).sendKeys(PropertyManager.getProperty("yandex.password"));
        driver.findElement(PASSWORD).submit();
    }
}
