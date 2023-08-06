package com.hp.backend.model.report.mapper;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Report;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.report.dto.ReportAddRequestDTO;
import com.hp.backend.model.report.dto.ReportDetailResponseDTO;
import com.hp.backend.model.report.dto.ReportListMentorMenteeResponseDTO;
import com.hp.backend.model.report.dto.ReportListResponseDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.service.report.ReportService;
import com.hp.backend.utils.CommonUtils;

public class ReportMapperTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CommonUtils commonUtils;

    @InjectMocks
    private ReportMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   @Test
    void toReport_ValidRequest_ReturnsReport() {
        // Arrange
        int accountId = 1;

        ReportAddRequestDTO reportRequest = new ReportAddRequestDTO();
        reportRequest.setTitle("reportTitle");
        reportRequest.setContent("reportContent");
        // Set other properties if necessary

        // Act
        Report report = mapper.toReport(accountId, reportRequest);

        // Assert
        Assertions.assertEquals(accountId, report.getAccount_id());
        Assertions.assertEquals(reportRequest.getTitle(), report.getTitle());
        Assertions.assertEquals(reportRequest.getContent(), report.getContent());
        Assertions.assertEquals(0, report.getStatus());
        // Assert for currentDate if needed
    }

    @Test
    void toReportDetailResponseDTO_ValidReport_ReturnsReportDetailResponseDTO() throws CustomInternalServerException {
        // Arrange
        int accountId = 1;
        int reportId = 2;

        Report report = new Report();
        report.setAccount_id(accountId);
        report.setReport_id(reportId);
        report.setTitle("reportTitle");
        report.setContent("reportContent");
        report.setAnswer("reportAnswer");
        report.setDate(new Date(System.currentTimeMillis()));
        // Set other properties if necessary

        Account account = new Account();
        account.setAccount_id(accountId);
        // Set other account properties if necessary

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act
        ReportDetailResponseDTO response = mapper.toReportDetailResponseDTO(report);

        // Assert
        Assertions.assertEquals(report.getContent(), response.getContent());
        Assertions.assertEquals(report.getAnswer(), response.getAnswer());
        Assertions.assertEquals(report.getTitle(), response.getTitle());
        Assertions.assertEquals(reportId, response.getReport_id());
        Assertions.assertEquals(report.getDate(), response.getCreated_date());
    }
    
    @Test
    void toReportDetailResponseDTO_ReportSenderAccountNotFound_ThrowsCustomInternalServerException() {
        // Arrange
        int accountId = 1;
        int reportId = 2;

        Report report = new Report();
        report.setAccount_id(accountId);
        report.setReport_id(reportId);
        report.setTitle("reportTitle");
        report.setContent("reportContent");
        report.setAnswer("reportAnswer");
        report.setDate(new Date(System.currentTimeMillis()));
        // Set other properties if necessary

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(CustomInternalServerException.class, () -> {
            mapper.toReportDetailResponseDTO(report);
        });
    }

    @Test
    void toReportListMenteeMentorResponseDTO_ValidReport_ReturnsReportListMentorMenteeResponseDTO() {
        // Arrange
        Report report = new Report();
        report.setReport_id(1);
        report.setTitle("reportTitle");
        report.setContent("reportContent");
        report.setDate(new Date(System.currentTimeMillis()));
        report.setStatus(1);
        // Set other properties if necessary

        // Act
        ReportListMentorMenteeResponseDTO response = mapper.toReportListMenteeMentorResponseDTO(report);

        // Assert
        Assertions.assertEquals("reportTitle", response.getTitle());
        Assertions.assertEquals("reportContent", response.getContent());
        Assertions.assertEquals(report.getDate(), response.getCreated_date());
        Assertions.assertEquals(1, response.getStatus());
        Assertions.assertEquals(1, response.getReport_id());
    }

    @Test
    void testToReportListResponseDTO() throws CustomInternalServerException {
        int accountId = 1;
        int reportId = 2;

        Report report = new Report();
        report.setAccount_id(accountId);
        report.setReport_id(reportId);
        report.setTitle("reportTitle");
        report.setContent("reportContent");
        report.setStatus(1);
        // Set other properties if necessary

        Account account = new Account();
        account.setAccount_id(accountId);
        account.setUsername("accountUsername");
        account.setAvatar("accountAvatar".getBytes());
        account.setEmail("accountEmail");
        account.setRole(1);
        // Set other account properties if necessary

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(commonUtils.imageToFrontEnd(account.getAvatar())).thenReturn("convertedAccountAvatar");

        // Act
        ReportListResponseDTO response = mapper.toReportListResponseDTO(report);

        // Assert
        Assertions.assertEquals("accountUsername", response.getUsername());
        Assertions.assertEquals("convertedAccountAvatar", response.getAvatar());
        Assertions.assertEquals("accountEmail", response.getEmail());
        Assertions.assertEquals(report.getTitle(), response.getTitle());
        Assertions.assertEquals(reportId, response.getReport_id());
        Assertions.assertEquals(report.getDate(), response.getCreated_date());
        Assertions.assertEquals(report.getContent(), response.getContent());
        Assertions.assertEquals(1, response.getRole());
        Assertions.assertEquals(report.getStatus(), response.getStatus());
    }

    @Test
    void toReportListResponseDTO_ReportSenderAccountNotFound_ThrowsCustomInternalServerException() {
        // Arrange
        int accountId = 1;
        int reportId = 2;

        Report report = new Report();
        report.setAccount_id(accountId);
        report.setReport_id(reportId);
        report.setTitle("reportTitle");
        report.setContent("reportContent");
        report.setStatus(1);
        // Set other properties if necessary

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(CustomInternalServerException.class, () -> {
            mapper.toReportListResponseDTO(report);
        });
    }
}
