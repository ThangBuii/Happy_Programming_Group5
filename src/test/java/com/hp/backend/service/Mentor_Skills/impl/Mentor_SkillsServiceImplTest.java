package com.hp.backend.service.Mentor_Skills.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hp.backend.entity.Mentor_Skills;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.repository.Mentor_SkillsRepository;

@ExtendWith(MockitoExtension.class)
class Mentor_SkillsServiceImplTest {

    @Mock
    private Mentor_SkillsRepository mentorSkillsRepository;

    @InjectMocks
    private Mentor_SkillsServiceImpl mentorSkillsService;

    private Mentor_Skills mentorSkills;

    @BeforeEach
    void setUp() {
        mentorSkills = new Mentor_Skills();
        mentorSkills.setSkill_id(1);
        mentorSkills.setAccount_id(1);
    }

    @Test
    void testFindSkillsById() throws CustomBadRequestException {
        when(mentorSkillsRepository.findById(1)).thenReturn(Optional.of(mentorSkills));

        Mentor_Skills result = mentorSkillsService.findSkillsById(1);

        assertEquals(mentorSkills, result);
    }

    @Test
    void testFindSkillsById_SkillsNotFound() {
        when(mentorSkillsRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(CustomBadRequestException.class,
                () -> mentorSkillsService.findSkillsById(1));
    }

    @Test
    void testAddMentorSkills() {
        mentorSkillsService.addMentor_Skills(1, 1);

        verify(mentorSkillsRepository).save(Mentor_Skills.builder().skill_id(1).account_id(1).build());
    }

    // Write similar test methods for other methods in Mentor_SkillsServiceImpl

}
