package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsUtils {

    private static final String httpRegex = "http(s)?://";

    public static String getAuthorizationUrl() {
        String url = AqualityServices.get(ISettingsFile.class).getValue("/url").toString();
        Matcher m = Pattern.compile(httpRegex).matcher(url);
        if (m.find()) {
            url = m.replaceFirst(m.group() + TestData.getUsername() + ":" + TestData.getPassword() + "@");
        }
        return url + TestData.getUsername() + "/" + TestData.getPassword();
    }
}
