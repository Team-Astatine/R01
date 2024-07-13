package teamzesa.exception.Enhance;

public class EnhanceItemMetaException extends Exception {
    private static String DEFAULT_MESSAGE = "";

    public EnhanceItemMetaException(String message) {
        super(message);
        DEFAULT_MESSAGE = message;
    }

    public EnhanceItemMetaException() {
        super(DEFAULT_MESSAGE);
    }
}
