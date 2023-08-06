package com.hp.backend.service.Mentor_Skills.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Mentor_Skills;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.CustomError;
import com.hp.backend.repository.Mentor_SkillsRepository;
import com.hp.backend.service.Mentor_Skills.Mentor_SkillsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Mentor_SkillsServiceImpl implements Mentor_SkillsService {

    private final Mentor_SkillsRepository mentor_SkillsRepository;

    @Override
    public Mentor_Skills findSkillsById(int id) throws CustomBadRequestException {
        Optional<Mentor_Skills> mentor_Skills = mentor_SkillsRepository.findById(id);
        if (!mentor_Skills.isPresent()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .message("There are no Mentor Skill with id: " + id).code("404").build());
        }
        return mentor_Skills.get();
    }

    @Override
    public void addMentor_Skills(int id, int account_id) {
        mentor_SkillsRepository.save(Mentor_Skills.builder().skill_id(id).account_id(account_id).build());
    }

}
