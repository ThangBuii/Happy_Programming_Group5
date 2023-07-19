package com.hp.backend.model.revenue.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class RevenueDTOTest {
    @Test
    void testBuilder() {
        RevenueDTO dto = RevenueDTO.builder()
                .revenue_id(1)
                .receipt_id(1001)
                .created_Date(Date.valueOf("2023-07-18"))
                .amount("100.50")
                .build();

        assertEquals(1, dto.getRevenue_id());
        assertEquals(1001, dto.getReceipt_id());
        assertEquals(Date.valueOf("2023-07-18"), dto.getCreated_Date());
        assertEquals("100.50", dto.getAmount());
    }

    @Test
    void testCanEqual() {
        RevenueDTO dto1 = RevenueDTO.builder().revenue_id(1).build();
        RevenueDTO dto2 = RevenueDTO.builder().revenue_id(1).build();
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    void testEquals() {
        RevenueDTO dto1 = RevenueDTO.builder().revenue_id(1).build();
        RevenueDTO dto2 = RevenueDTO.builder().revenue_id(1).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testGetAmount() {
        RevenueDTO dto = new RevenueDTO();
        String amount = "100.50";
        dto.setAmount(amount);
        assertEquals(amount, dto.getAmount());
    }

    @Test
    void testGetCreated_Date() {
        RevenueDTO dto = new RevenueDTO();
        Date createdDate = Date.valueOf("2023-07-18");
        dto.setCreated_Date(createdDate);
        assertEquals(createdDate, dto.getCreated_Date());
    }

    @Test
    void testGetReceipt_id() {
        RevenueDTO dto = new RevenueDTO();
        int receiptId = 1001;
        dto.setReceipt_id(receiptId);
        assertEquals(receiptId, dto.getReceipt_id());
    }

    @Test
    void testGetRevenue_id() {
        RevenueDTO dto = new RevenueDTO();
        int revenueId = 1;
        dto.setRevenue_id(revenueId);
        assertEquals(revenueId, dto.getRevenue_id());
    }

    @Test
    void testHashCode() {
        RevenueDTO dto1 = RevenueDTO.builder().revenue_id(1).build();
        RevenueDTO dto2 = RevenueDTO.builder().revenue_id(1).build();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetAmount() {
        RevenueDTO dto = new RevenueDTO();
        String amount = "100.50";
        dto.setAmount(amount);
        assertEquals(amount, dto.getAmount());
    }

    @Test
    void testSetCreated_Date() {
        RevenueDTO dto = new RevenueDTO();
        Date createdDate = Date.valueOf("2023-07-18");
        dto.setCreated_Date(createdDate);
        assertEquals(createdDate, dto.getCreated_Date());
    }

    @Test
    void testSetReceipt_id() {
        RevenueDTO dto = new RevenueDTO();
        int receiptId = 1001;
        dto.setReceipt_id(receiptId);
        assertEquals(receiptId, dto.getReceipt_id());
    }

    @Test
    void testSetRevenue_id() {
        RevenueDTO dto = new RevenueDTO();
        int revenueId = 1;
        dto.setRevenue_id(revenueId);
        assertEquals(revenueId, dto.getRevenue_id());
    }

    @Test
    void testToString() {
        RevenueDTO dto = RevenueDTO.builder()
                .revenue_id(1)
                .receipt_id(1001)
                .created_Date(Date.valueOf("2023-07-18"))
                .amount("100.50")
                .build();

        String expectedToString = "RevenueDTO(revenue_id=1, receipt_id=1001, created_Date=2023-07-18, amount=100.50)";
        assertEquals(expectedToString, dto.toString());
    }
}
