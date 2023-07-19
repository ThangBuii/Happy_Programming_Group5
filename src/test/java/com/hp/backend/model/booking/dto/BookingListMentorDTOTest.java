package com.hp.backend.model.booking.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookingListMentorDTOTest {
    @Test
    void testBuilder() {
        BookingListMentorDTO dto = BookingListMentorDTO.builder()
                .bookingID(1)
                .menteeID(101)
                .avatar("mentor_avatar.jpg")
                .username("mentor_user")
                .email("mentor@example.com")
                .scheduleDate(java.sql.Date.valueOf("2023-07-19"))
                .scheduleTime("14:00 - 16:00")
                .build();

        assertEquals(1, dto.getBookingID());
        assertEquals(101, dto.getMenteeID());
        assertEquals("mentor_avatar.jpg", dto.getAvatar());
        assertEquals("mentor_user", dto.getUsername());
        assertEquals("mentor@example.com", dto.getEmail());
        assertEquals(java.sql.Date.valueOf("2023-07-19"), dto.getScheduleDate());
        assertEquals("14:00 - 16:00", dto.getScheduleTime());
    }

    @Test
    void testCanEqual() {
        BookingListMentorDTO dto1 = BookingListMentorDTO.builder().bookingID(1).build();
        BookingListMentorDTO dto2 = BookingListMentorDTO.builder().bookingID(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        BookingListMentorDTO dto1 = BookingListMentorDTO.builder().bookingID(1).build();
        BookingListMentorDTO dto2 = BookingListMentorDTO.builder().bookingID(1).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetAvatar() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        String avatar = "mentor_avatar.jpg";
        dto.setAvatar(avatar);
        assertEquals(avatar, dto.getAvatar());
    }

    @Test
    void testGetBookingID() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        int bookingID = 1;
        dto.setBookingID(bookingID);
        assertEquals(bookingID, dto.getBookingID());
    }

    @Test
    void testGetEmail() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        String email = "mentor@example.com";
        dto.setEmail(email);
        assertEquals(email, dto.getEmail());
    }

    @Test
    void testGetMenteeID() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        int menteeID = 101;
        dto.setMenteeID(menteeID);
        assertEquals(menteeID, dto.getMenteeID());
    }

    @Test
    void testGetScheduleDate() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        java.sql.Date scheduleDate = java.sql.Date.valueOf("2023-07-19");
        dto.setScheduleDate(scheduleDate);
        assertEquals(scheduleDate, dto.getScheduleDate());
    }

    @Test
    void testGetScheduleTime() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        String scheduleTime = "14:00 - 16:00";
        dto.setScheduleTime(scheduleTime);
        assertEquals(scheduleTime, dto.getScheduleTime());
    }

    @Test
    void testGetUsername() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        String username = "mentor_user";
        dto.setUsername(username);
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testHashCode() {
        BookingListMentorDTO dto1 = BookingListMentorDTO.builder().bookingID(1).build();
        BookingListMentorDTO dto2 = BookingListMentorDTO.builder().bookingID(1).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetAvatar() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        String avatar = "mentor_avatar.jpg";
        dto.setAvatar(avatar);
        assertEquals(avatar, dto.getAvatar());
    }

    @Test
    void testSetBookingID() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        int bookingID = 1;
        dto.setBookingID(bookingID);
        assertEquals(bookingID, dto.getBookingID());
    }

    @Test
    void testSetEmail() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        String email = "mentor@example.com";
        dto.setEmail(email);
        assertEquals(email, dto.getEmail());
    }

    @Test
    void testSetMenteeID() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        int menteeID = 101;
        dto.setMenteeID(menteeID);
        assertEquals(menteeID, dto.getMenteeID());
    }

    @Test
    void testSetScheduleDate() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        java.sql.Date scheduleDate = java.sql.Date.valueOf("2023-07-19");
        dto.setScheduleDate(scheduleDate);
        assertEquals(scheduleDate, dto.getScheduleDate());
    }

    @Test
    void testSetScheduleTime() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        String scheduleTime = "14:00 - 16:00";
        dto.setScheduleTime(scheduleTime);
        assertEquals(scheduleTime, dto.getScheduleTime());
    }

    @Test
    void testSetUsername() {
        BookingListMentorDTO dto = new BookingListMentorDTO();
        String username = "mentor_user";
        dto.setUsername(username);
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testToString() {
        BookingListMentorDTO dto = BookingListMentorDTO.builder()
                .bookingID(1)
                .menteeID(101)
                .avatar("mentor_avatar.jpg")
                .username("mentor_user")
                .email("mentor@example.com")
                .scheduleDate(java.sql.Date.valueOf("2023-07-19"))
                .scheduleTime("14:00 - 16:00")
                .build();

        String expectedToString = "BookingListMentorDTO(bookingID=1, menteeID=101, " +
                "avatar=mentor_avatar.jpg, username=mentor_user, email=mentor@example.com, " +
                "scheduleDate=2023-07-19, scheduleTime=14:00 - 16:00)";
        assertEquals(expectedToString, dto.toString());
    }
}
