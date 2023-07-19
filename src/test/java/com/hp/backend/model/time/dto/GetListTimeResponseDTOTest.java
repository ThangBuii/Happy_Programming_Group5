package com.hp.backend.model.time.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetListTimeResponseDTOTest {
    @Test
    void testBuilder() {
        String startTime = "09:00 AM";
        int timeId = 1;

        GetListTimeResponseDTO dto = GetListTimeResponseDTO.builder()
                .start_time(startTime)
                .time_id(timeId)
                .build();

        assertEquals(startTime, dto.getStart_time());
        assertEquals(timeId, dto.getTime_id());
    }

    @Test
    void testCanEqual() {
        GetListTimeResponseDTO dto1 = GetListTimeResponseDTO.builder().time_id(1).build();
        GetListTimeResponseDTO dto2 = GetListTimeResponseDTO.builder().time_id(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        GetListTimeResponseDTO dto1 = GetListTimeResponseDTO.builder().time_id(1).build();
        GetListTimeResponseDTO dto2 = GetListTimeResponseDTO.builder().time_id(1).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetStart_time() {
        GetListTimeResponseDTO dto = new GetListTimeResponseDTO();
        String startTime = "09:00 AM";
        dto.setStart_time(startTime);
        assertEquals(startTime, dto.getStart_time());
    }

    @Test
    void testGetTime_id() {
        GetListTimeResponseDTO dto = new GetListTimeResponseDTO();
        int timeId = 1;
        dto.setTime_id(timeId);
        assertEquals(timeId, dto.getTime_id());
    }

    @Test
    void testHashCode() {
        GetListTimeResponseDTO dto1 = GetListTimeResponseDTO.builder().time_id(1).build();
        GetListTimeResponseDTO dto2 = GetListTimeResponseDTO.builder().time_id(1).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetStart_time() {
        GetListTimeResponseDTO dto = new GetListTimeResponseDTO();
        String startTime = "09:00 AM";
        dto.setStart_time(startTime);
        assertEquals(startTime, dto.getStart_time());
    }

    @Test
    void testSetTime_id() {
        GetListTimeResponseDTO dto = new GetListTimeResponseDTO();
        int timeId = 1;
        dto.setTime_id(timeId);
        assertEquals(timeId, dto.getTime_id());
    }

    @Test
    void testToString() {
        GetListTimeResponseDTO dto = GetListTimeResponseDTO.builder()
                .start_time("09:00 AM")
                .time_id(1)
                .build();

        String expectedToString = "GetListTimeResponseDTO(start_time=09:00 AM, time_id=1)";
        assertEquals(expectedToString, dto.toString());
    }
}
