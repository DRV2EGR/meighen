package ru.pominki.presenter.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The type Jwt configurer.
 */
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private ru.gruzoff.security.jwt.JwtTokenProvider jwtTokenProvider;

    /**
     * Instantiates a new Jwt configurer.
     *
     * @param jwtTokenProvider the jwt token provider
     */
    public JwtConfigurer(ru.gruzoff.security.jwt.JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        ru.gruzoff.security.jwt.JwtTokenFilter jwtTokenFilter = new ru.gruzoff.security.jwt.JwtTokenFilter(jwtTokenProvider);
        httpSecurity.addFilterBefore(jwtTokenFilter , UsernamePasswordAuthenticationFilter.class);
    }
}
