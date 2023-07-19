package com.hp.backend.model.Skills.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SkillsDTOTest {
    @Test
    void testBuilder() {
        SkillsDTO skillsDTO = SkillsDTO.builder()
                .skill_id(1)
                .skill_name("Programming")
                .count(10)
                .build();

        assertEquals(1L, skillsDTO.getSkill_id());
        assertEquals("Programming", skillsDTO.getSkill_name());
        assertEquals(10, skillsDTO.getCount());
    }

    @Test
    void testCanEqual() {
        SkillsDTO skillsDTO1 = SkillsDTO.builder()
                .skill_id(1)
                .skill_name("Programming")
                .count(10)
                .build();

        SkillsDTO skillsDTO2 = SkillsDTO.builder()
                .skill_id(1)
                .skill_name("Programming")
                .count(10)
                .build();

        assertTrue(skillsDTO1.canEqual(skillsDTO2));

        SkillsDTO skillsDTO3 = SkillsDTO.builder()
                .skill_id(2)
                .skill_name("Data Science")
                .count(5)
                .build();

        assertTrue(skillsDTO1.canEqual(skillsDTO3));
    }

    @Test
    void testEquals() {
        SkillsDTO skillsDTO1 = SkillsDTO.builder()
                .skill_id(1)
                .skill_name("Programming")
                .count(10)
                .build();

        SkillsDTO skillsDTO2 = SkillsDTO.builder()
                .skill_id(1)
                .skill_name("Programming")
                .count(10)
                .build();

        assertEquals(skillsDTO1, skillsDTO2);

        SkillsDTO skillsDTO3 = SkillsDTO.builder()
                .skill_id(2)
                .skill_name("Data Science")
                .count(5)
                .build();

        assertNotEquals(skillsDTO1, skillsDTO3);
    }

    @Test
    void testGetCount() {
        int count = 10;
        SkillsDTO skillsDTO = new SkillsDTO();
        skillsDTO.setCount(count);

        assertEquals(count, skillsDTO.getCount());
    }

    @Test
    void testGetSkill_id() {
        int skillId = 1;
        SkillsDTO skillsDTO = new SkillsDTO();
        skillsDTO.setSkill_id(skillId);

        assertEquals(skillId, skillsDTO.getSkill_id());
    }

    @Test
    void testGetSkill_name() {
        String skillName = "Programming";
        SkillsDTO skillsDTO = new SkillsDTO();
        skillsDTO.setSkill_name(skillName);

        assertEquals(skillName, skillsDTO.getSkill_name());
    }

    @Test
    void testHashCode() {
        SkillsDTO skillsDTO1 = SkillsDTO.builder()
                .skill_id(1)
                .skill_name("Programming")
                .count(10)
                .build();

        SkillsDTO skillsDTO2 = SkillsDTO.builder()
                .skill_id(1)
                .skill_name("Programming")
                .count(10)
                .build();

        assertEquals(skillsDTO1.hashCode(), skillsDTO2.hashCode());

        SkillsDTO skillsDTO3 = SkillsDTO.builder()
                .skill_id(2)
                .skill_name("Data Science")
                .count(5)
                .build();

        assertNotEquals(skillsDTO1.hashCode(), skillsDTO3.hashCode());
    }

    @Test
    void testSetCount() {
        int count = 10;
        SkillsDTO skillsDTO = new SkillsDTO();
        skillsDTO.setCount(count);

        assertEquals(count, skillsDTO.getCount());
    }

    @Test
    void testSetSkill_id() {
        int skillId = 1;
        SkillsDTO skillsDTO = new SkillsDTO();
        skillsDTO.setSkill_id(skillId);

        assertEquals(skillId, skillsDTO.getSkill_id());
    }

    @Test
    void testSetSkill_name() {
        String skillName = "Programming";
        SkillsDTO skillsDTO = new SkillsDTO();
        skillsDTO.setSkill_name(skillName);

        assertEquals(skillName, skillsDTO.getSkill_name());
    }

    @Test
    void testToString() {
        SkillsDTO skillsDTO = SkillsDTO.builder()
                .skill_id(1)
                .skill_name("Programming")
                .count(10)
                .build();

        String expectedToString = "SkillsDTO(skill_id=1, count=10, skill_name=Programming)";
        assertEquals(expectedToString, skillsDTO.toString());
    }
}
