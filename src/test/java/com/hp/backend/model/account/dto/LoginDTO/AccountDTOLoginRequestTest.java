package com.hp.backend.model.account.dto.LoginDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountDTOLoginRequestTest {
    @Test
    void testGetters() {
        AccountDTOLoginRequest account = AccountDTOLoginRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();

        Assertions.assertEquals("test@example.com", account.getEmail());
        Assertions.assertEquals("password", account.getPassword());
    }

    @Test
    void testSetters() {
        AccountDTOLoginRequest account = new AccountDTOLoginRequest();

        account.setEmail("test@example.com");
        account.setPassword("password");

        Assertions.assertEquals("test@example.com", account.getEmail());
        Assertions.assertEquals("password", account.getPassword());
    }

    @Test
    void testEqualsAndHashCode() {
        AccountDTOLoginRequest account1 = AccountDTOLoginRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();

        AccountDTOLoginRequest account2 = AccountDTOLoginRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();

        AccountDTOLoginRequest account3 = AccountDTOLoginRequest.builder()
                .email("test2@example.com")
                .password("password2")
                .build();

        Assertions.assertEquals(account1, account2);
        Assertions.assertNotEquals(account1, account3);

        Assertions.assertEquals(account1.hashCode(), account2.hashCode());
        Assertions.assertNotEquals(account1.hashCode(), account3.hashCode());
    }

    @Test
    void testToString() {
        AccountDTOLoginRequest account = AccountDTOLoginRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();

        String expectedToString = "AccountDTOLoginRequest(email=test@example.com, password=password)";
        String actualToString = account.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }
}
