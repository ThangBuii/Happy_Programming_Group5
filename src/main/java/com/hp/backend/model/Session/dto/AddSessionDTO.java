package com.hp.backend.model.Session.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddSessionDTO {
    @Min(value = 1, message = "Choose your skills")
    private int skill_id;

    @NotNull(message = "Session name cannot be null")
    private String session_name;

    @Min(value = 30, message = "Duration must be greater or equals to 30 minutes.")
    @NotNull(message = "Duration cannot be null")
    private int duration;

    @Min(value = 1, message = "Please enter session price!")
    @NotNull(message = "Price cannot be null")
    private float price;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

}
