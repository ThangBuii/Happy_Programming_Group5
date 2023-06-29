package com.hp.backend.model.account.dto.LoginDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTOLoginRequest {
    private String email;
    private String password;
}
