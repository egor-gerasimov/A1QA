package project.form;

import framework.driver.Driver;
import framework.element.ButtonElement;
import framework.element.ComboBoxElement;
import org.openqa.selenium.By;

public class AgeCheckForm {

    private static final By YEAR_BOX = By.xpath("//*[@id='ageYear']");
    private static final By OPEN_BUTTON = By.xpath("//a[@onclick='ViewProductPage()']");

    public boolean atPage() {
        return Driver.getInstance().getCurrentUrl().contains("agecheck");
    }

    public void setYear(String year) {
        new ComboBoxElement(YEAR_BOX).selectValue(year);
    }

    public void openPage() {
        new ButtonElement(OPEN_BUTTON).click();
    }
}
