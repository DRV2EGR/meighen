package ru.meighgen.presenter.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RepoShortDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new RepoShortDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        RepoShortDto repoShortDto = new RepoShortDto();
        assertTrue(repoShortDto.canEqual(new RepoShortDto()));
    }

    @Test
    void testConstructor() {
        RepoShortDto actualRepoShortDto = new RepoShortDto();
        actualRepoShortDto.setID(1L);
        actualRepoShortDto.setName("Name");
        assertEquals(1L, actualRepoShortDto.getID().longValue());
        assertEquals("Name", actualRepoShortDto.getName());
        assertEquals("RepoShortDto(ID=1, name=Name)", actualRepoShortDto.toString());
    }

    @Test
    void testConstructor2() {
        RepoShortDto actualRepoShortDto = new RepoShortDto(1L, "Name");
        actualRepoShortDto.setID(1L);
        actualRepoShortDto.setName("Name");
        assertEquals(1L, actualRepoShortDto.getID().longValue());
        assertEquals("Name", actualRepoShortDto.getName());
        assertEquals("RepoShortDto(ID=1, name=Name)", actualRepoShortDto.toString());
    }

    @Test
    void testEquals() {
        assertFalse((new RepoShortDto()).equals(null));
        assertFalse((new RepoShortDto()).equals("Different type to RepoShortDto"));
    }

    @Test
    void testEquals2() {
        RepoShortDto repoShortDto = new RepoShortDto();
        assertTrue(repoShortDto.equals(repoShortDto));
        int expectedHashCodeResult = repoShortDto.hashCode();
        assertEquals(expectedHashCodeResult, repoShortDto.hashCode());
    }

    @Test
    void testEquals3() {
        RepoShortDto repoShortDto = new RepoShortDto();
        RepoShortDto repoShortDto1 = new RepoShortDto();
        assertTrue(repoShortDto.equals(repoShortDto1));
        int expectedHashCodeResult = repoShortDto.hashCode();
        assertEquals(expectedHashCodeResult, repoShortDto1.hashCode());
    }

    @Test
    void testEquals4() {
        RepoShortDto repoShortDto = new RepoShortDto(1L, "Name");
        assertFalse(repoShortDto.equals(new RepoShortDto()));
    }

    @Test
    void testEquals5() {
        RepoShortDto repoShortDto = new RepoShortDto();
        assertFalse(repoShortDto.equals(new RepoShortDto(1L, "Name")));
    }

    @Test
    void testEquals6() {
        RepoShortDto repoShortDto = new RepoShortDto(null, "Name");
        assertFalse(repoShortDto.equals(new RepoShortDto()));
    }

    @Test
    void testEquals7() {
        RepoShortDto repoShortDto = new RepoShortDto(1L, "Name");
        RepoShortDto repoShortDto1 = new RepoShortDto(1L, "Name");

        assertTrue(repoShortDto.equals(repoShortDto1));
        int expectedHashCodeResult = repoShortDto.hashCode();
        assertEquals(expectedHashCodeResult, repoShortDto1.hashCode());
    }

    @Test
    void testEquals8() {
        RepoShortDto repoShortDto = new RepoShortDto();
        assertFalse(repoShortDto.equals(new RepoShortDto(null, "Name")));
    }
}

