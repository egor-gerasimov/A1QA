package framework.driver.exception;

public class WrongWebDriverException extends Exception {

    WrongWebDriverException() {
        super();
    }

    public WrongWebDriverException(String message) {
        super(message);
    }
}
