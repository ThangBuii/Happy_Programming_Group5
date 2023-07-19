package com.hp.backend.model.time.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class AddTimeRequestDTOTest {
    @Test
    void testBuilder() {
        Date date = Date.valueOf("2023-07-19");
        String startTime = "09:00 AM";
        String endTime = "11:00 AM";
        int sessionId = 1;

        AddTimeRequestDTO dto = AddTimeRequestDTO.builder()
                .date(date)
                .start_time(startTime)
                .end_time(endTime)
                .session_id(sessionId)
                .build();

        assertEquals(date, dto.getDate());
        assertEquals(startTime, dto.getStart_time());
        assertEquals(endTime, dto.getEnd_time());
        assertEquals(sessionId, dto.getSession_id());
    }

    @Test
    void testCanEqual() {
        AddTimeRequestDTO dto1 = AddTimeRequestDTO.builder().date(Date.valueOf("2023-07-19")).build();
        AddTimeRequestDTO dto2 = AddTimeRequestDTO.builder().date(Date.valueOf("2023-07-19")).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        AddTimeRequestDTO dto1 = AddTimeRequestDTO.builder().date(Date.valueOf("2023-07-19")).build();
        AddTimeRequestDTO dto2 = AddTimeRequestDTO.builder().date(Date.valueOf("2023-07-19")).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetDate() {
        AddTimeRequestDTO dto = new AddTimeRequestDTO();
        Date date = Date.valueOf("2023-07-19");
        dto.setDate(date);
        assertEquals(date, dto.getDate());
    }

    @Test
    void testGetEnd_time() {
        AddTimeRequestDTO dto = new AddTimeRequestDTO();
        String endTime = "11:00 AM";
        dto.setEnd_time(endTime);
        assertEquals(endTime, dto.getEnd_time());
    }

    @Test
    void testGetSession_id() {
        AddTimeRequestDTO dto = new AddTimeRequestDTO();
        int sessionId = 1;
        dto.setSession_id(sessionId);
        assertEquals(sessionId, dto.getSession_id());
    }

    @Test
    void testGetStart_time() {
        AddTimeRequestDTO dto = new AddTimeRequestDTO();
        String startTime = "09:00 AM";
        dto.setStart_time(startTime);
        assertEquals(startTime, dto.getStart_time());
    }

    @Test
    void testHashCode() {
        AddTimeRequestDTO dto1 = AddTimeRequestDTO.builder().date(Date.valueOf("2023-07-19")).build();
        AddTimeRequestDTO dto2 = AddTimeRequestDTO.builder().date(Date.valueOf("2023-07-19")).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetDate() {
        AddTimeRequestDTO dto = new AddTimeRequestDTO();
        Date date = Date.valueOf("2023-07-19");
        dto.setDate(date);
        assertEquals(date, dto.getDate());
    }

    @Test
    void testSetEnd_time() {
        AddTimeRequestDTO dto = new AddTimeRequestDTO();
        String endTime = "11:00 AM";
        dto.setEnd_time(endTime);
        assertEquals(endTime, dto.getEnd_time());
    }

    @Test
    void testSetSession_id() {
        AddTimeRequestDTO dto = new AddTimeRequestDTO();
        int sessionId = 1;
        dto.setSession_id(sessionId);
        assertEquals(sessionId, dto.getSession_id());
    }

    @Test
    void testSetStart_time() {
        AddTimeRequestDTO dto = new AddTimeRequestDTO();
        String startTime = "09:00 AM";
        dto.setStart_time(startTime);
        assertEquals(startTime, dto.getStart_time());
    }

    @Test
    void testToString() {
        AddTimeRequestDTO dto = AddTimeRequestDTO.builder()
                .date(Date.valueOf("2023-07-19"))
                .start_time("09:00 AM")
                .end_time("11:00 AM")
                .session_id(1)
                .build();

        String expectedToString = "AddTimeRequestDTO(date=2023-07-19, start_time=09:00 AM, end_time=11:00 AM, session_id=1)";
        assertEquals(expectedToString, dto.toString());
    }
}
