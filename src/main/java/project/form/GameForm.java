package project.form;

import static framework.utils.StringUtils.toDouble;

import framework.element.LabelElement;
import framework.form.BaseFrom;
import org.openqa.selenium.By;
import project.model.Game;

public class GameForm extends BaseFrom {

    private static final By DISCOUNT_PERCENT = By
        .xpath("//*[@id='game_area_purchase']/div[1]//div[@class='discount_pct']");
    private static final By FINAL_PRICE = By
        .xpath("//*[@id='game_area_purchase']/div[1]//div[@class='discount_final_price']");
    private static final By ORIGINAL_PRICE = By
        .xpath("//*[@id='game_area_purchase']/div[1]//div[@class='discount_original_price']");

    private final LabelElement finalPriceLabel = new LabelElement(FINAL_PRICE, "Final price");
    private final LabelElement originalPriceLabel = new LabelElement(ORIGINAL_PRICE, "Original price");
    private final LabelElement discountPercentLabel = new LabelElement(DISCOUNT_PERCENT, "Discount percent");

    private Game game;

    public void readGame() {
        double finalPrice = toDouble(finalPriceLabel.getText());
        game = new Game();
        if (originalPriceLabel.exists()) {
            double originalPrice = toDouble(originalPriceLabel.getText());
            double discountPercent = toDouble(discountPercentLabel.getText());
            game.setOriginalPrice(originalPrice);
            game.setDiscountRate(discountPercent);
        }
        game.setFinalPrice(finalPrice);
    }

    public Game getGame() {
        return game;
    }
}
