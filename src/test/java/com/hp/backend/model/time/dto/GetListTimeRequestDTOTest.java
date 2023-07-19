package com.hp.backend.model.time.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class GetListTimeRequestDTOTest {
    @Test
    void testBuilder() {
        int sessionId = 1;
        Date startDate = Date.valueOf("2023-07-19");

        GetListTimeRequestDTO dto = GetListTimeRequestDTO.builder()
                .session_id(sessionId)
                .start_date(startDate)
                .build();

        assertEquals(sessionId, dto.getSession_id());
        assertEquals(startDate, dto.getStart_date());
    }

    @Test
    void testCanEqual() {
        GetListTimeRequestDTO dto1 = GetListTimeRequestDTO.builder().session_id(1).build();
        GetListTimeRequestDTO dto2 = GetListTimeRequestDTO.builder().session_id(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        AddTimeRequestDTO dto1 = AddTimeRequestDTO.builder().date(Date.valueOf("2023-07-19")).build();
        AddTimeRequestDTO dto2 = AddTimeRequestDTO.builder().date(Date.valueOf("2023-07-19")).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetSession_id() {
        GetListTimeRequestDTO dto = new GetListTimeRequestDTO();
        int sessionId = 1;
        dto.setSession_id(sessionId);
        assertEquals(sessionId, dto.getSession_id());
    }

    @Test
    void testGetStart_date() {
        GetListTimeRequestDTO dto = new GetListTimeRequestDTO();
        Date startDate = Date.valueOf("2023-07-19");
        dto.setStart_date(startDate);
        assertEquals(startDate, dto.getStart_date());
    }

    @Test
    void testHashCode() {
        GetListTimeRequestDTO dto1 = GetListTimeRequestDTO.builder().session_id(1).build();
        GetListTimeRequestDTO dto2 = GetListTimeRequestDTO.builder().session_id(1).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetSession_id() {
        GetListTimeRequestDTO dto = new GetListTimeRequestDTO();
        int sessionId = 1;
        dto.setSession_id(sessionId);
        assertEquals(sessionId, dto.getSession_id());
    }

    @Test
    void testSetStart_date() {
        GetListTimeRequestDTO dto = new GetListTimeRequestDTO();
        Date startDate = Date.valueOf("2023-07-19");
        dto.setStart_date(startDate);
        assertEquals(startDate, dto.getStart_date());
    }

    @Test
    void testToString() {
        GetListTimeRequestDTO dto = GetListTimeRequestDTO.builder()
                .session_id(1)
                .start_date(Date.valueOf("2023-07-19"))
                .build();

        String expectedToString = "GetListTimeRequestDTO(session_id=1, start_date=2023-07-19)";
        assertEquals(expectedToString, dto.toString());
    }
}
