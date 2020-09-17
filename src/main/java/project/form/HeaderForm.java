package project.form;

import framework.element.ButtonElement;
import framework.form.BaseFrom;
import framework.utils.PropertyManager;
import org.openqa.selenium.By;

public class HeaderForm extends BaseFrom {

    private static final By INSTALL_STEAM_BUTTON = By.xpath("//*[@id='global_action_menu']/div[1]");
    private static final By BROWSE_MENU = By.xpath("//*[@id='genre_tab']/span");

    public void clickInstallSteam() {
        new ButtonElement(INSTALL_STEAM_BUTTON).clickOnLocation();
    }

    private By getActionLocator() {
        String actionName;
        switch (PropertyManager.getProperty("lang")) {
            case ("ru"):
                actionName = "Экшн";
                break;
            case ("en"):
                actionName = "Action";
                break;
            default:
                actionName = "";
        }
        return By.xpath("//*[@id='genre_flyout']//a[contains( text(),'" + actionName + "')]");
    }
    private By getIndieLocator() {
        String actionName;
        switch (PropertyManager.getProperty("lang")) {
            case ("ru"):
                actionName = "Инди";
                break;
            case ("en"):
                actionName = "Indie";
                break;
            default:
                actionName = "";
        }
        return By.xpath("//*[@id='genre_flyout']//a[contains( text(),'" + actionName + "')]");
    }

    public void clickOnAction() {
        new ButtonElement(BROWSE_MENU).moveTo();
        new ButtonElement(getActionLocator()).click();
    }

    public void clickOnIndie() {
        new ButtonElement(BROWSE_MENU).moveTo();
        new ButtonElement(getIndieLocator()).click();
    }
}
