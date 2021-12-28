package ru.meighgen.presenter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class CustomSerializerServiceTest {
    @Test
    void testSerialize() {
        byte[] actualSerializeResult = (new CustomSerializerService()).serialize("Topic", "Data");
        assertEquals(6, actualSerializeResult.length);
        assertEquals('"', actualSerializeResult[0]);
        assertEquals('D', actualSerializeResult[1]);
        assertEquals('a', actualSerializeResult[2]);
        assertEquals('t', actualSerializeResult[3]);
        assertEquals('a', actualSerializeResult[4]);
        assertEquals('"', actualSerializeResult[5]);
    }

    @Test
    void testSerialize2() {
        byte[] actualSerializeResult = (new CustomSerializerService()).serialize("Topic", 0);
        assertEquals(1, actualSerializeResult.length);
        assertEquals('0', actualSerializeResult[0]);
    }

    @Test
    void testSerialize3() {
        assertNull((new CustomSerializerService()).serialize("Topic", null));
    }

    @Test
    void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CustomSerializerService.objectMapper

        CustomSerializerService actualCustomSerializerService = new CustomSerializerService();
        actualCustomSerializerService.close();
        actualCustomSerializerService.configure(new HashMap<String, Object>(1), true);
    }
}

