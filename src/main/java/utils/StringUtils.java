package utils;

import java.io.File;
import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {

    public static String getRandomString() {
        StringBuilder randPass = new StringBuilder();
        String capitalLetter = RandomStringUtils.random(1, 65, 90, true, false);
        String numeric = RandomStringUtils.random(1, false, true);
        String pass = RandomStringUtils.random(8, true, true);
        randPass.append(capitalLetter).append(numeric).append(pass);
        return randPass.toString();
    }

    public static String toMultiOS(String path) {
        return path.replace("/", File.separator);
    }
}
