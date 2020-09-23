package project.form;

import framework.element.ButtonElement;
import framework.form.BaseFrom;
import framework.utils.Dictionary;
import framework.utils.Logger;
import org.openqa.selenium.By;

public class HeaderForm extends BaseFrom {

    private static final By INSTALL_STEAM_BUTTON = By.xpath("//*[@id='global_action_menu']/div[1]");
    private static final By BROWSE_MENU = By.xpath("//*[@id='genre_tab']/span");
    private final ButtonElement installSteamButton = new ButtonElement(INSTALL_STEAM_BUTTON, "Install Steam");
    private final ButtonElement browseMenuTab = new ButtonElement(BROWSE_MENU, "Browse");

    public void clickInstallSteam() {
        installSteamButton.clickOnLocation();
    }

    public void clickOnGenre(String genre) {
        browseMenuTab.moveTo();
        ButtonElement genreButton = new ButtonElement(getGenreLocator(genre));
        genreButton.click();
    }

    private By getGenreLocator(String genre) {
        return By.xpath("//*[@id='genre_flyout']//a[contains( text(),'" + Dictionary.getWord(genre) + "')]");
    }
}
