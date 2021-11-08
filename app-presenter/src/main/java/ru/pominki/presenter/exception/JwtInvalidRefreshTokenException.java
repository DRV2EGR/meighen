package ru.pominki.presenter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Jwt invalid refresh token exception.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class JwtInvalidRefreshTokenException extends BadRequestException {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Jwt invalid refresh token exception.
     *
     * @param message the message
     */
    public JwtInvalidRefreshTokenException(String message) {
        super(message);
    }
}
