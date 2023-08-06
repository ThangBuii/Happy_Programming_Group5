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

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Times;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.booking.dto.BookingListMentorDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.repository.TimeRepository;
import com.hp.backend.utils.CommonUtils;

class BookingListMentorServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private CommonUtils commonUtils;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingListMentorServiceImpl bookingListMentorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindBookingByID() throws CustomBadRequestException {
        // Mock data
        int bookingId = 1;
        int menteeId = 2;
        int status = 0;

        Booking booking = new Booking();
        booking.setBooking_id(bookingId);
        booking.setMentee_id(menteeId);
        booking.setStatus(status);

        // Mock repository call
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        // Call the service method
        Booking result = bookingListMentorService.findBookingByID(bookingId);

        // Verify the result
        assertEquals(booking, result);
    }

    @Test
    void testFindBookingDetailByID() {

    }

    @Test
    void testGetAllMentorBooking() throws CustomNotFoundException {
        // Mock data
        int mentorId = 1;
        int menteeId = 0;
        String menteeUsername = "mentee1";
        String menteeEmail = "mentee1@example.com";
        java.sql.Date scheduleDate = java.sql.Date.valueOf("2023-07-17");
        String scheduleTime = "10:00:00-11:00:00";

        Booking booking = new Booking();
        booking.setBooking_id(1);
        booking.setMentee_id(menteeId);
        Times time = new Times();
        time.setStart_date(scheduleDate);
        time.setStart_time(java.sql.Time.valueOf("10:00:00"));
        time.setEnd_time(java.sql.Time.valueOf("11:00:00"));
        booking.setTime(time);

        Account mentee = new Account();
        mentee.setUsername(menteeUsername);
        mentee.setEmail(menteeEmail);

        List<Integer> menteeIds = new ArrayList<>();
        menteeIds.add(menteeId);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        // Mock repository calls
        when(bookingRepository.findMenteeIdsByMentorId(mentorId)).thenReturn(menteeIds);
        when(bookingRepository.findAllByMentee_Id(menteeId)).thenReturn(bookings);
        when(accountRepository.findById(menteeId)).thenReturn(Optional.of(mentee));

        // Call the service method
        List<BookingListMentorDTO> mentorBookings = bookingListMentorService.getAllMentorBooking(mentorId);

        // Verify the results
        assertEquals(1, mentorBookings.size());
        BookingListMentorDTO bookingDTO = mentorBookings.get(0);
        assertEquals(menteeId, bookingDTO.getMenteeID());
        assertEquals(menteeUsername, bookingDTO.getUsername());
        assertEquals(menteeEmail, bookingDTO.getEmail());
        assertEquals(scheduleDate, bookingDTO.getScheduleDate());
        assertEquals(scheduleTime, bookingDTO.getScheduleTime());
    }

    @Test
    void testGetDashboardMentorBooking() {

    }

    @Test
    void testSaveMentorBooking() {
        // Mock data
        int bookingId = 1;
        int menteeId = 2;
        int status = 1;

        Booking booking = new Booking();
        booking.setBooking_id(bookingId);
        booking.setMentee_id(menteeId);
        booking.setStatus(status);

        // Call the service method
        bookingListMentorService.saveMentorBooking(booking);

        // Verify the repository call
        verify(bookingRepository).save(booking);
    }
}
