//package ru.meighgen.presenter.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import ru.meighgen.presenter.controller.CommitController;
//import ru.meighgen.presenter.dto.BranchDto;
//import ru.meighgen.presenter.dto.CommitDto;
//import ru.meighgen.presenter.dto.RepoDto;
//import ru.meighgen.presenter.dto.RepoShortDto;
//import ru.meighgen.presenter.entity.Branch;
//import ru.meighgen.presenter.entity.Commit;
//import ru.meighgen.presenter.entity.Repository;
//import ru.meighgen.presenter.entity.User;
//import ru.meighgen.presenter.entity.Role;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//class ClassCastToDtoTest {
//    @MockBean
//    UserService userService;
//
//    @Autowired
//    private ClassCastToDto classCastToDto;
//
//    @Test
//    void testConvertRepoToRepoDto() {
//        Commit commit = new Commit();
//        commit.setMessage("Not all who wander are lost");
//        commit.setFolderId("42");
//        commit.setNext(new Commit());
//        commit.setId(123L);
//        commit.setCommitId("42");
//
//        Commit commit1 = new Commit();
//        commit1.setMessage("Not all who wander are lost");
//        commit1.setFolderId("42");
//        commit1.setNext(commit);
//        commit1.setId(123L);
//        commit1.setCommitId("42");
//
//        Commit commit2 = new Commit();
//        commit2.setMessage("Not all who wander are lost");
//        commit2.setFolderId("42");
//        commit2.setNext(commit1);
//        commit2.setId(123L);
//        commit2.setCommitId("42");
//
//        Branch branch = new Branch();
//        branch.setFolderId("42");
//        branch.setTimeOfBranchCreation(LocalDateTime.of(1, 1, 1, 1, 1));
//        branch.setValid(true);
//        branch.setCommits(new ArrayList<Commit>());
//        branch.setId(123L);
//        branch.setHEAD("HEAD");
//        branch.setName("Name");
//        branch.setCreator(1L);
//
//        Repository repository = new Repository();
//        repository.setOwner(1L);
//        repository.setFolderId("42");
//        repository.setTimeOfRepoCreation(LocalDateTime.of(1, 1, 1, 1, 1));
//        repository.setValid(true);
//        repository.setId(123L);
//        repository.setName("Name");
//        repository.setHEAD(commit2);
//        ArrayList<User> userList = new ArrayList<User>();
//        repository.setCollaborators(userList);
//        repository.setDefaultBranch(branch);
//        repository.setBranches(new ArrayList<Branch>());
//        RepoDto actualConvertRepoToRepoDtoResult = this.classCastToDto.convertRepoToRepoDto(repository);
//        assertEquals(userList, actualConvertRepoToRepoDtoResult.getBranches());
//        assertEquals("Name", actualConvertRepoToRepoDtoResult.getName());
//        assertEquals("01:01", actualConvertRepoToRepoDtoResult.getTimeOfRepoCreation().toLocalTime().toString());
//        assertEquals("Name", actualConvertRepoToRepoDtoResult.getMainBranch());
//        assertEquals(123L, actualConvertRepoToRepoDtoResult.getID().longValue());
//        assertEquals(userList, actualConvertRepoToRepoDtoResult.getCollaborators());
//    }
//
//    @Test
//    void testConvertRepoToRepoDto2() {
//        Commit commit = new Commit();
//        commit.setMessage("Not all who wander are lost");
//        commit.setFolderId("42");
//        commit.setNext(new Commit());
//        commit.setId(123L);
//        commit.setCommitId("42");
//
//        Commit commit1 = new Commit();
//        commit1.setMessage("Not all who wander are lost");
//        commit1.setFolderId("42");
//        commit1.setNext(commit);
//        commit1.setId(123L);
//        commit1.setCommitId("42");
//
//        Commit commit2 = new Commit();
//        commit2.setMessage("Not all who wander are lost");
//        commit2.setFolderId("42");
//        commit2.setNext(commit1);
//        commit2.setId(123L);
//        commit2.setCommitId("42");
//
//        Role role = new Role();
//        role.setId(123L);
//        role.setName("Name");
//
//        User user = new User();
//        user.setLastName("Doe");
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setActivationCode("Activation Code");
//        user.setId(123L);
//        user.setRepositories(new ArrayList<Repository>());
//        user.setPhoneNumber("4105551212");
//        user.setRole(role);
//        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
//        user.setCreatedActivationCode("Jan 1, 2020 8:00am GMT+0100");
//        user.setUserProfileImageUrl("https://example.org/example");
//        user.setFirstName("Jane");
//        user.setUsername("janedoe");
//        user.setSecondName("Second Name");
//
//        ArrayList<User> userList = new ArrayList<User>();
//        userList.add(user);
//
//        Branch branch = new Branch();
//        branch.setFolderId("42");
//        branch.setTimeOfBranchCreation(LocalDateTime.of(1, 1, 1, 1, 1));
//        branch.setValid(true);
//        ArrayList<Commit> commitList = new ArrayList<Commit>();
//        branch.setCommits(commitList);
//        branch.setId(123L);
//        branch.setHEAD("HEAD");
//        branch.setName("Name");
//        branch.setCreator(1L);
//
//        Repository repository = new Repository();
//        repository.setOwner(1L);
//        repository.setFolderId("42");
//        repository.setTimeOfRepoCreation(LocalDateTime.of(1, 1, 1, 1, 1));
//        repository.setValid(true);
//        repository.setId(123L);
//        repository.setName("Name");
//        repository.setHEAD(commit2);
//        repository.setCollaborators(userList);
//        repository.setDefaultBranch(branch);
//        repository.setBranches(new ArrayList<Branch>());
//        RepoDto actualConvertRepoToRepoDtoResult = this.classCastToDto.convertRepoToRepoDto(repository);
//        assertEquals(commitList, actualConvertRepoToRepoDtoResult.getBranches());
//        assertEquals(
//                "RepoDto(ID=123, name=Name, timeOfRepoCreation=0001-01-01T01:01, mainBranch=Name, branches=[],"
//                        + " collaborators=[UserDto(id=123, firstName=Jane, secondName=Second Name, lastName=Doe, username=janedoe,"
//                        + " email=jane.doe@example.org, phoneNumber=4105551212, role=Name)])",
//                actualConvertRepoToRepoDtoResult.toString());
//        assertEquals("Name", actualConvertRepoToRepoDtoResult.getName());
//        assertEquals("01:01", actualConvertRepoToRepoDtoResult.getTimeOfRepoCreation().toLocalTime().toString());
//        assertEquals("Name", actualConvertRepoToRepoDtoResult.getMainBranch());
//        assertEquals(123L, actualConvertRepoToRepoDtoResult.getID().longValue());
//        assertEquals(1, actualConvertRepoToRepoDtoResult.getCollaborators().size());
//    }
//
//    @Test
//    void testConvertCommitToCommitDto() {
//        Commit commit = new Commit();
//        commit.setMessage("Not all who wander are lost");
//        commit.setFolderId("42");
//        commit.setNext(new Commit());
//        commit.setId(123L);
//        commit.setCommitId("42");
//
//        Commit commit1 = new Commit();
//        commit1.setMessage("Not all who wander are lost");
//        commit1.setFolderId("42");
//        commit1.setNext(commit);
//        commit1.setId(123L);
//        commit1.setCommitId("42");
//
//        Commit commit2 = new Commit();
//        commit2.setMessage("Not all who wander are lost");
//        commit2.setFolderId("42");
//        commit2.setNext(commit1);
//        commit2.setId(123L);
//        commit2.setCommitId("42");
//
//        Commit commit3 = new Commit();
//        commit3.setMessage("Not all who wander are lost");
//        commit3.setFolderId("42");
//        commit3.setNext(commit2);
//        commit3.setId(123L);
//        commit3.setCommitId("42");
//        CommitDto actualConvertCommitToCommitDtoResult = this.classCastToDto.convertCommitToCommitDto(commit3);
//        assertEquals("42", actualConvertCommitToCommitDtoResult.getCommitId());
//        assertEquals("42", actualConvertCommitToCommitDtoResult.getNext());
//        assertEquals("Not all who wander are lost", actualConvertCommitToCommitDtoResult.getMessage());
//    }
//
//    @Test
//    void testConvertBranchToBranchDto() {
//        Branch branch = new Branch();
//        branch.setFolderId("42");
//        branch.setTimeOfBranchCreation(LocalDateTime.of(1, 1, 1, 1, 1));
//        branch.setValid(true);
//        ArrayList<Commit> commitList = new ArrayList<Commit>();
//        branch.setCommits(commitList);
//        branch.setId(123L);
//        branch.setHEAD("HEAD");
//        branch.setName("Name");
//        branch.setCreator(1L);
//        BranchDto actualConvertBranchToBranchDtoResult = this.classCastToDto.convertBranchToBranchDto(branch);
//        assertEquals(123L, actualConvertBranchToBranchDtoResult.getBranchId().longValue());
//        assertEquals("Name", actualConvertBranchToBranchDtoResult.getName());
//        assertEquals("01:01", actualConvertBranchToBranchDtoResult.getTimeOfBranchCreation().toLocalTime().toString());
//        assertEquals("HEAD", actualConvertBranchToBranchDtoResult.getHead());
//        assertEquals(1L, actualConvertBranchToBranchDtoResult.getCreatorId().longValue());
//        assertEquals(commitList, actualConvertBranchToBranchDtoResult.getCommits());
//    }
//
//    @Test
//    void testConvertBranchToBranchDto2() {
//        Commit commit = new Commit();
//        commit.setMessage("Not all who wander are lost");
//        commit.setFolderId("42");
//        commit.setNext(new Commit());
//        commit.setId(123L);
//        commit.setCommitId("42");
//
//        Commit commit1 = new Commit();
//        commit1.setMessage("Not all who wander are lost");
//        commit1.setFolderId("42");
//        commit1.setNext(commit);
//        commit1.setId(123L);
//        commit1.setCommitId("42");
//
//        Commit commit2 = new Commit();
//        commit2.setMessage("Not all who wander are lost");
//        commit2.setFolderId("42");
//        commit2.setNext(commit1);
//        commit2.setId(123L);
//        commit2.setCommitId("42");
//
//        Commit commit3 = new Commit();
//        commit3.setMessage("Not all who wander are lost");
//        commit3.setFolderId("42");
//        commit3.setNext(commit2);
//        commit3.setId(123L);
//        commit3.setCommitId("42");
//
//        ArrayList<Commit> commitList = new ArrayList<Commit>();
//        commitList.add(commit3);
//
//        Branch branch = new Branch();
//        branch.setFolderId("42");
//        branch.setTimeOfBranchCreation(LocalDateTime.of(1, 1, 1, 1, 1));
//        branch.setValid(true);
//        branch.setCommits(commitList);
//        branch.setId(123L);
//        branch.setHEAD("HEAD");
//        branch.setName("Name");
//        branch.setCreator(1L);
//        BranchDto actualConvertBranchToBranchDtoResult = this.classCastToDto.convertBranchToBranchDto(branch);
//        assertEquals(123L, actualConvertBranchToBranchDtoResult.getBranchId().longValue());
//        assertEquals("Name", actualConvertBranchToBranchDtoResult.getName());
//        assertEquals("01:01", actualConvertBranchToBranchDtoResult.getTimeOfBranchCreation().toLocalTime().toString());
//        assertEquals("HEAD", actualConvertBranchToBranchDtoResult.getHead());
//        assertEquals(1L, actualConvertBranchToBranchDtoResult.getCreatorId().longValue());
//        List<CommitDto> commits = actualConvertBranchToBranchDtoResult.getCommits();
//        assertEquals(1, commits.size());
//        CommitDto getResult = commits.get(0);
//        assertEquals("42", getResult.getCommitId());
//        assertEquals("42", getResult.getNext());
//        assertEquals("Not all who wander are lost", getResult.getMessage());
//    }
//
//    @Test
//    void testConvertBranchToBranchDto3() {
//        Branch branch = new Branch();
//        branch.setFolderId("42");
//        branch.setTimeOfBranchCreation(LocalDateTime.of(1, 1, 1, 1, 1));
//        branch.setValid(true);
//        ArrayList<Commit> commitList = new ArrayList<Commit>();
//        branch.setCommits(commitList);
//        branch.setId(123L);
//        branch.setHEAD(null);
//        branch.setName("Name");
//        branch.setCreator(1L);
//        BranchDto actualConvertBranchToBranchDtoResult = this.classCastToDto.convertBranchToBranchDto(branch);
//        assertEquals(123L, actualConvertBranchToBranchDtoResult.getBranchId().longValue());
//        assertEquals("Name", actualConvertBranchToBranchDtoResult.getName());
//        assertEquals("01:01", actualConvertBranchToBranchDtoResult.getTimeOfBranchCreation().toLocalTime().toString());
//        assertNull(actualConvertBranchToBranchDtoResult.getHead());
//        assertEquals(1L, actualConvertBranchToBranchDtoResult.getCreatorId().longValue());
//        assertEquals(commitList, actualConvertBranchToBranchDtoResult.getCommits());
//    }
//
//    @Test
//    void testConvertRepositoryToRepoShortDto() {
//        Commit commit = new Commit();
//        commit.setMessage("Not all who wander are lost");
//        commit.setFolderId("42");
//        commit.setNext(new Commit());
//        commit.setId(123L);
//        commit.setCommitId("42");
//
//        Commit commit1 = new Commit();
//        commit1.setMessage("Not all who wander are lost");
//        commit1.setFolderId("42");
//        commit1.setNext(commit);
//        commit1.setId(123L);
//        commit1.setCommitId("42");
//
//        Commit commit2 = new Commit();
//        commit2.setMessage("Not all who wander are lost");
//        commit2.setFolderId("42");
//        commit2.setNext(commit1);
//        commit2.setId(123L);
//        commit2.setCommitId("42");
//
//        Branch branch = new Branch();
//        branch.setFolderId("42");
//        branch.setTimeOfBranchCreation(LocalDateTime.of(1, 1, 1, 1, 1));
//        branch.setValid(true);
//        branch.setCommits(new ArrayList<Commit>());
//        branch.setId(123L);
//        branch.setHEAD("HEAD");
//        branch.setName("Name");
//        branch.setCreator(1L);
//
//        Repository repository = new Repository();
//        repository.setOwner(1L);
//        repository.setFolderId("42");
//        repository.setTimeOfRepoCreation(LocalDateTime.of(1, 1, 1, 1, 1));
//        repository.setValid(true);
//        repository.setId(123L);
//        repository.setName("Name");
//        repository.setHEAD(commit2);
//        repository.setCollaborators(new ArrayList<User>());
//        repository.setDefaultBranch(branch);
//        repository.setBranches(new ArrayList<Branch>());
//        RepoShortDto actualConvertRepositoryToRepoShortDtoResult = this.classCastToDto
//                .convertRepositoryToRepoShortDto(repository);
//        assertEquals(123L, actualConvertRepositoryToRepoShortDtoResult.getID().longValue());
//        assertEquals("Name", actualConvertRepositoryToRepoShortDtoResult.getName());
//    }
//}
//
