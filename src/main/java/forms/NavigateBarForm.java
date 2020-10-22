package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NavigateBarForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final IButton btnProfile = elementFactory.getButton(By.id("l_pr"), "Profile");

    public NavigateBarForm() {
        super(By.xpath("//div[@id='side_bar_inner']//nav"), "Navigate bar");
    }

    public void goToProfile() {
        btnProfile.click();
    }
}
