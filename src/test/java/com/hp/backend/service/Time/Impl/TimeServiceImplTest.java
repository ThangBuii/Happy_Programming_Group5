package com.hp.backend.service.Time.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Session;
import com.hp.backend.entity.Times;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.time.dto.AddTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseDTO;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.repository.TimeRepository;

public class TimeServiceImplTest {
    @Mock
    private TimeRepository timeRepository;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private TimeServiceImpl timeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTime() {
        // Mock data
        int sessionId = 1;
        Date startDate = Date.valueOf("2023-07-17");
        GetListTimeRequestDTO requestDTO = new GetListTimeRequestDTO(sessionId, startDate);
        List<Times> mockTimesList = new ArrayList<>();
        mockTimesList.add(new Times(1, Time.valueOf("10:00:00"), Time.valueOf("11:00:00"), new Session(), startDate));

        // Mock repository method
        when(timeRepository.findStartTimeAndEndTime(sessionId, startDate)).thenReturn(mockTimesList);

        // Call the service method
        List<GetListTimeResponseDTO> result = timeService.getAllTime(requestDTO);

        // Assertions
        assertEquals(1, result.size());
        GetListTimeResponseDTO responseDTO = result.get(0);
        assertEquals("10:00:00-11:00:00", responseDTO.getStart_time());
        assertEquals(1, responseDTO.getTime_id());
    }


    @Test
    void testAddTimeInvalidDate() {
        // Mock data
        int accountId = 1;
        Date date = Date.valueOf("2023-07-17");
        String startTimeStr = "10:00:00";
        String endTimeStr = "11:00:00";
        int sessionId = 1;
        AddTimeRequestDTO addTimeRequestDTO = new AddTimeRequestDTO(date, startTimeStr, endTimeStr, sessionId);

        // Call the service method and assert the exception
        assertThrows(CustomBadRequestException.class, () -> timeService.addTime(accountId, addTimeRequestDTO));
    }

    @Test
    void testAddTimeInvalidDuration() {
        // Mock data
        int accountId = 1;
        Date date = Date.valueOf("2023-07-17");
        String startTimeStr = "10:00:00";
        String endTimeStr = "11:30:00"; // End time with 30 minutes duration, but the session requires 60 minutes
        int sessionId = 1;
        AddTimeRequestDTO addTimeRequestDTO = new AddTimeRequestDTO(date, startTimeStr, endTimeStr, sessionId);

        LocalTime startTime = LocalTime.parse(startTimeStr);
        LocalTime endTime = LocalTime.parse(endTimeStr);
        Time startTimeObj = Time.valueOf(startTime);
        Time endTimeObj = Time.valueOf(endTime);

        Session session = Session.builder()
                .mentor_id(accountId)
                .duration(60)
                .build();

        // Mock repository methods
        when(timeRepository.existsByStartTime(startTimeObj, date)).thenReturn(false);
        when(timeRepository.existsByEndTime(endTimeObj, date)).thenReturn(false);
        when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));

        // Call the service method and assert the exception
        assertThrows(CustomBadRequestException.class, () -> timeService.addTime(accountId, addTimeRequestDTO));
    }

    // Write more test cases to cover different scenarios as needed

    // @Test
    // void testAddTime() throws CustomBadRequestException {

    // }

    // @Test
    // void testGetAllTime() {

    // }
}
