package com.hp.backend.model.account.dto.LoginDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountDTOCreateTest {
    @Test
    void testGetters() {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        AccountDTOCreate account = AccountDTOCreate.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .created_date(currentDate)
                .role(1)
                .build();

        Assertions.assertEquals("testuser", account.getUsername());
        Assertions.assertEquals("test@example.com", account.getEmail());
        Assertions.assertEquals("password", account.getPassword());
        Assertions.assertEquals(currentDate, account.getCreated_date());
        Assertions.assertEquals(1, account.getRole());
    }

    @Test
    void testSetters() {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        AccountDTOCreate account = new AccountDTOCreate();

        account.setUsername("testuser");
        account.setEmail("test@example.com");
        account.setPassword("password");
        account.setCreated_date(currentDate);
        account.setRole(1);

        Assertions.assertEquals("testuser", account.getUsername());
        Assertions.assertEquals("test@example.com", account.getEmail());
        Assertions.assertEquals("password", account.getPassword());
        Assertions.assertEquals(currentDate, account.getCreated_date());
        Assertions.assertEquals(1, account.getRole());
    }

    @Test
    void testEqualsAndHashCode() {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        AccountDTOCreate account1 = AccountDTOCreate.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .created_date(currentDate)
                .role(1)
                .build();

        AccountDTOCreate account2 = AccountDTOCreate.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .created_date(currentDate)
                .role(1)
                .build();

        AccountDTOCreate account3 = AccountDTOCreate.builder()
                .username("testuser2")
                .email("test2@example.com")
                .password("password2")
                .created_date(currentDate)
                .role(2)
                .build();

        Assertions.assertEquals(account1, account2);
        Assertions.assertNotEquals(account1, account3);

        Assertions.assertEquals(account1.hashCode(), account2.hashCode());
        Assertions.assertNotEquals(account1.hashCode(), account3.hashCode());
    }

    @Test
    void testToString() {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        AccountDTOCreate account = AccountDTOCreate.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .created_date(currentDate)
                .role(1)
                .build();

        String expectedToString = "AccountDTOCreate(username=testuser, email=test@example.com, password=password, created_date=<current date>, role=1)";
        String actualToString = account.toString();

        // Replace the date value with <current date> in the actualToString
        actualToString = actualToString.replaceAll("\\d{4}-\\d{2}-\\d{2}", "<current date>");

        Assertions.assertEquals(expectedToString, actualToString);
    }
}
