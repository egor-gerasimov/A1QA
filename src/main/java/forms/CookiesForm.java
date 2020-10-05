package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookiesForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final IButton accept = elementFactory.getButton(
        By.xpath("//button[contains(@class,'button button--solid button--transparent')]"), "Terms & Conditions");

    protected CookiesForm(By locator, String name) {
        super(locator, name);
    }

    public void acceptCookies() {
        accept.click();
    }
}
