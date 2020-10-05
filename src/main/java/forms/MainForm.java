package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainForm extends Form {

    private final CookiesForm cookiesForm = new CookiesForm(By.className("cookies"), "Cookies");
    private final LoginForm loginForm = new LoginForm(By.className("login-form__container"), "Login");
    private final ProfileForm profileForm = new ProfileForm(By.className("avatar-and-interests__form"), "Profile");
    private final HelpForm helpForm = new HelpForm(By.className("help-form__container"), "Help");

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final ILabel timer = elementFactory.getLabel(By.xpath("//div[contains(@class, 'timer--center')]"), "Timer");
    private final ILabel pageIndicator = elementFactory.getLabel(By.xpath("//div[@class='page-indicator']"), "Page indicator");
    private final IButton closePopup = elementFactory.getButton(By.xpath("//span[@class='modal__close-copyright']/span"), "Close popup");

    public MainForm(By locator, String name) {
        super(locator, name);
    }

    public void clearFromPopups() {
        if (closePopup.state().isExist()) {
            closePopup.click();
        }
    }

    public void acceptCookies() {
        cookiesForm.acceptCookies();
    }

    public CookiesForm getCookiesForm() {
        return cookiesForm;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public ProfileForm getProfileForm() {
        return profileForm;
    }

    public HelpForm getHelpForm() {
        return helpForm;
    }

    public String getTimerValue() {
        return timer.getText();
    }

    public boolean atLoginPage() {
        return loginForm.state().isExist();
    }

    public boolean atProfilePage() {
        return profileForm.state().isExist();
    }

    public boolean atThirdPage() {
        return pageIndicator.getText().equals("3 / 4");
    }
}
