package com.hp.backend.model.account.dto.AdminSiteDTO;

import java.sql.Date;
import java.util.List;

import com.hp.backend.entity.Skills;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MentorDTODetailResponse {
    private int account_id;

    private String email;

    private String username;

    private Date created_date;

    private int gender;

    private Date dob;

    private String avatar;

    private String country;

    private String city;

    private String university;

    private String major;

    private String degree;

    private String description;

    private String jobtitle;

    private String workplace;

    private String short_description;

    private List<Skills> skills;
}
