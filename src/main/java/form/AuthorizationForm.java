package form;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthorizationForm extends Form {

    private final ILabel lblBody = AqualityServices.getElementFactory().getLabel(By.xpath("//body"), "Body text");

    public AuthorizationForm() {
        super(By.xpath("/html"), "Authorization");
    }

    public String getResultText() {
        return lblBody.getText();
    }
}
