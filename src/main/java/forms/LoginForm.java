package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ITextBox txbEmail = elementFactory.getTextBox(By.xpath("//input[@name='email']"), "Email");
    private final ITextBox txbPassword = elementFactory.getTextBox(By.xpath("//input[@name='pass']"), "Password");
    private final IButton btnLogin = elementFactory.getButton(By.xpath("//button[@id='index_login_button']"), "Login");


    public LoginForm() {
        super(By.id("index_login"), "Welcome page");
    }

    public void login(String email, String password) {
        txbEmail.clearAndType(email);
        txbPassword.clearAndType(password);
        btnLogin.click();
    }
}
