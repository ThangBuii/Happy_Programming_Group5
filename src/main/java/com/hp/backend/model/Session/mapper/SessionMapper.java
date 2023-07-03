package com.hp.backend.model.Session.mapper;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Session;
import com.hp.backend.entity.Skills;
import com.hp.backend.model.Session.dto.MentorSessionDTO;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Session.dto.ViewSessionDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.SkillsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SessionMapper {
    private final AccountRepository accountRepository;

    private final SkillsRepository skillsRepository;

    public SessionDTO toSessionDTO(Session session) {
        Account account = accountRepository.findById(session.getMentor_id()).get();
        Skills skills = skillsRepository.findById(session.getSkill_id()).get();

        return SessionDTO.builder().username(account.getUsername()).skill_name(skills.getSkill_name())
                .duration(session.getDuration()).session_Name(session.getName())
                .status(session.getStatus()).session_id(session.getSession_id()).build();
    }

    public ViewSessionDTO toViewSessionDTO(Session session) {
        Account account = accountRepository.findById(session.getMentor_id()).get();
        Skills skills = skillsRepository.findById(session.getSkill_id()).get();

        return ViewSessionDTO.builder().session_name(session.getName()).mentor_name(account.getUsername())
                .duration(session.getDuration()).description(session.getDescription()).price(session.getPrice())
                .skill_name(skills.getSkill_name()).status(session.getStatus()).build();

    }

    public MentorSessionDTO toMentorSessionDTO(Session session) {
        Account account = accountRepository.findById(session.getMentor_id()).get();
        Skills skills = skillsRepository.findById(session.getSkill_id()).get();

        return MentorSessionDTO.builder().skill_name(skills.getSkill_name()).session_name(session.getName())
                .duration(session.getDuration()).description(session.getDescription()).price(session.getPrice())
                .status(session.getStatus()).session_id(session.getSession_id()).build();
    }

}
