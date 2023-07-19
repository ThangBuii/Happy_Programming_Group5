package com.hp.backend.model.booking.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Session;
import com.hp.backend.entity.Times;
import com.hp.backend.model.booking.dto.DashboardMenteeDTO;
import com.hp.backend.model.booking.dto.DashboardMentorDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.utils.CommonUtils;

class DashboardMapperTest {
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CommonUtils commonUtils;

    @InjectMocks
    private DashboardMapper dashboardMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindEmailAndUsernameByBookingID() {
        // Prepare mock objects
        Booking booking = new Booking();
        booking.setBooking_id(1);
        booking.setCreated_date(Date.valueOf("2023-07-19"));
        booking.setStatus(1);

        Times time = new Times();
        Session session = new Session();
        session.setMentor_id(101); // Replace with the desired mentor ID
        time.setSession(session);
        booking.setTime(time);

        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));

        Account mentorAccount = new Account();
        mentorAccount.setUsername("mentorUsername");
        mentorAccount.setEmail("mentor@example.com");
        when(accountRepository.findById(101)).thenReturn(Optional.of(mentorAccount));

        // Test the method
        DashboardMenteeDTO result = dashboardMapper.findEmailAndUsernameByBookingID(1);

        // Assert the result
        assertEquals(1, result.getBookingID());
        assertEquals("mentorUsername", result.getUsername());
        assertEquals("mentor@example.com", result.getEmail());
        assertEquals(Date.valueOf("2023-07-19"), result.getCreated_Date());
        assertEquals(1, result.getStatus());
    }

    @Test
    void testToBookingDTO() {
        // Prepare mock objects
        DashboardMenteeDTO inputDTO = DashboardMenteeDTO.builder()
                .avatar("avatar")
                .username("username")
                .email("email@example.com")
                .created_Date(Date.valueOf("2023-07-19"))
                .build();

        // Test the method
        DashboardMenteeDTO result = DashboardMapper.toBookingDTO(inputDTO);

        // Assert the result
        assertEquals("avatar", result.getAvatar());
        assertEquals("username", result.getUsername());
        assertEquals("email@example.com", result.getEmail());
        assertEquals(Date.valueOf("2023-07-19"), result.getCreated_Date());
    }

    @Test
    void testToBookingMentorDTO() {
        // Prepare mock objects
        DashboardMentorDTO inputDTO = DashboardMentorDTO.builder()
                .avatar("avatar")
                .username("username")
                .email("email@example.com")
                .created_Date(Date.valueOf("2023-07-19"))
                .build();

        // Test the method
        DashboardMentorDTO result = DashboardMapper.toBookingMentorDTO(inputDTO);

        // Assert the result
        assertEquals("avatar", result.getAvatar());
        assertEquals("username", result.getUsername());
        assertEquals("email@example.com", result.getEmail());
        assertEquals(Date.valueOf("2023-07-19"), result.getCreated_Date());
    }
}
