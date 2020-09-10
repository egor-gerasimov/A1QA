package yandex.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import yandex.driver.PropertyManager;

public class LoginPageYandex {

    private static final By LOGIN = By.id("passp-field-login");
    private static final By PASSWORD = By.cssSelector("#passp-field-passwd");
    private static final By BUTTON_ENTER_LOGIN = By.xpath("//button[@data-t='button:action']");

    private final WebDriver driver;

    public LoginPageYandex(WebDriver driver) {
        this.driver = driver;
    }

    public void login() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofSeconds(3))
            .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(LOGIN));
        driver.findElement(LOGIN).click();
        driver.findElement(LOGIN).sendKeys(PropertyManager.getInstance().getYandexLogin());
        driver.findElement(LOGIN).submit();
        driver.findElement(PASSWORD).sendKeys(PropertyManager.getInstance().getYandexPassword());
        driver.findElement(PASSWORD).submit();
    }
}
