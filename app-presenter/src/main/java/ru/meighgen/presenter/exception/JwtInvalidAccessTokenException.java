package ru.meighgen.presenter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Jwt invalid access token exception.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class JwtInvalidAccessTokenException extends BadRequestException {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Jwt invalid access token exception.
     *
     * @param message the message
     */
    public JwtInvalidAccessTokenException(String message) {
        super(message);
    }
}
