package ru.meighgen.presenter.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.meighgen.presenter.dto.UserDto;
import ru.meighgen.presenter.payload.UserDtoPayload;
import ru.meighgen.presenter.service.UserService;

@ContextConfiguration(classes = {UserSignupController.class})
@ExtendWith(SpringExtension.class)
class UserSignupControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private UserSignupController userSignupController;

    @Test
    void testSignUpNewCustomer() throws Exception {
        when(this.userService.registerNewUser((UserDtoPayload) any()))
                .thenReturn(new UserDto("Jane", "Second Name", "Doe", "janedoe", "jane.doe@example.org", "4105551212", "Role"));

        UserDtoPayload userDtoPayload = new UserDtoPayload();
        userDtoPayload.setLastName("Doe");
        userDtoPayload.setEmail("jane.doe@example.org");
        userDtoPayload.setPassword("iloveyou");
        userDtoPayload.setUsername("janedoe");
        userDtoPayload.setSecondName("Second Name");
        userDtoPayload.setPhoneNumber("4105551212");
        userDtoPayload.setFirstName("Jane");
        userDtoPayload.setUserProfileImageUrl("https://example.org/example");
        String content = (new ObjectMapper()).writeValueAsString(userDtoPayload);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/signup/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userSignupController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"firstName\":\"Jane\",\"secondName\":\"Second Name\",\"lastName\":\"Doe\",\"username\":\"janedoe\",\"email\":"
                                        + "\"jane.doe@example.org\",\"phoneNumber\":\"4105551212\",\"role\":\"Role\"}"));
    }
}

