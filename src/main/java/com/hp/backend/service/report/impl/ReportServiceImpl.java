package com.hp.backend.service.report.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

import com.hp.backend.entity.Report;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.report.dto.ReportAddRequestDTO;
import com.hp.backend.model.report.dto.ReportDetailResponseDTO;
import com.hp.backend.model.report.dto.ReportListMentorMenteeResponseDTO;
import com.hp.backend.model.report.dto.ReportListResponseDTO;
import com.hp.backend.model.report.dto.ReportUpdateRequestDTO;
import com.hp.backend.model.report.mapper.ReportMapper;
import com.hp.backend.repository.ReportRepository;
import com.hp.backend.service.report.ReportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    @Override
    public List<ReportListResponseDTO> getReports() throws CustomInternalServerException {
        List<Report> reports = reportRepository.findAll();
        List<ReportListResponseDTO> reportListResponseDTOs = new ArrayList<>();

        for(Report report : reports) {
            reportListResponseDTOs.add(reportMapper.toReportListResponseDTO(report));
        }
        return reportListResponseDTOs;
    }

    @Override
    public void updateReport(ReportUpdateRequestDTO reportUpdateRequestDTO) throws CustomBadRequestException {
        Optional<Report> report = reportRepository.findById(reportUpdateRequestDTO.getReport_id());

        if(!report.isPresent()){
            throw new CustomBadRequestException(CustomError.builder().message("Report not exist").code("404").build());
        }
        Report report1  = report.get();

        report1.setStatus(1);
        report1.setAnswer(reportUpdateRequestDTO.getAnswer());
        reportRepository.save(report1);
    }

    @Override
    public ReportDetailResponseDTO getReportDetail(int id) throws CustomBadRequestException, CustomInternalServerException {
        Optional<Report> report = reportRepository.findById(id);

        if(!report.isPresent()){
            throw new CustomBadRequestException(CustomError.builder().message("Report not exist").code("404").build());
        }

        return reportMapper.toReportDetailResponseDTO(report.get());
    }

    @Override
    public List<ReportListMentorMenteeResponseDTO> getReportsMentorMentee(int account_id) {
        List<Report> reports = reportRepository.findByAccountId(account_id);
        List<ReportListMentorMenteeResponseDTO> reportListMentorMenteeResponseDTOs = new ArrayList<>();

        for(Report report : reports){
            reportListMentorMenteeResponseDTOs.add(reportMapper.toReportListMenteeMentorResponseDTO(report));
        }

        return reportListMentorMenteeResponseDTOs;
    }

    @Override
    public void addReport(int account_id, ReportAddRequestDTO reportAddRequestDTO) throws CustomBadRequestException {
        if(reportAddRequestDTO.getContent().isEmpty()){
            throw new CustomBadRequestException(CustomError.builder().message("Content is mandatory").code("404").build());
        }else if(reportAddRequestDTO.getTitle().isEmpty()){
            throw new CustomBadRequestException(CustomError.builder().message("Title is mandatory").code("404").build());
        }
        Report report = reportMapper.toReport(account_id, reportAddRequestDTO);



        reportRepository.save(report);
    }

    @Override
    public void deleteReport(int id) throws CustomBadRequestException {
        Optional<Report> report = reportRepository.findById(id);

        if(!report.isPresent()){
            throw new CustomBadRequestException(CustomError.builder().message("Report not exist").code("404").build());
        }

        reportRepository.delete(report.get());
        
    }
    
}
