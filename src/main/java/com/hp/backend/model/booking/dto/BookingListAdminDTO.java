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

public class BookingListAdminDTO {
    private int bookingID;
    private String avatarMentee;
    private String avatarMentor;
    private String scheduleDate;
    private String scheduleTime;
    private String menteeUsername;
    private String mentorUsername;
    private Date created_date;
    private int status;
    private int session_id;
}
