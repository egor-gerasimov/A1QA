package project.form;

import framework.element.ButtonElement;
import framework.form.BaseFrom;
import org.openqa.selenium.By;

public class CalendarForm extends BaseFrom {

    private static final By DAY = By.xpath("//div[@class='calendar-day__content']/span/span");
    private static final By NO_RETURN_BUTTON = By.xpath("//span[.='Обратный билет не нужен']");

    private final ButtonElement dayButton = new ButtonElement(DAY, "Day");
    private final ButtonElement noReturnButton = new ButtonElement(NO_RETURN_BUTTON, "No return");

    public void clickOnDay() {
        dayButton.click();
    }

    public void clickNoReturn() {
        noReturnButton.click();
    }
}
