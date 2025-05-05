package teamzesa.Exception.Enhance;

public class EnhanceScrollTypeException extends Exception {
    private static String DEFAULT_MESSAGE = "";

    public EnhanceScrollTypeException(String message) {
        super(message);
        DEFAULT_MESSAGE = message;
    }

    public EnhanceScrollTypeException() {
        super(DEFAULT_MESSAGE);
    }
}
