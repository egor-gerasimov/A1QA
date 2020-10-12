package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.hc.core5.net.URIBuilder;

public class SettingsUtils {

    public static String getAuthorizationUrl() {
        String url = AqualityServices.get(ISettingsFile.class).getValue("/url").toString();
        return getAuthorizationUrl(url);
    }

    private static String getAuthorizationUrl(String url) {
        return getAuthorizationUrl(url, TestData.getUsername(), TestData.getPassword());
    }

    public static String getAuthorizationUrl(String url, String username, String password) {
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            List<String> pathSegments = uriBuilder.getPathSegments();
            pathSegments.remove("");
            pathSegments.add(username);
            pathSegments.add(password);
            uriBuilder.setUserInfo(username, password).setPathSegments(pathSegments);
            url = uriBuilder.toString();
        } catch (URISyntaxException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return url;
    }

    public static String getExpectedResult(String username) {
        return String.format(Constants.getExpectedResultFormat(), username);
    }
}
