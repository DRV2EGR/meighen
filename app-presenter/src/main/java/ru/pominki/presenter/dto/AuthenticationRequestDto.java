package ru.pominki.presenter.dto;

import lombok.Data;

/**
 * The type Authentication request dto.
 */
@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
