package project.form;

import static framework.utils.StringUtils.toDouble;

import framework.driver.Driver;
import framework.element.ButtonElement;
import framework.element.LabelElement;
import framework.form.BaseFrom;
import framework.utils.Waits;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.model.Game;

public class GenreForm extends BaseFrom {

    private static final By GENRE_NAME = By.className("pageheader");
    private static final By TOP_SELLERS = By.xpath("//*[@id='tab_select_TopSellers']");
    private static final String TOP_SELLERS_LIST = "//*[@id='TopSellersRows']/a";
    private static final String FINAL_PRICE = "//div[@class='discount_final_price']";
    private static final String ORIGINAL_PRICE = "//div[@class='discount_original_price']";
    private static final String DISCOUNT_PERCENT = "//div[@class='discount_pct']";
    private static final String GAME_ID_ATTR = "data-ds-appid";

    public String getGenreName() {
        return new LabelElement(GENRE_NAME).getText();
    }

    public void clickTopSellers() {
        new ButtonElement(TOP_SELLERS).click();
    }

    public List<Game> getTopSellers() {
        List<WebElement> elements = Driver.getInstance().findElements(By.xpath(TOP_SELLERS_LIST));
        Waits.setImplicitlyWait(100, TimeUnit.MILLISECONDS);
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            Game game = new Game();
            String elementLocator = TOP_SELLERS_LIST + "[" + (i + 1) + "]";
            double finalPrice = toDouble(new LabelElement(By.xpath(elementLocator + FINAL_PRICE)).getText());
            LabelElement discount = new LabelElement(By.xpath(elementLocator + ORIGINAL_PRICE));
            if (!discount.isEmpty()) {
                double originalPrice = toDouble(discount.getText());
                double discountPercent = toDouble(
                    new LabelElement(By.xpath(elementLocator + DISCOUNT_PERCENT)).getText());
                game.setOriginalPrice(originalPrice);
                game.setDiscountRate(discountPercent);
            }
            game.setFinalPrice(finalPrice);
            game.setId(elements.get(i).getAttribute(GAME_ID_ATTR));
            games.add(game);
        }
        Waits.setImplicitlyWait();
        return games;
    }

    public Game getMaxDiscountElement(List<Game> games) {
        return games.stream().max(Comparator.comparingDouble(Game::getDiscountRate)).get();
    }

    public void clickOnGame(Game game) {
        String gameLocator = String.format("//a[@%s='%s']", GAME_ID_ATTR, game.getId());
        ButtonElement buttonElement = new ButtonElement(By.xpath(gameLocator));
        buttonElement.click();
    }

    public Game getMinDiscountElement(List<Game> games) {
        return games.stream().filter((o) -> o.getDiscountRate() > 0.0)
            .min(Comparator.comparingDouble(Game::getDiscountRate)).get();
    }
}
