package com.hp.backend.model.account.mapper;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Skills;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTOResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTOResponse;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOCreate;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginResponse;
import com.hp.backend.model.account.dto.MentorSiteDTO.MentorDTODetailUpdateRequest;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.SkillsRepository;
import com.hp.backend.utils.CommonUtils;

class AccountMapperTest {
    @Mock
    private SkillsRepository skillRepository;
    
    @Mock
    private CommonUtils commonUtils;
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private AccountRepository accountRepository;
    
    @InjectMocks
    private AccountMapper mapper;
    
     @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testToAccountDTOResponse() {
        Account account = Account.builder().role(1).build();

        // Act
        AccountDTOLoginResponse response = AccountMapper.toAccountDTOResponse(account);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.getRole());
    }

    @Test
    void testToFavoriteListResponseDTO() {

    }

    @Test
    void testToFindMentorResponse() {

    }

    @Test
    void testToMenteeDTODetailResponse() {
        Account account = new Account();
        account.setAccount_id(1);
        account.setAvatar(new byte[]{1, 2, 3}); // Example byte array
        account.setEmail("testuser@example.com");
        account.setUsername("testuser");
        account.setCreated_date(Date.valueOf("2023-07-19"));
        // Set up other account fields as needed
        String expectedAvatar = "imageString"; // Example converted string
        when(commonUtils.imageToFrontEnd(account.getAvatar())).thenReturn(expectedAvatar);
        // Act
        MenteeDTODetailResponse result = mapper.toMenteeDTODetailResponse(account);

        
        // Assert
        assertNotNull(result);
        assertEquals(expectedAvatar, result.getAvatar()); // Example byte array
        assertEquals("testuser@example.com", result.getEmail());
        assertEquals("testuser", result.getUsername());
        assertEquals(account.getCreated_date(), result.getCreated_date());
        // Assert other fields as needed

        // Verify the interaction with the commonUtils
        verify(commonUtils, times(1)).imageToFrontEnd(account.getAvatar());
    }

    @Test
    void testToMenteeDTOResponse() {
        Account account = new Account();
        account.setAccount_id(1);
        account.setAvatar(new byte[]{1, 2, 3}); // Example byte array
        account.setUsername("testuser");
        account.setCreated_date(Date.valueOf("2023-07-19"));

        int expectedNumberOfBookings = 5;
        when(bookingRepository.countBookingsByMenteeId(account.getAccount_id()))
                .thenReturn(expectedNumberOfBookings);

        String expectedAvatar = "imageString"; // Example converted string
        when(commonUtils.imageToFrontEnd(account.getAvatar())).thenReturn(expectedAvatar);

        // Act
        MenteeDTOResponse result = mapper.toMenteeDTOResponse(account);

        // Assert
        assertNotNull(result);
        assertEquals(expectedAvatar, result.getAvatar()); // Example converted string
        assertEquals("testuser", result.getUsername());
        assertEquals(Date.valueOf("2023-07-19"), result.getCreated_date());
        assertEquals(1, result.getAccount_id());
        assertEquals(expectedNumberOfBookings, result.getNumberOfBooking());

        // Verify the interaction with the booking repository
        verify(bookingRepository, times(1)).countBookingsByMenteeId(account.getAccount_id());
        // Verify the interaction with the commonUtils
        verify(commonUtils, times(1)).imageToFrontEnd(account.getAvatar());
    }

    @Test
    void testToMentorDTODetailResponse() {
        Account account = new Account();
        account.setAccount_id(1);
        account.setAvatar(new byte[]{1, 2, 3}); // Example byte array
        account.setEmail("testuser@example.com");
        account.setUsername("testuser");
        account.setCreated_date(Date.valueOf("2023-07-19"));
        // Set up other account fields as needed

        List<Skills> skills = new ArrayList<>();
        // Set up skills list as needed

        when(skillRepository.findSkillsByMentorId(account.getAccount_id()))
                .thenReturn(skills);

                 String expectedAvatar = "imageString"; // Example converted string
        when(commonUtils.imageToFrontEnd(account.getAvatar())).thenReturn(expectedAvatar);
        // Act
        MentorDTODetailResponse result = mapper.toMentorDTODetailResponse(account);

        // Assert
        assertNotNull(result);
        assertEquals(expectedAvatar, result.getAvatar()); // Example byte array
        assertEquals("testuser@example.com", result.getEmail());
        assertEquals("testuser", result.getUsername());
        assertEquals(account.getCreated_date(), result.getCreated_date());
        // Assert other fields as needed

        assertEquals(skills, result.getSkills());

        // Verify the interaction with the skill repository
        verify(skillRepository, times(1)).findSkillsByMentorId(account.getAccount_id());
    }

    @Test
    void testToMentorDTOResponse() {
        Account account = new Account();
        account.setAccount_id(1);
        account.setAvatar(new byte[]{1, 2, 3}); // Example byte array
        account.setUsername("testuser");
        account.setCreated_date(Date.valueOf("2023-07-19"));

        int expectedNumberOfBookings = 5;
        double expectedEarned = 100.50;

        when(bookingRepository.getSessionCountByMentorId(account.getAccount_id()))
                .thenReturn(expectedNumberOfBookings);
        when(bookingRepository.sumPriceByTimeSessionMentorId(account.getAccount_id()))
                .thenReturn(expectedEarned);

                String expectedAvatar = "imageString"; // Example converted string
        when(commonUtils.imageToFrontEnd(account.getAvatar())).thenReturn(expectedAvatar);

        // Act
        MentorDTOResponse result = mapper.toMentorDTOResponse(account);

        // Assert
        assertNotNull(result);
        assertEquals(expectedAvatar, result.getAvatar()); // Example byte array
        assertEquals("testuser", result.getUsername());
        assertEquals(Date.valueOf("2023-07-19"), result.getCreated_date());
        assertEquals(1, result.getAccount_id());
        assertEquals(expectedNumberOfBookings, result.getNumberOfBooking());
        assertEquals("100.5", result.getEarned());

        // Verify the interaction with the booking repository
        verify(bookingRepository, times(1)).getSessionCountByMentorId(account.getAccount_id());
        verify(bookingRepository, times(1)).sumPriceByTimeSessionMentorId(account.getAccount_id());
    }


    @Test
    void testToUpdatedMentorAccount() {

    }

    @Test
    void testToUser() {
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password123")
                .role(1)
                .build();

        // Act
        Account account = AccountMapper.toUser(accountDTOCreate);

        // Assert
        assertNotNull(account);
        assertEquals("testuser", account.getUsername());
        assertEquals("testuser@example.com", account.getEmail());
        assertEquals("password123", account.getPassword());
        assertNotNull(account.getCreated_date());
        assertEquals(1, account.getRole());
    }

}
