package com.hp.backend.service.Skills;

import java.util.List;

import com.hp.backend.entity.Skills;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.Skills.dto.SkillsDTO;
import com.hp.backend.model.Skills.dto.SkillsRequestDTO;

public interface SkillsService {

    public List<SkillsDTO> getListMentorBySkills() throws CustomBadRequestException;

    public void deleteSkillsById(int id) throws CustomBadRequestException;

    public Skills findSkillsByID(int id) throws CustomBadRequestException;

    public void saveSkills(Skills skills);

    public void addSkills(SkillsRequestDTO skillsRequestDTO);
}
