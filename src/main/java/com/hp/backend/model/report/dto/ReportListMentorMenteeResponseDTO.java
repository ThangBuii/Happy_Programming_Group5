package com.hp.backend.model.report.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportListMentorMenteeResponseDTO {
    private String title;
    private String content;
    private Date created_date;
    private int status;
    private int report_id;
}
