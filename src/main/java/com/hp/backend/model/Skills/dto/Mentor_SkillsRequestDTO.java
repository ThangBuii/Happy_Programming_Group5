package com.hp.backend.model.Skills.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mentor_SkillsRequestDTO {
    private int mentor_id;
}
