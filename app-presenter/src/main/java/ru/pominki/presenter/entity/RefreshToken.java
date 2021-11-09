package ru.pominki.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Refresh token.
 */
@Entity
@Table(name = "refresh_tokens")
@Data
@NoArgsConstructor
public class RefreshToken {
    /**
     * The User id.
     */
    @Column(name = "user_id")
    protected long userId;

    /**
     * The Refresh token.
     */
    @Id
    @Column(name = "refresh_token")
    protected String refreshToken;

    /**
     * Instantiates a new Refresh token.
     *
     * @param userId       the user id
     * @param refreshToken the refresh token
     */
    public RefreshToken(long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
}