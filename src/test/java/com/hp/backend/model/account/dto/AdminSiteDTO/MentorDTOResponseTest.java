package com.hp.backend.model.account.dto.AdminSiteDTO;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MentorDTOResponseTest {
    @Test
    void testGetters() {
        Date currentDate = new Date(System.currentTimeMillis());

        MentorDTOResponse mentor = MentorDTOResponse.builder()
                .account_id(1)
                .username("testuser")
                .avatar("avatar.png")
                .created_date(currentDate)
                .numberOfBooking(5)
                .earned("1000")
                .build();

        Assertions.assertEquals(1, mentor.getAccount_id());
        Assertions.assertEquals("testuser", mentor.getUsername());
        Assertions.assertEquals("avatar.png", mentor.getAvatar());
        Assertions.assertEquals(currentDate, mentor.getCreated_date());
        Assertions.assertEquals(5, mentor.getNumberOfBooking());
        Assertions.assertEquals("1000", mentor.getEarned());
    }

    @Test
    void testSetters() {
        Date currentDate = new Date(System.currentTimeMillis());

        MentorDTOResponse mentor = new MentorDTOResponse();

        mentor.setAccount_id(1);
        mentor.setUsername("testuser");
        mentor.setAvatar("avatar.png");
        mentor.setCreated_date(currentDate);
        mentor.setNumberOfBooking(5);
        mentor.setEarned("1000");

        Assertions.assertEquals(1, mentor.getAccount_id());
        Assertions.assertEquals("testuser", mentor.getUsername());
        Assertions.assertEquals("avatar.png", mentor.getAvatar());
        Assertions.assertEquals(currentDate, mentor.getCreated_date());
        Assertions.assertEquals(5, mentor.getNumberOfBooking());
        Assertions.assertEquals("1000", mentor.getEarned());
    }

    @Test
    void testEqualsAndHashCode() {
        Date currentDate = new Date(System.currentTimeMillis());

        MentorDTOResponse mentor1 = MentorDTOResponse.builder()
                .account_id(1)
                .username("testuser")
                .avatar("avatar.png")
                .created_date(currentDate)
                .numberOfBooking(5)
                .earned("1000")
                .build();

        MentorDTOResponse mentor2 = MentorDTOResponse.builder()
                .account_id(1)
                .username("testuser")
                .avatar("avatar.png")
                .created_date(currentDate)
                .numberOfBooking(5)
                .earned("1000")
                .build();

        MentorDTOResponse mentor3 = MentorDTOResponse.builder()
                .account_id(2)
                .username("testuser2")
                .avatar("avatar2.png")
                .created_date(currentDate)
                .numberOfBooking(10)
                .earned("2000")
                .build();

        Assertions.assertEquals(mentor1, mentor2);
        Assertions.assertNotEquals(mentor1, mentor3);

        Assertions.assertEquals(mentor1.hashCode(), mentor2.hashCode());
        Assertions.assertNotEquals(mentor1.hashCode(), mentor3.hashCode());
    }

    @Test
    void testToString() {
        Date currentDate = new Date(System.currentTimeMillis());

        MentorDTOResponse mentor = MentorDTOResponse.builder()
                .account_id(1)
                .username("testuser")
                .avatar("avatar.png")
                .created_date(currentDate)
                .numberOfBooking(5)
                .earned("1000")
                .build();
        String actualToString = mentor.toString();

        // Replace the date value with <current date> in the actualToString
        actualToString = actualToString.replaceAll("\\d{4}-\\d{2}-\\d{2}", "<current date>");
        String expectedToString = "MentorDTOResponse(account_id=1, username=testuser, avatar=avatar.png, created_date=<current date>, numberOfBooking=5, earned=1000)";
        Assertions.assertEquals(expectedToString, actualToString);
    }
}
