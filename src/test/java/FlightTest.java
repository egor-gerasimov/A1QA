import static org.testng.Assert.assertEquals;

import framework.utils.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import project.form.HomeForm;
import project.form.FlightsForm;
import project.model.Flight;

public class FlightTest extends BaseTest {

    @DataProvider(name = "flights")
    public static Object[][] flights() {
        return new Object[][]{
            {"Минск", "Стамбул"},
            {"Стамбул", "Минск"}
        };
    }

    @Test(dataProvider = "flights")
    public void cheapestFlightTest(String cityFrom, String cityTo) {
        HomeForm homeForm = new HomeForm();
        Logger.writeLog("Set city from to " + cityFrom);
        homeForm.setCityFrom(cityFrom);
        Logger.writeLog("Check city from is " + cityFrom);
        assertEquals(homeForm.getCityFrom(), cityFrom, "Wrong city from");
        Logger.writeLog("Set city to to " + cityTo);
        homeForm.setCityTo(cityTo);
        Logger.writeLog("Check city to is " + cityTo);
        assertEquals(homeForm.getCityTo(), cityTo, "Wrong city to");
        Logger.writeLog("Choose day");
        homeForm.chooseDayFrom();
        Logger.writeLog("Choose no return");
        homeForm.chooseNoReturn();
        Logger.writeLog("Click hotel search");
        homeForm.clickHotelSearch();
        Logger.writeLog("Click search");
        homeForm.search();
        FlightsForm flightsForm = new FlightsForm();
        Logger.writeLog("Wait results");
        flightsForm.waitResults();
        Logger.writeLog("Close popup");
        flightsForm.closePopup();
        Logger.writeLog("Read flights");
        flightsForm.readFlights();
        Logger.writeLog("Check flights");
        for (Flight flight : flightsForm.getFlights()) {
            assertEquals(flight.getCityFrom(), cityFrom, "Wrong city from");
            assertEquals(flight.getCityTo(), cityTo, "Wrong city to");
        }
        Logger.writeLog("Get cheapest flight");
        Flight cheapestPriceFlight = flightsForm.getCheapestPriceFlight();
        Logger.writeLog("Get first flight");
        Flight firstFlight = flightsForm.getFlights().get(0);
        Logger.writeLog("Check first and cheapest flights");
        assertEquals(cheapestPriceFlight, firstFlight, "First flight isn't cheapest");
    }
}
