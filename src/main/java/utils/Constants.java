package utils;

public class Constants {

    private static final String expectedSimpleAlertText = "I am a JS Alert";
    private static final String expectedSimpleAlertResultText = "You successfuly clicked an alert";
    private static final String expectedConfirmAlertText = "I am a JS Confirm";
    private static final String expectedConfirmAlertResultText = "You clicked: Ok";
    private static final String expectedPromptAlertText = "I am a JS prompt";
    private static final String expectedPromptAlertResultText = "You entered: %s";

    public static String getExpectedSimpleAlertText() {
        return expectedSimpleAlertText;
    }

    public static String getExpectedSimpleAlertResultText() {
        return expectedSimpleAlertResultText;
    }

    public static String getExpectedConfirmAlertText() {
        return expectedConfirmAlertText;
    }

    public static String getExpectedConfirmAlertResultText() {
        return expectedConfirmAlertResultText;
    }

    public static String getExpectedPromptAlertText() {
        return expectedPromptAlertText;
    }

    public static String getExpectedPromptAlertResultText(String text) {
        return String.format(expectedPromptAlertResultText, text);
    }
}
