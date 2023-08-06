package com.hp.backend.model.receipt.dto;

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
public class InvoiceAdminDTO {
    private int receipt_id;
    private String menteeUsername;
    private String menteeEmail;
    private String menteeAvatar;
    private String mentorUsername;
    private String mentorEmail;
    private String mentorAvatar;
    private int booking_id;
    private String payment_method;
    private String amount;
    private Date created_Date;
}
