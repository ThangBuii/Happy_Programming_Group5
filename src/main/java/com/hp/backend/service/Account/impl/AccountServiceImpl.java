package com.hp.backend.service.Account.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Account;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOCreate;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginRequest;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginResponse;
import com.hp.backend.model.account.mapper.AccountMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.service.Account.AccountService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public Map<String, AccountDTOLoginResponse> authenticate(Map<String, AccountDTOLoginRequest> accountLoginRequestMap)
            throws CustomBadRequestException {
        AccountDTOLoginRequest accountDTOLoginRequest = accountLoginRequestMap.get("account");

        Optional<Account> accountOptional = accountRepository.findByEmail(accountDTOLoginRequest.getEmail());

        boolean isAuthen = false;
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getPassword().equals(accountDTOLoginRequest.getPassword())) {
                isAuthen = true;
            }
        }

        if (!isAuthen) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Email or password incorrect").build());
        }

        return buildDTOResponse(accountOptional.get());
    }

    @Override
    public Map<String, AccountDTOLoginResponse> registerAccount(Map<String, AccountDTOCreate> accountDTOCreateMap) {
        AccountDTOCreate accountDTOCreate = accountDTOCreateMap.get("account");
        Account account = AccountMapper.toUser(accountDTOCreate);

        account = accountRepository.save(account);
        return buildDTOResponse(account);
    }

    private Map<String, AccountDTOLoginResponse> buildDTOResponse(Account account) {
        Map<String, AccountDTOLoginResponse> wrapper = new HashMap<>();
        AccountDTOLoginResponse accountDTOResponse = AccountMapper.toAccountDTOResponse(account);
        accountDTOResponse.setToken(jwtTokenUtil.generateToken(account, 24 * 60 * 60));
        wrapper.put("account", accountDTOResponse);
        return wrapper;
    }

}
