package ru.pominki.presenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pominki.presenter.entity.User;
import ru.pominki.presenter.exception.UserNotFoundExeption;
import ru.pominki.presenter.repository.RepositoryRepository;
import ru.pominki.presenter.service.ClassCastToDto;
import ru.pominki.presenter.service.Storage.FilesUploader;
import ru.pominki.presenter.service.UserService;
import ru.pominki.presenter.service.producer.BranchProducerService;
import ru.pominki.presenter.service.producer.ProducerService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/private")
public class restfull {
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

    @GetMapping("/check_auth")
    public ResponseEntity<?> checkAuth() {
        User u = getAuthentificatedUser();
        return ResponseEntity.ok(new String("OK"));
    }
}
