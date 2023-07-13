package com.hp.backend.service.Booking.impl;

import org.springframework.stereotype.Service;

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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddBookingServiceImpl implements AddBookingService {
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final EmailService emailService;
    private final AccountRepository accountRepository;
    
    @Override
    public void addBookingForMentee(AddBookingRequestDTO addBooking, int account_id) {
        Account mentee = accountRepository.findById(account_id).get();
        Booking booking = bookingMapper.toBooking(addBooking, account_id);
        Times time = booking.getTime();
        Session session = time.getSession();
        Account mentor = accountRepository.findById(session.getMentor_id()).get();
        bookingRepository.save(booking);
        emailService.sendEmail(mentee.getEmail(), "Booking successfully", "Your booking of session " + session.getName() + " has been successfully. Please wait till the mentor accept your request" );
        emailService.sendEmail(mentor.getEmail(), "New booking", "You have a new booking from mentee " + mentee.getUsername() + " on session " + session.getName() +". Please check your dashboard to view booking detail");

    }

}
