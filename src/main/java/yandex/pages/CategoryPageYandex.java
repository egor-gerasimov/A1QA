package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPageYandex {

    private static final By CATEGORY_NAME = By.className("_39qdPorEKz");
    private final WebDriver driver;

    public CategoryPageYandex(WebDriver driver) {
        this.driver = driver;
    }

    public String getCategoryName() {
        return driver.findElement(CATEGORY_NAME).getText();
    }
}
