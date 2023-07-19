package com.hp.backend.model.Session.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UpdateSessionDTO_UpdateSessionDTOBuilderTest {
    @Test
    void testBuild() {
        UpdateSessionDTO updateSessionDTO = UpdateSessionDTO.builder()
                .status(1)
                .build();

        assertEquals(1, updateSessionDTO.getStatus());
    }

    @Test
    void testStatus() {
        int status = 1;
        UpdateSessionDTO updateSessionDTO = new UpdateSessionDTO();
        updateSessionDTO.setStatus(status);

        assertEquals(status, updateSessionDTO.getStatus());
    }

    @Test
    void testToString() {
        UpdateSessionDTO updateSessionDTO = UpdateSessionDTO.builder()
                .status(1)
                .build();

        String expectedToString = "UpdateSessionDTO(status=1)";
        assertEquals(expectedToString, updateSessionDTO.toString());
    }
}
