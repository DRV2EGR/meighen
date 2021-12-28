package ru.meighgen.presenter.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User dto payload.
 */
@Data
@NoArgsConstructor
public class UserDtoPayload extends BasicPayload {
    private String firstName;
    private String secondName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String userProfileImageUrl;

    /**
     * Instantiates a new User dto payload.
     *
     * @param firstName           the first name
     * @param secondName          the second name
     * @param lastName            the last name
     * @param username            the username
     * @param email               the email
     * @param password            the password
     * @param phoneNumber         the phone number
     * @param userProfileImageUrl the user profile image url
     */
    public UserDtoPayload(String firstName, String secondName, String lastName, String username, String email, String password, String phoneNumber, String userProfileImageUrl) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userProfileImageUrl = userProfileImageUrl;
    }
}
