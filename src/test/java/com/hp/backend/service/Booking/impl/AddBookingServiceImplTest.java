package com.hp.backend.service.Booking.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.service.EmailService;

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
        // Create a sample input data
        int accountId = 1;
        AddBookingRequestDTO addBookingRequest = AddBookingRequestDTO.builder()
                .time_id(123) // Replace with an appropriate time_id value
                .build();

        // Create mock entities and responses
        Account mentee = new Account();
        mentee.setEmail("mentee@example.com");
        mentee.setUsername("MenteeUser");
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(mentee));

        Time starTime = Time.valueOf("09:00:00");
        Time endTime = Time.valueOf("11:00:00");
        Times time = new Times();
        time.setStart_time(starTime);
        time.setEnd_time(endTime);
        Session session = new Session();
        session.setName("Sample Session");
        session.setMentor_id(2); // Replace with an appropriate mentor_id value
        time.setSession(session);
        when(bookingMapper.toBooking(addBookingRequest, accountId)).thenReturn(
                Booking.builder()
                        .mentee_id(accountId)
                        .time(time)
                        .created_date(Date.valueOf(LocalDate.now())) // Replace with the current date value
                        .status(0)
                        .build());

        Account mentor = new Account();
        mentor.setEmail("mentor@example.com");
        mentor.setUsername("MentorUser");
        when(accountRepository.findById(session.getMentor_id())).thenReturn(Optional.of(mentor));

        // Call the service method
        addBookingService.addBookingForMentee(addBookingRequest, accountId);

        // Verify that the repository's save method was called once
        verify(bookingRepository, times(1)).save(any());

        // Verify that the email service's sendEmail method was called twice
        verify(emailService, times(1))
                .sendEmail(eq(mentee.getEmail()), eq("Booking successfully"), anyString());
        verify(emailService, times(1))
                .sendEmail(eq(mentor.getEmail()), eq("New booking"), anyString());
    }
}
