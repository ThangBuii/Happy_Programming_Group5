package com.hp.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOCreate;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginRequest;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginResponse;
import com.hp.backend.service.Account.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    public Map<String, AccountDTOLoginResponse> login(
            @RequestBody Map<String, AccountDTOLoginRequest> accountLoginRequestMap) throws CustomBadRequestException {
        return accountService.authenticate(accountLoginRequestMap);
    }

    @PostMapping("/register")
    public Map<String, AccountDTOLoginResponse> register(@RequestBody Map<String, AccountDTOCreate> accountDTOCreateMap) {
        return accountService.registerAccount(accountDTOCreateMap);
    }

}
