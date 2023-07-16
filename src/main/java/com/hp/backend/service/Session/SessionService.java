package com.hp.backend.service.Session;

import java.util.List;

import com.hp.backend.entity.Session;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.Session.dto.AddSessionDTO;
import com.hp.backend.model.Session.dto.MentorSessionDTO;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;

public interface SessionService {

    List<SessionDTO> getAllSession() throws CustomNotFoundException, CustomBadRequestException;

    ViewSessionDTO findSessionByID(int id) throws CustomBadRequestException;

    List<MentorSessionDTO> getListSessionByMentorId(int account_id) throws CustomBadRequestException;
    
    void addSession(AddSessionDTO addSessionDTO, int i);

    void saveSesison(Session session);
}
