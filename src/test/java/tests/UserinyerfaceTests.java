package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import forms.MainForm;
import forms.StartForm;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Constants;
import utils.StringUtils;

public class UserinyerfaceTests extends BaseTest {

    private final Browser browser = AqualityServices.getBrowser();

    private final StartForm startForm = new StartForm();
    private final MainForm mainForm = new MainForm();
    private final String url = AqualityServices.get(ISettingsFile.class).getValue("/url").toString();

    @BeforeMethod
    protected void goToPage() {
        browser.goTo(url);
        browser.waitForPageToLoad();
        assertTrue(startForm.atPage(), "Start page didn't open");
        startForm.clickHere();
        browser.waitForPageToLoad();
        assertTrue(mainForm.atLoginPage(), "Main page didn't open");
    }

    @Test
    public void testCase1() {
        mainForm.getLoginForm().setPassword(StringUtils.getRandomString());
        String firstLetter = String.valueOf(mainForm.getLoginForm().getPassword().charAt(0));
        mainForm.getLoginForm().setEmail(firstLetter + StringUtils.getRandomString());
        mainForm.getLoginForm().setDomain(StringUtils.getRandomString());
        mainForm.getLoginForm().selectRandomTld();
        mainForm.getLoginForm().acceptTermsAndConditions();
        mainForm.getLoginForm().clickNext();
        assertTrue(mainForm.atProfilePage(), "Profile page didn't open");
        mainForm.getProfileForm().checkThreeInterests();
        mainForm.getProfileForm().uploadImage();
        mainForm.getProfileForm().clickNext();
        assertTrue(mainForm.atThirdPage(), "Third page didn't open");
    }

    @Test
    public void testCase2() {
        mainForm.getHelpForm().hide();
        assertTrue(mainForm.getHelpForm().isHidden(), "Help form didn't hide");
    }

    @Test
    public void testCase3() {
        mainForm.getCookiesForm().acceptCookies();
        assertFalse(mainForm.getCookiesForm().state().isExist(), "Cookies form didn't close");
    }

    @Test
    public void testCase4() {
        String timerValue = mainForm.getTimerValue();
        assertEquals(timerValue, Constants.getTimerZero(), "Timer didn't start from 00:00:00");
    }
}
