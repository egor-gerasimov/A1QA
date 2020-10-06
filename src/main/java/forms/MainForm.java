package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainForm extends Form {

    private final CookiesForm cookiesForm = new CookiesForm();
    private final LoginForm loginForm = new LoginForm();
    private final ProfileForm profileForm = new ProfileForm();
    private final HelpForm helpForm = new HelpForm();

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final ILabel lblTimer = elementFactory.getLabel(By.xpath("//div[contains(@class, 'timer--center')]"), "Timer");
    private final ILabel lblPageNumber = elementFactory.getLabel(By.xpath("//div[@class='page-indicator']"), "Page indicator");
    private final IButton btnClosePopup = elementFactory.getButton(By.xpath("//span[@class='modal__close-copyright']/span"), "Close popup");

    public MainForm() {
        super(By.xpath("/html"), "Main page");
    }

    public void clearFromPopups() {
        if (btnClosePopup.state().isExist()) {
            btnClosePopup.click();
        }
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
        return lblTimer.getText();
    }

    public boolean atLoginPage() {
        return loginForm.state().isExist();
    }

    public boolean atProfilePage() {
        return profileForm.state().isExist();
    }

    public boolean atThirdPage() {
        return lblPageNumber.getText().charAt(0) == '3';
    }
}
