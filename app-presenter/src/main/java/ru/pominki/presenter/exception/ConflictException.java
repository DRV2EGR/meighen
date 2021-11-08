package ru.pominki.presenter.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Conflict exception.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends ApiClientException {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Conflict exception.
     *
     * @param message the message
     */
    public ConflictException(String message) {
        super(message);
    }
}
