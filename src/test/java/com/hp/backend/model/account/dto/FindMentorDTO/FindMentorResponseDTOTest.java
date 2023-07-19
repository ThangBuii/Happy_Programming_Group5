package com.hp.backend.model.account.dto.FindMentorDTO;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.hp.backend.entity.Skills;

public class FindMentorResponseDTOTest {
    @Test
    void testGetters() {
        List<Skills> skills = new ArrayList<>();

        FindMentorResponseDTO mentor = FindMentorResponseDTO.builder()
                .avatar("avatar.png")
                .username("testuser")
                .short_description("Short description")
                .description("Description")
                .skills(skills)
                .mentor_id(1)
                .isFavorite(true)
                .build();

        Assertions.assertEquals("avatar.png", mentor.getAvatar());
        Assertions.assertEquals("testuser", mentor.getUsername());
        Assertions.assertEquals("Short description", mentor.getShort_description());
        Assertions.assertEquals("Description", mentor.getDescription());
        Assertions.assertEquals(skills, mentor.getSkills());
        Assertions.assertEquals(1, mentor.getMentor_id());
        Assertions.assertTrue(mentor.isFavorite());
    }

    @Test
    void testSetters() {
        List<Skills> skills = new ArrayList<>();

        FindMentorResponseDTO mentor = new FindMentorResponseDTO();

        mentor.setAvatar("avatar.png");
        mentor.setUsername("testuser");
        mentor.setShort_description("Short description");
        mentor.setDescription("Description");
        mentor.setSkills(skills);
        mentor.setMentor_id(1);
        mentor.setFavorite(true);

        Assertions.assertEquals("avatar.png", mentor.getAvatar());
        Assertions.assertEquals("testuser", mentor.getUsername());
        Assertions.assertEquals("Short description", mentor.getShort_description());
        Assertions.assertEquals("Description", mentor.getDescription());
        Assertions.assertEquals(skills, mentor.getSkills());
        Assertions.assertEquals(1, mentor.getMentor_id());
        Assertions.assertTrue(mentor.isFavorite());
    }

    @Test
    void testEqualsAndHashCode() {
        List<Skills> skills1 = new ArrayList<>();
    

        List<Skills> skills2 = new ArrayList<>();

        List<Skills> skills3 = new ArrayList<>();

        FindMentorResponseDTO mentor1 = FindMentorResponseDTO.builder()
                .avatar("avatar.png")
                .username("testuser")
                .short_description("Short description")
                .description("Description")
                .skills(skills1)
                .mentor_id(1)
                .isFavorite(true)
                .build();

        FindMentorResponseDTO mentor2 = FindMentorResponseDTO.builder()
                .avatar("avatar.png")
                .username("testuser")
                .short_description("Short description")
                .description("Description")
                .skills(skills2)
                .mentor_id(1)
                .isFavorite(true)
                .build();

        FindMentorResponseDTO mentor3 = FindMentorResponseDTO.builder()
                .avatar("avatar2.png")
                .username("testuser2")
                .short_description("Short description2")
                .description("Description2")
                .skills(skills3)
                .mentor_id(2)
                .isFavorite(false)
                .build();

        Assertions.assertEquals(mentor1, mentor2);
        Assertions.assertNotEquals(mentor1, mentor3);

        Assertions.assertEquals(mentor1.hashCode(), mentor2.hashCode());
        Assertions.assertNotEquals(mentor1.hashCode(), mentor3.hashCode());
    }

    @Test
    void testToString() {
        List<Skills> skills = new ArrayList<>();

        FindMentorResponseDTO mentor = FindMentorResponseDTO.builder()
                .avatar("avatar.png")
                .username("testuser")
                .short_description("Short description")
                .description("Description")
                .skills(skills)
                .mentor_id(1)
                .isFavorite(true)
                .build();

        String expectedToString = "FindMentorResponseDTO(avatar=avatar.png, username=testuser, short_description=Short description, description=Description, skills=[], mentor_id=1, isFavorite=true)";
        Assertions.assertEquals(expectedToString, mentor.toString());
    }
}
