package com.hp.backend.model.account.dto.AdminSiteDTO;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenteeDTODetailResponse {
    private int account_id;

    private String email;

    private String username;

    private Date created_date;

    private int gender;

    private Date dob;

    private String avatar;

    private String country;

    private String city;

    private String description;
}
