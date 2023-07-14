package com.hp.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.entity.Skills;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.model.Skills.dto.SkillsDTO;
import com.hp.backend.model.Skills.dto.SkillsRequestDTO;
import com.hp.backend.service.Mentor_Skills.Mentor_SkillsService;
import com.hp.backend.service.Skills.SkillsService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SkillController {
    private final SkillsService skillsService;

    private final JwtTokenUtil jwtTokenUtil;

    private final Mentor_SkillsService mentor_SkillsService;

    @GetMapping("/admin/skills")
    List<SkillsDTO> getSkills() throws CustomBadRequestException {
        return skillsService.getListMentorBySkills();
    }

    @DeleteMapping("/admin/skills/{id}")
    void deleteSkills(@PathVariable int id) throws CustomBadRequestException {
        skillsService.deleteSkillsById(id);
    }

    @PutMapping("/admin/skills/{id}")
    void updateMentorBooking(@PathVariable int id, @RequestBody SkillsRequestDTO skillsRequestDTO)
            throws CustomBadRequestException {
        Skills skills = skillsService.findSkillsByID(id);
        skills.setSkill_name(skillsRequestDTO.getSkill_name());
        skillsService.saveSkills(skills);
    }

    @PostMapping("/admin/skills")
    void addSkills(@RequestBody SkillsRequestDTO skillsRequestDTO) {
        skillsService.addSkills(skillsRequestDTO);
    }

    @GetMapping("/public/men/skills")
    List<Skills> getAllSkills(){
        return skillsService.getAllSkills();
    }

    @PostMapping("/mentor/skills/{id}")
    void addMentor_Skills(@PathVariable int id, HttpServletRequest request) {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        mentor_SkillsService.addMentor_Skills(id, tokenPayload.getAccount_id());
    }
}