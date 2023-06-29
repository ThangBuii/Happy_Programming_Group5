package com.hp.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.model.booking.dto.BookingListAdminDTO;
import com.hp.backend.service.Booking.BookingListAdminService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {
    private final BookingListAdminService bookingListAdminService;

    @GetMapping("/admin/bookings")
    List<BookingListAdminDTO> getAdminBookingList() {
        return bookingListAdminService.getAllAdminBooking();
    }
}
