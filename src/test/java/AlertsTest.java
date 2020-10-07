import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import forms.MainForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertsTest extends BaseTest {

    private final Browser browser = AqualityServices.getBrowser();
    private final String url = AqualityServices.get(ISettingsFile.class).getValue("/url").toString();
    private final MainForm mainForm = new MainForm();

    @BeforeMethod
    public void goToUrl() {
        browser.goTo(url);
        browser.waitForPageToLoad();
    }

    @Test
    public void alertsTest() {
        mainForm.clickAlert();
        Alert alert = AqualityServices.getBrowser().getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert", "Wrong alert text");
        AqualityServices.getBrowser().handleAlert(AlertActions.ACCEPT);
        Assert.assertEquals(mainForm.getResult(), "You successfuly clicked an alert", "Wrong result text");
        mainForm.clickConfirm();
        alert = AqualityServices.getBrowser().getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Confirm", "Wrong alert text");
        alert.accept();
        Assert.assertEquals(mainForm.getResult(), "You clicked: Ok", "Wrong result text");
        mainForm.clickPrompt();
        alert = AqualityServices.getBrowser().getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS prompt", "Wrong alert text");
        String randomString = RandomStringUtils.random(10, true, true);
        alert.sendKeys(randomString);
        alert.accept();
        Assert.assertEquals(mainForm.getResult(), "You entered: " + randomString, "Wrong result text");
    }
}
