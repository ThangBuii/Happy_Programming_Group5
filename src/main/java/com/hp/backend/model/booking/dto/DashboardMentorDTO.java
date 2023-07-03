package com.hp.backend.model.booking.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardMentorDTO {
    private int bookingID;
    private String username;
    private String email;
    private Date created_Date;
    private int status;
}
