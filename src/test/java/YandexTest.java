import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import yandex.Helper;
import yandex.driver.BrowserFactory;
import yandex.driver.PropertyManager;
import yandex.pages.CategoryPageYandex;
import yandex.pages.HomePageYandex;
import yandex.pages.LoginPageYandex;

public class YandexTest {

    WebDriver driver;
    HomePageYandex homePage;
    LoginPageYandex loginPage;
    CategoryPageYandex categoryPage;
    List<String> popularCategories;
    List<String> categories;
    PropertyManager propertyManager;

    @BeforeClass
    public void setUp() {
        propertyManager = PropertyManager.getInstance();
        driver = BrowserFactory.getBrowser(propertyManager.getBrowserName());
        homePage = new HomePageYandex(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(propertyManager.getUrl());
    }

    @Test
    public void openHomePage() {
        assertTrue(homePage.isAtPage(), "Wrong page");
    }

    @Test(dependsOnMethods = {"openHomePage"})
    public void clickLogin() {
        homePage.clickLogin();
        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(handles.size() - 1));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Авторизация"));
        assertEquals(driver.getTitle(), "Авторизация", "Wrong page title");
    }

    @Test(dependsOnMethods = {"clickLogin"})
    public void login() {
        loginPage = new LoginPageYandex(driver);
        loginPage.login();
        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(0));
        assertTrue(driver.getTitle().contains("Яндекс.Маркет"), "Wrong page title");
        assertTrue(homePage.isLoggedIn(), "Didn't log in");
    }

    @Test(dependsOnMethods = {"login"})
    public void openPopularCategory() {
        popularCategories = homePage.getPopularCategories();
        String randomCategoryName = popularCategories.get(new Random().nextInt(popularCategories.size()));
        homePage.clearFromPopups();
        driver.findElement(By.linkText(randomCategoryName)).click();
        categoryPage = new CategoryPageYandex(driver);
        assertEquals(categoryPage.getCategoryName(), randomCategoryName, "Wrong category");
    }

    @Test(dependsOnMethods = {"login"}, priority = 2)
    public void getAllCategories() {
        driver.get(propertyManager.getUrl());
        assertTrue(homePage.isAtPage(), "Didn't open home page");
        homePage.clickCatalog();
        categories = homePage.getAllCategories();
        Helper.writeCategoriesToFile(categories);
        assertTrue(Helper.categoriesFileIsExists(), "Category file wasn't created");
    }

    @Test(dependsOnMethods = {"getAllCategories"})
    public void compareCategoryLists() {
        for (String popularCategory : popularCategories) {
            assertTrue(categories.contains(popularCategory),
                "List of categories doesn't contain popular category (" + popularCategory + ")");
        }
    }

    @Test(dependsOnMethods = {"login"}, priority = 3)
    public void logout() {
        homePage.clickLogout();
        assertTrue(homePage.isLoggedOut(), "Didn't log out");
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}
