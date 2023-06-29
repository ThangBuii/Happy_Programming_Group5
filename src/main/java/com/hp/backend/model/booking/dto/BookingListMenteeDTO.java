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

public class BookingListMenteeDTO {
    private String username;
    private String email;
    private Date scheduleDate;
    private String scheduleTime;
}
