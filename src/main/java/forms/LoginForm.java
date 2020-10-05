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
import utils.StringUtils;

public class LoginForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final ITextBox password = elementFactory.getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "Password");
    private final ITextBox email = elementFactory.getTextBox(By.cssSelector("input[placeholder='Your email']"), "Email");
    private final ITextBox domain = elementFactory.getTextBox(By.cssSelector("input[placeholder='Domain']"), "Domain");
    private final IComboBox tld = elementFactory.getComboBox(By.xpath("//div[contains(@class,'dropdown--')]"), "TLD");
    private final ICheckBox notAccept = elementFactory.getCheckBox(By.className("checkbox"), "Not accept");
    private final IButton next = elementFactory.getButton(By.xpath("//a[.='Next']"), "Next");

    private final By tldLocator = By.xpath("//div[@class='dropdown__list']/div[contains(text(), '.')]");

    public LoginForm(By locator, String name) {
        super(locator, name);
    }

    public void setPassword(String text) {
        password.clearAndType(text);
    }

    public void setPasswordRandom() {
        setPassword(StringUtils.getRandomString());
    }

    public void setEmail(String text) {
        email.clearAndType(text);
    }

    public void setEmailRandom() {
        String firstLetter = String.valueOf(password.getValue().charAt(0));
        setEmail(firstLetter + StringUtils.getRandomString().toLowerCase());
    }

    public void setDomain(String text) {
        domain.clearAndType(text);
    }

    public void setDomainRandom() {
        setDomain(StringUtils.getRandomString().toLowerCase());
    }

    private List<IButton> getTldList() {
        return elementFactory.findElements(tldLocator, ElementType.BUTTON);
    }

    public void selectRandomTld() {
        tld.click();
        List<IButton> tldList = getTldList();
        tldList.get(new Random().nextInt(tldList.size())).click();
    }

    public void acceptTermsAndConditions() {
        notAccept.click();
    }

    public void clickNext() {
        next.click();
    }
}
