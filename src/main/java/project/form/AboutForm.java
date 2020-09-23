package project.form;

import framework.element.ButtonElement;
import framework.form.BaseFrom;
import org.openqa.selenium.By;

public class AboutForm extends BaseFrom {

    private static final By INSTALL_STEAM_BUTTON = By.className("about_install_steam_link");
    private final ButtonElement installSteamButton = new ButtonElement(INSTALL_STEAM_BUTTON, "Install Steam");

    public boolean atPage() {
        return installSteamButton.exists();
    }

    public void installSteamNow() {
        installSteamButton.click();
    }
}
