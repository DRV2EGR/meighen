package ru.meighgen.presenter.security;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The type Jwt token filter.
 */
public class JwtTokenFilter implements Filter {
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Instantiates a new Jwt token filter.
     *
     * @param jwtTokenProvider the jwt token provider
     */
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String bearerToken = jwtTokenProvider.resolveAccessToken((HttpServletRequest) servletRequest);
        if (bearerToken != null && jwtTokenProvider.validateAccessToken(bearerToken)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(bearerToken);
            if (authentication != null)
                SecurityContextHolder.getContext().setAuthentication(authentication);
        }
//        else throw new JwtInvalidAccessTokenException();
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
