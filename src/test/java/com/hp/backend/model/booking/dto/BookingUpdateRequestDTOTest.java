package com.hp.backend.model.booking.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookingUpdateRequestDTOTest {
    @Test
    void testBuilder() {
        BookingUpdateRequestDTO dto = BookingUpdateRequestDTO.builder()
                .status(1)
                .build();

        assertEquals(1, dto.getStatus());
    }

    @Test
    void testCanEqual() {
        BookingUpdateRequestDTO dto1 = BookingUpdateRequestDTO.builder().status(1).build();
        BookingUpdateRequestDTO dto2 = BookingUpdateRequestDTO.builder().status(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        BookingUpdateRequestDTO dto1 = BookingUpdateRequestDTO.builder().status(1).build();
        BookingUpdateRequestDTO dto2 = BookingUpdateRequestDTO.builder().status(1).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetStatus() {
        BookingUpdateRequestDTO dto = new BookingUpdateRequestDTO();
        int status = 1;
        dto.setStatus(status);
        assertEquals(status, dto.getStatus());
    }

    @Test
    void testHashCode() {
        BookingUpdateRequestDTO dto1 = BookingUpdateRequestDTO.builder().status(1).build();
        BookingUpdateRequestDTO dto2 = BookingUpdateRequestDTO.builder().status(1).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetStatus() {
        BookingUpdateRequestDTO dto = new BookingUpdateRequestDTO();
        int status = 1;
        dto.setStatus(status);
        assertEquals(status, dto.getStatus());
    }

    @Test
    void testToString() {
        BookingUpdateRequestDTO dto = BookingUpdateRequestDTO.builder()
                .status(1)
                .build();

        String expectedToString = "BookingUpdateRequestDTO(status=1)";
        assertEquals(expectedToString, dto.toString());
    }
}
