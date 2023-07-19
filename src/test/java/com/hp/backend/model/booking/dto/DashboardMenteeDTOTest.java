package com.hp.backend.model.booking.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class DashboardMenteeDTOTest {
    @Test
    void testBuilder() {
        DashboardMenteeDTO dto = DashboardMenteeDTO.builder()
                .bookingID(1)
                .avatar("mentee_avatar.jpg")
                .username("mentee_user")
                .email("mentee@example.com")
                .created_Date(Date.valueOf("2023-07-19"))
                .status(1)
                .build();

        assertEquals(1, dto.getBookingID());
        assertEquals("mentee_avatar.jpg", dto.getAvatar());
        assertEquals("mentee_user", dto.getUsername());
        assertEquals("mentee@example.com", dto.getEmail());
        assertEquals(Date.valueOf("2023-07-19"), dto.getCreated_Date());
        assertEquals(1, dto.getStatus());
    }

    @Test
    void testCanEqual() {
        DashboardMenteeDTO dto1 = DashboardMenteeDTO.builder().bookingID(1).build();
        DashboardMenteeDTO dto2 = DashboardMenteeDTO.builder().bookingID(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        DashboardMenteeDTO dto1 = DashboardMenteeDTO.builder().bookingID(1).build();
        DashboardMenteeDTO dto2 = DashboardMenteeDTO.builder().bookingID(1).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetAvatar() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        String avatar = "mentee_avatar.jpg";
        dto.setAvatar(avatar);
        assertEquals(avatar, dto.getAvatar());
    }

    @Test
    void testGetBookingID() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        int bookingID = 1;
        dto.setBookingID(bookingID);
        assertEquals(bookingID, dto.getBookingID());
    }

    @Test
    void testGetCreated_Date() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        Date createdDate = Date.valueOf("2023-07-19");
        dto.setCreated_Date(createdDate);
        assertEquals(createdDate, dto.getCreated_Date());
    }

    @Test
    void testGetEmail() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        String email = "mentee@example.com";
        dto.setEmail(email);
        assertEquals(email, dto.getEmail());
    }

    @Test
    void testGetStatus() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        int status = 1;
        dto.setStatus(status);
        assertEquals(status, dto.getStatus());
    }

    @Test
    void testGetUsername() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        String username = "mentee_user";
        dto.setUsername(username);
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testHashCode() {
        DashboardMenteeDTO dto1 = DashboardMenteeDTO.builder().bookingID(1).build();
        DashboardMenteeDTO dto2 = DashboardMenteeDTO.builder().bookingID(1).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetAvatar() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        String avatar = "mentee_avatar.jpg";
        dto.setAvatar(avatar);
        assertEquals(avatar, dto.getAvatar());
    }

    @Test
    void testSetBookingID() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        int bookingID = 1;
        dto.setBookingID(bookingID);
        assertEquals(bookingID, dto.getBookingID());
    }

    @Test
    void testSetCreated_Date() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        Date createdDate = Date.valueOf("2023-07-19");
        dto.setCreated_Date(createdDate);
        assertEquals(createdDate, dto.getCreated_Date());
    }

    @Test
    void testSetEmail() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        String email = "mentee@example.com";
        dto.setEmail(email);
        assertEquals(email, dto.getEmail());
    }

    @Test
    void testSetStatus() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        int status = 1;
        dto.setStatus(status);
        assertEquals(status, dto.getStatus());
    }

    @Test
    void testSetUsername() {
        DashboardMenteeDTO dto = new DashboardMenteeDTO();
        String username = "mentee_user";
        dto.setUsername(username);
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testToString() {
        DashboardMenteeDTO dto = DashboardMenteeDTO.builder()
                .bookingID(1)
                .avatar("mentee_avatar.jpg")
                .username("mentee_user")
                .email("mentee@example.com")
                .created_Date(Date.valueOf("2023-07-19"))
                .status(1)
                .build();

        String expectedToString = "DashboardMenteeDTO(bookingID=1, avatar=mentee_avatar.jpg, " +
                "username=mentee_user, email=mentee@example.com, created_Date=2023-07-19, " +
                "status=1)";
        assertEquals(expectedToString, dto.toString());
    }
}
