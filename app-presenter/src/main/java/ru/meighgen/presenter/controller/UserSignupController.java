package ru.meighgen.presenter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.meighgen.presenter.service.UserService;
import ru.meighgen.presenter.dto.UserDto;
import ru.meighgen.presenter.payload.UserDtoPayload;

/**
 * The type User signup controller.
 */
@Controller
@RequestMapping("/api/signup")
public class UserSignupController {
    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * Sign up new customer response entity.
     *
     * @param userDtoPayload the user dto payload
     * @return the response entity
     */
    @PostMapping("/")
    public ResponseEntity<UserDto> signUpNewCustomer(@RequestBody UserDtoPayload userDtoPayload) {
        UserDto resp = userService.registerNewUser(userDtoPayload);
        return ResponseEntity.ok(resp);
    }


}
