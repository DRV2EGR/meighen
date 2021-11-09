package ru.pominki.commiter.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User.
 */
@Entity
@Table(name ="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
    /**
     * The First name.
     */
    @Column(name = "first_name")
    protected String firstName;

    /**
     * The Second name.
     */
    @Column(name = "second_name")
    protected String secondName;

    /**
     * The Last name.
     */
    @Column(name = "last_name")
    protected String lastName;

    /**
     * The Username.
     */
    @Column(name = "username")
    protected String username;

    /**
     * The Email.
     */
    @Column(name = "email", nullable = false)
    protected String email;

    /**
     * The Password.
     */
    @Column(name = "password", nullable = false)
    protected String password;

    /**
     * The Phone number.
     */
    @Column(name = "phone_number")
    protected String phoneNumber;

    /**
     * The Activation code.
     */
    @Column(name = "activation_code")
    protected String activationCode;

    /**
     * The Time of account creation.
     */
    @Column(name = "time_of_account_creation")
    protected LocalDateTime timeOfAccountCreation;

    /**
     * The Created activation code.
     */
    @Column(name = "creation_activation_code")
    protected String createdActivationCode;

    /**
     * The User profile image url.
     */
    @Column(name = "user_profile_image_url", nullable = true)
    protected String userProfileImageUrl;

    @OneToMany
    protected List<Repository> repositories;

    /**
     * Instantiates a new User.
     *
     * @param firstName   the first name
     * @param secondName  the second name
     * @param lastName    the last name
     * @param email       the email
     * @param phoneNumber the phone number
     */
    public User(String firstName, String secondName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
