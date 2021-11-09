package pl.grizzlysoftware.dotykacka.util.exception;

public class ExceptionPreconditions {
    private ExceptionPreconditions() {
        throw new UnsupportedOperationException("You should not instantiate utility class");
    }

    public static <T> T checkNotNull(T object, String id, String message) {
        if (object == null) {
            throw new IdNullPointerException(id, message);
        }
        return object;
    }

    public static void checkArgument(boolean expression, String id, String message) {
        if (expression) {
            throw new IdIllegalArgumentException(id, message);
        }
    }
}
