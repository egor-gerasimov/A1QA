package project.form;

import framework.driver.Driver;
import framework.element.ButtonElement;
import framework.form.BaseFrom;
import framework.utils.PropertyManager;
import org.openqa.selenium.By;

public class StoreForm extends BaseFrom {

    private static final By LANGUAGE_BUTTON = By.xpath("//*[@id='language_pulldown']");
    private static final By CHOSEN_LANGUAGE = By.partialLinkText(PropertyManager.getProperty("lang"));


    public void changeLanguage() {
        new ButtonElement(LANGUAGE_BUTTON).click();
        new ButtonElement(CHOSEN_LANGUAGE).click();
    }

    public boolean atPage() {
        return Driver.getInstance().getCurrentUrl().contains("https://store.steampowered.com/");
    }
}
