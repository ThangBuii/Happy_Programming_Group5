package com.hp.backend.service.Session.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
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

import com.hp.backend.entity.Session;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.Session.dto.AddSessionDTO;
import com.hp.backend.model.Session.dto.MentorSessionDTO;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;
import com.hp.backend.model.Session.mapper.SessionMapper;
import com.hp.backend.repository.SessionRepository;

@ExtendWith(MockitoExtension.class)
class SessionServiceImplTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private SessionMapper sessionMapper;

    @InjectMocks
    private SessionServiceImpl sessionService;

    private Session session;
    private SessionDTO sessionDTO;
    private ViewSessionDTO viewSessionDTO;
    private List<Session> sessions;
    private MentorSessionDTO mentorSessionDTO;
    private AddSessionDTO addSessionDTO;


    @BeforeEach
    void setUp() {
        session = new Session();
        session.setMentor_id(1);
        session.setName("Session 1");
        sessionDTO = new SessionDTO();
        sessionDTO.setSession_id(1);
        sessionDTO.setSession_Name("Session 1 DTO");
        viewSessionDTO = new ViewSessionDTO();
        viewSessionDTO.setSession_name("View Session 1");
        sessions = new ArrayList<>();
        sessions.add(session);

        addSessionDTO = new AddSessionDTO();
        addSessionDTO.setSkill_id(1);
        addSessionDTO.setSession_name("New Session");
        addSessionDTO.setDuration(1);
        addSessionDTO.setDescription("Session description");
        addSessionDTO.setPrice(50);

        mentorSessionDTO = new MentorSessionDTO();
        mentorSessionDTO.setSession_id(1);
        mentorSessionDTO.setSession_name("Mentor Session 1");
    }

    @Test
    void testFindSessionByID() throws CustomBadRequestException {
        when(sessionRepository.findById(1)).thenReturn(Optional.of(session));
        when(sessionMapper.toViewSessionDTO(session)).thenReturn(viewSessionDTO);

        ViewSessionDTO result = sessionService.findSessionByID(1);

        assertNotNull(result);
        assertEquals(viewSessionDTO, result);
    }

    @Test
    void testFindSessionByID_SessionNotFound() {
        when(sessionRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(CustomBadRequestException.class,
                () -> sessionService.findSessionByID(1));
    }

    @Test
    void testGetAllSession() throws CustomBadRequestException, CustomNotFoundException {
        when(sessionRepository.findAll()).thenReturn(sessions);
        when(sessionMapper.toSessionDTO(session)).thenReturn(sessionDTO);

        List<SessionDTO> result = sessionService.getAllSession();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sessionDTO, result.get(0));
    }

    // @Test
    // void testGetAllSession_NoSessionFound() {
    //     when(sessionRepository.findAll()).thenReturn(new ArrayList<>());
    //     assertThrows(CustomBadRequestException.class,
    //             () -> sessionService.getAllSession());
    // }

    @Test
    void testGetListSessionByMentorId() throws CustomBadRequestException {
        when(sessionRepository.findAllByMentorId(1)).thenReturn(sessions);
        when(sessionMapper.toMentorSessionDTO(session)).thenReturn(mentorSessionDTO);

        List<MentorSessionDTO> result = sessionService.getListSessionByMentorId(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mentorSessionDTO, result.get(0));
    }

    @Test
    void testGetListSessionByMentorId_NoSessionFound() {
        when(sessionRepository.findAllByMentorId(anyInt())).thenReturn(new ArrayList<>());
        assertThrows(CustomBadRequestException.class,
                () -> sessionService.getListSessionByMentorId(1));
    }

    @Test
    void testAddSession() {
        sessionService.addSession(addSessionDTO, 1);

        // Verify that the sessionRepository.save() method was called with the expected session object
        verify(sessionRepository).save(argThat(s -> s.getName().equals("New Session")));
    }

    // @Test
    // void testSaveSession() {
    //     sessionService.saveSesison(session);

    //     // Verify that the sessionRepository.save() method was called with the expected session object
    //     verify(sessionRepository).save(session);
    // }

    // Write similar test methods for other methods in SessionServiceImpl

}
