package com.hp.backend.service.Session.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Session;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.Session.dto.AddSessionDTO;
import com.hp.backend.model.Session.dto.MentorSessionDTO;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;
import com.hp.backend.model.Session.mapper.SessionMapper;
import com.hp.backend.repository.AccountRepository;
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

    private final AccountRepository accountRepository;

    @Override
    public ViewSessionDTO findSessionByID(int id) throws CustomBadRequestException {
        Optional<Session> session = sessionRepository.findById(id);
        if (!session.isPresent()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .message("There are no session for the session id: " + id).code("404").build());
        }   

        return sessionMapper.toViewSessionDTO(session.get());
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
        for (int i = sessions.size() - 1; i >= 0; i--) {
            Session session = sessions.get(i);
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
        Account account = accountRepository.findById(session.getMentor_id()).get();
        String status = session.getStatus() == 1 ? "accepted" : "rejected"; 
        emailService.sendEmail(account.getEmail(), "Session Accepted", "Your session " + session.getName() + " has been " + status);
        sessionRepository.save(session);
    }

    @Override
    public List<MentorSessionDTO> getListSessionFindMentorByMentorId(int account_id) throws CustomBadRequestException {
        List<MentorSessionDTO> mentorSessionDTOs = new ArrayList<>();
        List<Session> session = sessionRepository.findAcceptedByMentorId(account_id);
        if (session.isEmpty()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .message("There are no session for the mentor id: " + account_id).code("404").build());
        }
        for (Session sessions : session) {
            mentorSessionDTOs.add(sessionMapper.toMentorSessionDTO(sessions));
        }
        return mentorSessionDTOs;
    }

}
