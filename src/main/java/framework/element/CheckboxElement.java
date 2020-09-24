package framework.element;

import org.openqa.selenium.By;

public class CheckboxElement extends BaseElement {

    public CheckboxElement(By locator) {
        super(locator);
    }

    public CheckboxElement(By locator, String name) {
        super(locator, name);
    }

    public boolean isSelected() {
        return getElement().isSelected();
    }

    @Override
    public String getElementName() {
        return "Checkbox";
    }
}
