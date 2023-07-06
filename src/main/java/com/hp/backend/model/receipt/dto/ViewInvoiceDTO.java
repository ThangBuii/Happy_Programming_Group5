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
    private int receipt_id;
    private String paymen_method;
    private Date created_Date;
}
