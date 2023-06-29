package com.hp.backend.model.Skills.mapper;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Mentor_Skills;
import com.hp.backend.entity.Skills;
import com.hp.backend.model.Skills.dto.SkillsDTO;
import com.hp.backend.repository.Mentor_SkillsRepository;
import com.hp.backend.repository.SkillsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SkillsMapper {
    private final Mentor_SkillsRepository mentor_SkillsRepository;

    private final SkillsRepository skillsRepository;

    public SkillsDTO toSkillsDTO(Skills skills) {
        int count = skillsRepository.countSkillsByMentorID(skills.getSkill_id());
        return SkillsDTO.builder().skill_id(skills.getSkill_id()).skill_name(skills.getSkill_name())
                .count(count).build();
    }
}
