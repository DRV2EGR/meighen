package ru.meighgen.presenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User public dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPublicDto {
    private long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
}
