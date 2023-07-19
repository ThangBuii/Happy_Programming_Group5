package com.hp.backend.model.account.dto.AdminSiteDTO;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenteeDTODetailResponseTest {
    @Test
    void testGetters() {
        Date currentDate = new Date(System.currentTimeMillis());

        MenteeDTODetailResponse mentee = MenteeDTODetailResponse.builder()
                .email("test@example.com")
                .username("testuser")
                .created_date(currentDate)
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .description("Description")
                .build();

        Assertions.assertEquals("test@example.com", mentee.getEmail());
        Assertions.assertEquals("testuser", mentee.getUsername());
        Assertions.assertEquals(currentDate, mentee.getCreated_date());
        Assertions.assertEquals(1, mentee.getGender());
        Assertions.assertEquals(currentDate, mentee.getDob());
        Assertions.assertEquals("avatar.png", mentee.getAvatar());
        Assertions.assertEquals("Country", mentee.getCountry());
        Assertions.assertEquals("City", mentee.getCity());
        Assertions.assertEquals("Description", mentee.getDescription());
    }

    @Test
    void testSetters() {
        Date currentDate = new Date(System.currentTimeMillis());

        MenteeDTODetailResponse mentee = new MenteeDTODetailResponse();

        mentee.setEmail("test@example.com");
        mentee.setUsername("testuser");
        mentee.setCreated_date(currentDate);
        mentee.setGender(1);
        mentee.setDob(currentDate);
        mentee.setAvatar("avatar.png");
        mentee.setCountry("Country");
        mentee.setCity("City");
        mentee.setDescription("Description");

        Assertions.assertEquals("test@example.com", mentee.getEmail());
        Assertions.assertEquals("testuser", mentee.getUsername());
        Assertions.assertEquals(currentDate, mentee.getCreated_date());
        Assertions.assertEquals(1, mentee.getGender());
        Assertions.assertEquals(currentDate, mentee.getDob());
        Assertions.assertEquals("avatar.png", mentee.getAvatar());
        Assertions.assertEquals("Country", mentee.getCountry());
        Assertions.assertEquals("City", mentee.getCity());
        Assertions.assertEquals("Description", mentee.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        Date currentDate = new Date(System.currentTimeMillis());

        MenteeDTODetailResponse mentee1 = MenteeDTODetailResponse.builder()
                .email("test@example.com")
                .username("testuser")
                .created_date(currentDate)
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .description("Description")
                .build();

        MenteeDTODetailResponse mentee2 = MenteeDTODetailResponse.builder()
                .email("test@example.com")
                .username("testuser")
                .created_date(currentDate)
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .description("Description")
                .build();

        MenteeDTODetailResponse mentee3 = MenteeDTODetailResponse.builder()
                .email("test2@example.com")
                .username("testuser2")
                .created_date(currentDate)
                .gender(2)
                .dob(currentDate)
                .avatar("avatar2.png")
                .country("Country2")
                .city("City2")
                .description("Description2")
                .build();

        Assertions.assertEquals(mentee1, mentee2);
        Assertions.assertNotEquals(mentee1, mentee3);

        Assertions.assertEquals(mentee1.hashCode(), mentee2.hashCode());
        Assertions.assertNotEquals(mentee1.hashCode(), mentee3.hashCode());
    }

    @Test
    void testToString() {
        Date currentDate = new Date(System.currentTimeMillis());

        MenteeDTODetailResponse mentee = MenteeDTODetailResponse.builder()
                .email("test@example.com")
                .username("testuser")
                .created_date(currentDate)
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .description("Description")
                .build();
        String actualToString = mentee.toString();

        // Replace the date value with <current date> in the actualToString
        actualToString = actualToString.replaceAll("\\d{4}-\\d{2}-\\d{2}", "<current date>");
        String expectedToString = "MenteeDTODetailResponse(email=test@example.com, username=testuser, created_date=<current date>, gender=1, dob=<current date>, avatar=avatar.png, country=Country, city=City, description=Description)";
        Assertions.assertEquals(expectedToString, actualToString);
    }
}
