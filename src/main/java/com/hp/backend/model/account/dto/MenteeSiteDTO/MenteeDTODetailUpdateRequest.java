package com.hp.backend.model.account.dto.MenteeSiteDTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenteeDTODetailUpdateRequest {
    private String username;

    private int gender;

    private Date dob;

    private String avatar;

    private String country;

    private String city;

    private String description;
}
