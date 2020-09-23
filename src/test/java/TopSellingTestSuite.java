import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import framework.utils.Dictionary;
import framework.utils.Logger;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import project.form.AgeCheckForm;
import project.form.GameForm;
import project.form.GenreForm;
import project.form.HeaderForm;
import project.model.Game;

public class TopSellingTestSuite extends BaseTest {

    @DataProvider(name = "genresCases")
    public static Object[][] genresCases() {
        return new Object[][]{
            {"Action", true},
            {"Indie", false}
        };
    }

    @Test(dataProvider = "genresCases")
    public void highestDiscountGameTest(String genre, boolean top) {
        HeaderForm headerForm = new HeaderForm();
        Logger.writeLog("Click on genre " + genre);
        headerForm.clickOnGenre(genre);
        GenreForm genreForm = new GenreForm();
        Logger.writeLog("Check right page");
        assertTrue(genreForm.getGenreName().contains(Dictionary.getWord(genre)), "Wrong page");
        Logger.writeLog("Click Top Sellers Tab");
        genreForm.clickTopSellers();
        List<Game> games = genreForm.getTopSellers();
        Logger.writeLog("Get top discount game");
        Game topGame = genreForm.getTopDiscountElement(games, top);
        Logger.writeLog("Click on game");
        genreForm.clickOnGame(topGame);
        AgeCheckForm ageCheckForm = new AgeCheckForm();
        ageCheckForm.openGame();
        GameForm gameForm = new GameForm();
        Logger.writeLog("Read game properties");
        gameForm.readGame();
        Logger.writeLog("Compare games");
        assertEquals(gameForm.getGame(), topGame, "Wrong game");
    }
}
