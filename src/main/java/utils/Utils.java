package utils;

import static utils.StringUtils.toMultiOS;

import aquality.selenium.core.logging.Logger;
import java.io.IOException;

public class Utils {

    private static final String resourcesPath = System.getProperty("user.dir") + "/src/test/resources/";
    private static final String imagePath = toMultiOS(resourcesPath + "great.png");
    private static final String uploadScriptPath = toMultiOS(resourcesPath + "fileUpload.exe");

    public static void autoUploadImage() {
        try {
            Process p = Runtime.getRuntime().exec(uploadScriptPath + " " + imagePath);
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            Logger.getInstance().error(e.getMessage());
        }
    }
}
