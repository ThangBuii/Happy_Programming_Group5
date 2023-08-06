package com.hp.backend.model.account.dto.LoginDTO;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTOCreate {
    @NotNull(message = "Username không được trống!")
    private String username;

    @NotNull(message = "Email không được trống!")
    @Email(message = "Email không hợp lệ!")
    private String email;
    
    @NotNull(message = "Mật khẩu không được trống!")
    @Size(min = 8, message = "Mật khẩu phải chứa ít nhất 8 kí tự!")
    @Pattern(regexp = ".*\\d.*", message = "Mật khẩu phải chứa ít nhất một chữ số!")
    private String password;
    private Date created_date;

    @Min(value = 1, message = "Hãy chọn role của bạn.")
    private int role;
}
