package framework.element;

import org.openqa.selenium.By;

public class ButtonElement extends BaseElement {

    public ButtonElement(By locator) {
        super(locator);
    }

    public ButtonElement(By locator, String name) {
        super(locator, name);
    }

    @Override
    public String getElementName() {
        return "Button";
    }
}
