package com.hp.backend.service.Mentor_Skills;

import com.hp.backend.entity.Mentor_Skills;
import com.hp.backend.exception.custom.CustomBadRequestException;

public interface Mentor_SkillsService {

    Mentor_Skills findSkillsById(int id) throws CustomBadRequestException;

    void addMentor_Skills(int id, int account_id);
    
}
