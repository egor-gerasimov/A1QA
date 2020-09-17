package framework.element;

import org.openqa.selenium.By;

public class ComboBoxElement extends BaseElement {

    public ComboBoxElement(By locator) {
        super(locator);
    }

    public void selectValue(String value) {
        getElement().sendKeys(value);
    }
}
