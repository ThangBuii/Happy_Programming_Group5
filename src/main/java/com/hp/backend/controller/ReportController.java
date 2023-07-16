package com.hp.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.model.report.dto.ReportAddRequestDTO;
import com.hp.backend.model.report.dto.ReportDetailResponseDTO;
import com.hp.backend.model.report.dto.ReportListMentorMenteeResponseDTO;
import com.hp.backend.model.report.dto.ReportListResponseDTO;
import com.hp.backend.model.report.dto.ReportUpdateRequestDTO;
import com.hp.backend.service.report.ReportService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class ReportController {
    private final ReportService reportService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/admin/reports")
    public List<ReportListResponseDTO> getReports() throws CustomInternalServerException{
        return reportService.getReports();
    }

    @PutMapping("/admin/report")
    public void updateReport(@RequestBody ReportUpdateRequestDTO reportUpdateRequestDTO) throws CustomBadRequestException{
        reportService.updateReport(reportUpdateRequestDTO);
    }

    @GetMapping("/report/{id}")
    public ReportDetailResponseDTO getReportDetail(@PathVariable int id) throws CustomBadRequestException, CustomInternalServerException{
        return reportService.getReportDetail(id);
    }

    @GetMapping("/men/reports")
    public List<ReportListMentorMenteeResponseDTO> getReportsMentorMentee(HttpServletRequest request){
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return reportService.getReportsMentorMentee(tokenPayload.getAccount_id());
    }

    @PostMapping("/men/report")
    public void addReport(@RequestBody ReportAddRequestDTO reportAddRequestDTO,HttpServletRequest request) throws CustomBadRequestException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        reportService.addReport(tokenPayload.getAccount_id(),reportAddRequestDTO);
    }

    @DeleteMapping("/report/{id}")
    public void deleteReport(@PathVariable int id) throws CustomBadRequestException{
        reportService.deleteReport(id);
    }
}
