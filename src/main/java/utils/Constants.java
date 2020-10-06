package utils;

public class Constants {

    private static final String expectedResult =
        "{\n"
        + "  \"authenticated\": true, \n"
        + "  \"user\": \"" + TestData.getUsername() + "\"\n"
        + "}";

    public static String getExpectedResult() {
        return expectedResult;
    }
}
