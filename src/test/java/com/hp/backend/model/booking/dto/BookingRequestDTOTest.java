package com.hp.backend.model.booking.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookingRequestDTOTest {
    @Test
    void testBuilder() {
        BookingRequestDTO dto = BookingRequestDTO.builder()
                .id(1)
                .build();

        assertEquals(1, dto.getId());
    }

    @Test
    void testCanEqual() {
        BookingRequestDTO dto1 = BookingRequestDTO.builder().id(1).build();
        BookingRequestDTO dto2 = BookingRequestDTO.builder().id(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        BookingRequestDTO dto1 = BookingRequestDTO.builder().id(1).build();
        BookingRequestDTO dto2 = BookingRequestDTO.builder().id(1).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetId() {
        BookingRequestDTO dto = new BookingRequestDTO();
        int id = 1;
        dto.setId(id);
        assertEquals(id, dto.getId());
    }

    @Test
    void testHashCode() {
        BookingRequestDTO dto1 = BookingRequestDTO.builder().id(1).build();
        BookingRequestDTO dto2 = BookingRequestDTO.builder().id(1).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetId() {
        BookingRequestDTO dto = new BookingRequestDTO();
        int id = 1;
        dto.setId(id);
        assertEquals(id, dto.getId());
    }

    @Test
    void testToString() {
        BookingRequestDTO dto = BookingRequestDTO.builder()
                .id(1)
                .build();

        String expectedToString = "BookingRequestDTO(id=1)";
        assertEquals(expectedToString, dto.toString());
    }
}
