package com.hp.backend.model.time.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.hp.backend.entity.Times;
import com.hp.backend.model.time.dto.GetListTimeResponseDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseFindMentorDTO;

public class TimeMapperTest {
    @InjectMocks
    private TimeMapper timeMapper;

    @Test
    void testToGetListTimeResponseDTO() {
        // Prepare mock data
        int timeId = 1;
        Time startTime = Time.valueOf("09:00:00");
        Time endTime = Time.valueOf("10:00:00");

        Times time = new Times();
        time.setTime_id(timeId);
        time.setStart_time(startTime);
        time.setEnd_time(endTime);

        // Call the mapper method
        TimeMapper timeMapper = new TimeMapper();
        GetListTimeResponseDTO result = timeMapper.toGetListTimeResponseDTO(time);

        // Check the result
        String expectedTimeRange = "09:00:00-10:00:00";
        assertEquals(timeId, result.getTime_id());
        assertEquals(expectedTimeRange, result.getStart_time());
    }

    @Test
    void testToGetListTimeResponseFindMentorDTO() {
        // Prepare mock data
        Date startDate = Date.valueOf("2023-07-19");

        List<GetListTimeResponseDTO> times = new ArrayList<>();
        times.add(new GetListTimeResponseDTO("09:00:00-10:00:00", 1));
        times.add(new GetListTimeResponseDTO("10:00:00-11:00:00", 2));

        // Call the mapper method
        TimeMapper timeMapper = new TimeMapper();
        GetListTimeResponseFindMentorDTO result = timeMapper.toGetListTimeResponseFindMentorDTO(startDate, times);

        // Check the result
        assertEquals(startDate, result.getSchedule_date());
        assertEquals(times, result.getTimes());

    }
}
