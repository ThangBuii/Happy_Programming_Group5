package com.hp.backend.model.booking.dto;

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
    private String email;
    private String scheduleDate;
    private String scheduleTime;
    private String menteeUsername;
    private String mentorUsername;
    private int status;
}
