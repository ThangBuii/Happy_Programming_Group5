package com.hp.backend.model.account.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AccountChangePasswordRequestDTOTest {
    @Test
    void testBuilder() {
        // Arrange
        String oldPassword = "oldPassword123";
        String newPassword = "newPassword456";
        String repass = "newPassword456";

        // Act
        AccountChangePasswordRequestDTO passwordDTO = AccountChangePasswordRequestDTO.builder()
                .old_password(oldPassword)
                .new_password(newPassword)
                .repass(repass)
                .build();

        // Assert
        assertNotNull(passwordDTO);
    }

    @Test
    void testCanEqual() {
        // Arrange
        AccountChangePasswordRequestDTO passwordDTO1 = AccountChangePasswordRequestDTO.builder().build();
        AccountChangePasswordRequestDTO passwordDTO2 = AccountChangePasswordRequestDTO.builder().build();

        // Assert
        assertTrue(passwordDTO1.canEqual(passwordDTO2));
    }

    @Test
    void testEquals() {
        // Arrange
        AccountChangePasswordRequestDTO passwordDTO1 = AccountChangePasswordRequestDTO.builder().build();
        AccountChangePasswordRequestDTO passwordDTO2 = AccountChangePasswordRequestDTO.builder().build();

        // Assert
        assertEquals(passwordDTO1, passwordDTO2);
    }

    @Test
    void testGetNew_password() {
        // Arrange
        String newPassword = "newPassword456";
        AccountChangePasswordRequestDTO passwordDTO = AccountChangePasswordRequestDTO.builder()
                .new_password(newPassword).build();

        // Assert
        assertEquals(newPassword, passwordDTO.getNew_password());
    }

    @Test
    void testGetOld_password() {
        // Arrange
        String oldPassword = "oldPassword123";
        AccountChangePasswordRequestDTO passwordDTO = AccountChangePasswordRequestDTO.builder()
                .old_password(oldPassword).build();

        // Assert
        assertEquals(oldPassword, passwordDTO.getOld_password());
    }

    @Test
    void testGetRepass() {
        // Arrange
        String repass = "newPassword456";
        AccountChangePasswordRequestDTO passwordDTO = AccountChangePasswordRequestDTO.builder().repass(repass).build();

        // Assert
        assertEquals(repass, passwordDTO.getRepass());
    }

    @Test
    void testHashCode() {
        // Arrange
        AccountChangePasswordRequestDTO passwordDTO = AccountChangePasswordRequestDTO.builder().build();

        // Assert
        assertEquals(passwordDTO.hashCode(), passwordDTO.hashCode());
    }

    @Test
    void testSetNew_password() {
        // Arrange
        String newPassword = "newPassword456";
        AccountChangePasswordRequestDTO passwordDTO = AccountChangePasswordRequestDTO.builder().build();

        // Act
        passwordDTO.setNew_password(newPassword);

        // Assert
        assertEquals(newPassword, passwordDTO.getNew_password());
    }

    @Test
    void testSetOld_password() {
        // Arrange
        String oldPassword = "oldPassword123";
        AccountChangePasswordRequestDTO passwordDTO = AccountChangePasswordRequestDTO.builder().build();

        // Act
        passwordDTO.setOld_password(oldPassword);

        // Assert
        assertEquals(oldPassword, passwordDTO.getOld_password());
    }

    @Test
    void testSetRepass() {
        // Arrange
        String repass = "newPassword456";
        AccountChangePasswordRequestDTO passwordDTO = AccountChangePasswordRequestDTO.builder().build();

        // Act
        passwordDTO.setRepass(repass);

        // Assert
        assertEquals(repass, passwordDTO.getRepass());
    }

    @Test
    void testToString() {
        // Arrange
        String oldPassword = "oldPassword123";
        String newPassword = "newPassword456";
        String repass = "newPassword456";
        AccountChangePasswordRequestDTO passwordDTO = AccountChangePasswordRequestDTO.builder()
                .old_password(oldPassword)
                .new_password(newPassword)
                .repass(repass)
                .build();

        // Assert
        assertEquals(
                "AccountChangePasswordRequestDTO(old_password=oldPassword123, new_password=newPassword456, repass=newPassword456)",
                passwordDTO.toString());
    }
}
