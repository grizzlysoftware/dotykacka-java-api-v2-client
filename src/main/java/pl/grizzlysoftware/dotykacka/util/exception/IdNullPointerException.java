package pl.grizzlysoftware.dotykacka.util.exception;

public class IdNullPointerException extends IdRuntimeException{
    public IdNullPointerException(String id) {
        super(id);
    }

    public IdNullPointerException(String id, String message) {
        super(id, message);
    }

    public IdNullPointerException(String id, String message, Throwable cause) {
        super(id, message, cause);
    }

    public IdNullPointerException(String id, Throwable cause) {
        super(id, cause);
    }
}
