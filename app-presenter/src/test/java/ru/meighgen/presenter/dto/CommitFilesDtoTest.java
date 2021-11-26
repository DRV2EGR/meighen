package ru.meighgen.presenter.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CommitFilesDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new CommitFilesDto("Name", "42")).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        CommitFilesDto commitFilesDto = new CommitFilesDto("Name", "42");
        assertTrue(commitFilesDto.canEqual(new CommitFilesDto("Name", "42")));
    }

    @Test
    void testConstructor() {
        CommitFilesDto actualCommitFilesDto = new CommitFilesDto();
        actualCommitFilesDto.setFileId("42");
        actualCommitFilesDto.setName("Name");
        assertEquals("42", actualCommitFilesDto.getFileId());
        assertEquals("Name", actualCommitFilesDto.getName());
        assertEquals("CommitFilesDto(name=Name, fileId=42)", actualCommitFilesDto.toString());
    }

    @Test
    void testConstructor2() {
        CommitFilesDto actualCommitFilesDto = new CommitFilesDto("Name", "42");
        actualCommitFilesDto.setFileId("42");
        actualCommitFilesDto.setName("Name");
        assertEquals("42", actualCommitFilesDto.getFileId());
        assertEquals("Name", actualCommitFilesDto.getName());
        assertEquals("CommitFilesDto(name=Name, fileId=42)", actualCommitFilesDto.toString());
    }

    @Test
    void testEquals() {
        assertFalse((new CommitFilesDto("Name", "42")).equals(null));
        assertFalse((new CommitFilesDto("Name", "42")).equals("Different type to CommitFilesDto"));
    }

    @Test
    void testEquals2() {
        CommitFilesDto commitFilesDto = new CommitFilesDto("Name", "42");
        assertTrue(commitFilesDto.equals(commitFilesDto));
        int expectedHashCodeResult = commitFilesDto.hashCode();
        assertEquals(expectedHashCodeResult, commitFilesDto.hashCode());
    }

    @Test
    void testEquals3() {
        CommitFilesDto commitFilesDto = new CommitFilesDto("Name", "42");
        CommitFilesDto commitFilesDto1 = new CommitFilesDto("Name", "42");

        assertTrue(commitFilesDto.equals(commitFilesDto1));
        int expectedHashCodeResult = commitFilesDto.hashCode();
        assertEquals(expectedHashCodeResult, commitFilesDto1.hashCode());
    }

    @Test
    void testEquals4() {
        CommitFilesDto commitFilesDto = new CommitFilesDto("42", "42");
        assertFalse(commitFilesDto.equals(new CommitFilesDto("Name", "42")));
    }

    @Test
    void testEquals5() {
        CommitFilesDto commitFilesDto = new CommitFilesDto(null, "42");
        assertFalse(commitFilesDto.equals(new CommitFilesDto("Name", "42")));
    }

    @Test
    void testEquals6() {
        CommitFilesDto commitFilesDto = new CommitFilesDto("Name", "Name");
        assertFalse(commitFilesDto.equals(new CommitFilesDto("Name", "42")));
    }

    @Test
    void testEquals7() {
        CommitFilesDto commitFilesDto = new CommitFilesDto("Name", null);
        assertFalse(commitFilesDto.equals(new CommitFilesDto("Name", "42")));
    }

    @Test
    void testEquals8() {
        CommitFilesDto commitFilesDto = new CommitFilesDto(null, "42");
        CommitFilesDto commitFilesDto1 = new CommitFilesDto(null, "42");

        assertTrue(commitFilesDto.equals(commitFilesDto1));
        int expectedHashCodeResult = commitFilesDto.hashCode();
        assertEquals(expectedHashCodeResult, commitFilesDto1.hashCode());
    }

    @Test
    void testEquals9() {
        CommitFilesDto commitFilesDto = new CommitFilesDto("Name", null);
        CommitFilesDto commitFilesDto1 = new CommitFilesDto("Name", null);

        assertTrue(commitFilesDto.equals(commitFilesDto1));
        int expectedHashCodeResult = commitFilesDto.hashCode();
        assertEquals(expectedHashCodeResult, commitFilesDto1.hashCode());
    }
}

