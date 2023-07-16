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
public class ViewInvoiceDTO {
    private int booking_id;
    private int receipt_id;
    private String payment_method;
    private Date created_Date;
    private String mentee_name;
    private String mentee_email;
    private String mentee_avatar;
    private String mentor_name;
    private String mentor_email;
    private String mentor_avatar;
    private float price;
    private String session_name;
}
