package com.hp.backend.model.feedback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedbackMenteeFeedbackMentorListDTO {
    private int mentor_id;
    private String mentor_name;
}
