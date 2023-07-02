package com.hp.backend.model.report.mapper;

import java.sql.Date;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Report;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.report.dto.ReportAddRequestDTO;
import com.hp.backend.model.report.dto.ReportDetailResponseDTO;
import com.hp.backend.model.report.dto.ReportListMentorMenteeResponseDTO;
import com.hp.backend.model.report.dto.ReportListResponseDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.ReportRepository;
import com.hp.backend.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReportMapper {
    private final ReportRepository reportRepository;
    private final AccountRepository accountRepository;
    private final DateUtil dateUtil;

    public ReportListResponseDTO toReportListResponseDTO(Report report) throws CustomInternalServerException {
        Account account = accountRepository.findById(report.getAccount_id()).get();

        if (account == null) {
            throw new CustomInternalServerException(
                    CustomError.builder().message("Report sender is not exist").code("500").build());
        }
        String avatar = Base64.getEncoder().encodeToString(account.getAvatar());
        return ReportListResponseDTO.builder().username(account.getUsername()).avatar(avatar)
                .email(account.getEmail())
                .title(report.getTitle()).report_id(report.getReport_id())
                .created_date(report.getDate())
                .role(account.getRole()).status(report.getStatus()).build();
    }

    public ReportDetailResponseDTO toReportDetailResponseDTO(Report report) throws CustomInternalServerException {
        Account account = accountRepository.findById(report.getAccount_id()).get();

        if (account == null) {
            throw new CustomInternalServerException(
                    CustomError.builder().message("Report sender is not exist").code("500").build());
        }
        String avatar = Base64.getEncoder().encodeToString(account.getAvatar());
        return ReportDetailResponseDTO.builder().username(account.getUsername()).avatar(avatar)
                .email(account.getEmail()).content(report.getContent()).answer(report.getAnswer())
                .title(report.getTitle()).report_id(report.getReport_id())
                .created_date(report.getDate())
                .role(account.getRole()).status(report.getStatus()).build();
    }

    public ReportListMentorMenteeResponseDTO toReportListMenteeMentorResponseDTO(Report report) {
        return ReportListMentorMenteeResponseDTO.builder().title(report.getTitle()).content(report.getContent())
                .created_date(report.getDate()).status(report.getStatus()).build();
    }

    public Report toReport(int account_id, ReportAddRequestDTO reportAddRequestDTO) {
        Date currentDate = dateUtil.getCurrentDate();

        return Report.builder().account_id(account_id).date(currentDate).title(reportAddRequestDTO.getTitle())
                .content(reportAddRequestDTO.getContent()).status(0).build();
    }
}
