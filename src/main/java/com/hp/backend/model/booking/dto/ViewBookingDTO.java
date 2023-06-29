package com.hp.backend.model.booking.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewBookingDTO {
    private String mentorUsername;
    private String mentorEmail;
    private String menteeUsername;
    private String menteeEmail;
    private Date scheduleDate;
    private String scheduleTime;
    private String mentorAvatar;
    private String menteeAvatar;
    private int status;
    private Date createdDate;
}
