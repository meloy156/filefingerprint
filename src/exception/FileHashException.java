package exception;


/**
 * Thrown when a file hash cannot be computed.
 */
public class FileHashException extends RuntimeException {
    public FileHashException() {
        super();
    }

    public FileHashException(String message) {
        super(message);
    }

    public FileHashException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileHashException(Throwable cause) {
        super(cause);
    }
}
