package com.hp.backend.service.Account.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Favorite_Mentor;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.account.dto.AccountChangePasswordRequestDTO;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTOResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTOResponse;
import com.hp.backend.model.account.dto.FindMentorDTO.FindMentorResponseDTO;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginRequest;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginResponse;
import com.hp.backend.model.account.dto.MentorSiteDTO.MentorDTODetailUpdateRequest;
import com.hp.backend.model.account.mapper.AccountMapper;
import com.hp.backend.model.favorite.dto.FavoriteListMenteeResponseDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.FavoriteRepository;
import com.hp.backend.service.Account.AccountService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Find;

public class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private FavoriteRepository favoriteRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;
    @Test
    void testAddFavorite_AccountExistsAndIsMentor() throws CustomBadRequestException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int menteeId = 1;
        int mentorId = 2;

        Account mentorAccount = new Account();
        mentorAccount.setRole(1);

        when(accountRepository.findById(mentorId)).thenReturn(Optional.of(mentorAccount));

        // Act
        accountService.addFavorite(menteeId, mentorId);

        // Assert
        verify(favoriteRepository, times(1)).save(any());
    }

    @Test
    void testAddFavorite_AccountNotExists() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int menteeId = 1;
        int mentorId = 2;

        when(accountRepository.findById(mentorId)).thenReturn(Optional.empty());

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            accountService.addFavorite(menteeId, mentorId);
        });

        assertEquals("400", exception.getErrors().get("errors").getCode());
        assertEquals("Account not exist", exception.getErrors().get("errors").getMessage());

        verify(favoriteRepository, never()).save(any());
    }
    @Test
    void testAddFavorite_AccountExistsButNotMentor() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int menteeId = 1;
        int mentorId = 2;

        Account nonMentorAccount = new Account();
        nonMentorAccount.setRole(2);

        when(accountRepository.findById(mentorId)).thenReturn(Optional.of(nonMentorAccount));

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            accountService.addFavorite(menteeId, mentorId);
        });

        assertEquals("400", exception.getErrors().get("errors").getCode());
        assertEquals("Mentor not exist", exception.getErrors().get("errors").getMessage());

        verify(favoriteRepository, never()).save(any());
    }

    @Test
    void testAuthenticate_ValidCredentials() throws CustomBadRequestException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        String email = "test@example.com";
        String password = "password";

        AccountDTOLoginRequest accountDTOLoginRequest = AccountDTOLoginRequest.builder().build();
        accountDTOLoginRequest.setEmail(email);
        accountDTOLoginRequest.setPassword(password);

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);

        Map<String, AccountDTOLoginRequest> accountLoginRequestMap = new HashMap<>();
        accountLoginRequestMap.put("account", accountDTOLoginRequest);

        when(accountRepository.findByEmail(email)).thenReturn(Optional.of(account));

        // Act
        Map<String, AccountDTOLoginResponse> response = accountService.authenticate(accountLoginRequestMap);

        // Assert
        assertTrue(response.containsKey("account"));
        assertNotNull(response.get("account"));
    }

    @Test
    void testAuthenticate_InvalidCredentials() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        String email = "test@example.com";
        String password = "password";
        String incorrectPassword = "incorrectPassword";

        AccountDTOLoginRequest accountDTOLoginRequest = AccountDTOLoginRequest.builder().build();
        accountDTOLoginRequest.setEmail(email);
        accountDTOLoginRequest.setPassword(incorrectPassword);

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);

        Map<String, AccountDTOLoginRequest> accountLoginRequestMap = new HashMap<>();
        accountLoginRequestMap.put("account", accountDTOLoginRequest);

        when(accountRepository.findByEmail(email)).thenReturn(Optional.of(account));

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            accountService.authenticate(accountLoginRequestMap);
        });

        assertEquals("400", exception.getErrors().get("errors").getCode());
        assertEquals("Email or password incorrect", exception.getErrors().get("errors").getMessage());

        verify(accountRepository, never()).findByEmail(any());
    }

    @Test
    void testAuthenticate_AccountNotFound() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        String email = "test@example.com";
        String password = "password";

        AccountDTOLoginRequest accountDTOLoginRequest = AccountDTOLoginRequest.builder().build();
        accountDTOLoginRequest.setEmail(email);
        accountDTOLoginRequest.setPassword(password);

        Map<String, AccountDTOLoginRequest> accountLoginRequestMap = new HashMap<>();
        accountLoginRequestMap.put("account", accountDTOLoginRequest);

        when(accountRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            accountService.authenticate(accountLoginRequestMap);
        });

        assertEquals("400", exception.getErrors().get("errors").getCode());
        assertEquals("Email or password incorrect", exception.getErrors().get("errors").getMessage());

        verify(accountRepository, never()).findByEmail(any());
    }

    @Test
    void testChangePassword_AccountExists_CorrectOldPassword_MatchingNewPasswordAndRepassword() throws CustomBadRequestException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";

        Account account = new Account();
        account.setPassword(oldPassword);

        AccountChangePasswordRequestDTO password = AccountChangePasswordRequestDTO.builder().build();
        password.setOld_password(oldPassword);
        password.setNew_password(newPassword);
        password.setRepass(newPassword);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act
        accountService.changePassword(password, accountId);

        // Assert
        assertEquals(newPassword, account.getPassword());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testChangePassword_AccountNotExists() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";

        AccountChangePasswordRequestDTO password = AccountChangePasswordRequestDTO.builder().build();
        password.setOld_password(oldPassword);
        password.setNew_password(newPassword);
        password.setRepass(newPassword);

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            accountService.changePassword(password, accountId);
        });

        assertEquals("400", exception.getErrors().get("errors").getCode());
        assertEquals("Account not exist", exception.getErrors().get("errors").getMessage());

        verify(accountRepository, never()).save(any());
    }

    @Test
    void testChangePassword_IncorrectOldPassword() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;
        String oldPassword = "oldPassword";
        String incorrectOldPassword = "incorrectOldPassword";
        String newPassword = "newPassword";

        Account account = new Account();
        account.setPassword(oldPassword);

        AccountChangePasswordRequestDTO password = AccountChangePasswordRequestDTO.builder().build();
        password.setOld_password(incorrectOldPassword);
        password.setNew_password(newPassword);
        password.setRepass(newPassword);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            accountService.changePassword(password, accountId);
        });

        assertEquals("400", exception.getErrors().get("errors").getCode());
        assertEquals("Old Password is not true", exception.getErrors().get("errors").getMessage());

        verify(accountRepository, never()).save(any());
    }

    @Test
    void testChangePassword_NewPasswordDoesNotMatchRepassword() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String differentRepassword = "differentRepassword";

        Account account = new Account();
        account.setPassword(oldPassword);

        AccountChangePasswordRequestDTO password = AccountChangePasswordRequestDTO.builder().build();
        password.setOld_password(oldPassword);
        password.setNew_password(newPassword);
        password.setRepass(differentRepassword);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            accountService.changePassword(password, accountId);
        });

        assertEquals("400", exception.getErrors().get("errors").getCode());
        assertEquals("Repassword is not equal to New Password", exception.getErrors().get("errors").getMessage());

        verify(accountRepository, never()).save(any());
    }

    @Test
    void testDeleteById_ExistingAccount() throws CustomBadRequestException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;
        Account account = new Account();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act
        accountService.deleteById(accountId);

        // Assert
        verify(accountRepository, times(1)).deleteById(accountId);
    }

    @Test
    void testDeleteById_NonExistingAccount() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> {
            accountService.deleteById(accountId);
        });

        verify(accountRepository, never()).deleteById(accountId);
    }
    

    @Test
    void testDeleteFavorite_FavoriteExists_MenteeIdMatches() throws CustomInternalServerException, CustomBadRequestException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int favoriteId = 1;
        int menteeId = 2;

        Favorite_Mentor favorite = new Favorite_Mentor();
        favorite.setMentee_id(menteeId);

        when(favoriteRepository.findById(favoriteId)).thenReturn(Optional.of(favorite));

        // Act
        accountService.deleteFavorite(favoriteId, menteeId);

        // Assert
        verify(favoriteRepository, times(1)).delete(favorite);
    }

    @Test
    void testDeleteFavorite_FavoriteNotExists() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int favoriteId = 1;
        int menteeId = 2;

        when(favoriteRepository.findById(favoriteId)).thenReturn(Optional.empty());

        // Act & Assert
        CustomInternalServerException exception = assertThrows(CustomInternalServerException.class, () -> {
            accountService.deleteFavorite(favoriteId, menteeId);
        });

        assertEquals("500", exception.getErrors().get("errors").getCode());
        assertEquals("System failure", exception.getErrors().get("errors").getMessage());

        verify(favoriteRepository, never()).delete(any());
    }

    @Test
    void testDeleteFavorite_MenteeIdDoesNotMatch() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int favoriteId = 1;
        int menteeId = 2;

        Favorite_Mentor favorite = new Favorite_Mentor();
        favorite.setMentee_id(3);

        when(favoriteRepository.findById(favoriteId)).thenReturn(Optional.of(favorite));

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            accountService.deleteFavorite(favoriteId, menteeId);
        });

        assertEquals("400", exception.getErrors().get("errors").getCode());
        assertEquals("Bad request", exception.getErrors().get("errors").getMessage());

        verify(favoriteRepository, never()).delete(any());
    }


    @Test
    void testFindMenteeByID_ExistAccount() throws CustomBadRequestException {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;
        Account account = new Account();
        account.setRole(2);
        MenteeDTODetailResponse expectedMentee = MenteeDTODetailResponse.builder().build();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(accountMapper.toMenteeDTODetailResponse(account)).thenReturn(expectedMentee);

        // Act
        MenteeDTODetailResponse actualMentee = accountService.findMenteeByID(accountId);

        // Assert
        assertNotNull(actualMentee);
        assertEquals(expectedMentee, actualMentee);
    }

    @Test
    void testFindMenteeByID_NonExistingAccount() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> {
            accountService.findMenteeByID(accountId);
        });
    }

    @Test
    void testFindMenteeByID_NonMenteeAccount() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;
        Account account = new Account();
        account.setRole(1);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> {
            accountService.findMenteeByID(accountId);
        });
    }

    @Test
    void testFindMentorByID_ExistAccount() throws CustomBadRequestException {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;
        Account account = new Account();
        MentorDTODetailResponse expectedMentor = MentorDTODetailResponse.builder().build();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(accountMapper.toMentorDTODetailResponse(account)).thenReturn(expectedMentor);

        // Act
        MentorDTODetailResponse actualMentor = accountService.findMentorByID(accountId);

        // Assert
        assertNotNull(actualMentor);
        assertEquals(expectedMentor, actualMentor);
    }

    @Test
    void testFindMentorByID_NonExistAccount() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> {
            accountService.findMentorByID(accountId);
        });
    }
    @Test
    void testGetAccountName_AccountExists() throws CustomBadRequestException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int receiverId = 1;
        String expectedUsername = "testUser";

        Account account = new Account();
        account.setUsername(expectedUsername);

        when(accountRepository.findById(receiverId)).thenReturn(Optional.of(account));

        // Act
        String actualUsername = accountService.getAccountName(receiverId);

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testGetAccountName_AccountNotExists() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int receiverId = 1;

        when(accountRepository.findById(receiverId)).thenReturn(Optional.empty());

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            accountService.getAccountName(receiverId);
        });

        assertEquals("400", exception.getErrors().get("errors").getCode());
        assertEquals("Account not exists", exception.getErrors().get("errors").getMessage());
    }


    @Test
    void testGetListFavorite() throws CustomBadRequestException {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;

        List<Favorite_Mentor> favorites = new ArrayList<>();
        Favorite_Mentor favorite1 = new Favorite_Mentor();
        Favorite_Mentor favorite2 = new Favorite_Mentor();
        favorites.add(favorite1);
        favorites.add(favorite2);

        List<FavoriteListMenteeResponseDTO> expectedResponseDTOs = new ArrayList<>();
        FavoriteListMenteeResponseDTO responseDTO1 = FavoriteListMenteeResponseDTO.builder().build();
        FavoriteListMenteeResponseDTO responseDTO2 = FavoriteListMenteeResponseDTO.builder().build();
        expectedResponseDTOs.add(responseDTO1);
        expectedResponseDTOs.add(responseDTO2);

        when(favoriteRepository.findByMentee_id(accountId)).thenReturn(favorites);
        when(accountMapper.toFavoriteListResponseDTO(favorite1)).thenReturn(responseDTO1);
        when(accountMapper.toFavoriteListResponseDTO(favorite2)).thenReturn(responseDTO2);

        // Act
        List<FavoriteListMenteeResponseDTO> actualResponseDTOs = accountService.getListFavorite(accountId);

        // Assert
        assertEquals(expectedResponseDTOs.size(), actualResponseDTOs.size());
        assertEquals(expectedResponseDTOs.get(0), actualResponseDTOs.get(0));
        assertEquals(expectedResponseDTOs.get(1), actualResponseDTOs.get(1));

    }

    @Test
    void testGetListFindMentor_SkillIdNotZero() {
        
    }

    @Test
    void testGetMenteeList() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account();
        Account account2 = new Account();
        accounts.add(account1);
        accounts.add(account2);

        List<MenteeDTOResponse> expectedMentees = new ArrayList<>();
        MenteeDTOResponse mentee1 = MenteeDTOResponse.builder().build();
        MenteeDTOResponse mentee2 = MenteeDTOResponse.builder().build();
        expectedMentees.add(mentee1);
        expectedMentees.add(mentee2);

        when(accountRepository.findAllByRole(2)).thenReturn(accounts);
        when(accountMapper.toMenteeDTOResponse(account1)).thenReturn(mentee1);
        when(accountMapper.toMenteeDTOResponse(account2)).thenReturn(mentee2);

        // Act
        List<MenteeDTOResponse> actualMentees = accountService.getMenteeList();

        // Assert
        assertEquals(expectedMentees.size(), actualMentees.size());
        assertEquals(expectedMentees.get(0), actualMentees.get(0));
        assertEquals(expectedMentees.get(1), actualMentees.get(1));
    }

    @Test
    void testGetMentorList() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account();
        Account account2 = new Account();
        accounts.add(account1);
        accounts.add(account2);

        List<MentorDTOResponse> expectedMentors = new ArrayList<>();
        MentorDTOResponse mentor1 = MentorDTOResponse.builder().build();
        MentorDTOResponse mentor2 = MentorDTOResponse.builder().build();
        expectedMentors.add(mentor1);
        expectedMentors.add(mentor2);

        when(accountRepository.findAllByRole(1)).thenReturn(accounts);
        when(accountMapper.toMentorDTOResponse(account1)).thenReturn(mentor1);
        when(accountMapper.toMentorDTOResponse(account2)).thenReturn(mentor2);

        // Act
        List<MentorDTOResponse> actualMentors = accountService.getMentorList();

        // Assert
        assertEquals(expectedMentors.size(), actualMentors.size());
        assertEquals(expectedMentors.get(0), actualMentors.get(0));
        assertEquals(expectedMentors.get(1), actualMentors.get(1));
    }

    @Test
    void testRegisterAccount() {

    }

    @Test
    void testUpdateMenteeProfile() throws CustomBadRequestException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;
        MentorDTODetailUpdateRequest mentee = MentorDTODetailUpdateRequest.builder().build();
        Account updatedAccount = new Account();

        when(accountMapper.toUpdatedMenteeAccount(mentee, accountId)).thenReturn(updatedAccount);

        // Act
        accountService.updateMenteeProfile(mentee, accountId);

        // Assert
        verify(accountRepository, times(1)).save(updatedAccount);
    }

    @Test
    void testUpdateMentorProfile() throws CustomBadRequestException {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int accountId = 1;
        MentorDTODetailUpdateRequest mentor = MentorDTODetailUpdateRequest.builder().build();
        Account updatedAccount = new Account();

        when(accountMapper.toUpdatedMentorAccount(mentor, accountId)).thenReturn(updatedAccount);

        // Act
        accountService.updateMentorProfile(mentor, accountId);

        // Assert
        verify(accountRepository, times(1)).save(updatedAccount);
    }
}
