import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import java.util.Collections;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CookieUtils;
import utils.TestData;

public class CookiesTest extends BaseTest {

    private final Browser browser = AqualityServices.getBrowser();
    private final String url = AqualityServices.get(ISettingsFile.class).getValue("/url").toString();

    @BeforeMethod
    public void goToUrl() {
        browser.goTo(url);
        browser.waitForPageToLoad();
    }

    @Test
    public void cookiesTest() {
        Set<Cookie> cookies = TestData.getCookies();
        CookieUtils.addCookies(cookies);
        Assert.assertEquals(cookies, CookieUtils.getCookies(), "Wrong cookies");
        String cookieToDeleteName = TestData.getValue("cookie.name.to.delete");
        CookieUtils.deleteCookieNamed(cookieToDeleteName);
        for (Cookie cookie : CookieUtils.getCookies()) {
            Assert.assertNotEquals(cookie.getName(), cookieToDeleteName, "Cookie didn't delete");
        }
        Cookie newCookie = TestData.getNewCookie();
        CookieUtils.addCookie(newCookie);
        Assert.assertEquals(CookieUtils.getCookieNamed(newCookie.getName()), newCookie, "Cookie value didn't change");
        CookieUtils.deleteAllCookies();
        Assert.assertEquals(CookieUtils.getCookies(), Collections.EMPTY_SET, "Cookies didn't delete");
    }
}
