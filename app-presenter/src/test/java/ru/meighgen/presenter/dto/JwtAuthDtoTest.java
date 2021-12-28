package ru.meighgen.presenter.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class JwtAuthDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new JwtAuthDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername("janedoe");
        assertTrue(jwtAuthDto.canEqual(jwtAuthDto1));
    }

    @Test
    void testConstructor() {
        JwtAuthDto actualJwtAuthDto = new JwtAuthDto();
        actualJwtAuthDto.setAccessToken("ABC123");
        actualJwtAuthDto.setRefreshToken("ABC123");
        actualJwtAuthDto.setUsername("janedoe");
        assertEquals("ABC123", actualJwtAuthDto.getAccessToken());
        assertEquals("ABC123", actualJwtAuthDto.getRefreshToken());
        assertEquals("janedoe", actualJwtAuthDto.getUsername());
        assertEquals("JwtAuthDto(username=janedoe, accessToken=ABC123, refreshToken=ABC123)", actualJwtAuthDto.toString());
    }

    @Test
    void testEquals() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername("janedoe");
        assertFalse(jwtAuthDto.equals(null));
    }

    @Test
    void testEquals2() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername("janedoe");
        assertFalse(jwtAuthDto.equals("Different type to JwtAuthDto"));
    }

    @Test
    void testEquals3() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername("janedoe");
        assertTrue(jwtAuthDto.equals(jwtAuthDto));
        int expectedHashCodeResult = jwtAuthDto.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthDto.hashCode());
    }

    @Test
    void testEquals4() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername("janedoe");

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername("janedoe");
        assertTrue(jwtAuthDto.equals(jwtAuthDto1));
        int expectedHashCodeResult = jwtAuthDto.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthDto1.hashCode());
    }

    @Test
    void testEquals5() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("janedoe");
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername("janedoe");

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername("janedoe");
        assertFalse(jwtAuthDto.equals(jwtAuthDto1));
    }

    @Test
    void testEquals6() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken(null);
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername("janedoe");

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername("janedoe");
        assertFalse(jwtAuthDto.equals(jwtAuthDto1));
    }

    @Test
    void testEquals7() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken("ExampleToken");
        jwtAuthDto.setUsername("janedoe");

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername("janedoe");
        assertFalse(jwtAuthDto.equals(jwtAuthDto1));
    }

    @Test
    void testEquals8() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken(null);
        jwtAuthDto.setUsername("janedoe");

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername("janedoe");
        assertFalse(jwtAuthDto.equals(jwtAuthDto1));
    }

    @Test
    void testEquals9() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername("ABC123");

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername("janedoe");
        assertFalse(jwtAuthDto.equals(jwtAuthDto1));
    }

    @Test
    void testEquals10() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername(null);

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername("janedoe");
        assertFalse(jwtAuthDto.equals(jwtAuthDto1));
    }

    @Test
    void testEquals11() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken(null);
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername("janedoe");

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken(null);
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername("janedoe");
        assertTrue(jwtAuthDto.equals(jwtAuthDto1));
        int expectedHashCodeResult = jwtAuthDto.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthDto1.hashCode());
    }

    @Test
    void testEquals12() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken(null);
        jwtAuthDto.setUsername("janedoe");

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken(null);
        jwtAuthDto1.setUsername("janedoe");
        assertTrue(jwtAuthDto.equals(jwtAuthDto1));
        int expectedHashCodeResult = jwtAuthDto.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthDto1.hashCode());
    }

    @Test
    void testEquals13() {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername(null);

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername(null);
        assertTrue(jwtAuthDto.equals(jwtAuthDto1));
        int expectedHashCodeResult = jwtAuthDto.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthDto1.hashCode());
    }
}

