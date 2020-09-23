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
    private String name;

    public BaseElement(By locator) {
        this.locator = locator;
    }

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElementName() {
        return "Element";
    }

    @Override
    public String toString() {
        return getElementName() + " " + getName();
    }

    public WebElement getElement() {
        return driver.findElement(locator);
    }

    public boolean exists() {
        return !driver.findElements(locator).isEmpty();
    }

    public void click() {
        if (getElement() != null) {
            Logger.writeLog("Click " + toString());
            getElement().click();
        }
    }

    public void explicitWaitLocated() {
        Waits.explicitWaitLocated(locator);
    }

    public void clickOnLocation() {
        Logger.writeLog("Find " + toString());
        WebElement element = getElement();
        Point location = element.getLocation();
        Dimension size = element.getSize();
        explicitWaitLocated();
        Logger.writeLog("Click on location of " + toString());
        new Actions(driver).moveByOffset(location.getX() + size.width / 2,
            location.getY() + size.height / 2).click().build().perform();
    }

    public void moveTo() {
        Logger.writeLog("Move to " + toString());
        new Actions(driver).moveToElement(getElement()).perform();
    }

    public void moveAndContextClick() {
        Logger.writeLog("Move and context click to " + toString());
        new Actions(driver).moveToElement(getElement()).contextClick().perform();
    }

    public void moveAndClick() {
        Logger.writeLog("Move and click to " + toString());
        new Actions(driver).moveToElement(getElement()).click().perform();
    }

    public String getText() {
        return exists() ? getElement().getText() : "";
    }
}
