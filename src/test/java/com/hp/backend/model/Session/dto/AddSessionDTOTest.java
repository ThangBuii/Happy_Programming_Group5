package com.hp.backend.model.Session.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddSessionDTOTest {
    @Test
    void testBuilder() {
        int skillId = 1;
        String sessionName = "Sample Session";
        int duration = 60;
        float price = 100.0f;
        String description = "This is a sample session description.";

        AddSessionDTO addSessionDTO = AddSessionDTO.builder()
                .skill_id(skillId)
                .session_name(sessionName)
                .duration(duration)
                .price(price)
                .description(description)
                .build();

        // Verify that the builder constructs the object correctly
        Assertions.assertEquals(skillId, addSessionDTO.getSkill_id());
        Assertions.assertEquals(sessionName, addSessionDTO.getSession_name());
        Assertions.assertEquals(duration, addSessionDTO.getDuration());
        Assertions.assertEquals(price, addSessionDTO.getPrice());
        Assertions.assertEquals(description, addSessionDTO.getDescription());
    }

    @Test
    void testCanEqual() {
        // Create an instance of AddSessionDTO
        AddSessionDTO addSessionDTO = AddSessionDTO.builder()
                .skill_id(1)
                .session_name("Sample Session")
                .duration(60)
                .price(100.0f)
                .description("This is a sample session description.")
                .build();

        // Compare with different object types
        assertFalse(addSessionDTO.canEqual(null)); // Comparing with null should return false
        assertFalse(addSessionDTO.canEqual("string")); // Comparing with a different object type should return false
        assertTrue(addSessionDTO.canEqual(addSessionDTO));
    }

    @Test
    void testEquals() {
        AddSessionDTO addSessionDTO1 = AddSessionDTO.builder()
                .skill_id(1)
                .session_name("Session 1")
                .duration(60)
                .price(100.0f)
                .description("Description 1")
                .build();

        AddSessionDTO addSessionDTO2 = AddSessionDTO.builder()
                .skill_id(1)
                .session_name("Session 1")
                .duration(60)
                .price(100.0f)
                .description("Description 1")
                .build();

        AddSessionDTO addSessionDTO3 = AddSessionDTO.builder()
                .skill_id(2)
                .session_name("Session 2")
                .duration(90)
                .price(150.0f)
                .description("Description 2")
                .build();

        // Test equality
        Assertions.assertEquals(addSessionDTO1, addSessionDTO2);
        Assertions.assertNotEquals(addSessionDTO1, addSessionDTO3);
    }

    @Test
    void testGetDescription() {
        String description = "This is a sample session description.";
        AddSessionDTO addSessionDTO = new AddSessionDTO();
        addSessionDTO.setDescription(description);
        Assertions.assertEquals(description, addSessionDTO.getDescription());
    }

    @Test
    void testGetDuration() {
        int duration = 60;
        AddSessionDTO addSessionDTO = new AddSessionDTO();
        addSessionDTO.setDuration(duration);
        Assertions.assertEquals(duration, addSessionDTO.getDuration());
    }

    @Test
    void testGetPrice() {
        float price = 100.0f;
        AddSessionDTO addSessionDTO = new AddSessionDTO();
        addSessionDTO.setPrice(price);
        Assertions.assertEquals(price, addSessionDTO.getPrice());
    }

    @Test
    void testGetSession_name() {
        String sessionName = "Sample Session";
        AddSessionDTO addSessionDTO = new AddSessionDTO();
        addSessionDTO.setSession_name(sessionName);
        Assertions.assertEquals(sessionName, addSessionDTO.getSession_name());
    }

    @Test
    void testGetSkill_id() {
        int skillId = 1;
        AddSessionDTO addSessionDTO = new AddSessionDTO();
        addSessionDTO.setSkill_id(skillId);
        Assertions.assertEquals(skillId, addSessionDTO.getSkill_id());
    }

    @Test
    void testHashCode() {
        AddSessionDTO addSessionDTO1 = AddSessionDTO.builder()
                .skill_id(1)
                .session_name("Session 1")
                .duration(60)
                .price(100.0f)
                .description("Description 1")
                .build();

        AddSessionDTO addSessionDTO2 = AddSessionDTO.builder()
                .skill_id(1)
                .session_name("Session 1")
                .duration(60)
                .price(100.0f)
                .description("Description 1")
                .build();

        // Test hash code consistency
        Assertions.assertEquals(addSessionDTO1.hashCode(), addSessionDTO2.hashCode());
    }

    @Test
    void testSetDescription() {
        String description = "This is a sample session description.";
        AddSessionDTO addSessionDTO = new AddSessionDTO();
        addSessionDTO.setDescription(description);
        Assertions.assertEquals(description, addSessionDTO.getDescription());
    }

    @Test
    void testSetDuration() {
        int duration = 60;
        AddSessionDTO addSessionDTO = new AddSessionDTO();
        addSessionDTO.setDuration(duration);
        Assertions.assertEquals(duration, addSessionDTO.getDuration());
    }

    @Test
    void testSetPrice() {
        float price = 100.0f;
        AddSessionDTO addSessionDTO = new AddSessionDTO();
        addSessionDTO.setPrice(price);
        Assertions.assertEquals(price, addSessionDTO.getPrice());
    }

    @Test
    void testSetSession_name() {
        String sessionName = "Sample Session";
        AddSessionDTO addSessionDTO = new AddSessionDTO();
        addSessionDTO.setSession_name(sessionName);
        Assertions.assertEquals(sessionName, addSessionDTO.getSession_name());
    }

    @Test
    void testSetSkill_id() {
        int skillId = 1;
        AddSessionDTO addSessionDTO = new AddSessionDTO();
        addSessionDTO.setSkill_id(skillId);
        Assertions.assertEquals(skillId, addSessionDTO.getSkill_id());
    }

    @Test
    void testToString() {
        int skillId = 1;
        String sessionName = "Sample Session";
        int duration = 60;
        float price = 100.0f;
        String description = "This is a sample session description.";

        AddSessionDTO addSessionDTO = AddSessionDTO.builder()
                .skill_id(skillId)
                .session_name(sessionName)
                .duration(duration)
                .price(price)
                .description(description)
                .build();

        String expectedToString = "AddSessionDTO(skill_id=1, session_name=Sample Session, " +
                "duration=60, price=100.0, description=This is a sample session description.)";

        // Test toString()
        Assertions.assertEquals(expectedToString, addSessionDTO.toString());
    }
}
