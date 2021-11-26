package ru.meighgen.presenter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.meighgen.presenter.dto.UserDto;
import ru.meighgen.presenter.entity.Repository;
import ru.meighgen.presenter.entity.User;
import ru.meighgen.presenter.exception.NotFoundException;
import ru.meighgen.presenter.exception.UserNotFoundExeption;
import ru.meighgen.presenter.payload.UserDtoPayload;
import ru.meighgen.presenter.repository.RoleRepository;
import ru.meighgen.presenter.repository.UserRepository;
import ru.meighgen.presenter.entity.Role;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    void testFindByUsername() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setActivationCode("Activation Code");
        user.setId(123L);
        user.setRepositories(new ArrayList<Repository>());
        user.setPhoneNumber("4105551212");
        user.setRole(role);
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findByUsername((String) any())).thenReturn(ofResult);
        Optional<User> actualFindByUsernameResult = this.userService.findByUsername("janedoe");
        assertSame(ofResult, actualFindByUsernameResult);
        assertTrue(actualFindByUsernameResult.isPresent());
        verify(this.userRepository, atLeast(1)).findByUsername((String) any());
    }

    @Test
    void testFindByUsername2() {
        Optional<User> emptyResult = Optional.<User>empty();
        when(this.userRepository.findByUsername((String) any())).thenReturn(emptyResult);
        Optional<User> actualFindByUsernameResult = this.userService.findByUsername("janedoe");
        assertSame(emptyResult, actualFindByUsernameResult);
        assertFalse(actualFindByUsernameResult.isPresent());
        verify(this.userRepository, atLeast(1)).findByUsername((String) any());
    }

    @Test
    void testFindUserByUsername() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setActivationCode("Activation Code");
        user.setId(123L);
        user.setRepositories(new ArrayList<Repository>());
        user.setPhoneNumber("4105551212");
        user.setRole(role);
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findByUsername((String) any())).thenReturn(ofResult);
        assertSame(user, this.userService.findUserByUsername("janedoe"));
        verify(this.userRepository).findByUsername((String) any());
    }

    @Test
    void testFindUserByUsername2() {
        when(this.userRepository.findByUsername((String) any())).thenReturn(Optional.<User>empty());
        assertThrows(UserNotFoundExeption.class, () -> this.userService.findUserByUsername("janedoe"));
        verify(this.userRepository).findByUsername((String) any());
    }

    @Test
    void testFindUserById() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setActivationCode("Activation Code");
        user.setId(123L);
        user.setRepositories(new ArrayList<Repository>());
        user.setPhoneNumber("4105551212");
        user.setRole(role);
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, this.userService.findUserById(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testFindUserById2() {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        assertThrows(UserNotFoundExeption.class, () -> this.userService.findUserById(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testFindById() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setActivationCode("Activation Code");
        user.setId(123L);
        user.setRepositories(new ArrayList<Repository>());
        user.setPhoneNumber("4105551212");
        user.setRole(role);
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<User> actualFindByIdResult = this.userService.findById(123L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.userRepository, atLeast(1)).findById((Long) any());
    }

    @Test
    void testFindById2() {
        Optional<User> emptyResult = Optional.<User>empty();
        when(this.userRepository.findById((Long) any())).thenReturn(emptyResult);
        Optional<User> actualFindByIdResult = this.userService.findById(123L);
        assertSame(emptyResult, actualFindByIdResult);
        assertFalse(actualFindByIdResult.isPresent());
        verify(this.userRepository, atLeast(1)).findById((Long) any());
    }

    @Test
    void testActivateUser() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setActivationCode("Activation Code");
        user.setId(123L);
        user.setRepositories(new ArrayList<Repository>());
        user.setPhoneNumber("4105551212");
        user.setRole(role);
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        Optional<User> ofResult = Optional.<User>of(user);

        Role role1 = new Role();
        role1.setId(123L);
        role1.setName("Name");

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setActivationCode("Activation Code");
        user1.setId(123L);
        user1.setRepositories(new ArrayList<Repository>());
        user1.setPhoneNumber("4105551212");
        user1.setRole(role1);
        user1.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
        user1.setUserProfileImageUrl("https://example.org/example");
        user1.setFirstName("Jane");
        user1.setUsername("janedoe");
        user1.setSecondName("Second Name");
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findByActivationCode((String) any())).thenReturn(ofResult);
        this.userService.activateUser("secret");
        verify(this.userRepository).findByActivationCode((String) any());
        verify(this.userRepository).save((User) any());
    }

    @Test
    void testActivateUser2() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setActivationCode("Activation Code");
        user.setId(123L);
        user.setRepositories(new ArrayList<Repository>());
        user.setPhoneNumber("4105551212");
        user.setRole(role);
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findByActivationCode((String) any())).thenReturn(Optional.<User>empty());
        assertThrows(NotFoundException.class, () -> this.userService.activateUser("secret"));
        verify(this.userRepository).findByActivationCode((String) any());
    }

    @Test
    void testCreateNewUserAndFillBasicFields() {
        User actualCreateNewUserAndFillBasicFieldsResult = this.userService
                .createNewUserAndFillBasicFields(new UserDtoPayload("Jane", "Second Name", "Doe", "janedoe",
                        "jane.doe@example.org", "iloveyou", "4105551212", "https://example.org/example"));
        assertEquals("janedoe", actualCreateNewUserAndFillBasicFieldsResult.getUsername());
        assertEquals("Second Name", actualCreateNewUserAndFillBasicFieldsResult.getSecondName());
        assertEquals("4105551212", actualCreateNewUserAndFillBasicFieldsResult.getPhoneNumber());
        assertEquals("Doe", actualCreateNewUserAndFillBasicFieldsResult.getLastName());
        assertEquals("Jane", actualCreateNewUserAndFillBasicFieldsResult.getFirstName());
        assertEquals("jane.doe@example.org", actualCreateNewUserAndFillBasicFieldsResult.getEmail());
    }

    @Test
    void testCreateNewUserAndFillBasicFields2() {
        UserDtoPayload userDtoPayload = new UserDtoPayload("Jane", "Second Name", "Doe", "janedoe", "jane.doe@example.org",
                "iloveyou", "4105551212", "https://example.org/example");
        userDtoPayload.setLastName("Doe");
        User actualCreateNewUserAndFillBasicFieldsResult = this.userService.createNewUserAndFillBasicFields(userDtoPayload);
        assertEquals("janedoe", actualCreateNewUserAndFillBasicFieldsResult.getUsername());
        assertEquals("Second Name", actualCreateNewUserAndFillBasicFieldsResult.getSecondName());
        assertEquals("4105551212", actualCreateNewUserAndFillBasicFieldsResult.getPhoneNumber());
        assertEquals("Doe", actualCreateNewUserAndFillBasicFieldsResult.getLastName());
        assertEquals("Jane", actualCreateNewUserAndFillBasicFieldsResult.getFirstName());
        assertEquals("jane.doe@example.org", actualCreateNewUserAndFillBasicFieldsResult.getEmail());
    }

    @Test
    void testFindByEmail() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setActivationCode("Activation Code");
        user.setId(123L);
        user.setRepositories(new ArrayList<Repository>());
        user.setPhoneNumber("4105551212");
        user.setRole(role);
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findByEmail((String) any())).thenReturn(ofResult);
        Optional<User> actualFindByEmailResult = this.userService.findByEmail("jane.doe@example.org");
        assertSame(ofResult, actualFindByEmailResult);
        assertTrue(actualFindByEmailResult.isPresent());
        verify(this.userRepository, atLeast(1)).findByEmail((String) any());
    }

    @Test
    void testFindByEmail2() {
        Optional<User> emptyResult = Optional.<User>empty();
        when(this.userRepository.findByEmail((String) any())).thenReturn(emptyResult);
        Optional<User> actualFindByEmailResult = this.userService.findByEmail("jane.doe@example.org");
        assertSame(emptyResult, actualFindByEmailResult);
        assertFalse(actualFindByEmailResult.isPresent());
        verify(this.userRepository, atLeast(1)).findByEmail((String) any());
    }

    @Test
    void testConvertUserToUserDto() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setActivationCode("Activation Code");
        user.setId(123L);
        user.setRepositories(new ArrayList<Repository>());
        user.setPhoneNumber("4105551212");
        user.setRole(role);
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        UserDto actualConvertUserToUserDtoResult = this.userService.convertUserToUserDto(user);
        assertEquals("jane.doe@example.org", actualConvertUserToUserDtoResult.getEmail());
        assertEquals("janedoe", actualConvertUserToUserDtoResult.getUsername());
        assertEquals("Second Name", actualConvertUserToUserDtoResult.getSecondName());
        assertEquals("Name", actualConvertUserToUserDtoResult.getRole());
        assertEquals("4105551212", actualConvertUserToUserDtoResult.getPhoneNumber());
        assertEquals("Doe", actualConvertUserToUserDtoResult.getLastName());
        assertEquals(123L, actualConvertUserToUserDtoResult.getId());
        assertEquals("Jane", actualConvertUserToUserDtoResult.getFirstName());
    }

    @Test
    void testRegisterNewUser() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setActivationCode("Activation Code");
        user.setId(123L);
        user.setRepositories(new ArrayList<Repository>());
        user.setPhoneNumber("4105551212");
        user.setRole(role);
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        when(this.userRepository.save((User) any())).thenReturn(user);

        Role role1 = new Role();
        role1.setId(123L);
        role1.setName("Name");
        Optional<Role> ofResult = Optional.<Role>of(role1);
        when(this.roleRepository.findById((Long) any())).thenReturn(ofResult);
        UserDto actualRegisterNewUserResult = this.userService.registerNewUser(new UserDtoPayload("Jane", "Second Name",
                "Doe", "janedoe", "jane.doe@example.org", "iloveyou", "4105551212", "https://example.org/example"));
        assertEquals("jane.doe@example.org", actualRegisterNewUserResult.getEmail());
        assertEquals("janedoe", actualRegisterNewUserResult.getUsername());
        assertEquals("Second Name", actualRegisterNewUserResult.getSecondName());
        assertEquals("Name", actualRegisterNewUserResult.getRole());
        assertEquals("4105551212", actualRegisterNewUserResult.getPhoneNumber());
        assertEquals("Doe", actualRegisterNewUserResult.getLastName());
        assertEquals(0L, actualRegisterNewUserResult.getId());
        assertEquals("Jane", actualRegisterNewUserResult.getFirstName());
        verify(this.userRepository).save((User) any());
        verify(this.roleRepository).findById((Long) any());
    }
}

