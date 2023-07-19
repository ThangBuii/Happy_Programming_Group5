package com.hp.backend.model.booking.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookingListAdminDTOTest {
    @Test
    void testBuilder() {
        BookingListAdminDTO dto = BookingListAdminDTO.builder()
                .bookingID(1)
                .avatarMentee("mentee_avatar.jpg")
                .avatarMentor("mentor_avatar.jpg")
                .scheduleDate("2023-07-19")
                .scheduleTime("14:00 - 16:00")
                .menteeUsername("mentee_user")
                .mentorUsername("mentor_user")
                .created_date(java.sql.Date.valueOf("2023-07-18"))
                .status(1)
                .session_id(123)
                .build();

        // Add more assertions based on your requirements and available data.
        assertEquals(1, dto.getBookingID());
        assertEquals("mentee_avatar.jpg", dto.getAvatarMentee());
        assertEquals("mentor_avatar.jpg", dto.getAvatarMentor());
        assertEquals("2023-07-19", dto.getScheduleDate());
        assertEquals("14:00 - 16:00", dto.getScheduleTime());
        assertEquals("mentee_user", dto.getMenteeUsername());
        assertEquals("mentor_user", dto.getMentorUsername());
        assertEquals(java.sql.Date.valueOf("2023-07-18"), dto.getCreated_date());
        assertEquals(1, dto.getStatus());
        assertEquals(123, dto.getSession_id());
    }

    @Test
    void testCanEqual() {
        BookingListAdminDTO dto1 = BookingListAdminDTO.builder().bookingID(1).build();
        BookingListAdminDTO dto2 = BookingListAdminDTO.builder().bookingID(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        BookingListAdminDTO dto1 = BookingListAdminDTO.builder().bookingID(1).build();
        BookingListAdminDTO dto2 = BookingListAdminDTO.builder().bookingID(1).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetAvatarMentee() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String avatarMentee = "mentee_avatar.jpg";
        dto.setAvatarMentee(avatarMentee);
        assertEquals(avatarMentee, dto.getAvatarMentee());
    }

    @Test
    void testGetAvatarMentor() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String avatarMentor = "mentor_avatar.jpg";
        dto.setAvatarMentor(avatarMentor);
        assertEquals(avatarMentor, dto.getAvatarMentor());
    }

    @Test
    void testGetBookingID() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        int bookingID = 1;
        dto.setBookingID(bookingID);
        assertEquals(bookingID, dto.getBookingID());
    }

    @Test
    void testGetCreated_date() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        java.sql.Date createdDate = java.sql.Date.valueOf("2023-07-18");
        dto.setCreated_date(createdDate);
        assertEquals(createdDate, dto.getCreated_date());
    }

    @Test
    void testGetMenteeUsername() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String menteeUsername = "mentee_user";
        dto.setMenteeUsername(menteeUsername);
        assertEquals(menteeUsername, dto.getMenteeUsername());
    }

    @Test
    void testGetMentorUsername() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String mentorUsername = "mentor_user";
        dto.setMentorUsername(mentorUsername);
        assertEquals(mentorUsername, dto.getMentorUsername());
    }

    @Test
    void testGetScheduleDate() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String scheduleDate = "2023-07-19";
        dto.setScheduleDate(scheduleDate);
        assertEquals(scheduleDate, dto.getScheduleDate());
    }

    @Test
    void testGetScheduleTime() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String scheduleTime = "14:00 - 16:00";
        dto.setScheduleTime(scheduleTime);
        assertEquals(scheduleTime, dto.getScheduleTime());
    }

    @Test
    void testGetSession_id() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        int sessionId = 123;
        dto.setSession_id(sessionId);
        assertEquals(sessionId, dto.getSession_id());
    }

    @Test
    void testGetStatus() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        int status = 1;
        dto.setStatus(status);
        assertEquals(status, dto.getStatus());
    }

    @Test
    void testSetAvatarMentee() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String avatarMentee = "mentee_avatar.jpg";
        dto.setAvatarMentee(avatarMentee);
        assertEquals(avatarMentee, dto.getAvatarMentee());
    }

    @Test
    void testSetAvatarMentor() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String avatarMentor = "mentor_avatar.jpg";
        dto.setAvatarMentor(avatarMentor);
        assertEquals(avatarMentor, dto.getAvatarMentor());
    }

    @Test
    void testSetBookingID() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        int bookingID = 1;
        dto.setBookingID(bookingID);
        assertEquals(bookingID, dto.getBookingID());
    }

    @Test
    void testSetCreated_date() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        java.sql.Date createdDate = java.sql.Date.valueOf("2023-07-18");
        dto.setCreated_date(createdDate);
        assertEquals(createdDate, dto.getCreated_date());
    }

    @Test
    void testSetMenteeUsername() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String menteeUsername = "mentee_user";
        dto.setMenteeUsername(menteeUsername);
        assertEquals(menteeUsername, dto.getMenteeUsername());
    }

    @Test
    void testSetMentorUsername() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String mentorUsername = "mentor_user";
        dto.setMentorUsername(mentorUsername);
        assertEquals(mentorUsername, dto.getMentorUsername());
    }

    @Test
    void testSetScheduleDate() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String scheduleDate = "2023-07-19";
        dto.setScheduleDate(scheduleDate);
        assertEquals(scheduleDate, dto.getScheduleDate());
    }

    @Test
    void testSetScheduleTime() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        String scheduleTime = "14:00 - 16:00";
        dto.setScheduleTime(scheduleTime);
        assertEquals(scheduleTime, dto.getScheduleTime());
    }

    @Test
    void testSetSession_id() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        int sessionId = 123;
        dto.setSession_id(sessionId);
        assertEquals(sessionId, dto.getSession_id());
    }

    @Test
    void testSetStatus() {
        BookingListAdminDTO dto = new BookingListAdminDTO();
        int status = 1;
        dto.setStatus(status);
        assertEquals(status, dto.getStatus());
    }

    @Test
    void testToString() {
        BookingListAdminDTO dto = BookingListAdminDTO.builder()
                .bookingID(1)
                .avatarMentee("mentee_avatar.jpg")
                .avatarMentor("mentor_avatar.jpg")
                .scheduleDate("2023-07-19")
                .scheduleTime("14:00 - 16:00")
                .menteeUsername("mentee_user")
                .mentorUsername("mentor_user")
                .created_date(java.sql.Date.valueOf("2023-07-18"))
                .status(1)
                .session_id(123)
                .build();

        String expectedToString = "BookingListAdminDTO(bookingID=1, avatarMentee=mentee_avatar.jpg, " +
                "avatarMentor=mentor_avatar.jpg, scheduleDate=2023-07-19, scheduleTime=14:00 - 16:00, " +
                "menteeUsername=mentee_user, mentorUsername=mentor_user, created_date=2023-07-18, " +
                "status=1, session_id=123)";
        assertEquals(expectedToString, dto.toString());
    }
}
