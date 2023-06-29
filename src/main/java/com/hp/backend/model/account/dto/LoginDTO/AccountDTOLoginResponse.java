package com.hp.backend.model.account.dto.LoginDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTOLoginResponse {
    private String token;
    private int role;
}
