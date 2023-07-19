package com.hp.backend.model.account.dto.AdminSiteDTO;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenteeDTOResponseTest {
    @Test
    void testGetters() {
        Date currentDate = new Date(System.currentTimeMillis());

        MenteeDTOResponse mentee = MenteeDTOResponse.builder()
                .account_id(1)
                .username("testuser")
                .avatar("avatar.png")
                .created_date(currentDate)
                .numberOfBooking(5)
                .build();

        Assertions.assertEquals(1, mentee.getAccount_id());
        Assertions.assertEquals("testuser", mentee.getUsername());
        Assertions.assertEquals("avatar.png", mentee.getAvatar());
        Assertions.assertEquals(currentDate, mentee.getCreated_date());
        Assertions.assertEquals(5, mentee.getNumberOfBooking());
    }

    @Test
    void testSetters() {
        Date currentDate = new Date(System.currentTimeMillis());

        MenteeDTOResponse mentee = new MenteeDTOResponse();

        mentee.setAccount_id(1);
        mentee.setUsername("testuser");
        mentee.setAvatar("avatar.png");
        mentee.setCreated_date(currentDate);
        mentee.setNumberOfBooking(5);

        Assertions.assertEquals(1, mentee.getAccount_id());
        Assertions.assertEquals("testuser", mentee.getUsername());
        Assertions.assertEquals("avatar.png", mentee.getAvatar());
        Assertions.assertEquals(currentDate, mentee.getCreated_date());
        Assertions.assertEquals(5, mentee.getNumberOfBooking());
    }

    @Test
    void testEqualsAndHashCode() {
        Date currentDate = new Date(System.currentTimeMillis());

        MenteeDTOResponse mentee1 = MenteeDTOResponse.builder()
                .account_id(1)
                .username("testuser")
                .avatar("avatar.png")
                .created_date(currentDate)
                .numberOfBooking(5)
                .build();

        MenteeDTOResponse mentee2 = MenteeDTOResponse.builder()
                .account_id(1)
                .username("testuser")
                .avatar("avatar.png")
                .created_date(currentDate)
                .numberOfBooking(5)
                .build();

        MenteeDTOResponse mentee3 = MenteeDTOResponse.builder()
                .account_id(2)
                .username("testuser2")
                .avatar("avatar2.png")
                .created_date(currentDate)
                .numberOfBooking(10)
                .build();

        Assertions.assertEquals(mentee1, mentee2);
        Assertions.assertNotEquals(mentee1, mentee3);

        Assertions.assertEquals(mentee1.hashCode(), mentee2.hashCode());
        Assertions.assertNotEquals(mentee1.hashCode(), mentee3.hashCode());
    }

    @Test
    void testToString() {
        Date currentDate = new Date(System.currentTimeMillis());

        MenteeDTOResponse mentee = MenteeDTOResponse.builder()
                .account_id(1)
                .username("testuser")
                .avatar("avatar.png")
                .created_date(currentDate)
                .numberOfBooking(5)
                .build();
        String actualToString = mentee.toString();

        // Replace the date value with <current date> in the actualToString
        actualToString = actualToString.replaceAll("\\d{4}-\\d{2}-\\d{2}", "<current date>");
        String expectedToString = "MenteeDTOResponse(account_id=1, username=testuser, avatar=avatar.png, created_date=<current date>, numberOfBooking=5)";
        Assertions.assertEquals(expectedToString, actualToString);
    }
}
