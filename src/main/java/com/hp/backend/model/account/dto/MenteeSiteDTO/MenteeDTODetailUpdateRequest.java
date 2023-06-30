package com.hp.backend.model.account.dto.MenteeSiteDTO;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenteeDTODetailUpdateRequest {
    private String email;

    private String username;

    private int gender;

    private Date dob;

    private String avatar;

    private String country;

    private String city;

    private String description;
}
