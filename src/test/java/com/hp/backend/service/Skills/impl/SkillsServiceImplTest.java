package com.hp.backend.service.Skills.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hp.backend.entity.Skills;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.Skills.dto.SkillsDTO;
import com.hp.backend.model.Skills.dto.SkillsRequestDTO;
import com.hp.backend.model.Skills.mapper.SkillsMapper;
import com.hp.backend.repository.SkillsRepository;

@ExtendWith(MockitoExtension.class)
class SkillsServiceImplTest {

    @Mock
    private SkillsRepository skillsRepository;

    @Mock
    private SkillsMapper skillsMapper;

    @InjectMocks
    private SkillsServiceImpl skillsService;

    private Skills skills;
    private SkillsDTO skillsDTO;
    private SkillsRequestDTO skillsRequestDTO;

    @BeforeEach
    void setUp() {
        skills = new Skills();
        skills.setSkill_id(1);
        skills.setSkill_name("Java");

        skillsDTO = new SkillsDTO();
        skillsDTO.setSkill_id(1);
        skillsDTO.setSkill_name("Java");

        skillsRequestDTO = new SkillsRequestDTO();
        skillsRequestDTO.setSkill_name("Python");
    }

    @Test
    void testGetListMentorBySkills() throws CustomBadRequestException {
        List<Skills> skillsList = new ArrayList<>();
        skillsList.add(skills);

        when(skillsRepository.findAll()).thenReturn(skillsList);
        when(skillsMapper.toSkillsDTO(skills)).thenReturn(skillsDTO);

        List<SkillsDTO> result = skillsService.getListMentorBySkills();

        assertEquals(1, result.size());
        assertEquals(skillsDTO, result.get(0));
    }

    @Test
    void testGetListMentorBySkills_NoSkillsFound() {
        when(skillsRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(CustomBadRequestException.class,
                () -> skillsService.getListMentorBySkills());
    }

    @Test
    void testDeleteSkillsById() {
        when(skillsRepository.findById(1)).thenReturn(Optional.of(skills));
        doNothing().when(skillsRepository).deleteById(1);

        assertDoesNotThrow(() -> skillsService.deleteSkillsById(1));
        verify(skillsRepository).deleteById(1);
    }

    @Test
    void testDeleteSkillsById_SkillsNotFound() {
        when(skillsRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(CustomBadRequestException.class,
                () -> skillsService.deleteSkillsById(1));

    }

    @Test
    void testFindSkillsByID() throws CustomBadRequestException {
        when(skillsRepository.findById(1)).thenReturn(Optional.of(skills));

        Skills result = skillsService.findSkillsByID(1);

        assertEquals(skills, result);
    }

    @Test
    void testFindSkillsByID_SkillsNotFound() {
        when(skillsRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(CustomBadRequestException.class,
                () -> skillsService.findSkillsByID(1));

    }

    @Test
    void testSaveSkills() {
        skillsService.saveSkills(skills);
        verify(skillsRepository).save(skills);
    }

    @Test
    void testAddSkills() {
        skillsService.addSkills(skillsRequestDTO);

        verify(skillsRepository).save(argThat(s -> s.getSkill_name().equals("Python")));
    }

    // Write similar test methods for other methods in SkillsServiceImpl

}
