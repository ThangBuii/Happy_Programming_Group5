package com.hp.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.Session.dto.SessionDTO;
import com.hp.backend.model.Skills.dto.SkillsDTO;
import com.hp.backend.repository.SkillsRepository;
import com.hp.backend.service.Skills.SkillsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SkillController {
    private final SkillsRepository skillsRepository;

    private final SkillsService skillsService;

    @GetMapping("/admin/skills")
    List<SkillsDTO> getSkills() throws CustomBadRequestException {
        return skillsService.getListMentorBySkills();
    }
}
