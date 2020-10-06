package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class StartForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ILink linkHere = elementFactory.getLink(By.className("start__link"), "HERE");

    public StartForm(By locator, String name) {
        super(locator, name);
    }

    public void clickHere() {
        linkHere.click();
    }

    public boolean atPage() {
        return linkHere.state().isExist();
    }
}
