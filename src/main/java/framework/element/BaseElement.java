package framework.element;

import framework.driver.Driver;
import framework.utils.Logger;
import framework.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class BaseElement {

    private final WebDriver driver = Driver.getInstance();
    private final By locator;

    public BaseElement(By locator) {
        this.locator = locator;
    }

    public WebElement getElement() {
        return driver.findElement(locator);
    }

    public boolean isEmpty() {
        return driver.findElements(locator).isEmpty();
    }

    public void click() {
        if (getElement() != null) {
            getElement().click();
        }
    }

    public void explicitWaitLocated() {
        Waits.explicitWaitLocated(locator);
    }

    public void clickOnLocation() {
        Logger.writeLog("Find element");
        WebElement element = getElement();
        Point location = element.getLocation();
        Dimension size = element.getSize();
        explicitWaitLocated();
        Logger.writeLog("Click element");
        new Actions(driver).moveByOffset(location.getX() + size.width / 2,
            location.getY() + size.height / 2).click().build().perform();
    }

    public void moveTo() {
        new Actions(driver).moveToElement(getElement()).build().perform();
    }

    public String getText() {
        return isEmpty() ? "" : getElement().getText();
    }
}
