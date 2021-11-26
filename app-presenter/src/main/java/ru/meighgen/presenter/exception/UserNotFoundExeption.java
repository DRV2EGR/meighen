package ru.meighgen.presenter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type User not found exeption.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundExeption extends ApiServerException {
    /**
     * Instantiates a new User not found exeption.
     *
     * @param message the message
     */
    public UserNotFoundExeption(String message) {
        super(message);
    }
}
