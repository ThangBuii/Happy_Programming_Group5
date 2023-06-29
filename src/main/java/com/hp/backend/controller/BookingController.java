package com.hp.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.model.booking.dto.BookingListAdminDTO;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.booking.dto.BookingRequestDTO;
import com.hp.backend.service.Booking.BookingListAdminService;
import com.hp.backend.service.Booking.BookingListMenteeService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {
    private final BookingListAdminService bookingListAdminService;
    private final JwtTokenUtil jwtTokenUtil;
    private final BookingListMenteeService bookingListMenteeService;

    @GetMapping("/admin/bookings")
    List<BookingListAdminDTO> getAdminBookingList() {
        return bookingListAdminService.getAllAdminBooking();
    }

    @GetMapping("/mentee/bookings")
    List<BookingListMenteeDTO> getBookingListMentee(HttpServletRequest request) throws CustomNotFoundException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return bookingListMenteeService.getAllBooking(tokenPayload.getAccount_id());
    }
}
