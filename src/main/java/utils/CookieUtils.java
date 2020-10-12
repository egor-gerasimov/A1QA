package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CookieUtils {

    private static final WebDriver driver = AqualityServices.getBrowser().getDriver();
    private static final Logger logger = AqualityServices.getLogger();

    public static Set<Cookie> getCookies() {
        logger.info("Getting cookies");
        return driver.manage().getCookies();
    }

    public static void addCookie(Cookie cookie) {
        logger.info("Adding cookie " + cookie.getName() + ":" + cookie.getValue());
        driver.manage().addCookie(cookie);
    }

    public static void addCookies(Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            addCookie(cookie);
        }
    }

    public static void deleteCookieNamed(String name) {
        logger.info("Deleting cookie named " + name);
        driver.manage().deleteCookieNamed(name);
    }

    public static Cookie getCookieNamed(String name) {
        logger.info("Getting cookie named " + name);
        return driver.manage().getCookieNamed(name);
    }

    public static void deleteAllCookies() {
        logger.info("Deleting all cookies");
        driver.manage().deleteAllCookies();
    }

    public static boolean hasCookieNamed(String name) {
        for (Cookie cookie : getCookies()) {
            if (cookie.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
