package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utils.Utils;

public class IFrameForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ITextBox txbText = elementFactory.getTextBox(By.id("tinymce"), "Text box");

    protected IFrameForm(By locator, String name) {
        super(locator, name);
    }

    public void clearAntTypeText(String text) {
        txbText.clearAndType(text);
    }

    public String getText() {
        return txbText.getText();
    }

    public void selectAllText() {
        txbText.sendKeys(Keys.CONTROL + "a");
    }

    public boolean isBoldText(String text) {
        return Utils.findTextElement(text).getElement().getTagName().equals("strong");
    }
}
