package framework.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static final String DOUBLE_PATTERN = "\\d+(\\.\\d{2})?";

    public static String toMultiOS(String path) {
        return path.replace("/", File.separator);
    }

    public static Double toDouble(String text) {
        double result = 0;
        Pattern p = Pattern.compile(DOUBLE_PATTERN);
        Matcher m = p.matcher(text);
        if (m.find()) {
            result = Double.parseDouble(m.group());
        }
        return result;
    }
}
