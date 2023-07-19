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
import com.hp.backend.model.account.dto.MentorSiteDTO.MentorDTODetailUpdateRequest;
import com.hp.backend.model.account.mapper.AccountMapper;
import com.hp.backend.model.favorite.dto.FavoriteListMenteeResponseDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.FavoriteRepository;
import com.hp.backend.repository.Mentor_SkillsRepository;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.service.EmailService;
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
    private final SessionRepository sessionRepository;
    private final EmailService emailService;
    public static final String ACCOUNT_NOT_EXIST = "Account not exist";
    public static final String ACCOUNT = "account";

    @Override
    public Map<String, AccountDTOLoginResponse> authenticate(Map<String, AccountDTOLoginRequest> accountLoginRequestMap)
            throws CustomBadRequestException {
        AccountDTOLoginRequest accountDTOLoginRequest = accountLoginRequestMap.get(ACCOUNT);

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
    public Map<String, AccountDTOLoginResponse> registerAccount(Map<String, AccountDTOCreate> accountDTOCreateMap)
            throws CustomBadRequestException {
        AccountDTOCreate accountDTOCreate = accountDTOCreateMap.get(ACCOUNT);
        if (accountRepository.existsByEmail(accountDTOCreate.getEmail())) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Email has already existed").build());
        } else if (accountRepository.existsByUsername(accountDTOCreate.getUsername())) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Username has already existed").build());
        }
        Account account = AccountMapper.toUser(accountDTOCreate);

        account = accountRepository.save(account);
        emailService.sendEmail(account.getEmail(), "Register Account Successfully",
                "Welcome to happy programming. Contact us by this email!");
        return buildDTOResponse(account);
    }

    private Map<String, AccountDTOLoginResponse> buildDTOResponse(Account account) {
        Map<String, AccountDTOLoginResponse> wrapper = new HashMap<>();
        AccountDTOLoginResponse accountDTOResponse = AccountMapper.toAccountDTOResponse(account);
        accountDTOResponse.setToken(jwtTokenUtil.generateToken(account, 24 * 60 * 60));
        wrapper.put(ACCOUNT, accountDTOResponse);
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

        if (account.isPresent()) {
            return accountMapper.toMentorDTODetailResponse(account.get());
        } else {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message(ACCOUNT_NOT_EXIST).build());
        }

    }

    @Override
    public MenteeDTODetailResponse findMenteeByID(int id) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent() && account.get().getRole() == 2) {
            return accountMapper.toMenteeDTODetailResponse(account.get());
        } else {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message(ACCOUNT_NOT_EXIST).build());
        }
    }

    @Override
    public void deleteById(int id) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            accountRepository.deleteById(id);
        } else {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message(ACCOUNT_NOT_EXIST).build());
        }
    }

    @Override
    public void updateMenteeProfile(MentorDTODetailUpdateRequest mentee, int account_id)
            throws CustomBadRequestException {

        Account account = accountMapper.toUpdatedMenteeAccount(mentee, account_id);
        accountRepository.save(account);
    }

    @Override
    public void updateMentorProfile(MentorDTODetailUpdateRequest mentor, int account_id)
            throws CustomBadRequestException {
        Account account = accountMapper.toUpdatedMentorAccount(mentor, account_id);
        accountRepository.save(account);
    }

    @Override
    public void changePassword(AccountChangePasswordRequestDTO password, int account_id)
            throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(account_id);

        if (!account.isPresent()) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message(ACCOUNT_NOT_EXIST).build());
        } else if (!account.get().getPassword().equals(password.getOld_password())) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Old Password is not true").build());
        } else if (!password.getRepass().equals(password.getNew_password())) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Repassword is not equal to New Password").build());
        }

        account.get().setPassword(password.getNew_password());
        accountRepository.save(account.get());
    }

    @Override
    public List<FavoriteListMenteeResponseDTO> getListFavorite(int account_id) throws CustomBadRequestException {
        List<Favorite_Mentor> favorites = favoriteRepository.findByMentee_id(account_id);
        List<FavoriteListMenteeResponseDTO> responseDTOs = new ArrayList<>();
        for (Favorite_Mentor favorite : favorites) {
            responseDTOs.add(accountMapper.toFavoriteListResponseDTO(favorite));
        }

        return responseDTOs;
    }

    @Override
    public void addFavorite(int mentee_id, int mentor_id) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(mentor_id);

        if (!account.isPresent()) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message(ACCOUNT_NOT_EXIST).build());
        }

        if (account.get().getRole() != 1) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Mentor not exist").build());
        }

        favoriteRepository.save(Favorite_Mentor.builder().mentee_id(mentee_id).mentor_id(mentor_id).build());
    }

    @Override
    public void deleteFavorite(int mentor_id, int mentee_id)
            throws CustomInternalServerException, CustomBadRequestException {
        Favorite_Mentor favorite = favoriteRepository.findByMentorMenteeId(mentor_id, mentee_id);

        if (favorite.getMentee_id() != mentee_id) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Bad request").build());
        }

        favoriteRepository.delete(favorite);
    }

    @Override
    public List<FindMentorResponseDTO> getListFindMentor(int account_id, List<Integer> skills) {
        List<Account> accounts = new ArrayList<>();

        List<Integer> mentor_ids;
        if (skills.get(0) != 0) {
            mentor_ids = sessionRepository.findAllBySkill_ID(skills);
        } else {
            mentor_ids = sessionRepository.findAllBySkill_ID();
        }

        int[] mentorIdsArray = mentor_ids.stream().mapToInt(Integer::intValue).toArray();
        for (int id : mentorIdsArray) {
            Optional<Account> accountOptional = accountRepository.findById(id);
            if (accountOptional.isPresent()) {
                accounts.add(accountOptional.get());
            }
        }
        List<FindMentorResponseDTO> results = new ArrayList<>();
        for (Account account : accounts) {
            results.add(accountMapper.toFindMentorResponse(account, account_id));
        }

        return results;
    }

    @Override
    public String getAccountName(int receiver_id) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(receiver_id);
        if (!account.isPresent()) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("ACCOUNT_NOT_EXIST").build());
        }

        return account.get().getUsername();
    }

    @Override
    public FindMentorResponseDTO findMentorProfile(int mentor_id, int account_id) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findById(mentor_id);
        if (!account.isPresent()) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message(ACCOUNT_NOT_EXIST).build());
        }
        return accountMapper.toFindMentorResponse(account.get(), account_id);
    }

}
