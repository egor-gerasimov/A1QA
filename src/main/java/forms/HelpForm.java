package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final IButton sendToBottom = elementFactory
        .getButton(By.xpath("//button[contains(@class,'help-form__send-to-bottom-button')]"), "Send to bottom");
    private final ILabel title = elementFactory.getLabel(By.xpath("//h2[@class='help-form__title']"), "Title");

    public HelpForm(By locator, String name) {
        super(locator, name);
    }

    public void hide() {
        sendToBottom.click();
        title.state().waitForNotDisplayed();
    }

    public boolean isHidden() {
        return title.state().isDisplayed();
    }
}
