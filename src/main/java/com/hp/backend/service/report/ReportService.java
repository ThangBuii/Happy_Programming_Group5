package com.hp.backend.service.report;

import java.util.List;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.report.dto.ReportAddRequestDTO;
import com.hp.backend.model.report.dto.ReportDetailResponseDTO;
import com.hp.backend.model.report.dto.ReportListMentorMenteeResponseDTO;
import com.hp.backend.model.report.dto.ReportListResponseDTO;
import com.hp.backend.model.report.dto.ReportUpdateRequestDTO;



public interface ReportService {

    public List<ReportListResponseDTO> getReports() throws CustomInternalServerException;

    public void updateReport(ReportUpdateRequestDTO reportUpdateRequestDTO) throws CustomBadRequestException;

    public ReportDetailResponseDTO getReportDetail(int id) throws CustomBadRequestException, CustomInternalServerException;

    public List<ReportListMentorMenteeResponseDTO> getReportsMentorMentee(int account_id);

    public void addReport(int account_id, ReportAddRequestDTO reportAddRequestDTO) throws CustomBadRequestException;

    public void deleteReport(int id) throws CustomBadRequestException;
    
}
