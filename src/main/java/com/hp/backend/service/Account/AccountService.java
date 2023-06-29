package com.hp.backend.service.Account;

import java.util.List;
import java.util.Map;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTOResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTOResponse;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOCreate;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginRequest;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginResponse;



public interface AccountService {

    public Map<String, AccountDTOLoginResponse> authenticate(Map<String, AccountDTOLoginRequest> accountLoginRequestMap)
            throws CustomBadRequestException;

    public Map<String, AccountDTOLoginResponse> registerAccount(Map<String, AccountDTOCreate> accountDTOCreateMap);

    public List<MenteeDTOResponse> getMenteeList();

    public List<MentorDTOResponse> getMentorList();

    public MentorDTODetailResponse findMentorByID(int id) throws CustomBadRequestException;

    public MenteeDTODetailResponse findMenteeByID(int id) throws CustomBadRequestException;

    public void deleteById(int id) throws CustomBadRequestException;
}
