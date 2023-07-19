package com.hp.backend.model.time.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class GetListTimeResponseFindMentorDTOTest {
    @Test
    void testBuilder() {
        Date scheduleDate = Date.valueOf("2023-07-19");
        List<GetListTimeResponseDTO> times = Arrays.asList(
                GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build(),
                GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());

        GetListTimeResponseFindMentorDTO dto = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(scheduleDate)
                .times(times)
                .build();

        assertEquals(scheduleDate, dto.getSchedule_date());
        assertEquals(times, dto.getTimes());
    }

    @Test
    void testCanEqual() {
        List<GetListTimeResponseDTO> times1 = Arrays.asList(
                GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build(),
                GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());
        List<GetListTimeResponseDTO> times2 = Arrays.asList(
                GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build(),
                GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());

        GetListTimeResponseFindMentorDTO dto1 = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(Date.valueOf("2023-07-19"))
                .times(times1)
                .build();

        GetListTimeResponseFindMentorDTO dto2 = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(Date.valueOf("2023-07-19"))
                .times(times2)
                .build();

        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        List<GetListTimeResponseDTO> times1 = Arrays.asList(
                GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build(),
                GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());
        List<GetListTimeResponseDTO> times2 = Arrays.asList(
                GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build(),
                GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());

        GetListTimeResponseFindMentorDTO dto1 = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(Date.valueOf("2023-07-19"))
                .times(times1)
                .build();

        GetListTimeResponseFindMentorDTO dto2 = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(Date.valueOf("2023-07-19"))
                .times(times2)
                .build();

        assertEquals(dto1, dto2);
    }

    @Test
    void testGetSchedule_date() {
        Date scheduleDate = Date.valueOf("2023-07-19");
        List<GetListTimeResponseDTO> times = Arrays.asList(
                GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build(),
                GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());

        GetListTimeResponseFindMentorDTO dto = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(scheduleDate)
                .times(times)
                .build();

        assertEquals(scheduleDate, dto.getSchedule_date());
    }

    @Test
    void testGetTimes() {
        Date scheduleDate = Date.valueOf("2023-07-19");
        List<GetListTimeResponseDTO> times = Arrays.asList(
                GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build(),
                GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());

        GetListTimeResponseFindMentorDTO dto = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(scheduleDate)
                .times(times)
                .build();

        assertEquals(times, dto.getTimes());
    }

    @Test
    void testHashCode() {
        List<GetListTimeResponseDTO> times1 = Arrays.asList(
                GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build(),
                GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());
        List<GetListTimeResponseDTO> times2 = Arrays.asList(
                GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build(),
                GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());

        GetListTimeResponseFindMentorDTO dto1 = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(Date.valueOf("2023-07-19"))
                .times(times1)
                .build();

        GetListTimeResponseFindMentorDTO dto2 = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(Date.valueOf("2023-07-19"))
                .times(times2)
                .build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetSchedule_date() {
        // Create a schedule date
        Date scheduleDate = Date.valueOf("2023-07-19");

        // Create a list of times
        List<GetListTimeResponseDTO> times = new ArrayList<>();
        times.add(GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build());
        times.add(GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());

        // Create a GetListTimeResponseFindMentorDTO using the builder with schedule
        // date and times
        GetListTimeResponseFindMentorDTO dto = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(scheduleDate)
                .times(times)
                .build();

        // Check if the schedule date was set correctly
        assertEquals(scheduleDate, dto.getSchedule_date());
    }

    @Test
    void testSetTimes() {
        // Create a schedule date
        Date scheduleDate = Date.valueOf("2023-07-19");

        // Create a list of times
        List<GetListTimeResponseDTO> times = new ArrayList<>();
        times.add(GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build());
        times.add(GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());

        // Create a GetListTimeResponseFindMentorDTO using the builder with schedule
        // date and times
        GetListTimeResponseFindMentorDTO dto = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(scheduleDate)
                .times(times)
                .build();

        // Check if the list of times was set correctly
        assertEquals(times, dto.getTimes());
    }

    @Test
    void testToString() {
        Date scheduleDate = Date.valueOf("2023-07-19");
        List<GetListTimeResponseDTO> times = Arrays.asList(
                GetListTimeResponseDTO.builder().start_time("09:00 AM").time_id(1).build(),
                GetListTimeResponseDTO.builder().start_time("02:00 PM").time_id(2).build());

        GetListTimeResponseFindMentorDTO dto = GetListTimeResponseFindMentorDTO.builder()
                .schedule_date(scheduleDate)
                .times(times)
                .build();

        String expectedToString = "GetListTimeResponseFindMentorDTO(schedule_date=2023-07-19, times=[GetListTimeResponseDTO(start_time=09:00 AM, time_id=1), GetListTimeResponseDTO(start_time=02:00 PM, time_id=2)])";
        assertEquals(expectedToString, dto.toString());
    }
}
