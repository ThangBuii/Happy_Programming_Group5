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

    
}
