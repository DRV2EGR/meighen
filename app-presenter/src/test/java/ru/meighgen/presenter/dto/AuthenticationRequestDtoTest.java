package ru.meighgen.presenter.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AuthenticationRequestDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new AuthenticationRequestDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();

        AuthenticationRequestDto authenticationRequestDto1 = new AuthenticationRequestDto();
        authenticationRequestDto1.setEmail("jane.doe@example.org");
        authenticationRequestDto1.setPassword("iloveyou");
        assertTrue(authenticationRequestDto.canEqual(authenticationRequestDto1));
    }

    @Test
    void testConstructor() {
        AuthenticationRequestDto actualAuthenticationRequestDto = new AuthenticationRequestDto();
        actualAuthenticationRequestDto.setEmail("jane.doe@example.org");
        actualAuthenticationRequestDto.setPassword("iloveyou");
        assertEquals("jane.doe@example.org", actualAuthenticationRequestDto.getEmail());
        assertEquals("iloveyou", actualAuthenticationRequestDto.getPassword());
        assertEquals("AuthenticationRequestDto(email=jane.doe@example.org, password=iloveyou)",
                actualAuthenticationRequestDto.toString());
    }

    @Test
    void testEquals() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("jane.doe@example.org");
        authenticationRequestDto.setPassword("iloveyou");
        assertFalse(authenticationRequestDto.equals(null));
    }

    @Test
    void testEquals2() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("jane.doe@example.org");
        authenticationRequestDto.setPassword("iloveyou");
        assertFalse(authenticationRequestDto.equals("Different type to AuthenticationRequestDto"));
    }

    @Test
    void testEquals3() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("jane.doe@example.org");
        authenticationRequestDto.setPassword("iloveyou");
        assertTrue(authenticationRequestDto.equals(authenticationRequestDto));
        int expectedHashCodeResult = authenticationRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, authenticationRequestDto.hashCode());
    }

    @Test
    void testEquals4() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("jane.doe@example.org");
        authenticationRequestDto.setPassword("iloveyou");

        AuthenticationRequestDto authenticationRequestDto1 = new AuthenticationRequestDto();
        authenticationRequestDto1.setEmail("jane.doe@example.org");
        authenticationRequestDto1.setPassword("iloveyou");
        assertTrue(authenticationRequestDto.equals(authenticationRequestDto1));
        int expectedHashCodeResult = authenticationRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, authenticationRequestDto1.hashCode());
    }

    @Test
    void testEquals5() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("iloveyou");
        authenticationRequestDto.setPassword("iloveyou");

        AuthenticationRequestDto authenticationRequestDto1 = new AuthenticationRequestDto();
        authenticationRequestDto1.setEmail("jane.doe@example.org");
        authenticationRequestDto1.setPassword("iloveyou");
        assertFalse(authenticationRequestDto.equals(authenticationRequestDto1));
    }

    @Test
    void testEquals6() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail(null);
        authenticationRequestDto.setPassword("iloveyou");

        AuthenticationRequestDto authenticationRequestDto1 = new AuthenticationRequestDto();
        authenticationRequestDto1.setEmail("jane.doe@example.org");
        authenticationRequestDto1.setPassword("iloveyou");
        assertFalse(authenticationRequestDto.equals(authenticationRequestDto1));
    }

    @Test
    void testEquals7() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("jane.doe@example.org");
        authenticationRequestDto.setPassword("jane.doe@example.org");

        AuthenticationRequestDto authenticationRequestDto1 = new AuthenticationRequestDto();
        authenticationRequestDto1.setEmail("jane.doe@example.org");
        authenticationRequestDto1.setPassword("iloveyou");
        assertFalse(authenticationRequestDto.equals(authenticationRequestDto1));
    }

    @Test
    void testEquals8() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("jane.doe@example.org");
        authenticationRequestDto.setPassword(null);

        AuthenticationRequestDto authenticationRequestDto1 = new AuthenticationRequestDto();
        authenticationRequestDto1.setEmail("jane.doe@example.org");
        authenticationRequestDto1.setPassword("iloveyou");
        assertFalse(authenticationRequestDto.equals(authenticationRequestDto1));
    }

    @Test
    void testEquals9() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail(null);
        authenticationRequestDto.setPassword("iloveyou");

        AuthenticationRequestDto authenticationRequestDto1 = new AuthenticationRequestDto();
        authenticationRequestDto1.setEmail(null);
        authenticationRequestDto1.setPassword("iloveyou");
        assertTrue(authenticationRequestDto.equals(authenticationRequestDto1));
        int expectedHashCodeResult = authenticationRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, authenticationRequestDto1.hashCode());
    }

    @Test
    void testEquals10() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("jane.doe@example.org");
        authenticationRequestDto.setPassword(null);

        AuthenticationRequestDto authenticationRequestDto1 = new AuthenticationRequestDto();
        authenticationRequestDto1.setEmail("jane.doe@example.org");
        authenticationRequestDto1.setPassword(null);
        assertTrue(authenticationRequestDto.equals(authenticationRequestDto1));
        int expectedHashCodeResult = authenticationRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, authenticationRequestDto1.hashCode());
    }
}

