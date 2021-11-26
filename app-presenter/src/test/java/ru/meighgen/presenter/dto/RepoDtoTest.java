package ru.meighgen.presenter.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RepoDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new RepoDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        RepoDto repoDto = new RepoDto();
        assertTrue(repoDto.canEqual(new RepoDto()));
    }

    @Test
    void testConstructor() {
        RepoDto actualRepoDto = new RepoDto();
        ArrayList<BranchDto> branchDtoList = new ArrayList<BranchDto>();
        actualRepoDto.setBranches(branchDtoList);
        ArrayList<UserDto> userDtoList = new ArrayList<UserDto>();
        actualRepoDto.setCollaborators(userDtoList);
        actualRepoDto.setID(1L);
        actualRepoDto.setMainBranch("janedoe/featurebranch");
        actualRepoDto.setName("Name");
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualRepoDto.setTimeOfRepoCreation(ofResult);
        List<BranchDto> branches = actualRepoDto.getBranches();
        assertSame(branchDtoList, branches);
        assertEquals(userDtoList, branches);
        List<UserDto> collaborators = actualRepoDto.getCollaborators();
        assertSame(userDtoList, collaborators);
        assertEquals(branches, collaborators);
        assertEquals(1L, actualRepoDto.getID().longValue());
        assertEquals("janedoe/featurebranch", actualRepoDto.getMainBranch());
        assertEquals("Name", actualRepoDto.getName());
        assertSame(ofResult, actualRepoDto.getTimeOfRepoCreation());
        assertEquals("RepoDto(ID=1, name=Name, timeOfRepoCreation=0001-01-01T01:01, mainBranch=janedoe/featurebranch,"
                + " branches=[], collaborators=[])", actualRepoDto.toString());
    }

    @Test
    void testConstructor2() {
        LocalDateTime timeOfRepoCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branchDtoList = new ArrayList<BranchDto>();
        ArrayList<UserDto> userDtoList = new ArrayList<UserDto>();
        RepoDto actualRepoDto = new RepoDto(1L, "Name", timeOfRepoCreation, "janedoe/featurebranch", branchDtoList,
                userDtoList);
        ArrayList<BranchDto> branchDtoList1 = new ArrayList<BranchDto>();
        actualRepoDto.setBranches(branchDtoList1);
        ArrayList<UserDto> userDtoList1 = new ArrayList<UserDto>();
        actualRepoDto.setCollaborators(userDtoList1);
        actualRepoDto.setID(1L);
        actualRepoDto.setMainBranch("janedoe/featurebranch");
        actualRepoDto.setName("Name");
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualRepoDto.setTimeOfRepoCreation(ofResult);
        List<BranchDto> branches = actualRepoDto.getBranches();
        assertSame(branchDtoList1, branches);
        assertEquals(branchDtoList, branches);
        assertEquals(userDtoList, branches);
        assertEquals(userDtoList1, branches);
        List<UserDto> collaborators = actualRepoDto.getCollaborators();
        assertSame(userDtoList1, collaborators);
        assertEquals(branchDtoList, collaborators);
        assertEquals(userDtoList, collaborators);
        assertEquals(branches, collaborators);
        assertEquals(1L, actualRepoDto.getID().longValue());
        assertEquals("janedoe/featurebranch", actualRepoDto.getMainBranch());
        assertEquals("Name", actualRepoDto.getName());
        assertSame(ofResult, actualRepoDto.getTimeOfRepoCreation());
        assertEquals("RepoDto(ID=1, name=Name, timeOfRepoCreation=0001-01-01T01:01, mainBranch=janedoe/featurebranch,"
                + " branches=[], collaborators=[])", actualRepoDto.toString());
    }

    @Test
    void testEquals() {
        assertFalse((new RepoDto()).equals(null));
        assertFalse((new RepoDto()).equals("Different type to RepoDto"));
    }

    @Test
    void testEquals2() {
        RepoDto repoDto = new RepoDto();
        assertTrue(repoDto.equals(repoDto));
        int expectedHashCodeResult = repoDto.hashCode();
        assertEquals(expectedHashCodeResult, repoDto.hashCode());
    }

    @Test
    void testEquals3() {
        RepoDto repoDto = new RepoDto();
        RepoDto repoDto1 = new RepoDto();
        assertTrue(repoDto.equals(repoDto1));
        int expectedHashCodeResult = repoDto.hashCode();
        assertEquals(expectedHashCodeResult, repoDto1.hashCode());
    }

    @Test
    void testEquals4() {
        LocalDateTime timeOfRepoCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branches = new ArrayList<BranchDto>();
        RepoDto repoDto = new RepoDto(1L, "Name", timeOfRepoCreation, "janedoe/featurebranch", branches,
                new ArrayList<UserDto>());
        assertFalse(repoDto.equals(new RepoDto()));
    }

    @Test
    void testEquals5() {
        RepoDto repoDto = new RepoDto();
        LocalDateTime timeOfRepoCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branches = new ArrayList<BranchDto>();
        assertFalse(repoDto.equals(
                new RepoDto(1L, "Name", timeOfRepoCreation, "janedoe/featurebranch", branches, new ArrayList<UserDto>())));
    }

    @Test
    void testEquals6() {
        RepoDto repoDto = new RepoDto();
        repoDto.setMainBranch("janedoe/featurebranch");
        assertFalse(repoDto.equals(new RepoDto()));
    }

    @Test
    void testEquals7() {
        LocalDateTime timeOfRepoCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branches = new ArrayList<BranchDto>();
        RepoDto repoDto = new RepoDto(null, "Name", timeOfRepoCreation, "janedoe/featurebranch", branches,
                new ArrayList<UserDto>());
        assertFalse(repoDto.equals(new RepoDto()));
    }

    @Test
    void testEquals8() {
        LocalDateTime timeOfRepoCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branches = new ArrayList<BranchDto>();
        RepoDto repoDto = new RepoDto(1L, "Name", timeOfRepoCreation, "janedoe/featurebranch", branches,
                new ArrayList<UserDto>());
        LocalDateTime timeOfRepoCreation1 = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branches1 = new ArrayList<BranchDto>();
        RepoDto repoDto1 = new RepoDto(1L, "Name", timeOfRepoCreation1, "janedoe/featurebranch", branches1,
                new ArrayList<UserDto>());

        assertTrue(repoDto.equals(repoDto1));
        int expectedHashCodeResult = repoDto.hashCode();
        assertEquals(expectedHashCodeResult, repoDto1.hashCode());
    }

    @Test
    void testEquals9() {
        RepoDto repoDto = new RepoDto();

        RepoDto repoDto1 = new RepoDto();
        repoDto1.setMainBranch("janedoe/featurebranch");
        assertFalse(repoDto.equals(repoDto1));
    }

    @Test
    void testEquals10() {
        RepoDto repoDto = new RepoDto();
        LocalDateTime timeOfRepoCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branches = new ArrayList<BranchDto>();
        assertFalse(repoDto.equals(
                new RepoDto(null, "Name", timeOfRepoCreation, "janedoe/featurebranch", branches, new ArrayList<UserDto>())));
    }

    @Test
    void testEquals11() {
        LocalDateTime timeOfRepoCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branches = new ArrayList<BranchDto>();
        RepoDto repoDto = new RepoDto(null, null, timeOfRepoCreation, "janedoe/featurebranch", branches,
                new ArrayList<UserDto>());
        assertFalse(repoDto.equals(new RepoDto()));
    }

    @Test
    void testEquals12() {
        ArrayList<BranchDto> branches = new ArrayList<BranchDto>();
        RepoDto repoDto = new RepoDto(1L, "Name", null, "janedoe/featurebranch", branches, new ArrayList<UserDto>());
        LocalDateTime timeOfRepoCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branches1 = new ArrayList<BranchDto>();
        assertFalse(repoDto.equals(
                new RepoDto(1L, "Name", timeOfRepoCreation, "janedoe/featurebranch", branches1, new ArrayList<UserDto>())));
    }

    @Test
    void testEquals13() {
        ArrayList<BranchDto> branchDtoList = new ArrayList<BranchDto>();
        branchDtoList.add(new BranchDto());
        LocalDateTime timeOfRepoCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        RepoDto repoDto = new RepoDto(1L, "Name", timeOfRepoCreation, "janedoe/featurebranch", branchDtoList,
                new ArrayList<UserDto>());
        LocalDateTime timeOfRepoCreation1 = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branches = new ArrayList<BranchDto>();
        assertFalse(repoDto.equals(
                new RepoDto(1L, "Name", timeOfRepoCreation1, "janedoe/featurebranch", branches, new ArrayList<UserDto>())));
    }

    @Test
    void testEquals14() {
        ArrayList<UserDto> userDtoList = new ArrayList<UserDto>();
        userDtoList.add(new UserDto("Jane", "Second Name", "Doe", "janedoe", "jane.doe@example.org", "4105551212", "Role"));
        LocalDateTime timeOfRepoCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        RepoDto repoDto = new RepoDto(1L, "Name", timeOfRepoCreation, "janedoe/featurebranch", new ArrayList<BranchDto>(),
                userDtoList);
        LocalDateTime timeOfRepoCreation1 = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<BranchDto> branches = new ArrayList<BranchDto>();
        assertFalse(repoDto.equals(
                new RepoDto(1L, "Name", timeOfRepoCreation1, "janedoe/featurebranch", branches, new ArrayList<UserDto>())));
    }
}

