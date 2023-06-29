package com.hp.backend.model.account.dto.AdminSiteDTO;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenteeDTOResponse {
    private int account_id;
    private String username;
    private String avatar;
    private Date created_date;
    private int numberOfBooking;
}
