package ru.meighgen.presenter.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * The type Jwt user.
 */
public class JwtUser implements UserDetails {
    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> authorities;

    /**
     * Instantiates a new Jwt user.
     *
     * @param username         the username
     * @param password         the password
     * @param grantedAuthority the granted authority
     */
    public JwtUser(
            String username,
            String password,
            GrantedAuthority grantedAuthority
    ) {
        this.username = username;
        this.password = password;
        this.authorities = new ArrayList<>();
        authorities.add(grantedAuthority);
    }

    /**
     * Instantiates a new Jwt user.
     *
     * @param username    the username
     * @param password    the password
     * @param authorities the authorities
     */
    public JwtUser(String username, String password, Collection<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}