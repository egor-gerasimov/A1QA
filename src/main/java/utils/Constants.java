package utils;

public class Constants {

    private static final String expectedResultFormat =
        "{\n"
        + "  \"authenticated\": true, \n"
        + "  \"user\": \"%s\"\n"
        + "}";

    public static String getExpectedResultFormat() {
        return expectedResultFormat;
    }
}
