import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import yandex.utils.Helper;
import yandex.utils.PropertyManager;
import yandex.utils.Waiter;
import yandex.driver.WebDriverSingleton;
import yandex.pages.CategoryPageYandex;
import yandex.pages.HomePageYandex;
import yandex.pages.LoginPageYandex;

public class YandexTest {

    WebDriver driver;
    CategoryPageYandex categoryPage;
    List<String> popularCategories;
    List<String> categories;

    @BeforeClass
    public void setUp() {
        PropertyManager.readProperties();
        driver = WebDriverSingleton.getInstance();
        WebDriverSingleton.maximizeWindow();
        Waiter.setImplicitlyWait();
    }

    @Test
    public void TestCaseYandexMarket() {
        HomePageYandex homePage = new HomePageYandex();
        driver.get(PropertyManager.getProperty("url"));
        assertTrue(homePage.isAtPage(), "Wrong page");
        homePage.clickLogin();
        WebDriverSingleton.switchToNextHandle();
        Waiter.explicitWaitTitle("Авторизация");
        assertEquals(driver.getTitle(), "Авторизация", "Wrong page title");
        LoginPageYandex loginPage = new LoginPageYandex();
        loginPage.login();
        WebDriverSingleton.switchToFirstHandle();
        assertTrue(homePage.isAtPage(), "Wrong page title");
        assertTrue(homePage.isLoggedIn(), "Didn't log in");
        popularCategories = homePage.getPopularCategories();
        String randomCategoryName = popularCategories.get(new Random().nextInt(popularCategories.size()));
        homePage.clearFromPopups();
        driver.findElement(By.linkText(randomCategoryName)).click();
        categoryPage = new CategoryPageYandex();
        assertEquals(categoryPage.getCategoryName(), randomCategoryName, "Wrong category");
        driver.get(PropertyManager.getProperty("url"));
        assertTrue(homePage.isAtPage(), "Didn't open home page");
        homePage.clickCatalog();
        categories = homePage.getAllCategories();
        Helper.writeCategoriesToFile(categories);
        assertTrue(Helper.categoriesFileIsExists(), "Category file wasn't created");
        for (String popularCategory : popularCategories) {
            assertTrue(categories.contains(popularCategory),
                "List of categories doesn't contain popular category (" + popularCategory + ")");
        }
        homePage.clickLogout();
        assertTrue(homePage.isLoggedOut(), "Didn't log out");
    }

    @AfterClass
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
