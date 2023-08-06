package com.hp.backend.model.Session.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SessionRequestDTOTest {
    @Test
    void testBuilder() {
        SessionRequestDTO sessionRequestDTO = SessionRequestDTO.builder()
                .id(1)
                .build();

        assertEquals(1, sessionRequestDTO.getId());
    }

    @Test
    void testCanEqual() {
        SessionRequestDTO sessionRequestDTO1 = SessionRequestDTO.builder()
                .id(1)
                .build();

        SessionRequestDTO sessionRequestDTO2 = SessionRequestDTO.builder()
                .id(1)
                .build();

        assertTrue(sessionRequestDTO1.canEqual(sessionRequestDTO2));

        SessionRequestDTO sessionRequestDTO3 = SessionRequestDTO.builder()
                .id(2)
                .build();

        assertTrue(sessionRequestDTO1.canEqual(sessionRequestDTO3));
    }

    @Test
    void testEquals() {
        SessionRequestDTO sessionRequestDTO1 = SessionRequestDTO.builder()
                .id(1)
                .build();

        SessionRequestDTO sessionRequestDTO2 = SessionRequestDTO.builder()
                .id(1)
                .build();

        assertEquals(sessionRequestDTO1, sessionRequestDTO2);

        SessionRequestDTO sessionRequestDTO3 = SessionRequestDTO.builder()
                .id(2)
                .build();

        assertNotEquals(sessionRequestDTO1, sessionRequestDTO3);
    }

    @Test
    void testGetId() {
        int sessionId = 1;
        SessionRequestDTO sessionDTO = new SessionRequestDTO();
        sessionDTO.setId(sessionId);

        assertEquals(sessionId, sessionDTO.getId());
    }

    @Test
    void testHashCode() {
        SessionRequestDTO sessionRequestDTO1 = SessionRequestDTO.builder()
                .id(1)
                .build();

        SessionRequestDTO sessionRequestDTO2 = SessionRequestDTO.builder()
                .id(1)
                .build();

        assertEquals(sessionRequestDTO1.hashCode(), sessionRequestDTO2.hashCode());

        SessionRequestDTO sessionRequestDTO3 = SessionRequestDTO.builder()
                .id(2)
                .build();

        assertNotEquals(sessionRequestDTO1.hashCode(), sessionRequestDTO3.hashCode());
    }

    @Test
    void testSetId() {
        int id = 1;
        SessionRequestDTO sessionRequestDTO = new SessionRequestDTO();
        sessionRequestDTO.setId(id);

        assertEquals(id, sessionRequestDTO.getId());
    }

    @Test
    void testToString() {
        int id = 1;
        SessionRequestDTO sessionRequestDTO = new SessionRequestDTO();
        sessionRequestDTO.setId(id);

        String expectedToString = "SessionRequestDTO(id=1)";
        assertEquals(expectedToString, sessionRequestDTO.toString());
    }
}
