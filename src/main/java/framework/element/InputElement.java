package framework.element;

import framework.utils.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class InputElement extends BaseElement {

    public InputElement(By locator) {
        super(locator);
    }

    public InputElement(By locator, String name) {
        super(locator, name);
    }

    public String getValue() {
        return getElement().getAttribute("value");
    }

    public void setValue(String value) {
        clear();
        click();
        Logger.writeLog("Set value " + value);
        getElement().sendKeys(value);
    }

    public void setFirstDropdown() {
        getElement().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    private void clear() {
        if (getElement() != null) {
            Logger.writeLog("Clear " + toString());
            getElement().clear();
        }
    }

    @Override
    public String getElementName() {
        return "Input field";
    }
}
