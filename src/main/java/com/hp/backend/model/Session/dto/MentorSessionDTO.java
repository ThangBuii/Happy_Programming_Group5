package com.hp.backend.model.Session.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class 
MentorSessionDTO {
    private String session_name;
    private int session_id;
    private String skill_name;
    private int duration;
    private String description;
    private float price;
    private int status;
}
