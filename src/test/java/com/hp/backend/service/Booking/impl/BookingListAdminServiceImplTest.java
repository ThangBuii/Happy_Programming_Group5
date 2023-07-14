package com.hp.backend.service.Booking.impl;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Booking;
import com.hp.backend.model.booking.dto.BookingListAdminDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.repository.TimeRepository;

public class BookingListAdminServiceImplTest {
    @Mock
    private TimeRepository timeRepository;

@Mock
    private BookingRepository bookingRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingListAdminServiceImpl bookingListAdminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllAdminBooking() {
 // Arrange
        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        bookings.add(booking);

        BookingListAdminDTO bookingDTO = new BookingListAdminDTO();
        when(bookingRepository.findAll()).thenReturn(bookings);
        when(bookingMapper.findEmailAndUsernameByAdminBookingID(booking.getBooking_id())).thenReturn(bookingDTO);

        // Act
        List<BookingListAdminDTO> result = bookingListAdminService.getAllAdminBooking();

        // Assert
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(bookingDTO, result.get(0));
        verify(bookingRepository, times(1)).findAll();
        verify(bookingMapper, times(1)).findEmailAndUsernameByAdminBookingID(booking.getBooking_id());
    }
}
