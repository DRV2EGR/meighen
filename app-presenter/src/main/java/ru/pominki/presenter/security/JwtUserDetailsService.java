package ru.pominki.presenter.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.pominki.presenter.entity.User;
import ru.pominki.presenter.service.UserService;

/**
 * The type Jwt user details service.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
//    @Autowired
    private final UserService userService;

    /**
     * Instantiates a new Jwt user details service.
     *
     * @param userService the user service
     */
    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with given username doesn't exist."));
        return JwtUserFactory.create(user);
    }
}
