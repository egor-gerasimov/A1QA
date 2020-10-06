package framework.element;

import framework.utils.Logger;
import org.openqa.selenium.By;

public class ComboBoxElement extends BaseElement {

    public ComboBoxElement(By locator) {
        super(locator);
    }

    public ComboBoxElement(By locator, String name) {
        super(locator, name);
    }

    public void selectValue(String value) {
        Logger.writeLog("Select " + value + " on " + toString());
        getElement().sendKeys(value);
    }

    @Override
    public String getElementName() {
        return "ComboBox";
    }
}
