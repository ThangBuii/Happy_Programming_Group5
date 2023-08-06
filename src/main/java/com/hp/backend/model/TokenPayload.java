package com.hp.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenPayload {
    private int account_id;
    private int role;
}
