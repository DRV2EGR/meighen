package ru.pominki.presenter.service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pominki.presenter.dto.UserDto;
import ru.pominki.presenter.entity.User;
import ru.pominki.presenter.exception.NotFoundException;
import ru.pominki.presenter.exception.UserNotFoundExeption;
import ru.pominki.presenter.payload.BasicPayload;
import ru.pominki.presenter.payload.UserDtoPayload;
import ru.pominki.presenter.repository.RoleRepository;
import ru.pominki.presenter.repository.UserRepository;

/**
 * The type User service.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    /**
     * The B crypt password encoder.
     */
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * The Mail service.
     */
//    @Autowired
//    MailService mailService;

    /**
     * The Log.
     */
    Logger log = LoggerFactory.getLogger("securityLogger");

    /**
     * Password encoder b crypt password encoder.
     *
     * @return the b crypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    public Optional<User> findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent())
        log.info("User " + optionalUser.get().toString() + "found by username " + username);
        else log.info("User with username '" + username + "' not found.");
        return userRepository.findByUsername(username);
    }

    /**
     * Find user by username user.
     *
     * @param username the username
     * @return the user
     */
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundExeption("User with this username not found!")
        );
    }

    /**
     * Find user by id user.
     *
     * @param userId the user id
     * @return the user
     */
    public User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundExeption("User with this id not found!")
        );
    }

    /**
     * Find by id optional.
     *
     * @param userId the user id
     * @return the optional
     */
    public Optional<User> findById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent())
            log.info("User " + optionalUser.get().toString() + "found by id " + userId);
        else log.info("User with id '" + userId + "' not found.");
        return userRepository.findById(userId);
    }

    /**
     * Activate user.
     *
     * @param encodedUserActivationCode the encoded user activation code
     */
    public void activateUser(String encodedUserActivationCode) {

        User activatedUser = userRepository.findByActivationCode(encodedUserActivationCode).orElseThrow(
                () -> { throw new NotFoundException("Activation code not found");}
        );

        activatedUser.setTimeOfAccountCreation(LocalDateTime.now());

        log.info("Activated profile of userID: " + activatedUser.getId() + " by activation ccode " + encodedUserActivationCode);

        activatedUser.setActivationCode(null);
        userRepository.save(activatedUser);
    }

    /**
     * Create new user and fill basic fields user.
     *
     * @param basicPayload the basic payload
     * @return the user
     */
    public User createNewUserAndFillBasicFields(BasicPayload basicPayload) {
        User user = new User();

        user.setUsername(basicPayload.getUsername());
        user.setEmail(basicPayload.getEmail());
        user.setFirstName(basicPayload.getFirstName());
        user.setLastName(basicPayload.getLastName());
        user.setPhoneNumber(basicPayload.getPhoneNumber());
        user.setSecondName(basicPayload.getSecondName());

        return user;
    }


    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    public Optional<User> findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent())
            log.info("User " + optionalUser.get().toString() + "found by email " + email);
        else log.info("User with email '" + email + "' not found.");
        return userRepository.findByEmail(email);
    }



    /**
     * Convert user to user dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole().getName());
        userDto.setSecondName(user.getSecondName());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }

    public UserDto registerNewUser(UserDtoPayload userDtoPayload) {
        User user = createNewUserAndFillBasicFields(userDtoPayload);

        user.setRole(roleRepository.findById(1L).get());
        String encodedPassword = bCryptPasswordEncoder.encode(userDtoPayload.getPassword());
        user.setPassword(encodedPassword);

        user.setActivationCode(UUID.randomUUID().toString());
        user.setTimeOfAccountCreation(LocalDateTime.now());

//        mailService.send(user.getEmail(), "Активация аккаунта.", mailService.completeRegistrationEmail(user.getSecondName(),
//                user.getLastName(), user.getActivationCode()));

        userRepository.save(user);
        return convertUserToUserDto(user);
    }
}
