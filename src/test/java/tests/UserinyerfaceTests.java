package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import forms.MainForm;
import forms.StartForm;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserinyerfaceTests extends BaseTest {

    private final Browser browser = AqualityServices.getBrowser();

    private final StartForm startForm = new StartForm(By.xpath("/html"), "Start page");
    private final MainForm mainForm = new MainForm(By.xpath("/html"), "Main page");

    @BeforeMethod
    protected void goToPage() {
        browser.goTo("https://userinyerface.com/game.html%20target=");
        browser.waitForPageToLoad();
        assertTrue(startForm.atPage(), "Start page didn't open");
        startForm.clickHere();
        browser.waitForPageToLoad();
        assertTrue(mainForm.atLoginPage(), "Main page didn't open");
    }

    @Test
    public void testCase1() {
        mainForm.getLoginForm().setPasswordRandom();
        mainForm.getLoginForm().setEmailRandom();
        mainForm.getLoginForm().setDomainRandom();
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
        assertFalse(mainForm.getHelpForm().isHidden(), "Help form didn't hide");
    }

    @Test
    public void testCase3() {
        mainForm.acceptCookies();
        assertFalse(mainForm.getCookiesForm().state().isExist(), "Cookies form didn't close");
    }

    @Test
    public void testCase4() {
        String timerValue = mainForm.getTimerValue();
        assertEquals(timerValue, "00:00:00", "Timer didn't start from 00:00:00");
    }
}
