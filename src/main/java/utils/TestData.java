package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;
import models.User;

public class TestData {

    private static final Logger logger = AqualityServices.getLogger();
    private static final JsonSettingsFile file = new JsonSettingsFile("test.data.json");

    public static String getStringValue(String value) {
        logger.info("Read value '" + value + "' from test data");
        return file.getValue("/" + value).toString();
    }

    public static long getLongValue(String value) {
        logger.info("Read value '" + value + "' from test data");
        return (int) file.getValue("/" + value);
    }

    public static User getUserStep5() {
        return Utils.readObjectFromJSON(getStringValue("user.step.5"), User.class);
    }
}
