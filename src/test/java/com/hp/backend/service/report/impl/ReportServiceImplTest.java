package com.hp.backend.service.report.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import com.hp.backend.entity.Report;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.report.dto.ReportAddRequestDTO;
import com.hp.backend.model.report.dto.ReportDetailResponseDTO;
import com.hp.backend.model.report.dto.ReportListMentorMenteeResponseDTO;
import com.hp.backend.model.report.dto.ReportListResponseDTO;
import com.hp.backend.model.report.dto.ReportUpdateRequestDTO;
import com.hp.backend.model.report.mapper.ReportMapper;
import com.hp.backend.repository.ReportRepository;

class ReportServiceImplTest {
    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReportMapper reportMapper;

    @InjectMocks
    private ReportServiceImpl reportService;

    @Test
    void testAddReport() throws CustomBadRequestException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;

        ReportAddRequestDTO reportAddRequestDTO = new ReportAddRequestDTO();
        reportAddRequestDTO.setTitle("Test Title");
        reportAddRequestDTO.setContent("Test Content");

        Report report = new Report();
        // Set up the report object with the necessary data

        when(reportMapper.toReport(accountId, reportAddRequestDTO)).thenReturn(report);

        // Act
        assertDoesNotThrow(() -> {
            reportService.addReport(accountId, reportAddRequestDTO);
        });

        // Assert
        verify(reportRepository, times(1)).save(report);
    }

    @Test
    void testAddReport_EmptyContent() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;

        ReportAddRequestDTO reportAddRequestDTO = new ReportAddRequestDTO();
        reportAddRequestDTO.setTitle("Test Title");
        reportAddRequestDTO.setContent("");

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> {
            reportService.addReport(accountId, reportAddRequestDTO);
        });
    }

    @Test
    void testAddReport_EmptyTitle() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;

        ReportAddRequestDTO reportAddRequestDTO = new ReportAddRequestDTO();
        reportAddRequestDTO.setTitle("");
        reportAddRequestDTO.setContent("Test Content");

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> {
            reportService.addReport(accountId, reportAddRequestDTO);
        });
    }

    @Test
    void testDeleteReport() throws CustomBadRequestException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int reportId = 1;

        Report report = new Report();
        // Set up the report object with the necessary data

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        // Act
        assertDoesNotThrow(() -> {
            reportService.deleteReport(reportId);
        });

        // Assert
        verify(reportRepository, times(1)).delete(report);
    }

    @Test
    void testDeleteReport_ReportNotFound() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int reportId = 1;

        when(reportRepository.findById(reportId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> {
            reportService.deleteReport(reportId);
        });
    }

    @Test
    void testGetReportDetail() throws CustomInternalServerException, CustomBadRequestException {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int reportId = 1;

        Report report = new Report();
        // Set up the report object with the necessary data

        ReportDetailResponseDTO expectedResponseDTO = ReportDetailResponseDTO.builder().build();
        // Set up the expected response DTO with the necessary data

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));
        when(reportMapper.toReportDetailResponseDTO(report)).thenReturn(expectedResponseDTO);

        // Act
        ReportDetailResponseDTO actualResponseDTO = reportService.getReportDetail(reportId);

        // Assert
        assertEquals(expectedResponseDTO, actualResponseDTO);
    }

    @Test
    void testGetReportDetail_ReportNotFound() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int reportId = 1;

        when(reportRepository.findById(reportId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> {
            reportService.getReportDetail(reportId);
        });
    }

    @Test
    void testGetReports() throws CustomInternalServerException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        List<Report> reports = new ArrayList<>();
        Report report1 = new Report();
        Report report2 = new Report();
        reports.add(report1);
        reports.add(report2);

        List<ReportListResponseDTO> expectedResponseDTOs = new ArrayList<>();
        ReportListResponseDTO responseDTO1 = ReportListResponseDTO.builder().build();
        ReportListResponseDTO responseDTO2 = ReportListResponseDTO.builder().build();
        expectedResponseDTOs.add(responseDTO1);
        expectedResponseDTOs.add(responseDTO2);

        when(reportRepository.findAll()).thenReturn(reports);
        when(reportMapper.toReportListResponseDTO(report1)).thenReturn(responseDTO1);
        when(reportMapper.toReportListResponseDTO(report2)).thenReturn(responseDTO2);

        // Act
        List<ReportListResponseDTO> actualResponseDTOs = reportService.getReports();

        // Assert
        assertEquals(expectedResponseDTOs.size(), actualResponseDTOs.size());
        assertEquals(expectedResponseDTOs.get(0), actualResponseDTOs.get(0));
        assertEquals(expectedResponseDTOs.get(1), actualResponseDTOs.get(1));
    }

    @Test
    void testGetReportsMentorMentee() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;

        List<Report> reports = new ArrayList<>();
        Report report1 = new Report();
        Report report2 = new Report();
        reports.add(report1);
        reports.add(report2);

        List<ReportListMentorMenteeResponseDTO> expectedResponseDTOs = new ArrayList<>();
        ReportListMentorMenteeResponseDTO responseDTO1 = ReportListMentorMenteeResponseDTO.builder().build();
        ReportListMentorMenteeResponseDTO responseDTO2 = ReportListMentorMenteeResponseDTO.builder().build();
        expectedResponseDTOs.add(responseDTO1);
        expectedResponseDTOs.add(responseDTO2);

        when(reportRepository.findByAccountId(accountId)).thenReturn(reports);
        when(reportMapper.toReportListMenteeMentorResponseDTO(report1)).thenReturn(responseDTO1);
        when(reportMapper.toReportListMenteeMentorResponseDTO(report2)).thenReturn(responseDTO2);

        // Act
        List<ReportListMentorMenteeResponseDTO> actualResponseDTOs = reportService.getReportsMentorMentee(accountId);

        // Assert
        assertEquals(expectedResponseDTOs.size(), actualResponseDTOs.size());
        assertEquals(expectedResponseDTOs.get(0), actualResponseDTOs.get(0));
        assertEquals(expectedResponseDTOs.get(1), actualResponseDTOs.get(1));
    }

    @Test
    void testUpdateReport() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int reportId = 1;
        String answer = "Answer";

        ReportUpdateRequestDTO reportUpdateRequestDTO = ReportUpdateRequestDTO.builder().build();
        reportUpdateRequestDTO.setReport_id(reportId);
        reportUpdateRequestDTO.setAnswer(answer);

        Report report = new Report();
        report.setStatus(0); // Initial status

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        // Act
        assertDoesNotThrow(() -> {
            reportService.updateReport(reportUpdateRequestDTO);
        });

        // Assert
        assertEquals(1, report.getStatus());
        assertEquals(answer, report.getAnswer());
        verify(reportRepository, times(1)).save(report);
    }

    @Test
    void testUpdateReport_ReportNotFound() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int reportId = 1;
        String answer = "Answer";

        ReportUpdateRequestDTO reportUpdateRequestDTO = ReportUpdateRequestDTO.builder().report_id(reportId)
                .answer(answer).build();

        when(reportRepository.findById(reportId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> {
            reportService.updateReport(reportUpdateRequestDTO);
        });
    }
}
