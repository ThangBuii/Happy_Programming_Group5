package com.hp.backend.model.revenue.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Receipt;
import com.hp.backend.entity.System_Revenue;
import com.hp.backend.model.revenue.dto.RevenueDTO;
import com.hp.backend.repository.ReceiptRepository;

class RevenueMapperTest {
    @Mock
    private ReceiptRepository receiptRepository;

    @InjectMocks
    private RevenueMapper revenueMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToRevenueDTO() {
        // Prepare mock data
        int revenueId = 1;
        int receiptId = 101;
        double amount = 1000;
        Date createdDate = Date.valueOf("2023-07-19");

        System_Revenue systemRevenue = new System_Revenue(revenueId, receiptId, amount);

        int bookingId = 201;
        Booking booking = new Booking();
        booking.setBooking_id(bookingId);
        booking.setCreated_date(createdDate);

        Receipt receipt = new Receipt();
        receipt.setReceipt_id(receiptId);
        receipt.setBooking(booking);

        // Mock the repository method
        when(receiptRepository.findById(receiptId)).thenReturn(Optional.of(receipt));

        // Call the mapper method
        RevenueDTO result = revenueMapper.toRevenueDTO(systemRevenue);

        // Check the result
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(amount);
        assertEquals(revenueId, result.getRevenue_id());
        assertEquals(receiptId, result.getReceipt_id());
        assertEquals(formattedValue, result.getAmount());
        assertEquals(createdDate, result.getCreated_Date());
    }
}
