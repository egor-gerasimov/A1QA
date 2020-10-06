package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class StartForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ILink lnkHere = elementFactory.getLink(By.className("start__link"), "HERE");

    public StartForm() {
        super(By.xpath("/html"), "Start page");
    }

    public void clickHere() {
        lnkHere.click();
    }

    public boolean atPage() {
        return lnkHere.state().isExist();
    }
}
