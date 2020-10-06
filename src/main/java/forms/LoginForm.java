package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;

public class LoginForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final ITextBox txbPassword = elementFactory.getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "Password");
    private final ITextBox txbEmail = elementFactory.getTextBox(By.cssSelector("input[placeholder='Your email']"), "Email");
    private final ITextBox txbDomain = elementFactory.getTextBox(By.cssSelector("input[placeholder='Domain']"), "Domain");
    private final IComboBox cmbTld = elementFactory.getComboBox(By.xpath("//div[contains(@class,'dropdown--')]"), "TLD");
    private final ICheckBox ckbNotAccept = elementFactory.getCheckBox(By.className("checkbox"), "Not accept");
    private final IButton btnNext = elementFactory.getButton(By.xpath("//a[.='Next']"), "Next");

    private final By tldLocator = By.xpath("//div[@class='dropdown__list']//div[contains(text(), '.')]");

    public LoginForm() {
        super(By.className("login-form__container"), "Login");
    }

    public void setPassword(String text) {
        txbPassword.clearAndType(text);
    }

    public void setEmail(String text) {
        txbEmail.clearAndType(text);
    }

    public void setDomain(String text) {
        txbDomain.clearAndType(text);
    }

    public String getPassword() {
        return txbPassword.getText();
    }

    private List<IButton> getTldList() {
        return elementFactory.findElements(tldLocator, ElementType.BUTTON);
    }

    public void selectRandomTld() {
        cmbTld.click();
        List<IButton> tldList = getTldList();
        tldList.get(new Random().nextInt(tldList.size())).click();
    }

    public void acceptTermsAndConditions() {
        ckbNotAccept.click();
    }

    public void clickNext() {
        btnNext.click();
    }
}
