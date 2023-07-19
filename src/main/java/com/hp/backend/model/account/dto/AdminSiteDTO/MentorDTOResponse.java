package com.hp.backend.model.account.dto.AdminSiteDTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorDTOResponse {
    private int account_id;
    private String username;
    private String avatar;
    private Date created_date;
    private int numberOfBooking;
    private String earned;
}
