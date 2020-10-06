package yandex.utils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import yandex.driver.WebDriverSingleton;

public class Waiter {

    private static final WebDriver DRIVER = WebDriverSingleton.getInstance();
    private static final int TIMEOUT_IN_SECONDS = Integer
        .parseInt(PropertyManager.getProperty("timeout.in.sec"));
    private static final WebDriverWait EXPLICIT_WAIT = new WebDriverWait(DRIVER, TIMEOUT_IN_SECONDS);
    private static final FluentWait<WebDriver> FLUENT_WAIT = new FluentWait<>(DRIVER)
        .withTimeout(Duration.ofSeconds(TIMEOUT_IN_SECONDS))
        .pollingEvery(Duration.ofSeconds(3))
        .ignoring(NoSuchElementException.class);

    public static void explicitWaitLocated(By locator) {
        EXPLICIT_WAIT.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void explicitWaitTitle(String title) {
        EXPLICIT_WAIT.until(ExpectedConditions.titleIs(title));
    }

    public static void setImplicitlyWait() {
        DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebElement implicitWait(By locator) {
        return DRIVER.findElement(locator);
    }

    public static void fluentWait(By locator) {
        FLUENT_WAIT.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement implicitWait(By locator1, By locator2) {
        if (!DRIVER.findElements(locator1).isEmpty()) {
            return DRIVER.findElement(locator1);
        } else {
            return DRIVER.findElement(locator2);
        }
    }
}