package com.hp.backend.service.Session;

import java.util.List;

import com.hp.backend.entity.Session;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;

public interface SessionService {

    List<SessionDTO> getAllSession() throws CustomNotFoundException;

    ViewSessionDTO findSessionByID(int id) throws CustomBadRequestException;
    
}
