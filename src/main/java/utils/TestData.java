package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.openqa.selenium.Cookie;

public class TestData {

    private static final Logger logger = AqualityServices.getLogger();
    private static final JsonSettingsFile file = new JsonSettingsFile("test.data.json");

    public static String getValue(String value) {
        logger.info("Read value '" + value + "' from test data");
        return file.getValue("/" + value).toString();
    }

    public static Set<Cookie> getCookies() {
        logger.info("Read cookies from test data");
        Set<Cookie> cookies = new HashSet<>();
        Map<String, Object> cookiesData = file.getMap("/cookies");
        for (Entry<String, Object> cookieData : cookiesData.entrySet()) {
            cookies.add(new Cookie(cookieData.getKey(), cookieData.getValue().toString()));
        }
        return cookies;
    }

    public static Cookie getNewCookie() {
        logger.info("Read new cookie from test data");
        Map<String, Object> newCookies = file.getMap("/new.cookie");
        List<Cookie> cookies = new ArrayList<>();
        for (Entry<String, Object> newCookie : newCookies.entrySet()) {
            cookies.add(new Cookie(newCookie.getKey(), newCookie.getValue().toString()));
        }
        return cookies.get(0);
    }
}