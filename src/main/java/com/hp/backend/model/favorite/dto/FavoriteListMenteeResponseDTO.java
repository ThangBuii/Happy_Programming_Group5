package com.hp.backend.model.favorite.dto;

import java.util.List;

import com.hp.backend.entity.Skills;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoriteListMenteeResponseDTO {
    private String avatar;
    private String username;
    private String jobtitle;
    private String workplace;
    private String description;
    private List<Skills> skills;
    private int mentor_id;
    private int favorite_id;
}
