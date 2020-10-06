import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import form.AuthorizationForm;
import org.testng.annotations.Test;
import utils.Constants;
import utils.SettingsUtils;

public class BasicAuthorizationTest extends BaseTest {

    private final Browser browser = AqualityServices.getBrowser();
    private final String url = SettingsUtils.getAuthorizationUrl();

    private static final AuthorizationForm authForm = new AuthorizationForm();

    @Test
    public void goToUrl() {
        browser.goTo(url);
        browser.waitForPageToLoad();
        assertEquals(authForm.getResultText(), Constants.getExpectedResult(), "Wrong page result");
    }
}
