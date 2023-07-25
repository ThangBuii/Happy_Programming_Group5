package com.hp.backend.model.account.dto.MentorSiteDTO;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MentorDTODetailUpdateRequest {
    @NotEmpty(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Gender cannot be null")
    private int gender;

    @NotNull(message = "Date of birth cannot be null")
    private Date dob;

    private String avatar;

    @NotEmpty(message = "Country cannot be null")
    private String country;

    @NotEmpty(message = "City cannot be null")
    private String city;


    private String university;
    private String major;

    private String degree;
    private String description;

    private String short_description;
}
