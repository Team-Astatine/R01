package teamzesa.exception;

public class PermissionException extends Exception {

    private static final  String DEFAULT_MESSAGE = "Permission denied";

    public PermissionException() {
        super(DEFAULT_MESSAGE);
    }
}
