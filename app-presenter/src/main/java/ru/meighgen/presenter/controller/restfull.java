package ru.meighgen.presenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.meighgen.presenter.exception.UserNotFoundExeption;
import ru.meighgen.presenter.service.UserService;
import ru.meighgen.presenter.entity.User;

/**
 * The type Restfull.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/private")
public class restfull {
    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    private User getAuthentificatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByUsername(currentUserName).orElseThrow(
                () -> {throw new UserNotFoundExeption("");}
        );

        return currentUser;
    }

    /**
     * Check auth response entity.
     *
     * @return the response entity
     */
    @GetMapping("/check_auth")
    public ResponseEntity<?> checkAuth() {
        User u = getAuthentificatedUser();
        return ResponseEntity.ok(new String("OK"));
    }
}
