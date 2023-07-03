package com.hp.backend.model.account.dto.MentorSiteDTO;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MentorDTODetailUpdateRequest {
    private String username;

    private int gender;

    private Date dob;

    private String avatar;

    private String country;

    private String city;

    private String university;

    private String major;

    private String degree;

    private String description;

    private String short_description;
}
