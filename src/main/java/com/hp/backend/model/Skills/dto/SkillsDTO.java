package com.hp.backend.model.Skills.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillsDTO {
    private int skill_id;
    private int count;
    private String skill_name;
}
