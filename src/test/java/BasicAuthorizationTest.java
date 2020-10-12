import static org.testng.Assert.assertEquals;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import form.AuthorizationForm;
import org.testng.annotations.Test;
import utils.SettingsUtils;
import utils.TestData;

public class BasicAuthorizationTest extends BaseTest {

    private static final AuthorizationForm authForm = new AuthorizationForm();
    private final Browser browser = AqualityServices.getBrowser();
    private final String url = AqualityServices.get(ISettingsFile.class).getValue("/url").toString();

    @Test
    public void goToUrl() {
        browser.goTo(SettingsUtils.getAuthorizationUrl(url, TestData.getUsername(), TestData.getPassword()));
        browser.waitForPageToLoad();
        assertEquals(authForm.getResultText(), SettingsUtils.getExpectedResult(TestData.getUsername()),"Wrong page result");
    }
}
