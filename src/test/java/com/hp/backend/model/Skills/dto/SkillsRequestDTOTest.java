package com.hp.backend.model.Skills.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SkillsRequestDTOTest {
    @Test
    void testBuilder() {
        SkillsRequestDTO skillsRequestDTO = SkillsRequestDTO.builder()
                .skill_name("Programming")
                .build();

        assertEquals("Programming", skillsRequestDTO.getSkill_name());
    }

    @Test
    void testCanEqual() {
        SkillsRequestDTO skillsRequestDTO1 = SkillsRequestDTO.builder()
                .skill_name("Programming")
                .build();

        SkillsRequestDTO skillsRequestDTO2 = SkillsRequestDTO.builder()
                .skill_name("Programming")
                .build();

        assertTrue(skillsRequestDTO1.canEqual(skillsRequestDTO2));

        SkillsRequestDTO skillsRequestDTO3 = SkillsRequestDTO.builder()
                .skill_name("Data Science")
                .build();

        assertTrue(skillsRequestDTO1.canEqual(skillsRequestDTO2));
    }

    @Test
    void testEquals() {
        SkillsRequestDTO skillsRequestDTO1 = SkillsRequestDTO.builder()
                .skill_name("Programming")
                .build();

        SkillsRequestDTO skillsRequestDTO2 = SkillsRequestDTO.builder()
                .skill_name("Programming")
                .build();

        assertEquals(skillsRequestDTO1, skillsRequestDTO2);

        SkillsRequestDTO skillsRequestDTO3 = SkillsRequestDTO.builder()
                .skill_name("Data Science")
                .build();

        assertNotEquals(skillsRequestDTO1, skillsRequestDTO3);
    }

    @Test
    void testGetSkill_name() {
        String skillName = "Programming";
        SkillsRequestDTO skillsRequestDTO = new SkillsRequestDTO();
        skillsRequestDTO.setSkill_name(skillName);

        assertEquals(skillName, skillsRequestDTO.getSkill_name());
    }

    @Test
    void testHashCode() {
        SkillsRequestDTO skillsRequestDTO1 = SkillsRequestDTO.builder()
                .skill_name("Programming")
                .build();

        SkillsRequestDTO skillsRequestDTO2 = SkillsRequestDTO.builder()
                .skill_name("Programming")
                .build();

        assertEquals(skillsRequestDTO1.hashCode(), skillsRequestDTO2.hashCode());

        SkillsRequestDTO skillsRequestDTO3 = SkillsRequestDTO.builder()
                .skill_name("Data Science")
                .build();

        assertNotEquals(skillsRequestDTO1.hashCode(), skillsRequestDTO3.hashCode());
    }

    @Test
    void testSetSkill_name() {
        String skillName = "Programming";
        SkillsRequestDTO skillsRequestDTO = new SkillsRequestDTO();
        skillsRequestDTO.setSkill_name(skillName);

        assertEquals(skillName, skillsRequestDTO.getSkill_name());
    }

    @Test
    void testToString() {
        SkillsRequestDTO skillsRequestDTO = SkillsRequestDTO.builder()
                .skill_name("Programming")
                .build();

        String expectedToString = "SkillsRequestDTO(skill_name=Programming)";
        assertEquals(expectedToString, skillsRequestDTO.toString());
    }
}
