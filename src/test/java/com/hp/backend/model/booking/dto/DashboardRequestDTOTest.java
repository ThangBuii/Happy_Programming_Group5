package com.hp.backend.model.booking.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DashboardRequestDTOTest {
    @Test
    void testBuilder() {
        DashboardRequestDTO dto = DashboardRequestDTO.builder()
                .id(1)
                .build();

        assertEquals(1, dto.getId());
    }

    @Test
    void testCanEqual() {
        DashboardRequestDTO dto1 = DashboardRequestDTO.builder().id(1).build();
        DashboardRequestDTO dto2 = DashboardRequestDTO.builder().id(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        DashboardRequestDTO dto1 = DashboardRequestDTO.builder().id(1).build();
        DashboardRequestDTO dto2 = DashboardRequestDTO.builder().id(1).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetId() {
        DashboardRequestDTO dto = new DashboardRequestDTO();
        int id = 1;
        dto.setId(id);
        assertEquals(id, dto.getId());
    }

    @Test
    void testHashCode() {
        DashboardRequestDTO dto1 = DashboardRequestDTO.builder().id(1).build();
        DashboardRequestDTO dto2 = DashboardRequestDTO.builder().id(1).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetId() {
        DashboardRequestDTO dto = new DashboardRequestDTO();
        int id = 1;
        dto.setId(id);
        assertEquals(id, dto.getId());
    }

    @Test
    void testToString() {
        DashboardRequestDTO dto = DashboardRequestDTO.builder()
                .id(1)
                .build();

        String expectedToString = "DashboardRequestDTO(id=1)";
        assertEquals(expectedToString, dto.toString());
    }
}
