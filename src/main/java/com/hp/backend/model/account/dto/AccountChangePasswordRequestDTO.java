package com.hp.backend.model.account.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class AccountChangePasswordRequestDTO {
    private String old_password;
    private String new_password;
    private String repass;
}
