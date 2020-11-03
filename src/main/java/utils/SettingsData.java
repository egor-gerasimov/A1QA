package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;

public class SettingsData {

    private static final Logger logger = AqualityServices.getLogger();

    public static String getStringValue(String value) {
        logger.info("Read value '" + value + "' from settings");
        return AqualityServices.get(ISettingsFile.class).getValue("/" + value).toString();
    }
}
