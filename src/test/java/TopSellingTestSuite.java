import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import org.testng.annotations.Test;
import project.form.AgeCheckForm;
import project.form.GameForm;
import project.form.GenreForm;
import project.form.HeaderForm;
import project.model.Game;

public class TopSellingTestSuite extends BaseTest {

    @Test
    public void highestDiscountGameTest() {
        HeaderForm headerForm = new HeaderForm();
        headerForm.clickOnAction();
        GenreForm genreForm = new GenreForm();
        String genreName = genreForm.getGenreName();
        assertTrue(genreName.contains("Action") || genreName.contains("Экшн"), "Wrong page");
        genreForm.clickTopSellers();
        List<Game> games = genreForm.getTopSellers();
        Game maxDiscountGame = genreForm.getMaxDiscountElement(games);
        genreForm.clickOnGame(maxDiscountGame);
        AgeCheckForm ageCheckForm = new AgeCheckForm();
        if (ageCheckForm.atPage()) {
            ageCheckForm.setYear("1970");
            ageCheckForm.openPage();
        }
        GameForm gameForm = new GameForm();
        gameForm.readGame();
        assertEquals(gameForm.getGame(), maxDiscountGame, "Wrong game");
    }

    @Test
    public void lowestDiscountGameTest() {
        HeaderForm headerForm = new HeaderForm();
        headerForm.clickOnIndie();
        GenreForm genreForm = new GenreForm();
        String genreName = genreForm.getGenreName();
        assertTrue(genreName.contains("Indie") || genreName.contains("Инди"), "Wrong page");
        genreForm.clickTopSellers();
        List<Game> games = genreForm.getTopSellers();
        Game minDiscountGame = genreForm.getMinDiscountElement(games);
        genreForm.clickOnGame(minDiscountGame);
        AgeCheckForm ageCheckForm = new AgeCheckForm();
        if (ageCheckForm.atPage()) {
            ageCheckForm.setYear("1970");
            ageCheckForm.openPage();
        }
        GameForm gameForm = new GameForm();
        gameForm.readGame();
        assertEquals(gameForm.getGame(), minDiscountGame, "Wrong game");
    }
}
