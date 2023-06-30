package com.hp.backend.service.Skills;

import java.util.List;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Skills.dto.SkillsDTO;

public interface SkillsService {

    public List<SkillsDTO> getListMentorBySkills() throws CustomBadRequestException ;
    
    public void deleteSkillsById(int id) throws CustomBadRequestException;
}
