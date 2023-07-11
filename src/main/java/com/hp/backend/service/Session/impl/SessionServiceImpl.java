package com.hp.backend.service.Session.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Session;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.Session.dto.AddSessionDTO;
import com.hp.backend.model.Session.dto.MentorSessionDTO;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;
import com.hp.backend.model.Session.mapper.SessionMapper;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.service.EmailService;
import com.hp.backend.service.Session.SessionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;

    private final SessionRepository sessionRepository;

    private final EmailService emailService;

    @Override
    public ViewSessionDTO findSessionByID(int id) throws CustomBadRequestException {
        Session session = sessionRepository.findById(id).get();
        if (session == null) {
            throw new CustomBadRequestException(CustomError.builder()
                    .message("There are no session for the session id: " + id).code("404").build());
        }

        return sessionMapper.toViewSessionDTO(session);
    }

    @Override
    public List<SessionDTO> getAllSession() throws CustomBadRequestException {
        List<SessionDTO> sessionDTO = new ArrayList<>();
        List<Session> sessions = sessionRepository.findAll();
        if (sessions.isEmpty()) {
            throw new CustomBadRequestException(
                    CustomError.builder().message("There are no session").code("404").build());
        }
        for (Session session : sessions) {

            sessionDTO.add(sessionMapper.toSessionDTO(session));

        }

        return sessionDTO;
    }

    @Override
    public List<MentorSessionDTO> getListSessionByMentorId(int account_id) throws CustomBadRequestException {
        List<MentorSessionDTO> mentorSessionDTOs = new ArrayList<>();
        List<Session> session = sessionRepository.findAllByMentorId(account_id);
        if (session.isEmpty()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .message("There are no session for the mentor id: " + account_id).code("404").build());
        }
        for (Session sessions : session) {
            mentorSessionDTOs.add(sessionMapper.toMentorSessionDTO(sessions));
        }
        return mentorSessionDTOs;
    }

    @Override
    public void addSession(AddSessionDTO addSessionDTO, int id) {
        sessionRepository.save(Session.builder().skill_id(addSessionDTO.getSkill_id())
                .name(addSessionDTO.getSession_name()).duration(addSessionDTO.getDuration())
                .description(addSessionDTO.getDescription()).price(addSessionDTO.getPrice()).status(0).mentor_id(id)
                .build());
    }

    @Override
    public void saveSesison(Session session) {
        sessionRepository.save(session);
    }

}
