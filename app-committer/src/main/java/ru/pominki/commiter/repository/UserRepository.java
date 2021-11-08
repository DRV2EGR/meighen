package ru.pominki.commiter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.commiter.entity.User;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<User> findByUsername(String username);

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);

    /**
     * Find all by activation code not null list.
     *
     * @return the list
     */
    List<Optional<User>> findAllByActivationCodeNotNull();

    /**
     * Find by activation code optional.
     *
     * @param encodedUserActivationCode the encoded user activation code
     * @return the optional
     */
    Optional<User> findByActivationCode(String encodedUserActivationCode);
}
