package com.hp.backend.model.feedback.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedbackAddRequestDTO {
    @Min(value = 1, message = "Choose your mentor you want to give feedback")
    private int mentor_id;

    @NotEmpty(message = "Content must not be blank")
    private String content;

    @NotNull(message = "Please give rating!")
    @Min(value = 1, message = "Please give rating!")
    private Double rating;
}
