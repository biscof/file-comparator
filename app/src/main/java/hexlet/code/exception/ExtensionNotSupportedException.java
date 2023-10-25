package hexlet.code.exception;

public class ExtensionNotSupportedException extends Exception {

    public ExtensionNotSupportedException() {
        super("This extension is not supported. Please use json or yaml (yml).");
    }

}
