package project.form;

import static framework.utils.StringUtils.toDouble;
import static project.form.GenreForm.TOP_SELLERS_LIST;

import framework.element.LabelElement;
import framework.form.BaseFrom;
import org.openqa.selenium.By;
import project.model.Game;

public class GameOnGenreForm extends BaseFrom {

    private GenreForm genreForm;
    private Game game;
    private final LabelElement finalPriceLabel;
    private final LabelElement originalPriceLabel;
    private final LabelElement discountPercentLabel;

    public GameOnGenreForm(GenreForm genreForm, int order) {
        this.genreForm = genreForm;
        String elementLocator = TOP_SELLERS_LIST + "[" + (order + 1) + "]";
        By FINAL_PRICE = By.xpath(elementLocator + "//div[@class='discount_final_price']");
        By ORIGINAL_PRICE = By.xpath(elementLocator + "//div[@class='discount_original_price']");
        By DISCOUNT_PERCENT = By.xpath(elementLocator + "//div[@class='discount_pct']");
        finalPriceLabel = new LabelElement(FINAL_PRICE, "Final price");
        originalPriceLabel = new LabelElement(ORIGINAL_PRICE, "Original price");
        discountPercentLabel = new LabelElement(DISCOUNT_PERCENT, "Discount percent");
    }

    public GenreForm getGenreForm() {
        return genreForm;
    }

    public void setGenreForm(GenreForm genreForm) {
        this.genreForm = genreForm;
    }

    public void readGame() {
        game = new Game();
        double finalPrice = toDouble(finalPriceLabel.getText());
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
