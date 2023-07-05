package com.hp.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.model.booking.dto.BookingListAdminDTO;
import com.hp.backend.model.revenue.dto.RevenueDTO;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
@RequiredArgsConstructor
public class RevenueController {
    @GetMapping("/revenue")
    List<RevenueDTO> getAdminBookingList() {
        return revenueListService.getRevenueList();
    }
}
