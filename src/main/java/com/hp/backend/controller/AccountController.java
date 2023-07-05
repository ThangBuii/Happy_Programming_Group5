package com.hp.backend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.model.account.dto.AccountChangePasswordRequestDTO;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTOResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTOResponse;
import com.hp.backend.model.account.dto.FindMentorDTO.FindMentorResponseDTO;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOCreate;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginRequest;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginResponse;
import com.hp.backend.model.account.dto.MenteeSiteDTO.MenteeDTODetailUpdateRequest;
import com.hp.backend.model.account.dto.MentorSiteDTO.MentorDTODetailUpdateRequest;
import com.hp.backend.service.Account.AccountService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {

    private final AccountService accountService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public Map<String, AccountDTOLoginResponse> login(
            @RequestBody Map<String, AccountDTOLoginRequest> accountLoginRequestMap) throws CustomBadRequestException {
        return accountService.authenticate(accountLoginRequestMap);
    }

    @PostMapping("/register")
    public Map<String, AccountDTOLoginResponse> register(@RequestBody Map<String, AccountDTOCreate> accountDTOCreateMap) throws CustomBadRequestException {
        return accountService.registerAccount(accountDTOCreateMap);
    }

    @GetMapping("/admin/mentee-list")
    public List<MenteeDTOResponse> getMenteeList(){

        return accountService.getMenteeList();
    }

    @GetMapping("/admin/mentor-list")
    public List<MentorDTOResponse> getMentorList(){
        return accountService.getMentorList();
    }

    @GetMapping("/admin/mentee/{id}")
    public MenteeDTODetailResponse getMenteeByID(@PathVariable int id) throws CustomBadRequestException{
        return accountService.findMenteeByID(id);
    }

    @GetMapping("/admin/mentor/{id}")
    public MentorDTODetailResponse getMentorByID(@PathVariable int id) throws CustomBadRequestException{
        return accountService.findMentorByID(id);
    }

    @DeleteMapping("/admin/account/{id}")
    public void deleteAccount(@PathVariable int id) throws CustomBadRequestException{
        accountService.deleteById(id);
    }

    @GetMapping("/mentor/profile")
    public MentorDTODetailResponse getMentorProfile(HttpServletRequest request) throws CustomBadRequestException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return accountService.findMentorByID(tokenPayload.getAccount_id());
    }

    @GetMapping("/mentee/profile")
    public MenteeDTODetailResponse getMenteeProfile(HttpServletRequest request) throws CustomBadRequestException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return accountService.findMenteeByID(tokenPayload.getAccount_id());
    }

    @PostMapping("/mentee/profile")
    public void updateMenteeProfile(@RequestBody MenteeDTODetailUpdateRequest mentee ,HttpServletRequest request) throws CustomBadRequestException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        accountService.updateMenteeProfile(mentee,tokenPayload.getAccount_id());
    }

    @PostMapping("/mentor/profile")
    public void updateMentorProfile(@RequestBody MentorDTODetailUpdateRequest mentor ,HttpServletRequest request) throws CustomBadRequestException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        accountService.updateMentorProfile(mentor,tokenPayload.getAccount_id());
    }

    @PostMapping("/password")
    public void changePassword(@RequestBody AccountChangePasswordRequestDTO password ,HttpServletRequest request) throws CustomBadRequestException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        accountService.changePassword(password,tokenPayload.getAccount_id());
    }

    @GetMapping("/findMentor")
    public List<FindMentorResponseDTO> getListFindMentor(HttpServletRequest request,@RequestParam int skill_id){
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return accountService.getListFindMentor(tokenPayload.getAccount_id(), skill_id);
    } 

}