package com.hp.backend.model.account.dto.MenteeSiteDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenteeDTODetailUpdateRequestTest {
    @Test
    void testGetters() {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        MenteeDTODetailUpdateRequest mentee = MenteeDTODetailUpdateRequest.builder()
                .username("testuser")
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .description("Description")
                .build();

        Assertions.assertEquals("testuser", mentee.getUsername());
        Assertions.assertEquals(1, mentee.getGender());
        Assertions.assertEquals(currentDate, mentee.getDob());
        Assertions.assertEquals("avatar.png", mentee.getAvatar());
        Assertions.assertEquals("Country", mentee.getCountry());
        Assertions.assertEquals("City", mentee.getCity());
        Assertions.assertEquals("Description", mentee.getDescription());
    }

    @Test
    void testSetters() {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        MenteeDTODetailUpdateRequest mentee = new MenteeDTODetailUpdateRequest();

        mentee.setUsername("testuser");
        mentee.setGender(1);
        mentee.setDob(currentDate);
        mentee.setAvatar("avatar.png");
        mentee.setCountry("Country");
        mentee.setCity("City");
        mentee.setDescription("Description");

        Assertions.assertEquals("testuser", mentee.getUsername());
        Assertions.assertEquals(1, mentee.getGender());
        Assertions.assertEquals(currentDate, mentee.getDob());
        Assertions.assertEquals("avatar.png", mentee.getAvatar());
        Assertions.assertEquals("Country", mentee.getCountry());
        Assertions.assertEquals("City", mentee.getCity());
        Assertions.assertEquals("Description", mentee.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        MenteeDTODetailUpdateRequest mentee1 = MenteeDTODetailUpdateRequest.builder()
                .username("testuser")
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .description("Description")
                .build();

        MenteeDTODetailUpdateRequest mentee2 = MenteeDTODetailUpdateRequest.builder()
                .username("testuser")
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .description("Description")
                .build();

        MenteeDTODetailUpdateRequest mentee3 = MenteeDTODetailUpdateRequest.builder()
                .username("testuser2")
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
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        MenteeDTODetailUpdateRequest mentee = MenteeDTODetailUpdateRequest.builder()
                .username("testuser")
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .description("Description")
                .build();

        String expectedToString = "MenteeDTODetailUpdateRequest(username=testuser, gender=1, dob=<current date>, avatar=avatar.png, country=Country, city=City, description=Description)";
        String actualToString = mentee.toString();

        // Replace the date value with <current date> in the actualToString
        actualToString = actualToString.replaceAll("\\d{4}-\\d{2}-\\d{2}", "<current date>");

        Assertions.assertEquals(expectedToString, actualToString);
    }
}
