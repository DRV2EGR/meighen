package ru.pominki.presenter.exception;

/**
 * The type Api exception.
 */
public abstract class ApiException extends RuntimeException {
    /**
     * Instantiates a new Api exception.
     *
     * @param message the message
     */
    public ApiException(String message) {
        super(message);
    }
}
