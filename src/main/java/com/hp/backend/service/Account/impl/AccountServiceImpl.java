package com.hp.backend.service.Account.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Favorite_Mentor;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.CustomError;
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
import com.hp.backend.model.account.mapper.AccountMapper;
import com.hp.backend.model.favorite.dto.FavoriteListMenteeResponseDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.FavoriteRepository;
import com.hp.backend.repository.Mentor_SkillsRepository;
import com.hp.backend.service.Account.AccountService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final FavoriteRepository favoriteRepository;
    private final Mentor_SkillsRepository mentor_SkillsRepository;

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
    public Map<String, AccountDTOLoginResponse> registerAccount(Map<String, AccountDTOCreate> accountDTOCreateMap) throws CustomBadRequestException {
        AccountDTOCreate accountDTOCreate = accountDTOCreateMap.get("account");
        if(accountRepository.existsByEmail(accountDTOCreate.getEmail())){
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Email has already existed").build());
        }else if(accountRepository.existsByUsername(accountDTOCreate.getUsername())){
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Username has already existed").build());
        }
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

    @Override
    public List<MenteeDTOResponse> getMenteeList() {
        List<Account> accounts = accountRepository.findAllByRole(2);
        List<MenteeDTOResponse> mentees = new ArrayList<>();
        for (Account account : accounts) {
            mentees.add(accountMapper.toMenteeDTOResponse(account));
        }

        return mentees;
    }

    @Override
    public List<MentorDTOResponse> getMentorList() {
        List<Account> accounts = accountRepository.findAllByRole(1);
        List<MentorDTOResponse> mentors = new ArrayList<>();
        for (Account account : accounts) {
            mentors.add(accountMapper.toMentorDTOResponse(account));
        }

        return mentors;
    }

    @Override
    public MentorDTODetailResponse findMentorByID(int id) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(id);

        if(account.isPresent() && account.get().getRole() == 1){
            return accountMapper.toMentorDTODetailResponse(account.get());
        }else{
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Account not exist").build());
        }
        
    }

    @Override
    public MenteeDTODetailResponse findMenteeByID(int id) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(id);

        if(account.isPresent() && account.get().getRole() == 2){
            return accountMapper.toMenteeDTODetailResponse(account.get());
        }else{
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Account not exist").build());
        }
    }

    @Override
    public void deleteById(int id) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            accountRepository.deleteById(id);
        }else{
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Account not exist").build());
        }
    }

    @Override
    public void updateMenteeProfile(MenteeDTODetailUpdateRequest mentee, int account_id) throws CustomBadRequestException {
        
        Account account = accountMapper.toUpdatedMenteeAccount(mentee,account_id);
        accountRepository.save(account);
    }

    @Override
    public void updateMentorProfile(MentorDTODetailUpdateRequest mentor, int account_id) throws CustomBadRequestException {
        Account account = accountMapper.toUpdatedMentorAccount(mentor,account_id);
        accountRepository.save(account);
    }

    @Override
    public void changePassword(AccountChangePasswordRequestDTO password, int account_id) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(account_id);

        if(!account.isPresent()){
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Account not exist").build());
        }else if(!account.get().getPassword().equals(password.getOld_password())){
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Old Password is not true").build());
        }else if(!password.getRepass().equals(password.getNew_password())){
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Repassword is not equal to New Password").build());
        }

        account.get().setPassword(password.getNew_password());
        accountRepository.save(account.get());
    }

    @Override
    public List<FavoriteListMenteeResponseDTO> getListFavorite(int account_id) throws CustomBadRequestException {
        List<Favorite_Mentor> favorites = favoriteRepository.findByMentee_id(account_id);
        List<FavoriteListMenteeResponseDTO> responseDTOs  = new ArrayList<>();
        for(Favorite_Mentor favorite : favorites){
            responseDTOs.add(accountMapper.toFavoriteListResponseDTO(favorite));
        }

        return responseDTOs;
    }

    @Override
    public void addFavorite(int mentee_id, int mentor_id) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(mentor_id);

        if(!account.isPresent()){
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Account not exist").build());
        }

        if(account.get().getRole() != 1){
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Mentor not exist").build());
        }
        
        favoriteRepository.save(Favorite_Mentor.builder().mentee_id(mentee_id).mentor_id(mentor_id).build());
    }

    @Override
    public void deleteFavorite(int favorite_id,int mentee_id) throws CustomInternalServerException, CustomBadRequestException {
        Optional<Favorite_Mentor> favorite = favoriteRepository.findById(favorite_id);

        if(!favorite.isPresent()){
            throw new CustomInternalServerException(
                    CustomError.builder().code("500").message("System failure").build());
        }

        if(favorite.get().getMentee_id() != mentee_id){
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Bad request").build());
        }

        favoriteRepository.delete(favorite.get());
    }

    @Override
    public List<FindMentorResponseDTO> getListFindMentor(int account_id, int skill_id) {
        List<Account> accounts = new ArrayList<>();
        if(skill_id != 0){
            accounts = mentor_SkillsRepository.findBySkillId(skill_id);
        }else{
            accounts = accountRepository.findAllByRole(1);
        }
        List<FindMentorResponseDTO> results = new ArrayList<>();
        for(Account account : accounts){
            results.add(accountMapper.toFindMentorResponse(account, account_id));
        }

        return results;
    }

}
