package ru.meighgen.presenter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.meighgen.presenter.dto.UserPublicDto;
import ru.meighgen.presenter.service.ClassCastToDto;
import ru.meighgen.presenter.service.UserService;

/**
 * The type User public controller.
 */
@RestController
@RequestMapping(value = "/api/user/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserPublicController {
    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * The Class to dto service.
     */
    @Autowired
    ClassCastToDto classToDtoService;

    /**
     * Gets public user info by id.
     *
     * @param id the id
     * @return the public user info by id
     */
    @GetMapping("/user_by_id")
    public ResponseEntity<UserPublicDto> getPublicUserInfoById(@RequestParam long id) {
        return ResponseEntity.ok(
                classToDtoService.convertUserToUserPublicDto(
                    userService.findUserById(id)
                )
        );
    }

}
