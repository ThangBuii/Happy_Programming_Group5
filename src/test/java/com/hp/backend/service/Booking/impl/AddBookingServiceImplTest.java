package com.hp.backend.service.Booking.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Time;
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
import com.hp.backend.model.booking.dto.AddBookingRequestDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.service.EmailService;
import com.hp.backend.service.Booking.AddBookingService;

class AddBookingServiceImplTest {
    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AddBookingServiceImpl addBookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBookingForMentee() {
        // Mock data for the test
        int account_id = 1;
        AddBookingRequestDTO addBookingRequestDTO = AddBookingRequestDTO.builder().time_id(123).build();

        // Mocking Account object
        Account mentee = Account.builder().account_id(account_id).email("mentee@example.com").username("mentee").build();

        Account mentor = Account.builder().account_id(2).email("mentor@example.com").username("mentor").build();
        Session session = Session.builder().session_id(456).name("Session Name").mentor_id(mentor.getAccount_id()).build();

        // Use java.sql.Time to represent time values
        Time startTime = Time.valueOf("09:00:00");
        Time endTime = Time.valueOf("10:00:00");

        Times time = Times.builder().time_id(123).session(session).start_time(startTime).end_time(endTime).build();
        Booking booking = Booking.builder().mentee_id(mentee.getAccount_id()).time(time).build();

        // Configure mock behavior for accountRepository.findById
        when(accountRepository.findById(account_id)).thenReturn(Optional.of(mentee)); // Use Optional.of() to provide a
                                                                                      // value
        // Configure mock behavior for bookingMapper
        when(bookingMapper.toBooking(any(AddBookingRequestDTO.class), eq(account_id))).thenReturn(booking);
        // Configure mock behavior for bookingRepository.save
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // Call the method being tested directly
        addBookingService.addBookingForMentee(addBookingRequestDTO, account_id);

        // Verify that the required methods were called with the correct parameters
        verify(bookingMapper).toBooking(addBookingRequestDTO, account_id);
        verify(bookingRepository).save(booking);

        // Verify that the email service was called to send emails to mentee and mentor
        verify(emailService).sendEmail(mentee.getEmail(), "Booking successfully",
                "Your booking of session " + session.getName()
                        + " has been successfully. Please wait till the mentor accept your request");
        verify(emailService).sendEmail(mentor.getEmail(), "New booking",
                "You have a new booking from mentee " + mentee.getUsername() + " on session " + session.getName()
                        + ". Please check your dashboard to view booking detail");
    }
}
