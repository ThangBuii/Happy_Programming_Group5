package com.hp.backend.model.revenue.mapper;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Receipt;
import com.hp.backend.entity.System_Revenue;
import com.hp.backend.model.revenue.dto.RevenueDTO;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.ReceiptRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RevenueMapper {
    private final BookingRepository bookingRepository;
    private final ReceiptRepository receiptRepository;

    public RevenueDTO toRevenueDTO(System_Revenue revenue) {
        Receipt receipt = receiptRepository.findById(revenue.getReceipt_id()).get();
        Booking booking = receipt.getBooking();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(revenue.getAmount());
        return RevenueDTO.builder().revenue_id(revenue.getRevenue_id()).receipt_id(receipt.getReceipt_id())
                .amount(formattedValue)
                .created_Date(booking.getCreated_date())
                .build();

    }

}
