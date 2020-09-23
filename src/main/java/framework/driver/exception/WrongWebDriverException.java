package framework.driver.exception;

public class WrongWebDriverException extends RuntimeException {

    WrongWebDriverException() {
        super();
    }

    public WrongWebDriverException(String message) {
        super(message);
    }
}
