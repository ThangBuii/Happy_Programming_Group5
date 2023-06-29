package com.hp.backend.service.Skills.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Skills;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Skills.dto.SkillsDTO;
import com.hp.backend.model.Skills.mapper.SkillsMapper;
import com.hp.backend.repository.SkillsRepository;
import com.hp.backend.service.Skills.SkillsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillsServiceImpl implements SkillsService {

    private final SkillsRepository skillsRepository;

    private final SkillsMapper skillsMapper;

    @Override
    public List<SkillsDTO> getListMentorBySkills() throws CustomBadRequestException {
        List<SkillsDTO> lSkillsDTOs = new ArrayList<>();
        List<Skills> skills = skillsRepository.findAll();

        if (skills.isEmpty()) {
            throw new CustomBadRequestException(
                    CustomError.builder().message("There are no Skills").code("404").build());
        }

        for (Skills skill : skills) {
            lSkillsDTOs.add(skillsMapper.toSkillsDTO(skill));
        }
        return lSkillsDTOs;
    }

}
