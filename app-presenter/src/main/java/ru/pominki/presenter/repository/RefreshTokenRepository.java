package ru.pominki.presenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.presenter.entity.RefreshToken;

/**
 * The interface Refresh token repository.
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
