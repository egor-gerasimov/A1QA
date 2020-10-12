import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import forms.MainForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Constants;

public class IFrameTest extends BaseTest {

    private final Browser browser = AqualityServices.getBrowser();
    private final String url = AqualityServices.get(ISettingsFile.class).getValue("/url").toString();
    private final MainForm mainForm = new MainForm();

    @BeforeMethod
    public void goToUrl() {
        browser.goTo(url);
        browser.waitForPageToLoad();
    }

    @Test
    public void iFrameTest() {
        Assert.assertEquals(mainForm.getTitle(), Constants.TITLE, "Wrong title");
        String randomString = RandomStringUtils.random(10, true, true);
        mainForm.getIFrameForm().clearAndTypeText(randomString);
        Assert.assertEquals(mainForm.getIFrameForm().getText(), randomString, "Wrong text in iFrame");
        mainForm.getIFrameForm().selectAllText();
        mainForm.clickBold();
        Assert.assertTrue(mainForm.getIFrameForm().isBoldText(randomString), "Not bold text");
    }

}
