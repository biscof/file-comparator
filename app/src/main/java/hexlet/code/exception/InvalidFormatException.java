package hexlet.code.exception;

public class InvalidFormatException extends Exception {

    public InvalidFormatException() {
        super("Invalid format option. Please try again.");
    }

}
