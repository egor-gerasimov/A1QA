import static org.testng.Assert.assertTrue;

import framework.utils.Logger;
import org.testng.annotations.Test;
import project.form.AboutForm;
import project.form.HeaderForm;

public class SteamAppDownloadTest extends BaseTest {

    @Test
    public void steamAppDownload() {
        HeaderForm headerForm = new HeaderForm();
        headerForm.clickInstallSteam();
        AboutForm aboutForm = new AboutForm();
        assertTrue(aboutForm.atPage(), "Wrong page");
        Logger.writeLog("Click install steam");
        aboutForm.installSteamNow();
    }
}
