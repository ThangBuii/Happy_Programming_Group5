package com.hp.backend.model.feedback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedbackAddRequestDTO {
    private int mentor_id;
    private String content;
    private int rating;
}
