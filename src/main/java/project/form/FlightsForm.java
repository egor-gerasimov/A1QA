package project.form;

import static framework.utils.StringUtils.toDouble;

import framework.driver.Driver;
import framework.element.ButtonElement;
import framework.element.LabelElement;
import framework.utils.Waits;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.model.Flight;

public class FlightsForm {

    private static final By CLOSE_POPUP = By.className("_modal_header_buttonClose_1KZYh");
    private static final By MARKER_ELEMENT = By.className("prediction-advice");
    private static final String TICKETS = "//div[@class='product-list__item fade-enter-done']";
    private static final String FLIGHT_PRICE = "//div[@class='buy-button']//span[@data-testid='price-with-logic']";
    private static final String FLIGHT_CITY_FROM = "//div[@class='segment-route__endpoint origin']/div[@class='segment-route__city']";
    private static final String FLIGHT_CITY_TO = "//div[@class='segment-route__endpoint destination']/div[@class='segment-route__city']";
    private final ButtonElement closePopupButton = new ButtonElement(CLOSE_POPUP, "Close popup");
    private final List<Flight> flights = new ArrayList<>();

    public void waitResults() {
        Waits.explicitWaitLocated(MARKER_ELEMENT, 20);
    }

    public void closePopup() {
        closePopupButton.click();
    }

    public void readFlights() {
        List<WebElement> elements = Driver.getInstance().findElements(By.xpath(TICKETS));
        for (int i = 0; i < elements.size(); i++) {
            Flight flight = new Flight();
            String ticketLocator = String.format("%s[%d]", TICKETS, i + 1);
            String cityFrom = new LabelElement(By.xpath(ticketLocator + FLIGHT_CITY_FROM), "City from").getText();
            String cityTo = new LabelElement(By.xpath(ticketLocator + FLIGHT_CITY_TO), "City to").getText();
            double price = toDouble(new LabelElement(By.xpath(ticketLocator + FLIGHT_PRICE), "City to").getText());
            flight.setPrice(price);
            flight.setCityFrom(cityFrom);
            flight.setCityTo(cityTo);
            flights.add(flight);
        }
    }

    public Flight getCheapestPriceFlight() {
        return flights.stream().min(Comparator.comparingDouble(Flight::getPrice)).orElse(null);
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
