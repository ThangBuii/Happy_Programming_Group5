package com.hp.backend.model.report.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDetailResponseDTO {
    private int report_id;
    private String username;
    private String email;
    private String ava;
    private String title;
    private String content;
    private Date created_date;
    private int role;
    private int status;
    private String answer;
}
