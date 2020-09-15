package yandex.pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import yandex.utils.Waiter;
import yandex.driver.WebDriverSingleton;

public class HomePageYandex {

    private static final By POPULAR_CATEGORIES = By.xpath("//div[1]/div[@data-zone-name='category-link']/div/a/span");
    private static final By BUTTON_LOGIN = By.xpath("//span[.='Войти']");
    private static final By BUTTON_OPEN_LOGOUT = By.className("_3jM6DeytKy");
    private static final By BUTTON_LOGOUT = By.xpath("//*[.='Выйти']");
    private static final By BUTTON_PROFILE = By.xpath("//button[@data-tid-prop='1badb8e1']");
    private static final By BUTTON_CATALOG_1 = By.xpath("//button[@data-tid='e1c9fd84 5a689c45']");
    private static final By BUTTON_CATALOG_2 = By.xpath("//button[@data-tid='1eaa5af0 4d1c1150 fb59a24e f2d7f3b0']");
    private static final By ALL_CATEGORIES = By
        .xpath("//*[@id=\"27903767\"]//div[1]/div[@data-zone-name='category-link']/button/a/span");
    private static final By POPUP = By.className("_2y9LPPqeQi");
    public static final String PAGE_TITLE = "Яндекс.Маркет";

    private final WebDriver driver = WebDriverSingleton.getInstance();

    public void clickLogin() {
        driver.findElement(BUTTON_LOGIN).click();
    }

    public void clickLogout() {
        driver.findElement(BUTTON_PROFILE).click();
        if (!driver.findElements(BUTTON_OPEN_LOGOUT).isEmpty()) {
            driver.findElement(BUTTON_OPEN_LOGOUT).click();
        }
        driver.findElement(BUTTON_LOGOUT).click();
    }

    public List<String> getPopularCategories() {
        return driver.findElements(POPULAR_CATEGORIES).parallelStream()
            .map(WebElement::getText).filter(o -> !o.equals("")).collect(Collectors.toList());
    }

    public void clickCatalog() {
        WebElement buttonCatalog = Waiter.implicitWait(BUTTON_CATALOG_1, BUTTON_CATALOG_2);
        buttonCatalog.click();
    }

    public List<String> getAllCategories() {
        return driver.findElements(ALL_CATEGORIES).parallelStream().map(WebElement::getText).filter(o -> !o.equals(""))
            .collect(Collectors.toList());
    }

    public boolean isLoggedIn() {
        Waiter.explicitWaitLocated(BUTTON_PROFILE);
        return true;
    }

    public boolean isLoggedOut() {
        Waiter.explicitWaitLocated(BUTTON_LOGIN);
        return true;
    }

    public void clearFromPopups() {
        if (!driver.findElements(POPUP).isEmpty()) {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath("/html")), 1, 1).click().build().perform();
        }
    }

    public boolean isAtPage() {
        return driver.getTitle().contains(PAGE_TITLE);
    }
}