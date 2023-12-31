package com.hp.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.entity.Booking;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.model.booking.dto.AddBookingRequestDTO;
import com.hp.backend.model.booking.dto.BookingListAdminDTO;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.booking.dto.BookingListMentorDTO;
import com.hp.backend.model.booking.dto.BookingUpdateRequestDTO;
import com.hp.backend.model.booking.dto.DashboardMenteeDTO;
import com.hp.backend.model.booking.dto.DashboardMentorDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;
import com.hp.backend.service.Booking.AddBookingService;
import com.hp.backend.service.Booking.BookingListAdminService;
import com.hp.backend.service.Booking.BookingListMenteeService;
import com.hp.backend.service.Booking.BookingListMentorService;
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
    private final BookingListMentorService bookingListMentorService;
    private final AddBookingService addBookingService;

    @GetMapping("/admin/bookings")
    List<BookingListAdminDTO> getAdminBookingList() {
        return bookingListAdminService.getAllAdminBooking();
    }

    @GetMapping("/mentee/bookings")
    List<BookingListMenteeDTO> getBookingListMentee(HttpServletRequest request) throws CustomNotFoundException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return bookingListMenteeService.getAllMenteeBooking(tokenPayload.getAccount_id());
    }

    @GetMapping("/mentee/booking/{id}")
    ViewBookingDTO getBookingMenteeById(@PathVariable int id) throws CustomBadRequestException {
        return bookingListMenteeService.findMenteeBookingDetailByID(id);
    }

    @DeleteMapping("/mentee/booking/{id}")
    void deleteMenteeBooking(@PathVariable int id) throws CustomBadRequestException {
        bookingListMenteeService.deleteMenteeBookingByStatus(id);
    }

    @GetMapping("/mentee/dashboard")
    List<DashboardMenteeDTO> getDashboardMenteeBookingList(HttpServletRequest request) throws CustomNotFoundException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return bookingListMenteeService.getDashboardMenteeBooking(tokenPayload.getAccount_id());
    }

    @GetMapping("/mentor/bookings")
    List<BookingListMentorDTO> getMentorBookingList(HttpServletRequest request) throws CustomNotFoundException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return bookingListMentorService.getAllMentorBooking(tokenPayload.getAccount_id());
    }

    @GetMapping("/booking/{id}")
    ViewBookingDTO getBookingById(@PathVariable int id) throws CustomBadRequestException {
        return bookingListMentorService.findBookingDetailByID(id);
    }

    @PutMapping("/mentor/booking/{id}")
    void updateMentorBooking(@PathVariable int id, @RequestBody BookingUpdateRequestDTO booking)
            throws CustomBadRequestException {
        Booking b = bookingListMentorService.findBookingByID(id);
        b.setStatus(booking.getStatus());
        bookingListMentorService.saveMentorBooking(b);
    }

    @GetMapping("mentor/dashboard")
    List<DashboardMentorDTO> getDashboardMentorBookingList(HttpServletRequest request) throws CustomNotFoundException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return bookingListMentorService.getDashboardMentorBooking(tokenPayload.getAccount_id());
    }

    @PostMapping("mentee/booking")
    void addBookingForMentee(@RequestBody AddBookingRequestDTO addBooking, HttpServletRequest request) {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        addBookingService.addBookingForMentee(addBooking, tokenPayload.getAccount_id());
    }

}
