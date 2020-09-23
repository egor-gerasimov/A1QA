package project.form;

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
import org.openqa.selenium.support.ui.Wait;
import project.model.Game;

public class GenreForm extends BaseFrom {

    static final String TOP_SELLERS_LIST = "//*[@id='TopSellersRows']/a";
    private static final By GENRE_NAME = By.className("pageheader");
    private static final By TOP_SELLERS = By.xpath("//*[@id='tab_select_TopSellers']");
    private static final String GAME_ID_ATTR = "data-ds-appid";
    private final LabelElement genreNameLabel = new LabelElement(GENRE_NAME, "Genre name");
    private final ButtonElement topSellersTab = new ButtonElement(TOP_SELLERS, "Top Sellers");

    public String getGenreName() {
        return genreNameLabel.getText();
    }

    public void clickTopSellers() {
        topSellersTab.click();
    }

    public List<Game> getTopSellers() {
        List<WebElement> elements = Driver.getInstance().findElements(By.xpath(TOP_SELLERS_LIST));
        Waits.setImplicitlyWait(100, TimeUnit.MILLISECONDS);
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            GameOnGenreForm gameForm = new GameOnGenreForm(this, i);
            gameForm.readGame();
            Game game = gameForm.getGame();
            game.setId(elements.get(i).getAttribute(GAME_ID_ATTR));
            games.add(game);
        }
        Waits.setImplicitlyWait();
        return games;
    }

    public Game getTopDiscountElement(List<Game> games, Boolean max) {
        return max ? getMaxDiscountElement(games) : getMinDiscountElement(games);
    }

    public Game getMaxDiscountElement(List<Game> games) {
        return games.stream().max(Comparator.comparingDouble(Game::getDiscountRate)).orElse(null);
    }

    public Game getMinDiscountElement(List<Game> games) {
        return games.stream().filter((o) -> o.getDiscountRate() > 0.0)
            .min(Comparator.comparingDouble(Game::getDiscountRate)).orElse(null);
    }

    public void clickOnGame(Game game) {
        String gameLocator = String.format("//a[@%s='%s']", GAME_ID_ATTR, game.getId());
        ButtonElement buttonElement = new ButtonElement(By.xpath(gameLocator));
        buttonElement.click();
    }
}
