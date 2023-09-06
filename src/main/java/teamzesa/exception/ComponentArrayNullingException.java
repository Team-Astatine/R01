package teamzesa.exception;

public class ComponentArrayNullingException extends Exception {

    private static final  String DEFAULT_MESSAGE = "Array Nulling";

    public ComponentArrayNullingException() {
        super(DEFAULT_MESSAGE);
    }
}
