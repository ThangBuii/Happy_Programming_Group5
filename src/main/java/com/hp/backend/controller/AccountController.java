package com.hp.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
import com.hp.backend.model.CustomError;
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
import com.hp.backend.model.account.dto.MentorSiteDTO.MentorDTODetailUpdateRequest;
import com.hp.backend.service.Account.AccountService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AccountController {

    private final AccountService accountService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/public/login")
    public Map<String, AccountDTOLoginResponse> login(
            @RequestBody Map<String, AccountDTOLoginRequest> accountLoginRequestMap) throws CustomBadRequestException {
        return accountService.authenticate(accountLoginRequestMap);
    }

    @PostMapping("/public/register")
    public Map<String, AccountDTOLoginResponse> register(
            @RequestBody Map<String, AccountDTOCreate> accountDTOCreateMap, BindingResult bindingResult)
            throws CustomBadRequestException {

        for (Map.Entry<String, AccountDTOCreate> entry : accountDTOCreateMap.entrySet()) {
            String key = entry.getKey();
            AccountDTOCreate accountDTOCreate = entry.getValue();

            // Manually validate each AccountDTOCreate object
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<AccountDTOCreate>> violations = validator.validate(accountDTOCreate);

            // Check for validation errors and add them to BindingResult
            if (!violations.isEmpty()) {
                for (ConstraintViolation<AccountDTOCreate> violation : violations) {
                    String field = violation.getPropertyPath().toString();
                    String message = violation.getMessage();
                    bindingResult.addError(new FieldError("accountDTOCreateMap", field, message));
                }
            }
        }

        // Check if there are any validation errors
        if (bindingResult.hasErrors()) {
            FieldError firstError = bindingResult.getFieldErrors().get(0);
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message(firstError.getDefaultMessage()).build());
        }
        return accountService.registerAccount(accountDTOCreateMap);
    }

    @GetMapping("/admin/mentee-list")
    public List<MenteeDTOResponse> getMenteeList() {

        return accountService.getMenteeList();
    }

    @GetMapping("/admin/mentor-list")
    public List<MentorDTOResponse> getMentorList() {
        return accountService.getMentorList();
    }

    @GetMapping("/admin/men/{id}")
    public MentorDTODetailResponse getMentorByID(@PathVariable int id) throws CustomBadRequestException {
        return accountService.findMentorByID(id);
    }

    @DeleteMapping("/admin/account/{id}")
    public void deleteAccount(@PathVariable int id) throws CustomBadRequestException {
        accountService.deleteById(id);
    }

    @GetMapping("/mentor/profile")
    public MentorDTODetailResponse getMentorProfile(HttpServletRequest request) throws CustomBadRequestException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return accountService.findMentorByID(tokenPayload.getAccount_id());
    }

    @GetMapping("/mentee/profile")
    public MenteeDTODetailResponse getMenteeProfileLoggedIn(HttpServletRequest request)
            throws CustomBadRequestException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return accountService.findMenteeByID(tokenPayload.getAccount_id());
    }

    @PostMapping("/mentee/profile")
    public void updateMenteeProfile(
         @RequestBody @Valid MentorDTODetailUpdateRequest mentee, BindingResult bindingResult,
            HttpServletRequest request) throws CustomBadRequestException {
        if (bindingResult.hasErrors()) {
            FieldError firstError = bindingResult.getFieldErrors().get(0);
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message(firstError.getDefaultMessage()).build());
        }

        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);

        accountService.updateMenteeProfile(mentee, tokenPayload.getAccount_id());
    }

    @PostMapping("/mentor/profile")
    public void updateMentorProfile(@RequestBody @Valid MentorDTODetailUpdateRequest mentor, BindingResult bindingResult, HttpServletRequest request)
            throws CustomBadRequestException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
         if (bindingResult.hasErrors()) {
            FieldError firstError = bindingResult.getFieldErrors().get(0);
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message(firstError.getDefaultMessage()).build());
        }
        accountService.updateMentorProfile(mentor, tokenPayload.getAccount_id());
    }

    @PostMapping("/password")
    public void changePassword(@RequestBody AccountChangePasswordRequestDTO password, HttpServletRequest request)
            throws CustomBadRequestException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        accountService.changePassword(password, tokenPayload.getAccount_id());
    }

    @GetMapping("/public/findMentor")
    public List<FindMentorResponseDTO> getListFindMentor(
            HttpServletRequest request,
            @RequestParam("skill_id") List<Integer> skillIds) {
        String token = jwtTokenUtil.getRequestToken(request);
        int account_id = 0;
        if (!token.isEmpty()) {
            TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
            account_id = tokenPayload.getAccount_id();
        }

        return accountService.getListFindMentor(account_id, skillIds);
    }

    @GetMapping("/public/profile/{id}")
    public FindMentorResponseDTO getFindMentorProfile(@PathVariable int id, HttpServletRequest request)
            throws CustomBadRequestException {
        String token = jwtTokenUtil.getRequestToken(request);
        int account_id = 0;
        if (!token.isEmpty()) {
            TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
            account_id = tokenPayload.getAccount_id();
        }
        return accountService.findMentorProfile(id, account_id);
    }

}
