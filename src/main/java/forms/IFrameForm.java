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
    private final String id;

    protected IFrameForm(By locator, String name, String id) {
        super(locator, name);
        this.id = id;
    }

    private void switchToIFrame() {
        AqualityServices.getBrowser().getDriver().switchTo().frame(id);
    }

    private void switchToDefault() {
        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
    }

    public void clearAndTypeText(String text) {
        switchToIFrame();
        txbText.clearAndType(text);
        switchToDefault();
    }

    public String getText() {
        switchToIFrame();
        String text = txbText.getText();
        switchToDefault();
        return text;
    }

    public void selectAllText() {
        switchToIFrame();
        txbText.sendKeys(Keys.CONTROL + "a");
        switchToDefault();
    }

    public boolean isBoldText(String text) {
        switchToIFrame();
        boolean isBold = Utils.findTextElement(text).getElement().getTagName().equals("strong");
        switchToDefault();
        return isBold;
    }
}
