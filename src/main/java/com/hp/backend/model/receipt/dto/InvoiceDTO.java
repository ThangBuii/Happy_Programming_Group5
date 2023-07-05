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
public class InvoiceDTO {
    private int receipt_id;
    private String username;
    private String email;
    private String avatar;
    private String amount;
    private Date created_Date;
}
