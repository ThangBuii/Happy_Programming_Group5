package com.hp.backend.model.booking.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class ViewBookingDTOTest {
    @Test
    void testBuilder() {
        ViewBookingDTO dto = ViewBookingDTO.builder()
                .mentorUsername("mentor_user")
                .mentorEmail("mentor@example.com")
                .menteeUsername("mentee_user")
                .menteeEmail("mentee@example.com")
                .scheduleDate(Date.valueOf("2023-07-19"))
                .scheduleTime("10:00 AM")
                .mentorAvatar("mentor_avatar.jpg")
                .menteeAvatar("mentee_avatar.jpg")
                .status(1)
                .createdDate(Date.valueOf("2023-07-18"))
                .build();

        assertEquals("mentor_user", dto.getMentorUsername());
        assertEquals("mentor@example.com", dto.getMentorEmail());
        assertEquals("mentee_user", dto.getMenteeUsername());
        assertEquals("mentee@example.com", dto.getMenteeEmail());
        assertEquals(Date.valueOf("2023-07-19"), dto.getScheduleDate());
        assertEquals("10:00 AM", dto.getScheduleTime());
        assertEquals("mentor_avatar.jpg", dto.getMentorAvatar());
        assertEquals("mentee_avatar.jpg", dto.getMenteeAvatar());
        assertEquals(1, dto.getStatus());
        assertEquals(Date.valueOf("2023-07-18"), dto.getCreatedDate());
    }

    @Test
    void testCanEqual() {
        ViewBookingDTO dto1 = ViewBookingDTO.builder().mentorUsername("mentor_user").build();
        ViewBookingDTO dto2 = ViewBookingDTO.builder().mentorUsername("mentor_user").build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        ViewBookingDTO dto1 = ViewBookingDTO.builder().mentorUsername("mentor_user").build();
        ViewBookingDTO dto2 = ViewBookingDTO.builder().mentorUsername("mentor_user").build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetCreatedDate() {
        ViewBookingDTO dto = new ViewBookingDTO();
        Date createdDate = Date.valueOf("2023-07-18");
        dto.setCreatedDate(createdDate);
        assertEquals(createdDate, dto.getCreatedDate());
    }

    @Test
    void testGetMenteeAvatar() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String menteeAvatar = "mentee_avatar.jpg";
        dto.setMenteeAvatar(menteeAvatar);
        assertEquals(menteeAvatar, dto.getMenteeAvatar());
    }

    @Test
    void testGetMenteeEmail() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String menteeEmail = "mentee@example.com";
        dto.setMenteeEmail(menteeEmail);
        assertEquals(menteeEmail, dto.getMenteeEmail());
    }

    @Test
    void testGetMenteeUsername() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String menteeUsername = "mentee_user";
        dto.setMenteeUsername(menteeUsername);
        assertEquals(menteeUsername, dto.getMenteeUsername());
    }

    @Test
    void testGetMentorAvatar() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String mentorAvatar = "mentor_avatar.jpg";
        dto.setMentorAvatar(mentorAvatar);
        assertEquals(mentorAvatar, dto.getMentorAvatar());
    }

    @Test
    void testGetMentorEmail() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String mentorEmail = "mentor@example.com";
        dto.setMentorEmail(mentorEmail);
        assertEquals(mentorEmail, dto.getMentorEmail());
    }

    @Test
    void testGetMentorUsername() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String mentorUsername = "mentor_user";
        dto.setMentorUsername(mentorUsername);
        assertEquals(mentorUsername, dto.getMentorUsername());
    }

    @Test
    void testGetScheduleDate() {
        ViewBookingDTO dto = new ViewBookingDTO();
        Date scheduleDate = Date.valueOf("2023-07-19");
        dto.setScheduleDate(scheduleDate);
        assertEquals(scheduleDate, dto.getScheduleDate());
    }

    @Test
    void testGetScheduleTime() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String scheduleTime = "10:00 AM";
        dto.setScheduleTime(scheduleTime);
        assertEquals(scheduleTime, dto.getScheduleTime());
    }

    @Test
    void testGetStatus() {
        ViewBookingDTO dto = new ViewBookingDTO();
        int status = 1;
        dto.setStatus(status);
        assertEquals(status, dto.getStatus());
    }

    @Test
    void testHashCode() {
        ViewBookingDTO dto1 = ViewBookingDTO.builder().mentorUsername("mentor_user").build();
        ViewBookingDTO dto2 = ViewBookingDTO.builder().mentorUsername("mentor_user").build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetCreatedDate() {
        ViewBookingDTO dto = new ViewBookingDTO();
        Date createdDate = Date.valueOf("2023-07-18");
        dto.setCreatedDate(createdDate);
        assertEquals(createdDate, dto.getCreatedDate());
    }

    @Test
    void testSetMenteeAvatar() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String menteeAvatar = "mentee_avatar.jpg";
        dto.setMenteeAvatar(menteeAvatar);
        assertEquals(menteeAvatar, dto.getMenteeAvatar());
    }

    @Test
    void testSetMenteeEmail() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String menteeEmail = "mentee@example.com";
        dto.setMenteeEmail(menteeEmail);
        assertEquals(menteeEmail, dto.getMenteeEmail());
    }

    @Test
    void testSetMenteeUsername() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String menteeUsername = "mentee_user";
        dto.setMenteeUsername(menteeUsername);
        assertEquals(menteeUsername, dto.getMenteeUsername());
    }

    @Test
    void testSetMentorAvatar() {
        ViewBookingDTO dto = new ViewBookingDTO();
        String mentorAvatar = "mentor_avatar.jpg";
        dto.setMentorAvatar(mentorAvatar);
        assertEquals(mentorAvatar, dto.getMentorAvatar());
    }

    @Test
    void testSetMentorEmail() {

    }
}
