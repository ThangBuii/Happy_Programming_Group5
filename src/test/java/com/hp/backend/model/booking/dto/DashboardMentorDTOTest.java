package com.hp.backend.model.booking.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class DashboardMentorDTOTest {
    @Test
    void testBuilder() {
        DashboardMentorDTO dto = DashboardMentorDTO.builder()
                .bookingID(1)
                .avatar("mentor_avatar.jpg")
                .username("mentor_user")
                .email("mentor@example.com")
                .created_Date(Date.valueOf("2023-07-19"))
                .status(1)
                .build();

        assertEquals(1, dto.getBookingID());
        assertEquals("mentor_avatar.jpg", dto.getAvatar());
        assertEquals("mentor_user", dto.getUsername());
        assertEquals("mentor@example.com", dto.getEmail());
        assertEquals(Date.valueOf("2023-07-19"), dto.getCreated_Date());
        assertEquals(1, dto.getStatus());
    }

    @Test
    void testCanEqual() {
        DashboardMentorDTO dto1 = DashboardMentorDTO.builder().bookingID(1).build();
        DashboardMentorDTO dto2 = DashboardMentorDTO.builder().bookingID(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        DashboardMentorDTO dto1 = DashboardMentorDTO.builder().bookingID(1).build();
        DashboardMentorDTO dto2 = DashboardMentorDTO.builder().bookingID(1).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetAvatar() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        String avatar = "mentor_avatar.jpg";
        dto.setAvatar(avatar);
        assertEquals(avatar, dto.getAvatar());
    }

    @Test
    void testGetBookingID() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        int bookingID = 1;
        dto.setBookingID(bookingID);
        assertEquals(bookingID, dto.getBookingID());
    }

    @Test
    void testGetCreated_Date() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        Date createdDate = Date.valueOf("2023-07-19");
        dto.setCreated_Date(createdDate);
        assertEquals(createdDate, dto.getCreated_Date());
    }

    @Test
    void testGetEmail() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        String email = "mentor@example.com";
        dto.setEmail(email);
        assertEquals(email, dto.getEmail());
    }

    @Test
    void testGetStatus() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        int status = 1;
        dto.setStatus(status);
        assertEquals(status, dto.getStatus());
    }

    @Test
    void testGetUsername() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        String username = "mentor_user";
        dto.setUsername(username);
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testHashCode() {
        DashboardMentorDTO dto1 = DashboardMentorDTO.builder().bookingID(1).build();
        DashboardMentorDTO dto2 = DashboardMentorDTO.builder().bookingID(1).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetAvatar() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        String avatar = "mentor_avatar.jpg";
        dto.setAvatar(avatar);
        assertEquals(avatar, dto.getAvatar());
    }

    @Test
    void testSetBookingID() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        int bookingID = 1;
        dto.setBookingID(bookingID);
        assertEquals(bookingID, dto.getBookingID());
    }

    @Test
    void testSetCreated_Date() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        Date createdDate = Date.valueOf("2023-07-19");
        dto.setCreated_Date(createdDate);
        assertEquals(createdDate, dto.getCreated_Date());
    }

    @Test
    void testSetEmail() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        String email = "mentor@example.com";
        dto.setEmail(email);
        assertEquals(email, dto.getEmail());
    }

    @Test
    void testSetStatus() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        int status = 1;
        dto.setStatus(status);
        assertEquals(status, dto.getStatus());
    }

    @Test
    void testSetUsername() {
        DashboardMentorDTO dto = new DashboardMentorDTO();
        String username = "mentor_user";
        dto.setUsername(username);
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testToString() {
        DashboardMentorDTO dto = DashboardMentorDTO.builder()
                .bookingID(1)
                .avatar("mentor_avatar.jpg")
                .username("mentor_user")
                .email("mentor@example.com")
                .created_Date(Date.valueOf("2023-07-19"))
                .status(1)
                .build();

        String expectedToString = "DashboardMentorDTO(bookingID=1, avatar=mentor_avatar.jpg, " +
                "username=mentor_user, email=mentor@example.com, created_Date=2023-07-19, " +
                "status=1)";
        assertEquals(expectedToString, dto.toString());
    }
}
