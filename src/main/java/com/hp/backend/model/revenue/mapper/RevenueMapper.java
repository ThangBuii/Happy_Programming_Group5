package com.hp.backend.model.revenue.mapper;

import java.text.DecimalFormat;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Receipt;
import com.hp.backend.entity.Session;
import com.hp.backend.entity.Times;
import com.hp.backend.model.receipt.dto.InvoiceDTO;
import com.hp.backend.model.revenue.dto.RevenueDTO;

public class RevenueMapper {
    public RevenueDTO toMenteeInvoiceDTO(Receipt receipt) {
        Booking booking = receipt.getBooking();
        Times time = booking.getTime();
        Session session = time.getSession();
        Account mentor = accountRepository.findById(session.getMentor_id()).get();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(session.getPrice());
        return InvoiceDTO.builder().receipt_id(receipt.getReceipt_id()).username(mentor.getUsername())
                .email(mentor.getEmail()).avatar(mentor.getAvatar()).amount(formattedValue)
                .created_Date(receipt.getCreated_date())
                .build();

    }
}
