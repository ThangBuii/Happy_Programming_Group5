package com.hp.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.model.Session.dto.AddSessionDTO;
import com.hp.backend.model.Session.dto.MentorSessionDTO;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.SessionRequestDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;
import com.hp.backend.service.Session.SessionService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/admin/session")
    List<SessionDTO> getSessionList() throws CustomNotFoundException {
        return sessionService.getAllSession();
    }

    @GetMapping("/admin/session/{id}")
    ViewSessionDTO getSessionById(@PathVariable int id) throws CustomBadRequestException {
        return sessionService.findSessionByID(id);
    }

    @GetMapping("/mentor/session")
    List<MentorSessionDTO> getSessionListByMentorId(HttpServletRequest request) throws CustomBadRequestException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return sessionService.getListSessionByMentorId(tokenPayload.getAccount_id());
    }

    @PostMapping("/mentor/session")
        void addSession(@RequestBody AddSessionDTO addSessionDTO, HttpServletRequest request){
            String token = jwtTokenUtil.getRequestToken(request);
            TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
            sessionService.addSession(addSessionDTO,tokenPayload.getAccount_id());
        }
}
