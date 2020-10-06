package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    protected void beforeClass() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
    }

    @AfterClass
    protected void afterClass() {
        AqualityServices.getBrowser().quit();
    }

}
