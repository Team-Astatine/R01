package teamzesa.Exception;

public class NullReadUserException extends Exception {
    private static String DEFAULT_MESSAGE = "";

    public NullReadUserException(String message) {
        super(message);
        DEFAULT_MESSAGE = message;
    }

    public NullReadUserException() {
        super(DEFAULT_MESSAGE);
    }
}
