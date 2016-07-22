
package com.talentica.data.exception;


import com.talentica.domain.exception.ErrorBundle;

/**
 * Wrapper around Exceptions used to manage errors in the repository.
 */
public class RepositoryErrorBundle implements ErrorBundle {
    private final String Tag = "RepositoryErrorBundle";
    private final Exception exception;

    public RepositoryErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        String message = "";
        if (this.exception != null) {
            this.exception.getMessage();
        }
        return message;
    }
}
