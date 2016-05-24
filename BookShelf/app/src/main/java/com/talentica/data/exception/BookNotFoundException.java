
package com.talentica.data.exception;

/**
 * Exception throw by the application when a Book search can't return a valid result.
 */
public class BookNotFoundException extends Exception {

    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(final String message) {
        super(message);
    }

    public BookNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BookNotFoundException(final Throwable cause) {
        super(cause);
    }
}
