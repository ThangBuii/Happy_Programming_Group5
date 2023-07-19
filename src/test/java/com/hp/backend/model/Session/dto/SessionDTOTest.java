package com.hp.backend.model.Session.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.hp.backend.utils.CommonUtils;

public class SessionDTOTest {
    @Mock
    CommonUtils commonUtils;
    @Test
    void testBuilder() {
        SessionDTO sessionDTO = SessionDTO.builder()
                .session_id(1)
                .session_Name("Sample Session")
                .duration(60)
                .skill_name("Programming")
                .status(1)
                .username("user123")
                .avatar("avatar.jpg")
                .build();

        assertEquals(1, sessionDTO.getSession_id());
        assertEquals("Sample Session", sessionDTO.getSession_Name());
        assertEquals(60, sessionDTO.getDuration());
        assertEquals("Programming", sessionDTO.getSkill_name());
        assertEquals(1, sessionDTO.getStatus());
        assertEquals("user123", sessionDTO.getUsername());
        assertEquals("avatar.jpg", sessionDTO.getAvatar());
    }

    @Test
    void testCanEqual() {
        SessionDTO sessionDTO1 = SessionDTO.builder()
                .session_id(1)
                .build();

        SessionDTO sessionDTO2 = SessionDTO.builder()
                .session_id(1)
                .build();

        assertTrue(sessionDTO1.canEqual(sessionDTO2));

        SessionDTO sessionDTO3 = SessionDTO.builder()
                .session_id(1)
                .build();

        assertTrue(sessionDTO1.canEqual(sessionDTO3));
    }

    @Test
    void testEquals() {
        SessionDTO sessionDTO1 = SessionDTO.builder()
                .session_id(1)
                .build();

        SessionDTO sessionDTO2 = SessionDTO.builder()
                .session_id(1)
                .build();

        assertEquals(sessionDTO1, sessionDTO2);

        SessionDTO sessionDTO3 = SessionDTO.builder()
                .session_id(2)
                .build();

        assertNotEquals(sessionDTO1, sessionDTO3);
    }

    @Test
    void testGetAvatar() {
        String avatar = "avatar.jpg";
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setAvatar(avatar);

        assertEquals(avatar, sessionDTO.getAvatar());
    }

    @Test
    void testGetDuration() {
        int duration = 60;
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setDuration(duration);

        assertEquals(duration, sessionDTO.getDuration());
    }

    @Test
    void testGetSession_Name() {
        String sessionName = "Sample Session";
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setSession_Name(sessionName);

        assertEquals(sessionName, sessionDTO.getSession_Name());
    }

    @Test
    void testGetSession_id() {
        int sessionId = 1;
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setSession_id(sessionId);

        assertEquals(sessionId, sessionDTO.getSession_id());
    }

    @Test
    void testGetSkill_name() {
        String skillName = "Programming";
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setSkill_name(skillName);

        assertEquals(skillName, sessionDTO.getSkill_name());
    }

    @Test
    void testGetStatus() {
        int status = 1;
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setStatus(status);

        assertEquals(status, sessionDTO.getStatus());
    }

    @Test
    void testGetUsername() {
        String username = "user123";
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setUsername(username);

        assertEquals(username, sessionDTO.getUsername());
    }

    @Test
    void testHashCode() {
        SessionDTO sessionDTO1 = SessionDTO.builder()
                .session_id(1)
                .build();

        SessionDTO sessionDTO2 = SessionDTO.builder()
                .session_id(1)
                .build();

        assertEquals(sessionDTO1.hashCode(), sessionDTO2.hashCode());

        SessionDTO sessionDTO3 = SessionDTO.builder()
                .session_id(2)
                .build();

        assertNotEquals(sessionDTO1.hashCode(), sessionDTO3.hashCode());
    }

    @Test
    void testSetAvatar() {
        String avatar = "avatar.jpg";
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setAvatar(avatar);

        assertEquals(avatar, sessionDTO.getAvatar());
    }

    @Test
    void testSetDuration() {
        int duration = 60;
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setDuration(duration);

        assertEquals(duration, sessionDTO.getDuration());
    }

    @Test
    void testSetSession_Name() {
        String sessionName = "Sample Session";
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setSession_Name(sessionName);

        assertEquals(sessionName, sessionDTO.getSession_Name());
    }

    @Test
    void testSetSession_id() {
        int sessionId = 1;
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setSession_id(sessionId);

        assertEquals(sessionId, sessionDTO.getSession_id());
    }

    @Test
    void testSetSkill_name() {
        String skillName = "Programming";
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setSkill_name(skillName);

        assertEquals(skillName, sessionDTO.getSkill_name());
    }

    @Test
    void testSetStatus() {
        int status = 1;
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setStatus(status);

        assertEquals(status, sessionDTO.getStatus());
    }

    @Test
    void testSetUsername() {
        String username = "user123";
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setUsername(username);

        assertEquals(username, sessionDTO.getUsername());
    }

    @Test
    void testToString() {
        String sessionName = "Sample Session";
        int duration = 60;
        String avatar = "avatar.jpg";

        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setSession_Name(sessionName);
        sessionDTO.setDuration(duration);
        sessionDTO.setAvatar(avatar);

        String expectedToString = "SessionDTO(username=null, avatar=avatar.jpg, skill_name=null, duration=60, session_Name=Sample Session, status=0, session_id=0)";
        assertEquals(expectedToString, sessionDTO.toString());
    }
}
