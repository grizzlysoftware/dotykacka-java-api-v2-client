package pl.grizzlysoftware.dotykacka.util.exception;

public class IdRuntimeException extends RuntimeException {
    protected String id;

    public IdRuntimeException(String id) {
        this.id = id;
    }

    public IdRuntimeException(String id, String message) {
        super(message);
        this.id = id;
    }

    public IdRuntimeException(String id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public IdRuntimeException(String id, Throwable cause)  {
        super(cause);
        this.id = id;
    }
}
