package com.hp.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTOResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTOResponse;
import com.hp.backend.service.Account.AccountService;

public class AccountControllerTest {
    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMenteeList() {
        List<MenteeDTOResponse> expectedMentees = new ArrayList<>();
        expectedMentees.add(MenteeDTOResponse.builder().account_id(1).username("mentee1").build());
        expectedMentees.add(MenteeDTOResponse.builder().account_id(2).username("mentee2").build());

        Mockito.when(accountService.getMenteeList()).thenReturn(expectedMentees);

        // Act
        List<MenteeDTOResponse> actualMentees = accountController.getMenteeList();

        // Assert
        Assertions.assertEquals(expectedMentees.size(), actualMentees.size());
        Assertions.assertEquals(expectedMentees.get(0).getAccount_id(), actualMentees.get(0).getAccount_id());
        Assertions.assertEquals(expectedMentees.get(0).getUsername(), actualMentees.get(0).getUsername());
        Assertions.assertEquals(expectedMentees.get(1).getAccount_id(), actualMentees.get(1).getAccount_id());
        Assertions.assertEquals(expectedMentees.get(1).getUsername(), actualMentees.get(1).getUsername());
    }

    @Test
    void testGetMentorList() {
        List<MentorDTOResponse> expectedMentors = new ArrayList<>();
        expectedMentors.add(MentorDTOResponse.builder().account_id(1).username("mentor1").build());
        expectedMentors.add(MentorDTOResponse.builder().account_id(2).username("mentor2").build());

        Mockito.when(accountService.getMentorList()).thenReturn(expectedMentors);

        // Act
        List<MentorDTOResponse> actualMentors = accountController.getMentorList();

        // Assert
        Assertions.assertEquals(expectedMentors.size(), actualMentors.size());
        Assertions.assertEquals(expectedMentors.get(0).getAccount_id(), actualMentors.get(0).getAccount_id());
        Assertions.assertEquals(expectedMentors.get(0).getUsername(), actualMentors.get(0).getUsername());
        Assertions.assertEquals(expectedMentors.get(1).getAccount_id(), actualMentors.get(1).getAccount_id());
        Assertions.assertEquals(expectedMentors.get(1).getUsername(), actualMentors.get(1).getUsername());
    }
}
