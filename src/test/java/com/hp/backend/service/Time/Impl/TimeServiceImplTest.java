package com.hp.backend.service.Time.Impl;

import static org.junit.jupiter.api.Assertions.*;
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
import com.hp.backend.model.time.dto.GetListTimeResponseFindMentorDTO;
import com.hp.backend.model.time.mapper.TimeMapper;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.repository.TimeRepository;

class TimeServiceImplTest {
class TimeServiceImplTest {
    @Mock
    private TimeRepository timeRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private TimeMapper timeMapper;

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
        Date startDate = Date.valueOf("2023-07-19");

        Times time1 = new Times();
        time1.setTime_id(1);
        time1.setStart_time(Time.valueOf("09:00:00"));
        time1.setEnd_time(Time.valueOf("10:00:00"));
        time1.setStart_date(startDate);

        Times time2 = new Times();
        time2.setTime_id(2);
        time2.setStart_time(Time.valueOf("13:00:00"));
        time2.setEnd_time(Time.valueOf("14:00:00"));
        time2.setStart_date(startDate);

        List<Times> timesList = new ArrayList<>();
        timesList.add(time1);
        timesList.add(time2);

        GetListTimeRequestDTO requestDTO = GetListTimeRequestDTO.builder()
                .session_id(sessionId)
                .start_date(startDate)
                .build();

        GetListTimeResponseDTO responseDTO1 = GetListTimeResponseDTO.builder()
                .time_id(time1.getTime_id())
                .start_time(time1.getStart_time().toString())
                .build();

        GetListTimeResponseDTO responseDTO2 = GetListTimeResponseDTO.builder()
                .time_id(time2.getTime_id())
                .start_time(time2.getStart_time().toString())
                .build();

        List<GetListTimeResponseDTO> expectedResponse = new ArrayList<>();
        expectedResponse.add(responseDTO1);
        expectedResponse.add(responseDTO2);

        // Set up mock behavior
        when(timeRepository.findStartTimeAndEndTime(sessionId, startDate)).thenReturn(timesList);
        when(timeMapper.toGetListTimeResponseDTO(time1)).thenReturn(responseDTO1);
        when(timeMapper.toGetListTimeResponseDTO(time2)).thenReturn(responseDTO2);

        // Call the method to be tested
        List<GetListTimeResponseDTO> actualResponse = timeService.getAllTime(requestDTO);

        // Verify the results
        assertEquals(expectedResponse.size(), actualResponse.size());
        assertEquals(expectedResponse.get(0).getTime_id(), actualResponse.get(0).getTime_id());
        assertEquals(expectedResponse.get(0).getStart_time(), actualResponse.get(0).getStart_time());
        assertEquals(expectedResponse.get(1).getTime_id(), actualResponse.get(1).getTime_id());
        assertEquals(expectedResponse.get(1).getStart_time(), actualResponse.get(1).getStart_time());

        // Verify the interactions with mocks
        verify(timeRepository, times(1)).findStartTimeAndEndTime(sessionId, startDate);
        verify(timeMapper, times(1)).toGetListTimeResponseDTO(time1);
        verify(timeMapper, times(1)).toGetListTimeResponseDTO(time2);
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


}
