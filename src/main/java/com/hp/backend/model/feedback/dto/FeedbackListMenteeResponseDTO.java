package com.hp.backend.model.feedback.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedbackListMenteeResponseDTO {
    private String username;
    private String email;
    private String ava;
    private Date created_date;
    private double rating;
    private String content;
    private int feedback_id;
}
