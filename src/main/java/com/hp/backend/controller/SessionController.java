package com.hp.backend.controller;

import java.util.List;

import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;
import com.hp.backend.service.Session.SessionService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping("/admin/session")
        List<SessionDTO> getSessionList() throws CustomNotFoundException {
            return sessionService.getAllSession();
    }


    @GetMapping("/admin/session/{id}")
        ViewSessionDTO getSessionById(@PathVariable int id) throws CustomBadRequestException {
            return sessionService.findSessionByID(id);
    }

}
