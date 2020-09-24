package project.form;

import framework.element.ButtonElement;
import framework.element.CheckboxElement;
import framework.element.InputElement;
import framework.form.BaseFrom;
import org.openqa.selenium.By;

public class HomeForm extends BaseFrom {

    private static final By INPUT_FROM = By.xpath("//div[@class='avia-form__field --origin']/div/div/input");
    private static final By INPUT_TO = By.xpath("//div[@class='avia-form__field --destination']/div/div/input");
    private static final By DAY_FROM = By.xpath("//div[@class='trip-duration__field --departure']");
    private static final String HOTEL_SEARCH = "//div[@class='of_input_checkbox of_input_checkbox']";
    private static final By HOTEL_SEARCH_CHECKBOX = By.xpath(HOTEL_SEARCH + "/input");
    private static final By SUBMIT_SEARCH = By.xpath("//div[@class='avia-form__submit']");
    private final InputElement cityFromInput = new InputElement(INPUT_FROM, "From");
    private final InputElement cityToInput = new InputElement(INPUT_TO, "To");
    private final ButtonElement dayFrom = new ButtonElement(DAY_FROM, "Day from");
    private final ButtonElement searchButton = new ButtonElement(SUBMIT_SEARCH, "Search");
    private final ButtonElement hotelSearch = new ButtonElement(By.xpath(HOTEL_SEARCH), "Hotel search");
    private final CheckboxElement hotelSearchCheckbox = new CheckboxElement(HOTEL_SEARCH_CHECKBOX, "Hotel search");
    private final CalendarForm calendarForm = new CalendarForm();

    public String getCityFrom() {
        return cityFromInput.getValue();
    }

    public void setCityFrom(String city) {
        cityFromInput.setValue(city);
    }

    public String getCityTo() {
        return cityToInput.getValue();
    }

    public void setCityTo(String city) {
        cityToInput.setValue(city);
    }

    public void chooseDayFrom() {
        dayFrom.click();
        calendarForm.clickOnDay();
    }

    public void chooseNoReturn() {
        calendarForm.clickNoReturn();
    }

    public void clickHotelSearch() {
        if (hotelSearchCheckbox.isSelected()) {
            hotelSearch.click();
        }
    }

    public void search() {
        searchButton.click();
    }
}
