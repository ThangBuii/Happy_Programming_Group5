package com.hp.backend.service.Booking.impl;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Report;
import com.hp.backend.model.booking.dto.AddBookingRequestDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.service.Booking.AddBookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddBookingServiceImpl implements AddBookingService {
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    
    @Override
    public void addBookingForMentee(AddBookingRequestDTO addBooking, int account_id) {
        Booking booking = bookingMapper.toBooking(addBooking, account_id);
        bookingRepository.save(booking);
    }

}
