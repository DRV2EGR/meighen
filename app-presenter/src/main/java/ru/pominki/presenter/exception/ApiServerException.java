package ru.pominki.presenter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Api server exception.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public abstract class ApiServerException extends ApiException {
    /**
     * Instantiates a new Api server exception.
     *
     * @param message the message
     */
    public ApiServerException(String message) {
        super(message);
    }
}
