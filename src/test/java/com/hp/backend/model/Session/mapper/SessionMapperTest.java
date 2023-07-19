package com.hp.backend.model.Session.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Session;
import com.hp.backend.entity.Skills;
import com.hp.backend.model.Session.dto.MentorSessionDTO;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.SkillsRepository;
import com.hp.backend.utils.CommonUtils;

class SessionMapperTest {
    private SessionMapper sessionMapper;
    private AccountRepository accountRepository;
    private SkillsRepository skillsRepository;
    private CommonUtils commonUtils;

    @BeforeEach
    public void setup() {
        accountRepository = mock(AccountRepository.class);
        skillsRepository = mock(SkillsRepository.class);
        commonUtils = mock(CommonUtils.class);

        sessionMapper = new SessionMapper(accountRepository, skillsRepository, commonUtils);
    }

    @Test
    void testToMentorSessionDTO() {
        // Test data
        Session session = new Session();
        session.setName("Test Session");
        session.setSkill_id(1);
        session.setDuration(60);
        session.setDescription("Test session description");
        session.setPrice(100);
        session.setStatus(1);
        session.setSession_id(12);

        Skills skills = new Skills();
        skills.setSkill_name("Test Skill");

        // Mocking repository calls
        when(skillsRepository.findById(1)).thenReturn(Optional.of(skills));

        // Call the method to be tested
        MentorSessionDTO mentorSessionDTO = sessionMapper.toMentorSessionDTO(session);

        // Assertions
        assertNotNull(mentorSessionDTO);
        assertEquals("Test Skill", mentorSessionDTO.getSkill_name());
        assertEquals("Test Session", mentorSessionDTO.getSession_name());
        assertEquals(60, mentorSessionDTO.getDuration());
        assertEquals("Test session description", mentorSessionDTO.getDescription());
        assertEquals(100, mentorSessionDTO.getPrice());
        assertEquals(1, mentorSessionDTO.getStatus());
        assertEquals(12, mentorSessionDTO.getSession_id());
    }

    @Test
    void testToSessionDTO() {
        // Test data
        Session session = new Session();
        session.setName("Test Session");
        session.setMentor_id(1);
        session.setSkill_id(1);
        session.setDuration(60);
        session.setStatus(1);
        session.setSession_id(12);

        Account account = new Account();
        account.setUsername("Mentor User");
        account.setAvatar(commonUtils.imageToDatabase("avatar_image_data"));

        Skills skills = new Skills();
        skills.setSkill_name("Test Skill");

        // Mocking repository calls
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(skillsRepository.findById(1)).thenReturn(Optional.of(skills));
        when(commonUtils.imageToFrontEnd(account.getAvatar())).thenReturn("image_base64_string");

        // Call the method to be tested
        SessionDTO sessionDTO = sessionMapper.toSessionDTO(session);

        // Assertions
        assertNotNull(sessionDTO);
        assertEquals("Mentor User", sessionDTO.getUsername());
        assertEquals("Test Skill", sessionDTO.getSkill_name());
        assertEquals(60, sessionDTO.getDuration());
        assertEquals("Test Session", sessionDTO.getSession_Name());
        assertEquals("image_base64_string", sessionDTO.getAvatar());
        assertEquals(1, sessionDTO.getStatus());
        assertEquals(12, sessionDTO.getSession_id());
    }

    @Test
    void testToViewSessionDTO() {
        // Test data
        Session session = new Session();
        session.setName("Test Session");
        session.setMentor_id(1);
        session.setSkill_id(1);
        session.setDuration(60);
        session.setDescription("Test session description");
        session.setPrice(100);
        session.setStatus(1);

        Account account = new Account();
        account.setUsername("Mentor User");
        account.setEmail("mentor@test.com");
        account.setAvatar(commonUtils.imageToDatabase("image_url"));

        Skills skills = new Skills();
        skills.setSkill_name("Test Skill");

        // Mocking repository calls
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(skillsRepository.findById(1)).thenReturn(Optional.of(skills));
        when(commonUtils.imageToFrontEnd(account.getAvatar())).thenReturn("image_base64_string");

        // Call the method to be tested
        ViewSessionDTO viewSessionDTO = sessionMapper.toViewSessionDTO(session);

        // Assertions
        assertNotNull(viewSessionDTO);
        assertEquals("Test Session", viewSessionDTO.getSession_name());
        assertEquals("Mentor User", viewSessionDTO.getMentor_name());
        assertEquals(60, viewSessionDTO.getDuration());
        assertEquals("Test session description", viewSessionDTO.getDescription());
        assertEquals(100, viewSessionDTO.getPrice());
        assertEquals("Test Skill", viewSessionDTO.getSkill_name());
        assertEquals(1, viewSessionDTO.getStatus());
        assertEquals("mentor@test.com", viewSessionDTO.getEmail());
        assertEquals("image_base64_string", viewSessionDTO.getAvatar());
    }
}
