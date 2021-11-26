package ru.meighgen.presenter.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import ru.meighgen.presenter.entity.Branch;
import ru.meighgen.presenter.entity.Commit;
import ru.meighgen.presenter.entity.Repository;
import ru.meighgen.presenter.entity.User;
import ru.meighgen.presenter.entity.Role;

class UserDtoTest {
    @Test
    void testConstructor() {
        UserDto actualUserDto = new UserDto("Jane", "Second Name", "Doe", "janedoe", "jane.doe@example.org", "4105551212",
                "Role");

        assertEquals("jane.doe@example.org", actualUserDto.getEmail());
        assertEquals("UserDto(id=0, firstName=Jane, secondName=Second Name, lastName=Doe, username=janedoe, email=jane.doe"
                + "@example.org, phoneNumber=4105551212, role=Role)", actualUserDto.toString());
        assertEquals("janedoe", actualUserDto.getUsername());
        assertEquals("Second Name", actualUserDto.getSecondName());
        assertEquals("Role", actualUserDto.getRole());
        assertEquals("4105551212", actualUserDto.getPhoneNumber());
        assertEquals("Doe", actualUserDto.getLastName());
        assertEquals(0L, actualUserDto.getId());
        assertEquals("Jane", actualUserDto.getFirstName());
    }

    @Test
    void testConstructor2() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setRole(role);
        UserDto actualUserDto = new UserDto(user);
        assertNull(actualUserDto.getEmail());
        assertNull(actualUserDto.getUsername());
        assertNull(actualUserDto.getSecondName());
        assertEquals("Name", actualUserDto.getRole());
        assertNull(actualUserDto.getPhoneNumber());
        assertNull(actualUserDto.getLastName());
        assertNull(actualUserDto.getFirstName());
    }

    @Test
    void testConstructor3() {
        Commit commit = new Commit();
        commit.setMessage("Not all who wander are lost");
        commit.setFolderId("42");
        commit.setNext(new Commit());
        commit.setId(123L);
        commit.setCommitId("42");

        Commit commit1 = new Commit();
        commit1.setMessage("Not all who wander are lost");
        commit1.setFolderId("42");
        commit1.setNext(commit);
        commit1.setId(123L);
        commit1.setCommitId("42");

        Commit commit2 = new Commit();
        commit2.setMessage("Not all who wander are lost");
        commit2.setFolderId("42");
        commit2.setNext(commit1);
        commit2.setId(123L);
        commit2.setCommitId("42");

        Branch branch = new Branch();
        branch.setFolderId("42");
        branch.setTimeOfBranchCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        branch.setValid(true);
        branch.setCommits(new ArrayList<Commit>());
        branch.setId(123L);
        branch.setHEAD("HEAD");
        branch.setName("Name");
        branch.setCreator(0L);

        Repository repository = new Repository();
        repository.setOwner(0L);
        repository.setFolderId("42");
        repository.setTimeOfRepoCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        repository.setValid(true);
        repository.setId(123L);
        repository.setName("Name");
        repository.setHEAD(commit2);
        repository.setCollaborators(new ArrayList<User>());
        repository.setDefaultBranch(branch);
        repository.setBranches(new ArrayList<Branch>());

        ArrayList<Repository> repositoryList = new ArrayList<Repository>();
        repositoryList.add(repository);

        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setRole(role);
        user.setRepositories(repositoryList);
        UserDto actualUserDto = new UserDto(user);
        assertNull(actualUserDto.getEmail());
        assertNull(actualUserDto.getUsername());
        assertNull(actualUserDto.getSecondName());
        assertEquals("Name", actualUserDto.getRole());
        assertNull(actualUserDto.getPhoneNumber());
        assertNull(actualUserDto.getLastName());
        assertNull(actualUserDto.getFirstName());
    }
}

