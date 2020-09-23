package project.form;

import framework.driver.Driver;
import framework.element.ButtonElement;
import framework.element.ComboBoxElement;
import framework.utils.Logger;
import org.openqa.selenium.By;

public class AgeCheckForm {

    private static final By YEAR_BOX = By.xpath("//*[@id='ageYear']");
    private static final By OPEN_BUTTON = By.xpath("//a[@onclick='ViewProductPage()']");
    private final ComboBoxElement birthYearBox = new ComboBoxElement(YEAR_BOX, "Year");
    private final ButtonElement openGameButton = new ButtonElement(OPEN_BUTTON, "Open Game");

    public boolean atPage() {
        return Driver.getInstance().getCurrentUrl().contains("agecheck");
    }

    public void setYear(String year) {
        birthYearBox.selectValue(year);
    }

    public void openPage() {
        openGameButton.click();
    }

    public void openGame() {
        Logger.writeLog("Set 1970 year");
        if (atPage()) {
            setYear("1970");
            openPage();
        }
    }
}
