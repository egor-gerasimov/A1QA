package framework.element;

import org.openqa.selenium.By;

public class LabelElement extends BaseElement {

    public LabelElement(By locator) {
        super(locator);
    }

    public LabelElement(By locator, String name) {
        super(locator, name);
    }

    @Override
    public String getElementName() {
        return "Label";
    }
}
