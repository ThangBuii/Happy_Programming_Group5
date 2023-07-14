package com.hp.backend.service.Booking.impl;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Booking;
import com.hp.backend.model.booking.dto.AddBookingRequestDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.BookingRepository;

public class AddBookingServiceImplTest {
    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private AddBookingServiceImpl addBookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddBookingForMentee() {
 // Arrange
        int accountId = 1;
        AddBookingRequestDTO addBookingRequestDTO = new AddBookingRequestDTO();
        Booking booking = new Booking();
        when(bookingMapper.toBooking(addBookingRequestDTO, accountId)).thenReturn(booking);

        // Act
        addBookingService.addBookingForMentee(addBookingRequestDTO, accountId);

        // Assert
        verify(bookingRepository, times(1)).save(booking);
    }
}
