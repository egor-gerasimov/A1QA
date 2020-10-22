package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {

    LoginForm loginForm = new LoginForm();

    public WelcomePage() {
        super(By.xpath("//div[id='content']"), "Welcome page");
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }
}
