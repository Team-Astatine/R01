package teamzesa.exception.Enhance;

public class EnhanceItemSearchException extends Exception {
    private static String DEFAULT_MESSAGE = "";

    public EnhanceItemSearchException(String message) {
        super(message);
        DEFAULT_MESSAGE = message;
    }

    public EnhanceItemSearchException() {
        super(DEFAULT_MESSAGE);
    }
}
