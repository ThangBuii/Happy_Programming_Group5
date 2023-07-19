package com.hp.backend.model.booking.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookingListMenteeDTOTest {
    @Test
    void testBuilder() {
        BookingListMenteeDTO dto = BookingListMenteeDTO.builder()
                .bookingID(1)
                .username("mentee_user")
                .avatar("mentee_avatar.jpg")
                .email("mentee@example.com")
                .scheduleDate(java.sql.Date.valueOf("2023-07-19"))
                .scheduleTime("14:00 - 16:00")
                .build();

        assertEquals(1, dto.getBookingID());
        assertEquals("mentee_user", dto.getUsername());
        assertEquals("mentee_avatar.jpg", dto.getAvatar());
        assertEquals("mentee@example.com", dto.getEmail());
        assertEquals(java.sql.Date.valueOf("2023-07-19"), dto.getScheduleDate());
        assertEquals("14:00 - 16:00", dto.getScheduleTime());
    }

    @Test
    void testCanEqual() {
        BookingListMenteeDTO dto1 = BookingListMenteeDTO.builder().bookingID(1).build();
        BookingListMenteeDTO dto2 = BookingListMenteeDTO.builder().bookingID(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        BookingListMenteeDTO dto1 = BookingListMenteeDTO.builder().bookingID(1).build();
        BookingListMenteeDTO dto2 = BookingListMenteeDTO.builder().bookingID(1).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetAvatar() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        String avatar = "mentee_avatar.jpg";
        dto.setAvatar(avatar);
        assertEquals(avatar, dto.getAvatar());
    }

    @Test
    void testGetBookingID() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        int bookingID = 1;
        dto.setBookingID(bookingID);
        assertEquals(bookingID, dto.getBookingID());
    }

    @Test
    void testGetEmail() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        String email = "mentee@example.com";
        dto.setEmail(email);
        assertEquals(email, dto.getEmail());
    }

    @Test
    void testGetScheduleDate() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        java.sql.Date scheduleDate = java.sql.Date.valueOf("2023-07-19");
        dto.setScheduleDate(scheduleDate);
        assertEquals(scheduleDate, dto.getScheduleDate());
    }

    @Test
    void testGetScheduleTime() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        String scheduleTime = "14:00 - 16:00";
        dto.setScheduleTime(scheduleTime);
        assertEquals(scheduleTime, dto.getScheduleTime());
    }

    @Test
    void testGetUsername() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        String username = "mentee_user";
        dto.setUsername(username);
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testHashCode() {
        BookingListMenteeDTO dto1 = BookingListMenteeDTO.builder().bookingID(1).build();
        BookingListMenteeDTO dto2 = BookingListMenteeDTO.builder().bookingID(1).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetAvatar() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        String avatar = "mentee_avatar.jpg";
        dto.setAvatar(avatar);
        assertEquals(avatar, dto.getAvatar());
    }

    @Test
    void testSetBookingID() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        int bookingID = 1;
        dto.setBookingID(bookingID);
        assertEquals(bookingID, dto.getBookingID());
    }

    @Test
    void testSetEmail() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        String email = "mentee@example.com";
        dto.setEmail(email);
        assertEquals(email, dto.getEmail());
    }

    @Test
    void testSetScheduleDate() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        java.sql.Date scheduleDate = java.sql.Date.valueOf("2023-07-19");
        dto.setScheduleDate(scheduleDate);
        assertEquals(scheduleDate, dto.getScheduleDate());
    }

    @Test
    void testSetScheduleTime() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        String scheduleTime = "14:00 - 16:00";
        dto.setScheduleTime(scheduleTime);
        assertEquals(scheduleTime, dto.getScheduleTime());
    }

    @Test
    void testSetUsername() {
        BookingListMenteeDTO dto = new BookingListMenteeDTO();
        String username = "mentee_user";
        dto.setUsername(username);
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testToString() {
        BookingListMenteeDTO dto = BookingListMenteeDTO.builder()
                .bookingID(1)
                .username("mentee_user")
                .avatar("mentee_avatar.jpg")
                .email("mentee@example.com")
                .scheduleDate(java.sql.Date.valueOf("2023-07-19"))
                .scheduleTime("14:00 - 16:00")
                .build();

        String expectedToString = "BookingListMenteeDTO(bookingID=1, username=mentee_user, " +
                "avatar=mentee_avatar.jpg, email=mentee@example.com, " +
                "scheduleDate=2023-07-19, scheduleTime=14:00 - 16:00)";
        assertEquals(expectedToString, dto.toString());
    }
}
