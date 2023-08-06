package com.hp.backend.model.Session.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MentorSessionDTOTest {
    @Test
    void testBuilder() {
        String sessionName = "Sample Session";
        int duration = 60;
        float price = 100.0f;
        String description = "This is a sample session description.";
        int status = 1;
        int session_id = 1;
        String skill_name = "C#";

        MentorSessionDTO mentorSessionDTO = MentorSessionDTO.builder().session_id(session_id).session_name(sessionName)
                .skill_name(skill_name).status(status).description(description).duration(duration).price(price).build();

        Assertions.assertEquals(session_id, mentorSessionDTO.getSession_id());
        Assertions.assertEquals(sessionName, mentorSessionDTO.getSession_name());
        Assertions.assertEquals(duration, mentorSessionDTO.getDuration());
        Assertions.assertEquals(price, mentorSessionDTO.getPrice());
        Assertions.assertEquals(description, mentorSessionDTO.getDescription());
        Assertions.assertEquals(skill_name, mentorSessionDTO.getSkill_name());
        Assertions.assertEquals(status, mentorSessionDTO.getStatus());

    }

    @Test
    void testCanEqual() {
        MentorSessionDTO mentorSessionDTO = MentorSessionDTO.builder().session_id(1).session_name("Sample Session")
                .skill_name("C#").status(1).description("This is a sample session description.").duration(60)
                .price(100.0f).build();

        assertFalse(mentorSessionDTO.canEqual(null));
        assertFalse(mentorSessionDTO.canEqual("string"));
        assertTrue(mentorSessionDTO.canEqual(mentorSessionDTO));

    }

    @Test
    void testEquals() {
        MentorSessionDTO mentorSessionDTO1 = MentorSessionDTO.builder().session_id(1).session_name("Sample Session 1 ")
                .skill_name("C#").status(1).description("This is a sample session description.").duration(60)
                .price(100.0f).build();

        MentorSessionDTO mentorSessionDTO2 = MentorSessionDTO.builder().session_id(1).session_name("Sample Session 1 ")
                .skill_name("C#").status(1).description("This is a sample session description.").duration(60)
                .price(100.0f).build();

        MentorSessionDTO mentorSessionDTO3 = MentorSessionDTO.builder().session_id(1).session_name("Sample Session 3")
                .skill_name("C++").status(2).description("This is a sample session description 2.").duration(89)
                .price(110.0f).build();

        Assertions.assertEquals(mentorSessionDTO1, mentorSessionDTO2);
        Assertions.assertNotEquals(mentorSessionDTO1, mentorSessionDTO3);

    }

    @Test
    void testGetDescription() {
        // Create an instance of MentorSessionDTO
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        String description = "This is a sample session description.";
        mentorSessionDTO.setDescription(description);

        // Test the getDescription() method
        assertEquals(description, mentorSessionDTO.getDescription());
    }

    @Test
    void testGetDuration() {
        int duration = 60;
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setDuration(duration);

        assertEquals(duration, mentorSessionDTO.getDuration());
    }

    @Test
    void testGetPrice() {
        float price = 100.0f;
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setPrice(price);

        assertEquals(price, mentorSessionDTO.getPrice(), 0.01);
    }

    @Test
    void testGetSession_id() {
        int sessionId = 1;
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setSession_id(sessionId);

        assertEquals(sessionId, mentorSessionDTO.getSession_id());
    }

    @Test
    void testGetSession_name() {
        String sessionName = "Sample Session";
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setSession_name(sessionName);

        assertEquals(sessionName, mentorSessionDTO.getSession_name());
    }

    @Test
    void testGetSkill_name() {
        String skillName = "C#";
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setSkill_name(skillName);

        assertEquals(skillName, mentorSessionDTO.getSkill_name());
    }

    @Test
    void testGetStatus() {
        int status = 1;
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setStatus(status);

        assertEquals(status, mentorSessionDTO.getStatus());
    }

    @Test
    void testHashCode() {
        MentorSessionDTO mentorSessionDTO1 = new MentorSessionDTO();
        MentorSessionDTO mentorSessionDTO2 = new MentorSessionDTO();

        // Two objects with the same content should have the same hash code
        assertEquals(mentorSessionDTO1.hashCode(), mentorSessionDTO2.hashCode());
    }

    @Test
    void testSetDescription() {
        String description = "This is a sample session description.";
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setDescription(description);

        assertEquals(description, mentorSessionDTO.getDescription());
    }

    @Test
    void testSetDuration() {
        // Create an instance of MentorSessionDTO
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        int duration = 60;
        mentorSessionDTO.setDuration(duration);

        // Test the setDuration() and getDuration() methods
        assertEquals(duration, mentorSessionDTO.getDuration());
    }

    @Test
    void testSetPrice() {
        // Create an instance of MentorSessionDTO
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        float price = 100.0f;
        mentorSessionDTO.setPrice(price);

        // Test the setPrice() and getPrice() methods
        assertEquals(price, mentorSessionDTO.getPrice());
    }

    @Test
    void testSetSession_id() {
        int sessionId = 1;
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setSession_id(sessionId);

        assertEquals(sessionId, mentorSessionDTO.getSession_id());
    }

    @Test
    void testSetSession_name() {
        String sessionName = "Sample Session";
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setSession_name(sessionName);

        assertEquals(sessionName, mentorSessionDTO.getSession_name());
    }

    @Test
    void testSetSkill_name() {
        String skillName = "Programming";
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setSkill_name(skillName);

        assertEquals(skillName, mentorSessionDTO.getSkill_name());
    }

    @Test
    void testSetStatus() {
        int status = 1;
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setStatus(status);

        assertEquals(status, mentorSessionDTO.getStatus());
    }

    @Test
    void testToString() {
        MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setSession_name("Sample Session");
        mentorSessionDTO.setDuration(60);
        mentorSessionDTO.setPrice(100.0f);

        String expectedToString = "MentorSessionDTO(session_name=Sample Session, session_id=0, skill_name=null, duration=60, description=null, price=100.0, status=0)";
        assertEquals(expectedToString, mentorSessionDTO.toString());
    }
}
