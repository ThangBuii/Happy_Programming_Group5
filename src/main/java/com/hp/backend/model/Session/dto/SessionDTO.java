package com.hp.backend.model.Session.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    private String username;
    private String skill_name;
    private int duration;
    private String session_Name;
    private int status;
    private int session_id;
}
