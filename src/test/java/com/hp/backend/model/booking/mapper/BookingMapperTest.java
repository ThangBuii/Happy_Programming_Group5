package com.hp.backend.model.booking.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
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
import com.hp.backend.model.booking.dto.BookingListAdminDTO;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.TimeRepository;
import com.hp.backend.utils.CommonUtils;

public class BookingMapperTest {
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private CommonUtils commonUtils;

    @InjectMocks
    private BookingMapper bookingMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindEmailAndUsernameByAdminBookingID() {
        // Mock data
        int bookingId = 1;
        Booking booking = new Booking();
        booking.setBooking_id(bookingId);
        Times time = new Times();
        Session session = new Session();
        session.setMentor_id(2);
        time.setSession(session);
        booking.setTime(time);
        Account accountMentor = new Account();
        accountMentor.setAvatar(new byte[] { /* mentor avatar image data as byte[] */ });
        Account accountMentee = new Account();
        accountMentee.setAvatar(new byte[] { /* mentee avatar image data as byte[] */ });
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(accountRepository.findById(session.getMentor_id())).thenReturn(Optional.of(accountMentor));
        when(accountRepository.findById(booking.getMentee_id())).thenReturn(Optional.of(accountMentee));
        when(commonUtils.imageToFrontEnd(accountMentor.getAvatar())).thenReturn("front-end-mentor-avatar.jpg");
        when(commonUtils.imageToFrontEnd(accountMentee.getAvatar())).thenReturn("front-end-mentee-avatar.jpg");

        // Call the method to be tested
        BookingListAdminDTO result = bookingMapper.findEmailAndUsernameByAdminBookingID(bookingId);

        // Assertions
        assertEquals(bookingId, result.getBookingID());
        assertEquals("front-end-mentor-avatar.jpg", result.getAvatarMentor());
        assertEquals("front-end-mentee-avatar.jpg", result.getAvatarMentee());
        // Add more assertions as needed
    }

    @Test
    void testFindEmailAndUsernameByBookingID() {
        // Prepare mock objects
        Booking booking = new Booking();
        booking.setBooking_id(1);
        Times time = new Times();
        Session session = new Session();
        session.setMentor_id(101); // Replace with the desired mentor ID
        time.setSession(session);
        booking.setTime(time);
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
        when(timeRepository.findById(anyInt())).thenReturn(Optional.of(time));

        Account mentorAccount = new Account();
        mentorAccount.setUsername("mentorUsername");
        when(accountRepository.findById(101)).thenReturn(Optional.of(mentorAccount));

        // Test the method
        BookingListMenteeDTO result = bookingMapper.findEmailAndUsernameByBookingID(1);

        // Assert the result
        assertEquals("mentorUsername", result.getUsername());
    }

    @Test
    void testToBooking() {
        // Prepare mock objects
        AddBookingRequestDTO addBookingRequestDTO = new AddBookingRequestDTO();
        addBookingRequestDTO.setTime_id(1);
        when(timeRepository.findById(1)).thenReturn(Optional.of(new Times()));

        // Test the method
        Booking result = bookingMapper.toBooking(addBookingRequestDTO, 101);

        // Assert the result
        assertEquals(101, result.getMentee_id());
        // Add more assertions based on your requirements
    }

    @Test
    void testToBookingDTO() {
        // Prepare mock objects
        Booking booking = new Booking();
        booking.setBooking_id(1);
        booking.setCreated_date(Date.valueOf("2023-07-19"));
        Times time = new Times();
        time.setStart_time(Time.valueOf("09:00:00"));
        time.setEnd_time(Time.valueOf("11:00:00"));
        booking.setTime(time);

        Account account = new Account();
        account.setUsername("username");
        account.setEmail("user@example.com");

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(account));

        // Test the method
        BookingListMenteeDTO result = bookingMapper.toBookingDTO(booking);

        // Assert the result
        assertEquals(1, result.getBookingID());
        assertEquals("username", result.getUsername());
        assertEquals("user@example.com", result.getEmail());
        assertEquals(Date.valueOf("2023-07-19"), result.getScheduleDate());
        assertEquals("09:00:00-11:00:00", result.getScheduleTime());
    }

    @Test
    void testToViewBookingDTO() {
        // Prepare mock objects
        Booking booking = new Booking();
        booking.setBooking_id(1);
        booking.setStatus(1);

        Times time = new Times();
        Session session = new Session();
        session.setMentor_id(101); // Replace with the desired mentor ID
        time.setSession(session);
        booking.setTime(time);
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
        when(timeRepository.findById(anyInt())).thenReturn(Optional.of(time));

        Account mentorAccount = new Account();
        mentorAccount.setUsername("mentorUsername");
        mentorAccount.setEmail("mentor@example.com");
        when(accountRepository.findById(101)).thenReturn(Optional.of(mentorAccount));

        Account menteeAccount = new Account();
        menteeAccount.setUsername("menteeUsername");
        menteeAccount.setEmail("mentee@example.com");
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(menteeAccount));

        // Test the method
        ViewBookingDTO result = bookingMapper.toViewBookingDTO(booking);

        // Assert the result
        assertEquals("mentorUsername", result.getMentorUsername());
        assertEquals("mentor@example.com", result.getMentorEmail());
        assertEquals("menteeUsername", result.getMenteeUsername());
        assertEquals("mentee@example.com", result.getMenteeEmail());
        assertEquals(1, result.getStatus());
    }
}
