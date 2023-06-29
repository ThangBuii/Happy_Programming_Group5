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
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTOResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTOResponse;
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

    @GetMapping("/admin/mentee-list")
    public List<MenteeDTOResponse> getMenteeList(){
        return accountService.getMenteeList();
    }

    @GetMapping("admin/mentor-list")
    public List<MentorDTOResponse> getMentorList(){
        return accountService.getMentorList();
    }

    @GetMapping("admin/mentee/{id}")
    public MenteeDTODetailResponse getMenteeByID(@PathVariable int id) throws CustomBadRequestException{
        return accountService.findMenteeByID(id);
    }

    @GetMapping("admin/mentor/{id}")
    public MentorDTODetailResponse getMentorByID(@PathVariable int id) throws CustomBadRequestException{
        return accountService.findMentorByID(id);
    }

    @DeleteMapping("admin/account/{id}")
    public void deleteAccount(@PathVariable int id) throws CustomBadRequestException{
        accountService.deleteById(id);
    }

}
