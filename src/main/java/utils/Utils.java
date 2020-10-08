package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class Utils {

    public static ITextBox findTextElement(String text) {
        return AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//*[text()='" + text + "']"), "Text '" + text + "'");
    }
}
