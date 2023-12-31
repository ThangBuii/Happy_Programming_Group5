package com.hp.backend.model.report.mapper;

import java.sql.Date;
import java.util.Optional;

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
import com.hp.backend.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReportMapper {
    private final AccountRepository accountRepository;
    private final CommonUtils commonUtils;

    public ReportListResponseDTO toReportListResponseDTO(Report report) throws CustomInternalServerException {
        Optional<Account> account = accountRepository.findById(report.getAccount_id());

        if (!account.isPresent()) {
            throw new CustomInternalServerException(
                    CustomError.builder().message("Report sender is not exist").code("500").build());
        }

        return ReportListResponseDTO.builder().username(account.get().getUsername())
                .avatar(commonUtils.imageToFrontEnd(account.get().getAvatar()))
                .email(account.get().getEmail())
                .title(report.getTitle()).report_id(report.getReport_id())
                .created_date(report.getDate()).content(report.getContent())
                .role(account.get().getRole()).status(report.getStatus()).build();
    }

    public ReportDetailResponseDTO toReportDetailResponseDTO(Report report) throws CustomInternalServerException {
        Optional<Account> account = accountRepository.findById(report.getAccount_id());

        if (!account.isPresent()) {
            throw new CustomInternalServerException(
                    CustomError.builder().message("Report sender is not exist").code("500").build());
        }

        return ReportDetailResponseDTO.builder()
                .content(report.getContent()).answer(report.getAnswer())
                .title(report.getTitle()).report_id(report.getReport_id())
                .created_date(report.getDate())
                .build();
    }

    public ReportListMentorMenteeResponseDTO toReportListMenteeMentorResponseDTO(Report report) {
        return ReportListMentorMenteeResponseDTO.builder().title(report.getTitle()).content(report.getContent())
                .created_date(report.getDate()).status(report.getStatus()).report_id(report.getReport_id()).build();
    }

    public Report toReport(int account_id, ReportAddRequestDTO reportAddRequestDTO) {
        Date currentDate = commonUtils.getCurrentDate();

        return Report.builder().account_id(account_id).date(currentDate).title(reportAddRequestDTO.getTitle())
                .content(reportAddRequestDTO.getContent()).status(0).build();
    }
}
