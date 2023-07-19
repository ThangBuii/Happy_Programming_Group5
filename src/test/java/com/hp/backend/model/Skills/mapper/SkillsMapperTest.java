package com.hp.backend.model.Skills.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hp.backend.entity.Skills;
import com.hp.backend.model.Skills.dto.SkillsDTO;
import com.hp.backend.repository.SkillsRepository;

public class SkillsMapperTest {

    private SkillsMapper skillsMapper;
    private SkillsRepository skillsRepository;

    @BeforeEach
    public void setup() {
        skillsRepository = mock(SkillsRepository.class);
        skillsMapper = new SkillsMapper(skillsRepository);
    }

    @Test
    void testToSkillsDTO() {
        // Test data
        Skills skills = new Skills();
        skills.setSkill_id(1);
        skills.setSkill_name("Test Skill");

        // Mocking repository call
        when(skillsRepository.countSkillsByMentorID(1)).thenReturn(5);

        // Call the method to be tested
        SkillsDTO skillsDTO = skillsMapper.toSkillsDTO(skills);

        // Assertions
        assertNotNull(skillsDTO);
        assertEquals(1, skillsDTO.getSkill_id());
        assertEquals("Test Skill", skillsDTO.getSkill_name());
        assertEquals(5, skillsDTO.getCount());
    }
}
