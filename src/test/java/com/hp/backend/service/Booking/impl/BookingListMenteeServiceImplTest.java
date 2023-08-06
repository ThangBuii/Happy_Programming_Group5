package com.hp.backend.service.Booking.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Booking;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.booking.dto.DashboardMenteeDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.model.booking.mapper.DashboardMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.repository.TimeRepository;

class BookingListMenteeServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private DashboardMapper dashboardMapper;

    @InjectMocks
    private BookingListMenteeServiceImpl bookingListMenteeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteMenteeBookingByStatus() {
        // Arrange
        int id = 1;
        Booking booking = new Booking();
        booking.setStatus(0);

        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> bookingListMenteeService.deleteMenteeBookingByStatus(id));

        // Assert
        verify(bookingRepository, times(1)).findById(id);
        verify(bookingRepository, never()).delete(booking);
    }

    @Test
    void testDeleteMenteeBookingByStatus_ThrowsBadRequestException() {
        // Arrange
        int id = 1;
        Booking booking = new Booking();
        booking.setStatus(1);

        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> bookingListMenteeService.deleteMenteeBookingByStatus(id));
    }

    @Test
    void testFindBookingByID() throws CustomBadRequestException {
        // Arrange
        int id = 1;
        Booking booking = new Booking();

        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));

        // Act
        Booking result = bookingListMenteeService.findBookingByID(id);

        // Assert
        assertNotNull(result);
        assertEquals(booking, result);
    }

    @Test
    void testFindBookingByID_ThrowsBadRequestException() {
        // Arrange
        int id = 1;

        when(bookingRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> bookingListMenteeService.findBookingByID(id));
    }

    @Test
    void testFindMenteeBookingDetailByID() throws CustomBadRequestException {
        // Arrange
        int id = 1;
        Booking booking = new Booking();
        ViewBookingDTO expectedDto = new ViewBookingDTO();

        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));
        when(bookingMapper.toViewBookingDTO(booking)).thenReturn(expectedDto);

        // Act
        ViewBookingDTO result = bookingListMenteeService.findMenteeBookingDetailByID(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDto, result);
    }

    @Test
    void testFindMenteeBookingDetailByID_ThrowsBadRequestException() {
        // Arrange
        int id = 1;

        when(bookingRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomBadRequestException.class, () -> bookingListMenteeService.findMenteeBookingDetailByID(id));
    }

    @Test
    void testGetAllMenteeBooking() throws CustomNotFoundException {
        // Arrange
        int id = 1;
        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        bookings.add(booking);

        List<BookingListMenteeDTO> expectedDTOs = new ArrayList<>();
        BookingListMenteeDTO expectedDTO = new BookingListMenteeDTO();
        expectedDTOs.add(expectedDTO);

        when(bookingRepository.findAllByMentee_Id(id)).thenReturn(bookings);
        when(bookingMapper.findEmailAndUsernameByBookingID(booking.getBooking_id())).thenReturn(expectedDTO);

        // Act
        List<BookingListMenteeDTO> result = bookingListMenteeService.getAllMenteeBooking(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTOs, result);
    }

    @Test
    void testGetAllMenteeBooking_ThrowsNotFoundException() {
        // Arrange
        int id = 1;

        when(bookingRepository.findAllByMentee_Id(id)).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(CustomNotFoundException.class, () -> bookingListMenteeService.getAllMenteeBooking(id));
    }

    @Test
    void testGetDashboardMenteeBooking() throws CustomNotFoundException {
        // Arrange
        int id = 1;
        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        bookings.add(booking);

        List<DashboardMenteeDTO> expectedDTOs = new ArrayList<>();
        DashboardMenteeDTO expectedDTO = new DashboardMenteeDTO();
        expectedDTOs.add(expectedDTO);

        when(bookingRepository.findAllByMentee_Id(id)).thenReturn(bookings);
        when(dashboardMapper.findEmailAndUsernameByBookingID(booking.getBooking_id())).thenReturn(expectedDTO);

        // Act
        List<DashboardMenteeDTO> result = bookingListMenteeService.getDashboardMenteeBooking(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTOs, result);
    }

    @Test
    void testGetDashboardMenteeBooking_ThrowsNotFoundException() {
        // Arrange
        int id = 1;

        when(bookingRepository.findAllByMentee_Id(id)).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(CustomNotFoundException.class, () -> bookingListMenteeService.getDashboardMenteeBooking(id));
    }
}
