package ru.meighgen.presenter.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class BranchDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new BranchDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        BranchDto branchDto = new BranchDto();
        assertTrue(branchDto.canEqual(new BranchDto()));
    }

    @Test
    void testConstructor() {
        BranchDto actualBranchDto = new BranchDto();
        actualBranchDto.setBranchId(123L);
        ArrayList<CommitDto> commitDtoList = new ArrayList<CommitDto>();
        actualBranchDto.setCommits(commitDtoList);
        actualBranchDto.setCreatorId(123L);
        actualBranchDto.setHead("Head");
        actualBranchDto.setName("Name");
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualBranchDto.setTimeOfBranchCreation(ofResult);
        assertEquals(123L, actualBranchDto.getBranchId().longValue());
        assertSame(commitDtoList, actualBranchDto.getCommits());
        assertEquals(123L, actualBranchDto.getCreatorId().longValue());
        assertEquals("Head", actualBranchDto.getHead());
        assertEquals("Name", actualBranchDto.getName());
        assertSame(ofResult, actualBranchDto.getTimeOfBranchCreation());
        assertEquals("BranchDto(branchId=123, creatorId=123, name=Name, timeOfBranchCreation=0001-01-01T01:01, commits=[],"
                + " head=Head)", actualBranchDto.toString());
    }

    @Test
    void testConstructor2() {
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        ArrayList<CommitDto> commitDtoList = new ArrayList<CommitDto>();
        BranchDto actualBranchDto = new BranchDto(123L, 123L, "Name", timeOfBranchCreation, commitDtoList, "Head");
        actualBranchDto.setBranchId(123L);
        ArrayList<CommitDto> commitDtoList1 = new ArrayList<CommitDto>();
        actualBranchDto.setCommits(commitDtoList1);
        actualBranchDto.setCreatorId(123L);
        actualBranchDto.setHead("Head");
        actualBranchDto.setName("Name");
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualBranchDto.setTimeOfBranchCreation(ofResult);
        assertEquals(123L, actualBranchDto.getBranchId().longValue());
        List<CommitDto> commits = actualBranchDto.getCommits();
        assertSame(commitDtoList1, commits);
        assertEquals(commitDtoList, commits);
        assertEquals(123L, actualBranchDto.getCreatorId().longValue());
        assertEquals("Head", actualBranchDto.getHead());
        assertEquals("Name", actualBranchDto.getName());
        assertSame(ofResult, actualBranchDto.getTimeOfBranchCreation());
        assertEquals("BranchDto(branchId=123, creatorId=123, name=Name, timeOfBranchCreation=0001-01-01T01:01, commits=[],"
                + " head=Head)", actualBranchDto.toString());
    }

    @Test
    void testEquals() {
        assertFalse((new BranchDto()).equals(null));
        assertFalse((new BranchDto()).equals("Different type to BranchDto"));
    }

    @Test
    void testEquals2() {
        BranchDto branchDto = new BranchDto();
        assertTrue(branchDto.equals(branchDto));
        int expectedHashCodeResult = branchDto.hashCode();
        assertEquals(expectedHashCodeResult, branchDto.hashCode());
    }

    @Test
    void testEquals3() {
        BranchDto branchDto = new BranchDto();
        BranchDto branchDto1 = new BranchDto();
        assertTrue(branchDto.equals(branchDto1));
        int expectedHashCodeResult = branchDto.hashCode();
        assertEquals(expectedHashCodeResult, branchDto1.hashCode());
    }

    @Test
    void testEquals4() {
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        BranchDto branchDto = new BranchDto(123L, 123L, "Name", timeOfBranchCreation, new ArrayList<CommitDto>(), "Head");
        assertFalse(branchDto.equals(new BranchDto()));
    }

    @Test
    void testEquals5() {
        BranchDto branchDto = new BranchDto();
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        assertFalse(
                branchDto.equals(new BranchDto(123L, 123L, "Name", timeOfBranchCreation, new ArrayList<CommitDto>(), "Head")));
    }

    @Test
    void testEquals6() {
        BranchDto branchDto = new BranchDto();
        branchDto.setCreatorId(123L);
        assertFalse(branchDto.equals(new BranchDto()));
    }

    @Test
    void testEquals7() {
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        BranchDto branchDto = new BranchDto(123L, 123L, "Name", timeOfBranchCreation, new ArrayList<CommitDto>(), "Head");
        LocalDateTime timeOfBranchCreation1 = LocalDateTime.of(1, 1, 1, 1, 1);
        BranchDto branchDto1 = new BranchDto(123L, 123L, "Name", timeOfBranchCreation1, new ArrayList<CommitDto>(), "Head");

        assertTrue(branchDto.equals(branchDto1));
        int expectedHashCodeResult = branchDto.hashCode();
        assertEquals(expectedHashCodeResult, branchDto1.hashCode());
    }

    @Test
    void testEquals8() {
        BranchDto branchDto = new BranchDto();

        BranchDto branchDto1 = new BranchDto();
        branchDto1.setCreatorId(123L);
        assertFalse(branchDto.equals(branchDto1));
    }

    @Test
    void testEquals9() {
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        BranchDto branchDto = new BranchDto(123L, 123L, "Head", timeOfBranchCreation, new ArrayList<CommitDto>(), "Head");
        LocalDateTime timeOfBranchCreation1 = LocalDateTime.of(1, 1, 1, 1, 1);
        assertFalse(
                branchDto.equals(new BranchDto(123L, 123L, "Name", timeOfBranchCreation1, new ArrayList<CommitDto>(), "Head")));
    }

    @Test
    void testEquals10() {
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        BranchDto branchDto = new BranchDto(123L, 123L, null, timeOfBranchCreation, new ArrayList<CommitDto>(), "Head");
        LocalDateTime timeOfBranchCreation1 = LocalDateTime.of(1, 1, 1, 1, 1);
        assertFalse(
                branchDto.equals(new BranchDto(123L, 123L, "Name", timeOfBranchCreation1, new ArrayList<CommitDto>(), "Head")));
    }

    @Test
    void testEquals11() {
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(0, 1, 1, 1, 1);
        BranchDto branchDto = new BranchDto(123L, 123L, "Name", timeOfBranchCreation, new ArrayList<CommitDto>(), "Head");
        LocalDateTime timeOfBranchCreation1 = LocalDateTime.of(1, 1, 1, 1, 1);
        assertFalse(
                branchDto.equals(new BranchDto(123L, 123L, "Name", timeOfBranchCreation1, new ArrayList<CommitDto>(), "Head")));
    }

    @Test
    void testEquals12() {
        BranchDto branchDto = new BranchDto(123L, 123L, "Name", null, new ArrayList<CommitDto>(), "Head");
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        assertFalse(
                branchDto.equals(new BranchDto(123L, 123L, "Name", timeOfBranchCreation, new ArrayList<CommitDto>(), "Head")));
    }

    @Test
    void testEquals13() {
        ArrayList<CommitDto> commitDtoList = new ArrayList<CommitDto>();
        commitDtoList.add(new CommitDto("42", "Not all who wander are lost", "Next"));
        BranchDto branchDto = new BranchDto(123L, 123L, "Name", LocalDateTime.of(1, 1, 1, 1, 1), commitDtoList, "Head");
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        assertFalse(
                branchDto.equals(new BranchDto(123L, 123L, "Name", timeOfBranchCreation, new ArrayList<CommitDto>(), "Head")));
    }

    @Test
    void testEquals14() {
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        BranchDto branchDto = new BranchDto(123L, 123L, "Name", timeOfBranchCreation, new ArrayList<CommitDto>(), "Name");
        LocalDateTime timeOfBranchCreation1 = LocalDateTime.of(1, 1, 1, 1, 1);
        assertFalse(
                branchDto.equals(new BranchDto(123L, 123L, "Name", timeOfBranchCreation1, new ArrayList<CommitDto>(), "Head")));
    }

    @Test
    void testEquals15() {
        LocalDateTime timeOfBranchCreation = LocalDateTime.of(1, 1, 1, 1, 1);
        BranchDto branchDto = new BranchDto(123L, 123L, "Name", timeOfBranchCreation, new ArrayList<CommitDto>(), null);
        LocalDateTime timeOfBranchCreation1 = LocalDateTime.of(1, 1, 1, 1, 1);
        assertFalse(
                branchDto.equals(new BranchDto(123L, 123L, "Name", timeOfBranchCreation1, new ArrayList<CommitDto>(), "Head")));
    }
}

