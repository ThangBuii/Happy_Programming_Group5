package com.hp.backend.model.feedback.dto;


import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedbackListAdminResponseDTO {
    private int feedback_id;
    private String mentor_username;
    private String mentor_email;
    private String mentor_ava;
    private String mentee_username;
    private String mentee_email;
    private String mentee_ava;
    private Date created_date; 
    private String content;
    private double rating;         
}
