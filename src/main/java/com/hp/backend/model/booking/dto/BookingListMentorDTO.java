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

public class BookingListMentorDTO {
    private int bookingID;
    private int menteeID;
    private String avatar;
    private String username;
    private String email;
    private Date scheduleDate;
    private String scheduleTime;
}
