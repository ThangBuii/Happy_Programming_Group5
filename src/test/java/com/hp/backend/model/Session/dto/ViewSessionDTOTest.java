package com.hp.backend.model.Session.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ViewSessionDTOTest {
    @Test
    void testBuilder() {
        ViewSessionDTO viewSessionDTO = ViewSessionDTO.builder()
                .session_name("Sample Session")
                .duration(60)
                .price(100.0f)
                .build();

        assertEquals("Sample Session", viewSessionDTO.getSession_name());
        assertEquals(60, viewSessionDTO.getDuration());
        assertEquals(100.0f, viewSessionDTO.getPrice(), 0.01);
    }

    @Test
    void testCanEqual() {
        ViewSessionDTO viewSessionDTO1 = ViewSessionDTO.builder()
                .session_name("Sample Session")
                .build();

        ViewSessionDTO viewSessionDTO2 = ViewSessionDTO.builder()
                .session_name("Sample Session")
                .build();

        assertTrue(viewSessionDTO1.canEqual(viewSessionDTO2));

        ViewSessionDTO viewSessionDTO3 = ViewSessionDTO.builder()
                .session_name("Another Session")
                .build();

        assertTrue(viewSessionDTO1.canEqual(viewSessionDTO3));
    }

    @Test
    void testEquals() {
        ViewSessionDTO viewSessionDTO1 = ViewSessionDTO.builder()
                .session_name("Sample Session")
                .build();

        ViewSessionDTO viewSessionDTO2 = ViewSessionDTO.builder()
                .session_name("Sample Session")
                .build();

        assertEquals(viewSessionDTO1, viewSessionDTO2);

        ViewSessionDTO viewSessionDTO3 = ViewSessionDTO.builder()
                .session_name("Another Session")
                .build();

        assertNotEquals(viewSessionDTO1, viewSessionDTO3);
    }

    @Test
    void testGetAvatar() {
        String avatar = "avatar.jpg";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setAvatar(avatar);

        assertEquals(avatar, viewSessionDTO.getAvatar());
    }

    @Test
    void testGetDescription() {
        String description = "This is a sample session description.";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setDescription(description);

        assertEquals(description, viewSessionDTO.getDescription());
    }

    @Test
    void testGetDuration() {
        int duration = 60;
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setDuration(duration);

        assertEquals(duration, viewSessionDTO.getDuration());
    }

    @Test
    void testGetEmail() {
        String email = "user@example.com";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setEmail(email);

        assertEquals(email, viewSessionDTO.getEmail());
    }

    @Test
    void testGetMentor_name() {
        String mentorName = "John Doe";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setMentor_name(mentorName);

        assertEquals(mentorName, viewSessionDTO.getMentor_name());
    }

    @Test
    void testGetPrice() {
        float price = 100.0f;
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setPrice(price);

        assertEquals(price, viewSessionDTO.getPrice(), 0.01);
    }

    @Test
    void testGetSession_name() {
        String sessionName = "Sample Session";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setSession_name(sessionName);

        assertEquals(sessionName, viewSessionDTO.getSession_name());
    }

    @Test
    void testGetSkill_name() {
        String skillName = "Programming";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setSkill_name(skillName);

        assertEquals(skillName, viewSessionDTO.getSkill_name());
    }

    @Test
    void testGetStatus() {
        int status = 1;
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setStatus(status);

        assertEquals(status, viewSessionDTO.getStatus());
    }

    @Test
    void testHashCode() {
        ViewSessionDTO viewSessionDTO1 = ViewSessionDTO.builder()
                .session_name("Sample Session")
                .build();

        ViewSessionDTO viewSessionDTO2 = ViewSessionDTO.builder()
                .session_name("Sample Session")
                .build();

        assertEquals(viewSessionDTO1.hashCode(), viewSessionDTO2.hashCode());

        ViewSessionDTO viewSessionDTO3 = ViewSessionDTO.builder()
                .session_name("Another Session")
                .build();

        assertNotEquals(viewSessionDTO1.hashCode(), viewSessionDTO3.hashCode());
    }

    @Test
    void testSetAvatar() {
        String avatar = "avatar.jpg";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setAvatar(avatar);

        assertEquals(avatar, viewSessionDTO.getAvatar());
    }

    @Test
    void testSetDescription() {
        String description = "This is a sample session description.";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setDescription(description);

        assertEquals(description, viewSessionDTO.getDescription());
    }

    @Test
    void testSetDuration() {
        int duration = 60;
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setDuration(duration);

        assertEquals(duration, viewSessionDTO.getDuration());
    }

    @Test
    void testSetEmail() {
        String email = "user@example.com";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setEmail(email);

        assertEquals(email, viewSessionDTO.getEmail());
    }

    @Test
    void testSetMentor_name() {
        String mentorName = "John Doe";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setMentor_name(mentorName);

        assertEquals(mentorName, viewSessionDTO.getMentor_name());
    }

    @Test
    void testSetPrice() {
        float price = 100.0f;
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setPrice(price);

        assertEquals(price, viewSessionDTO.getPrice(), 0.01);
    }

    @Test
    void testSetSession_name() {
        String sessionName = "Sample Session";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setSession_name(sessionName);

        assertEquals(sessionName, viewSessionDTO.getSession_name());
    }

    @Test
    void testSetSkill_name() {
        String skillName = "Programming";
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setSkill_name(skillName);

        assertEquals(skillName, viewSessionDTO.getSkill_name());
    }

    @Test
    void testSetStatus() {
        int status = 1;
        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setStatus(status);

        assertEquals(status, viewSessionDTO.getStatus());
    }

    @Test
    void testToString() {
        ViewSessionDTO viewSessionDTO = ViewSessionDTO.builder()
                .session_name("Sample Session")
                .duration(60)
                .skill_name("Programming")
                .status(1)
                .mentor_name("user123")
                .avatar("avatar.jpg")
                .build();

        String expectedToString = "ViewSessionDTO(email=null, avatar=avatar.jpg, mentor_name=user123, skill_name=Programming, session_name=Sample Session, duration=60, price=0.0, description=null, status=1)";
        assertEquals(expectedToString, viewSessionDTO.toString());
    }
}
