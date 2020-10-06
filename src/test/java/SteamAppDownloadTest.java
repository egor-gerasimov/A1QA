import static org.testng.Assert.assertTrue;

import framework.utils.Logger;
import framework.utils.PropertyManager;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import project.form.AboutForm;
import project.form.HeaderForm;

public class SteamAppDownloadTest extends BaseTest {

    @Test
    public void steamAppDownload() throws InterruptedException {
        HeaderForm headerForm = new HeaderForm();
        Logger.writeLog("Click install Steam");
        headerForm.clickInstallSteam();
        AboutForm aboutForm = new AboutForm();
        Logger.writeLog("Check right page");
        assertTrue(aboutForm.atPage(), "Wrong page");
        Logger.writeLog("Click install steam");
        aboutForm.installSteamNow();
        TimeUnit.SECONDS.sleep(5);
        Logger.writeLog("Check file");
        File file = new File(PropertyManager.getProperty("resources.path") + "SteamSetup.exe");
        assertTrue(file.isFile(), "File not found");
        Logger.writeLog("Delete file");
        file.delete();
    }
}
