package com.hp.backend.model.account.dto.AdminSiteDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.hp.backend.entity.Skills;

public class MentorDTODetailResponseTest {
    @Test
    void testGetters() {
        Date currentDate = new Date(System.currentTimeMillis());
        List<Skills> skills = new ArrayList<>();

        MentorDTODetailResponse mentor = MentorDTODetailResponse.builder()
                .email("test@example.com")
                .username("testuser")
                .created_date(currentDate)
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .university("University")
                .major("Major")
                .degree("Degree")
                .description("Description")
                .short_description("Short Description")
                .role(1)
                .skills(skills)
                .build();

        Assertions.assertEquals("test@example.com", mentor.getEmail());
        Assertions.assertEquals("testuser", mentor.getUsername());
        Assertions.assertEquals(currentDate, mentor.getCreated_date());
        Assertions.assertEquals(1, mentor.getGender());
        Assertions.assertEquals(currentDate, mentor.getDob());
        Assertions.assertEquals("avatar.png", mentor.getAvatar());
        Assertions.assertEquals("Country", mentor.getCountry());
        Assertions.assertEquals("City", mentor.getCity());
        Assertions.assertEquals("University", mentor.getUniversity());
        Assertions.assertEquals("Major", mentor.getMajor());
        Assertions.assertEquals("Degree", mentor.getDegree());
        Assertions.assertEquals("Description", mentor.getDescription());
        Assertions.assertEquals("Short Description", mentor.getShort_description());
        Assertions.assertEquals(1, mentor.getRole());
        Assertions.assertEquals(skills, mentor.getSkills());
    }

    @Test
    void testSetters() {
        Date currentDate = new Date(System.currentTimeMillis());
        List<Skills> skills = new ArrayList<>();

        MentorDTODetailResponse mentor = new MentorDTODetailResponse();

        mentor.setEmail("test@example.com");
        mentor.setUsername("testuser");
        mentor.setCreated_date(currentDate);
        mentor.setGender(1);
        mentor.setDob(currentDate);
        mentor.setAvatar("avatar.png");
        mentor.setCountry("Country");
        mentor.setCity("City");
        mentor.setUniversity("University");
        mentor.setMajor("Major");
        mentor.setDegree("Degree");
        mentor.setDescription("Description");
        mentor.setShort_description("Short Description");
        mentor.setRole(1);
        mentor.setSkills(skills);

        Assertions.assertEquals("test@example.com", mentor.getEmail());
        Assertions.assertEquals("testuser", mentor.getUsername());
        Assertions.assertEquals(currentDate, mentor.getCreated_date());
        Assertions.assertEquals(1, mentor.getGender());
        Assertions.assertEquals(currentDate, mentor.getDob());
        Assertions.assertEquals("avatar.png", mentor.getAvatar());
        Assertions.assertEquals("Country", mentor.getCountry());
        Assertions.assertEquals("City", mentor.getCity());
        Assertions.assertEquals("University", mentor.getUniversity());
        Assertions.assertEquals("Major", mentor.getMajor());
        Assertions.assertEquals("Degree", mentor.getDegree());
        Assertions.assertEquals("Description", mentor.getDescription());
        Assertions.assertEquals("Short Description", mentor.getShort_description());
        Assertions.assertEquals(1, mentor.getRole());
        Assertions.assertEquals(skills, mentor.getSkills());
    }

    @Test
    void testEqualsAndHashCode() {
        Date currentDate = new Date(System.currentTimeMillis());
        List<Skills> skills = new ArrayList<>();

        MentorDTODetailResponse mentor1 = MentorDTODetailResponse.builder()
                .email("test@example.com")
                .username("testuser")
                .created_date(currentDate)
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .university("University")
                .major("Major")
                .degree("Degree")
                .description("Description")
                .short_description("Short Description")
                .role(1)
                .skills(skills)
                .build();

        MentorDTODetailResponse mentor2 = MentorDTODetailResponse.builder()
                .email("test@example.com")
                .username("testuser")
                .created_date(currentDate)
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .university("University")
                .major("Major")
                .degree("Degree")
                .description("Description")
                .short_description("Short Description")
                .role(1)
                .skills(skills)
                .build();

        MentorDTODetailResponse mentor3 = MentorDTODetailResponse.builder()
                .email("test2@example.com")
                .username("testuser2")
                .created_date(currentDate)
                .gender(2)
                .dob(currentDate)
                .avatar("avatar2.png")
                .country("Country2")
                .city("City2")
                .university("University2")
                .major("Major2")
                .degree("Degree2")
                .description("Description2")
                .short_description("Short Description2")
                .role(2)
                .skills(skills)
                .build();

        Assertions.assertEquals(mentor1, mentor2);
        Assertions.assertNotEquals(mentor1, mentor3);

        Assertions.assertEquals(mentor1.hashCode(), mentor2.hashCode());
        Assertions.assertNotEquals(mentor1.hashCode(), mentor3.hashCode());
    }

    @Test
    void testToString() {
        Date currentDate = new Date(System.currentTimeMillis());
        List<Skills> skills = new ArrayList<>();

        MentorDTODetailResponse mentor = MentorDTODetailResponse.builder()
                .email("test@example.com")
                .username("testuser")
                .created_date(currentDate)
                .gender(1)
                .dob(currentDate)
                .avatar("avatar.png")
                .country("Country")
                .city("City")
                .university("University")
                .major("Major")
                .degree("Degree")
                .description("Description")
                .short_description("Short Description")
                .role(1)
                .skills(skills)
                .build();
        String actualToString = mentor.toString();

        // Replace the date value with <current date> in the actualToString
        actualToString = actualToString.replaceAll("\\d{4}-\\d{2}-\\d{2}", "<current date>");
        String expectedToString = "MentorDTODetailResponse(email=test@example.com, username=testuser, created_date=<current date>, gender=1, dob=<current date>, avatar=avatar.png, country=Country, city=City, university=University, major=Major, degree=Degree, description=Description, short_description=Short Description, role=1, skills=[])";
        Assertions.assertEquals(expectedToString, actualToString);
    }
}
