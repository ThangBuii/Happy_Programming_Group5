package com.hp.backend.model.Skills.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Mentor_SkillsRequestDTOTest {
    @Test
    void testBuilder() {
        Mentor_SkillsRequestDTO mentorSkillsRequestDTO = Mentor_SkillsRequestDTO.builder()
                .mentor_id(1)
                .build();

        assertEquals(1, mentorSkillsRequestDTO.getMentor_id());
    }

    @Test
    void testCanEqual() {
        Mentor_SkillsRequestDTO mentorSkillsRequestDTO1 = Mentor_SkillsRequestDTO.builder()
                .mentor_id(1)
                .build();

        Mentor_SkillsRequestDTO mentorSkillsRequestDTO2 = Mentor_SkillsRequestDTO.builder()
                .mentor_id(1)
                .build();

        assertTrue(mentorSkillsRequestDTO1.canEqual(mentorSkillsRequestDTO2));

        Mentor_SkillsRequestDTO mentorSkillsRequestDTO3 = Mentor_SkillsRequestDTO.builder()
                .mentor_id(2)
                .build();

        assertTrue(mentorSkillsRequestDTO1.canEqual(mentorSkillsRequestDTO3));
    }

    @Test
    void testEquals() {
        Mentor_SkillsRequestDTO mentorSkillsRequestDTO1 = Mentor_SkillsRequestDTO.builder()
                .mentor_id(1)
                .build();

        Mentor_SkillsRequestDTO mentorSkillsRequestDTO2 = Mentor_SkillsRequestDTO.builder()
                .mentor_id(1)
                .build();

        assertEquals(mentorSkillsRequestDTO1, mentorSkillsRequestDTO2);

        Mentor_SkillsRequestDTO mentorSkillsRequestDTO3 = Mentor_SkillsRequestDTO.builder()
                .mentor_id(2)
                .build();

        assertNotEquals(mentorSkillsRequestDTO1, mentorSkillsRequestDTO3);
    }

    @Test
    void testGetMentor_id() {
        int mentorId = 1;
        Mentor_SkillsRequestDTO mentorSkillsRequestDTO = new Mentor_SkillsRequestDTO();
        mentorSkillsRequestDTO.setMentor_id(mentorId);

        assertEquals(mentorId, mentorSkillsRequestDTO.getMentor_id());
    }

    @Test
    void testHashCode() {
        Mentor_SkillsRequestDTO mentorSkillsRequestDTO1 = Mentor_SkillsRequestDTO.builder()
                .mentor_id(1)
                .build();

        Mentor_SkillsRequestDTO mentorSkillsRequestDTO2 = Mentor_SkillsRequestDTO.builder()
                .mentor_id(1)
                .build();

        assertEquals(mentorSkillsRequestDTO1.hashCode(), mentorSkillsRequestDTO2.hashCode());

        Mentor_SkillsRequestDTO mentorSkillsRequestDTO3 = Mentor_SkillsRequestDTO.builder()
                .mentor_id(2)
                .build();

        assertNotEquals(mentorSkillsRequestDTO1.hashCode(), mentorSkillsRequestDTO3.hashCode());
    }

    @Test
    void testSetMentor_id() {
        int mentorId = 1;
        Mentor_SkillsRequestDTO mentorSkillsRequestDTO = new Mentor_SkillsRequestDTO();
        mentorSkillsRequestDTO.setMentor_id(mentorId);

        assertEquals(mentorId, mentorSkillsRequestDTO.getMentor_id());
    }

    @Test
    void testToString() {
        Mentor_SkillsRequestDTO mentorSkillsRequestDTO = Mentor_SkillsRequestDTO.builder()
                .mentor_id(1)
                .build();

        String expectedToString = "Mentor_SkillsRequestDTO(mentor_id=1)";
        assertEquals(expectedToString, mentorSkillsRequestDTO.toString());

    }
}
