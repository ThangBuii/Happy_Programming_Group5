package com.hp.backend.service.Session.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.backend.entity.Session;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;
import com.hp.backend.model.Session.mapper.SessionMapper;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.service.Session.SessionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;

    private final SessionRepository sessionRepository;

    // @Override
    // public SessionDTO deleteSessionById(int id) {
    // throw new UnsupportedOperationException("Unimplemented method
    // 'deleteSessionById'");
    // }

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
    public List<SessionDTO> getAllSession() throws CustomNotFoundException {
        List<SessionDTO> sessionDTO = new ArrayList<>();
        List<Session> sessions = sessionRepository.findAll();
        if (sessions.isEmpty()) {
            CustomError error = new CustomError("No session found",
                    "There are no session",
                    null);
            throw new CustomNotFoundException(error);
        }
        for (Session session : sessions) {

            sessionDTO.add(sessionMapper.toSessionDTO(session));

        }

        return sessionDTO;
    }

}
