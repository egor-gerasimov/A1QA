import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import forms.MainPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Constants;

public class AlertsTest extends BaseTest {

    private final Browser browser = AqualityServices.getBrowser();
    private final String url = AqualityServices.get(ISettingsFile.class).getValue("/url").toString();
    private final MainPage mainPage = new MainPage();

    @BeforeMethod
    public void goToUrl() {
        browser.goTo(url);
        browser.waitForPageToLoad();
    }

    @Test
    public void alertsTest() {
        mainPage.clickAlert();
        Alert alert = AqualityServices.getBrowser().getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), Constants.getExpectedSimpleAlertText(), "Wrong alert text");
        AqualityServices.getBrowser().handleAlert(AlertActions.ACCEPT);
        Assert.assertEquals(mainPage.getResult(), Constants.getExpectedSimpleAlertResultText(), "Wrong result text");
        mainPage.clickConfirm();
        alert = AqualityServices.getBrowser().getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), Constants.getExpectedConfirmAlertText(), "Wrong alert text");
        alert.accept();
        Assert.assertEquals(mainPage.getResult(), Constants.getExpectedConfirmAlertResultText(), "Wrong result text");
        mainPage.clickPrompt();
        alert = AqualityServices.getBrowser().getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), Constants.getExpectedPromptAlertText(), "Wrong alert text");
        String randomString = RandomStringUtils.random(10, true, true);
        alert.sendKeys(randomString);
        alert.accept();
        Assert.assertEquals(mainPage.getResult(), Constants.getExpectedPromptAlertResultText(randomString), "Wrong result text");
    }
}
