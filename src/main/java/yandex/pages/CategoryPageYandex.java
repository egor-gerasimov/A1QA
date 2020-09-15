package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yandex.driver.WebDriverSingleton;

public class CategoryPageYandex {

    private static final By CATEGORY_NAME = By.xpath("//div[@data-tid='5ab9471d']");
    private final WebDriver driver = WebDriverSingleton.getInstance();

    public String getCategoryName() {
        return driver.findElement(CATEGORY_NAME).getText();
    }
}
