package com.hp.backend.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.hp.backend.entity.Session;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.Session.dto.MentorSessionDTO;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.service.Session.SessionService;
import com.hp.backend.utils.JwtTokenUtil;

public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private SessionRepository sessionRepository;

    @BeforeEach
    public void setup() throws CustomNotFoundException, CustomBadRequestException {
        // Mocking sessionService methods
        List<SessionDTO> sessionList = new ArrayList<>();
        // Add sample session DTOs to the list
        when(sessionService.getAllSession()).thenReturn(sessionList);

        ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
        // Set properties of the viewSessionDTO
        when(sessionService.findSessionByID(1)).thenReturn(viewSessionDTO);

        List<MentorSessionDTO> mentorSessionList = new ArrayList<>();
        // Add sample mentor session DTOs to the list
        when(sessionService.getListSessionByMentorId(1)).thenReturn(mentorSessionList);
        when(sessionService.getListSessionFindMentorByMentorId(1)).thenReturn(mentorSessionList);

        // Mocking sessionRepository method
        Session session = new Session();
        when(sessionRepository.findById(1)).thenReturn(Optional.of(session));
    }

    @Test
    void testAddSession() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/mentor/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test Session\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetSessionById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/sessions/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetSessionList() {

    }

    @Test
    void testGetSessionListByMentorId() {

    }

    @Test
    void testGetSessionListByMentorId2() {

    }

    @Test
    void testUpdateSession() {

    }
}
