package pl.grizzlysoftware.dotykacka.util.exception;

public class IdIllegalArgumentException extends IdRuntimeException{
    public IdIllegalArgumentException(String id) {
        super(id);
    }

    public IdIllegalArgumentException(String id, String message) {
        super(id, message);
    }

    public IdIllegalArgumentException(String id, String message, Throwable cause) {
        super(id, message, cause);
    }

    public IdIllegalArgumentException(String id, Throwable cause) {
        super(id, cause);
    }
}
