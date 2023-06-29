package com.hp.backend.model.Session.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewSessionDTO {
    private String mentor_name;
    private String skill_name;
    private String session_name;
    private int duration;
    private float price;
    private String description;
    private int status;
}
