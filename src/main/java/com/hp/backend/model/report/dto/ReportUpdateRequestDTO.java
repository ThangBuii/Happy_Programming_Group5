package com.hp.backend.model.report.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class ReportUpdateRequestDTO {
    private int report_id;
    private String answer;
}
