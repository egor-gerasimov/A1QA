package project.form;

import framework.driver.Driver;
import framework.element.ButtonElement;
import framework.form.BaseFrom;
import org.openqa.selenium.By;

public class AboutForm extends BaseFrom {

//    private static final By INSTALL_STEAM_BUTTON = By.xpath("//*[@id='about_greeting']/div[3]/div[1]");
    private static final By INSTALL_STEAM_BUTTON = By.className("about_install_steam_link");

    public boolean atPage() {
        return Driver.getInstance().getCurrentUrl().contains("https://store.steampowered.com/about/");
    }

    public void installSteamNow() {
        ButtonElement buttonElement = new ButtonElement(INSTALL_STEAM_BUTTON);
        buttonElement.click();
    }
}
