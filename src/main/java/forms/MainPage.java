package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    private static final String btnLocator = "//button[@onclick='%s()']";
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final IButton btnAlert = getJsButton("jsAlert", "Alert");
    private final IButton btnConfirm = getJsButton("jsConfirm", "Confirm");
    private final IButton btnPrompt = getJsButton("jsPrompt", "Prompt");
    private final ILabel lblResult = elementFactory.getLabel(By.id("result"), "Result");

    public MainPage() {
        super(By.id("content"), "Main");
    }

    public void clickAlert() {
        btnAlert.click();
    }

    public void clickConfirm() {
        btnConfirm.click();
    }

    public void clickPrompt() {
        btnPrompt.click();
    }

    public String getResult() {
        return lblResult.getText();
    }

    private IButton getJsButton(String locatorName, String name) {
        return elementFactory.getButton(By.xpath(String.format(btnLocator, locatorName)), name);
    }
}
