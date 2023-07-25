package com.hp.backend.service.Booking.impl;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Receipt;
import com.hp.backend.entity.Session;
import com.hp.backend.entity.System_Revenue;
import com.hp.backend.entity.Times;
import com.hp.backend.model.booking.dto.AddBookingRequestDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.ReceiptRepository;
import com.hp.backend.repository.SystemRevenueRepository;
import com.hp.backend.service.EmailService;
import com.hp.backend.service.Booking.AddBookingService;
import com.hp.backend.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddBookingServiceImpl implements AddBookingService {
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final EmailService emailService;
    private final AccountRepository accountRepository;
    private final CommonUtils commonUtils;
    private final ReceiptRepository receiptRepository;
    private final SystemRevenueRepository systemRevenueRepository;
    
    @Override
    public void addBookingForMentee(AddBookingRequestDTO addBooking, int account_id) {
        Account mentee = accountRepository.findById(account_id).get();
        Booking booking = bookingMapper.toBooking(addBooking, account_id);
        Times time = booking.getTime();
        Session session = time.getSession();
        Account mentor = accountRepository.findById(session.getMentor_id()).get();
        bookingRepository.save(booking);

        int booking_id = bookingRepository.getLatestID();
        Booking booking2 = bookingRepository.findById(booking_id).get();
        Date currentDate = commonUtils.getCurrentDate();
        receiptRepository.save(Receipt.builder().created_date(currentDate).booking(booking2).payment_method(addBooking.getPayment_method()).build());

        int receipt_id = receiptRepository.getLastestID();
        double revenue = (double) (session.getPrice() / 10);
        systemRevenueRepository.save(System_Revenue.builder().amount(revenue).receipt_id(receipt_id).build());
        emailService.sendEmail(mentee.getEmail(), "Booking successfully", "Your booking of session " + session.getName() + " has been successfully. Please wait till the mentor accept your request" );
        emailService.sendEmail(mentor.getEmail(), "New booking", "You have a new booking from mentee " + mentee.getUsername() + " on session " + session.getName() +". Please check your dashboard to view booking detail");

    }

}
