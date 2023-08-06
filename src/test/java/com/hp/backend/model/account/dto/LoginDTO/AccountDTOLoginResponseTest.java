package com.hp.backend.model.account.dto.LoginDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountDTOLoginResponseTest {
    @Test
    void testGetters() {
        AccountDTOLoginResponse account = AccountDTOLoginResponse.builder()
                .token("token123")
                .role(1)
                .build();

        Assertions.assertEquals("token123", account.getToken());
        Assertions.assertEquals(1, account.getRole());
    }

    @Test
    void testSetters() {
        AccountDTOLoginResponse account = new AccountDTOLoginResponse();

        account.setToken("token123");
        account.setRole(1);

        Assertions.assertEquals("token123", account.getToken());
        Assertions.assertEquals(1, account.getRole());
    }

    @Test
    void testEqualsAndHashCode() {
        AccountDTOLoginResponse account1 = AccountDTOLoginResponse.builder()
                .token("token123")
                .role(1)
                .build();

        AccountDTOLoginResponse account2 = AccountDTOLoginResponse.builder()
                .token("token123")
                .role(1)
                .build();

        AccountDTOLoginResponse account3 = AccountDTOLoginResponse.builder()
                .token("token456")
                .role(2)
                .build();

        Assertions.assertEquals(account1, account2);
        Assertions.assertNotEquals(account1, account3);

        Assertions.assertEquals(account1.hashCode(), account2.hashCode());
        Assertions.assertNotEquals(account1.hashCode(), account3.hashCode());
    }

    @Test
    void testToString() {
        AccountDTOLoginResponse account = AccountDTOLoginResponse.builder()
                .token("token123")
                .role(1)
                .build();

        String expectedToString = "AccountDTOLoginResponse(token=token123, role=1)";
        String actualToString = account.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }
}
