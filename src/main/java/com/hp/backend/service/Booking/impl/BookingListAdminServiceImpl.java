package com.hp.backend.service.Booking.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Booking;
import com.hp.backend.model.booking.dto.BookingListAdminDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.service.Booking.BookingListAdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingListAdminServiceImpl implements BookingListAdminService {

   private final BookingRepository bookingRepository;

   private final BookingMapper bookingMapper;

   @Override
   public List<BookingListAdminDTO> getAllAdminBooking() {
      List<BookingListAdminDTO> bookingAdminDTOs = new ArrayList<>();
      List<Booking> bookings = bookingRepository.findAll();
      for (Booking booking : bookings) {

         bookingAdminDTOs.add(bookingMapper.findEmailAndUsernameByAdminBookingID(booking.getBooking_id()));

      }

      return bookingAdminDTOs;
   }

}
