package ru.meighgen.presenter.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CommitDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new CommitDto("42", "Not all who wander are lost", "Next")).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        CommitDto commitDto = new CommitDto("42", "Not all who wander are lost", "Next");
        assertTrue(commitDto.canEqual(new CommitDto("42", "Not all who wander are lost", "Next")));
    }

    @Test
    void testConstructor() {
        CommitDto actualCommitDto = new CommitDto();
        actualCommitDto.setCommitId("42");
        actualCommitDto.setMessage("Not all who wander are lost");
        actualCommitDto.setNext("Next");
        assertEquals("42", actualCommitDto.getCommitId());
        assertEquals("Not all who wander are lost", actualCommitDto.getMessage());
        assertEquals("Next", actualCommitDto.getNext());
        assertEquals("CommitDto(commitId=42, message=Not all who wander are lost, next=Next)", actualCommitDto.toString());
    }

    @Test
    void testConstructor2() {
        CommitDto actualCommitDto = new CommitDto("42", "Not all who wander are lost", "Next");
        actualCommitDto.setCommitId("42");
        actualCommitDto.setMessage("Not all who wander are lost");
        actualCommitDto.setNext("Next");
        assertEquals("42", actualCommitDto.getCommitId());
        assertEquals("Not all who wander are lost", actualCommitDto.getMessage());
        assertEquals("Next", actualCommitDto.getNext());
        assertEquals("CommitDto(commitId=42, message=Not all who wander are lost, next=Next)", actualCommitDto.toString());
    }

    @Test
    void testEquals() {
        assertFalse((new CommitDto("42", "Not all who wander are lost", "Next")).equals(null));
        assertFalse((new CommitDto("42", "Not all who wander are lost", "Next")).equals("Different type to CommitDto"));
    }

    @Test
    void testEquals2() {
        CommitDto commitDto = new CommitDto("42", "Not all who wander are lost", "Next");
        assertTrue(commitDto.equals(commitDto));
        int expectedHashCodeResult = commitDto.hashCode();
        assertEquals(expectedHashCodeResult, commitDto.hashCode());
    }

    @Test
    void testEquals3() {
        CommitDto commitDto = new CommitDto("42", "Not all who wander are lost", "Next");
        CommitDto commitDto1 = new CommitDto("42", "Not all who wander are lost", "Next");

        assertTrue(commitDto.equals(commitDto1));
        int expectedHashCodeResult = commitDto.hashCode();
        assertEquals(expectedHashCodeResult, commitDto1.hashCode());
    }

    @Test
    void testEquals4() {
        CommitDto commitDto = new CommitDto("Not all who wander are lost", "Not all who wander are lost", "Next");
        assertFalse(commitDto.equals(new CommitDto("42", "Not all who wander are lost", "Next")));
    }

    @Test
    void testEquals5() {
        CommitDto commitDto = new CommitDto(null, "Not all who wander are lost", "Next");
        assertFalse(commitDto.equals(new CommitDto("42", "Not all who wander are lost", "Next")));
    }

    @Test
    void testEquals6() {
        CommitDto commitDto = new CommitDto("42", "42", "Next");
        assertFalse(commitDto.equals(new CommitDto("42", "Not all who wander are lost", "Next")));
    }

    @Test
    void testEquals7() {
        CommitDto commitDto = new CommitDto("42", null, "Next");
        assertFalse(commitDto.equals(new CommitDto("42", "Not all who wander are lost", "Next")));
    }

    @Test
    void testEquals8() {
        CommitDto commitDto = new CommitDto("42", "Not all who wander are lost", "42");
        assertFalse(commitDto.equals(new CommitDto("42", "Not all who wander are lost", "Next")));
    }

    @Test
    void testEquals9() {
        CommitDto commitDto = new CommitDto("42", "Not all who wander are lost", null);
        assertFalse(commitDto.equals(new CommitDto("42", "Not all who wander are lost", "Next")));
    }

    @Test
    void testEquals10() {
        CommitDto commitDto = new CommitDto(null, "Not all who wander are lost", "Next");
        CommitDto commitDto1 = new CommitDto(null, "Not all who wander are lost", "Next");

        assertTrue(commitDto.equals(commitDto1));
        int expectedHashCodeResult = commitDto.hashCode();
        assertEquals(expectedHashCodeResult, commitDto1.hashCode());
    }

    @Test
    void testEquals11() {
        CommitDto commitDto = new CommitDto("42", null, "Next");
        CommitDto commitDto1 = new CommitDto("42", null, "Next");

        assertTrue(commitDto.equals(commitDto1));
        int expectedHashCodeResult = commitDto.hashCode();
        assertEquals(expectedHashCodeResult, commitDto1.hashCode());
    }

    @Test
    void testEquals12() {
        CommitDto commitDto = new CommitDto("42", "Not all who wander are lost", null);
        CommitDto commitDto1 = new CommitDto("42", "Not all who wander are lost", null);

        assertTrue(commitDto.equals(commitDto1));
        int expectedHashCodeResult = commitDto.hashCode();
        assertEquals(expectedHashCodeResult, commitDto1.hashCode());
    }
}

