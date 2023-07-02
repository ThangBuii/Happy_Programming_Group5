package com.hp.backend.model.report.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportListResponseDTO {
    private int report_id;
    private String username;
    private String email;
    private String avatar;
    private String title;
    private Date created_date;
    private int role;
    private int status;
}
