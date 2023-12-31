package com.hp.backend.model.receipt.mapper;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Receipt;
import com.hp.backend.entity.Session;
import com.hp.backend.entity.Times;
import com.hp.backend.model.receipt.dto.InvoiceAdminDTO;
import com.hp.backend.model.receipt.dto.InvoiceDTO;
import com.hp.backend.model.receipt.dto.ViewInvoiceDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {
    private final AccountRepository accountRepository;
    private final CommonUtils commonUtils;

    public InvoiceDTO toMenteeInvoiceDTO(Receipt receipt) {
        Booking booking = receipt.getBooking();
        Times time = booking.getTime();
        Session session = time.getSession();
        Account mentor = accountRepository.findById(session.getMentor_id()).get();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(session.getPrice());
        return InvoiceDTO.builder().receipt_id(receipt.getReceipt_id()).username(mentor.getUsername())
                .email(mentor.getEmail()).avatar(commonUtils.imageToFrontEnd(mentor.getAvatar())).amount(formattedValue)
                .created_Date(receipt.getCreated_date())
                .build();

    }

    public InvoiceDTO toMentorInvoiceDTO(Receipt receipt) {
        Booking booking = receipt.getBooking();
        Times time = booking.getTime();
        Session session = time.getSession();
        Account mentor = accountRepository.findById(booking.getMentee_id()).get();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(session.getPrice());
        return InvoiceDTO.builder().receipt_id(receipt.getReceipt_id()).username(mentor.getUsername())
                .email(mentor.getEmail()).avatar(commonUtils.imageToFrontEnd(mentor.getAvatar())).amount(formattedValue)
                .created_Date(receipt.getCreated_date())
                .build();

    }

    public InvoiceAdminDTO toAdminInvoiceDTO(Receipt receipt) {
        Booking booking = receipt.getBooking();
        Times time = booking.getTime();
        Session session = time.getSession();
        Account mentor = accountRepository.findById(session.getMentor_id()).get();
        Account mentee = accountRepository.findById(booking.getMentee_id()).get();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(session.getPrice());
        return InvoiceAdminDTO.builder().receipt_id(receipt.getReceipt_id()).menteeUsername(mentee.getUsername())
                .menteeEmail(mentee.getEmail()).menteeAvatar(commonUtils.imageToFrontEnd(mentee.getAvatar()))
                .mentorUsername(mentor.getUsername()).booking_id(booking.getBooking_id()).payment_method(receipt.getPayment_method())
                .mentorEmail(mentor.getEmail()).mentorAvatar(commonUtils.imageToFrontEnd(mentor.getAvatar()))
                .amount(formattedValue).created_Date(receipt.getCreated_date())
                .build();

    }

    public ViewInvoiceDTO toViewInvoiceDTO(Receipt receipt) {
        Booking booking = receipt.getBooking();
        Account mentee = accountRepository.findById(booking.getMentee_id()).get();
        Times time = booking.getTime();
        Session session = time.getSession();
        Account mentor = accountRepository.findById(session.getMentor_id()).get();
        return ViewInvoiceDTO.builder().booking_id(receipt.getBooking().getBooking_id())
                .payment_method(receipt.getPayment_method()).receipt_id(receipt.getReceipt_id())
                .created_Date(receipt.getCreated_date()).mentee_name(mentee.getUsername())
                .mentee_email(mentee.getEmail()).mentee_avatar(commonUtils.imageToFrontEnd(mentee.getAvatar()))
                .mentor_name(mentor.getUsername()).mentor_email(mentor.getEmail()).session_name(session.getName())
                .mentor_avatar(commonUtils.imageToFrontEnd(mentor.getAvatar())).price(session.getPrice()).build();
    }

}
