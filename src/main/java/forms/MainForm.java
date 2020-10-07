package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final IButton btnAlert = elementFactory.getButton(By.xpath("//button[@onclick='jsAlert()']"), "Alert");
    private final IButton btnConfirm = elementFactory.getButton(By.xpath("//button[@onclick='jsConfirm()']"), "Confirm");
    private final IButton btnPrompt = elementFactory.getButton(By.xpath("//button[@onclick='jsPrompt()']"), "Prompt");
    private final ILabel lblResult = elementFactory.getLabel(By.id("result"), "Result");

    public MainForm() {
        super(By.xpath("/html"), "Main");
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
}
