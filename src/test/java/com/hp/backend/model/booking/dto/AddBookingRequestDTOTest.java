package com.hp.backend.model.booking.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddBookingRequestDTOTest {
    @Test
    void testBuilder() {
        AddBookingRequestDTO dto = AddBookingRequestDTO.builder().time_id(42).build();
        assertEquals(42, dto.getTime_id());
    }

    @Test
    void testCanEqual() {
        AddBookingRequestDTO dto1 = AddBookingRequestDTO.builder().time_id(42).build();
        AddBookingRequestDTO dto2 = AddBookingRequestDTO.builder().time_id(42).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        AddBookingRequestDTO dto1 = AddBookingRequestDTO.builder().time_id(42).build();
        AddBookingRequestDTO dto2 = AddBookingRequestDTO.builder().time_id(42).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetTime_id() {
        AddBookingRequestDTO dto = new AddBookingRequestDTO();
        dto.setTime_id(42);
        assertEquals(42, dto.getTime_id());
    }

    @Test
    void testHashCode() {
        AddBookingRequestDTO dto1 = AddBookingRequestDTO.builder().time_id(42).build();
        AddBookingRequestDTO dto2 = AddBookingRequestDTO.builder().time_id(42).build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetTime_id() {
        AddBookingRequestDTO dto = new AddBookingRequestDTO();
        dto.setTime_id(42);
        assertEquals(42, dto.getTime_id());
    }

    @Test
    void testToString() {
        AddBookingRequestDTO dto = AddBookingRequestDTO.builder().time_id(42).build();
        assertEquals("AddBookingRequestDTO(time_id=42)", dto.toString());
    }
}
