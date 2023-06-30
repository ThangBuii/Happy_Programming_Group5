package com.hp.backend.model.report.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReportAddRequestDTO {
    private String title;
    private String content;

}
