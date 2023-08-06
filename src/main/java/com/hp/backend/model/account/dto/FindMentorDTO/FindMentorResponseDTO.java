package com.hp.backend.model.account.dto.FindMentorDTO;

import java.util.List;

import com.hp.backend.entity.Skills;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindMentorResponseDTO {
    private String avatar;
    private String username;
    private String short_description;
    private String description;
    private List<Skills> skills;
    private int mentor_id;
    private boolean isFavorite;
}
