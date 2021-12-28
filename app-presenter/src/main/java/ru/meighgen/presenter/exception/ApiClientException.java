package ru.meighgen.presenter.exception;

/**
 * The type Api client exception.
 */
public abstract class ApiClientException extends ApiException {
    /**
     * Instantiates a new Api client exception.
     *
     * @param message the message
     */
    public ApiClientException(String message) {
        super(message);
    }
}
