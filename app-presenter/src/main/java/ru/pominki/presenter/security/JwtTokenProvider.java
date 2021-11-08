package ru.pominki.presenter.security;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.gruzoff.dto.JwtAuthDto;
import ru.gruzoff.entity.RefreshToken;
import ru.gruzoff.entity.User;
import ru.gruzoff.repository.RefreshTokenRepository;
import ru.gruzoff.service.UserService;

/**
 * The type Jwt token provider.
 */
@Component
@Slf4j
public class JwtTokenProvider {
    private Logger logger = LoggerFactory.getLogger("securityLogger");

    private final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.access}")
    private Long accessTokenExpiration;

    @Value("${jwt.expiration.refresh}")
    private Long refreshTokenExpiration;

    /**
     * Instantiates a new Jwt token provider.
     *
     * @param jwtUserDetailsService the jwt user details service
     */
    public JwtTokenProvider(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Init.
     */
    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    /**
     * Create access token string.
     *
     * @param user the user
     * @return the string
     */
    public String createAccessToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("role", user.getRole().getName());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenExpiration);

        logger.info("Created access JWT for user " + user.toString());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * Create refresh token string.
     *
     * @param userID the user id
     * @return the string
     */
    public String createRefreshToken(long userID) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + refreshTokenExpiration);

        UUID randUUID = UUID.randomUUID();

        String createdToken = Jwts.builder()
                .setSubject(String.valueOf(randUUID)) //TODO: записывать не в subject, а в id токена
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        refreshTokenRepository.save(new RefreshToken(userID, createdToken));
        return createdToken;
    }

    /**
     * Resolve access token string.
     *
     * @param req the req
     * @return the string
     */
    public String resolveAccessToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Validate access token boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public boolean validateAccessToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Validate refresh token boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public boolean validateRefreshToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Gets authentication.
     *
     * @param token the token
     * @return the authentication
     */
    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Refresh pair of tokens jwt auth dto.
     *
     * @param refreshTokenString the refresh token string
     * @return the jwt auth dto
     */
    public JwtAuthDto refreshPairOfTokens(String refreshTokenString) {
        if (!validateRefreshToken(refreshTokenString))
            throw new IllegalArgumentException("Refresh token is expired or invalid");

        UUID UUIDFromRefreshToken = UUID.fromString(Jwts.parser().setSigningKey(secret).parseClaimsJws(refreshTokenString).getBody().getSubject());

        RefreshToken currentRefreshToken = refreshTokenRepository.findById(refreshTokenString).orElseThrow(
                                        () -> {throw new IllegalArgumentException("No such refresh token");}
                                    );

        User subject = userService.findById(currentRefreshToken.getUserId()).orElseThrow(IllegalArgumentException::new);

        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setUsername(subject.getUsername());
        jwtAuthDto.setAccessToken(createAccessToken(subject));
        jwtAuthDto.setRefreshToken(createRefreshToken(subject.getId()));

        refreshTokenRepository.delete(currentRefreshToken);
        refreshTokenRepository.save(new RefreshToken(currentRefreshToken.getUserId(), jwtAuthDto.getRefreshToken()));

        return jwtAuthDto;
    }
}
