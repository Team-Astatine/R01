package teamzesa.exception;

public class NullReadUserException extends Exception {
    private static final String DEFAULT_MESSAGE = "Null Read User";

    public NullReadUserException() {
        super(DEFAULT_MESSAGE);
    }
}
