package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginForm extends Form {

    private final ITextBox txbEmail = getElementFactory().getTextBox(By.xpath("//input[@name='email']"), "Email");
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//input[@name='pass']"), "Password");
    private final IButton btnLogin = getElementFactory().getButton(By.xpath("//button[@id='index_login_button']"), "Login");


    public LoginForm() {
        super(By.id("index_login"), "Welcome page");
    }

    public void login(String email, String password) {
        typeEmail(email);
        typePassword(password);
        btnLogin.click();
    }

    public void typeEmail(String email) {
        txbEmail.clearAndType(email);
    }

    public void typePassword(String password) {
        txbPassword.clearAndType(password);
    }
}
