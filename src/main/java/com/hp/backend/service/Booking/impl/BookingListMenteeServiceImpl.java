package com.hp.backend.service.Booking.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Booking;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.repository.TimeRepository;
import com.hp.backend.service.Booking.BookingListMenteeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingListMenteeServiceImpl implements BookingListMenteeService {

   private final BookingRepository bookingRepository;
   private final TimeRepository timeRepository;
   private final AccountRepository accountRepository;
   private final SessionRepository sessionRepository;
   private final BookingMapper bookingMapper;

   @Override
   public List<BookingListMenteeDTO> getAllBooking(int id) throws CustomNotFoundException {
      List<BookingListMenteeDTO> bookingDTOs = new ArrayList<>();
      List<Booking> bookings = bookingRepository.findAllByMentee_Id(id);
      if (bookings.isEmpty()) {
         CustomError error = new CustomError("No bookings found",
               "There are no bookings for the mentee with ID = " + id,
               "");
         throw new CustomNotFoundException(error);
      }
      for (Booking booking : bookings) {
         bookingDTOs.add(bookingMapper.findEmailAndUsernameByBookingID(booking.getBooking_id()));
      }

      return bookingDTOs;
   }

   @Override
   public ViewBookingDTO findBookingDetailByID(int id) throws CustomBadRequestException {
      Booking booking = bookingRepository.findById(id).get();
      if (booking == null) {
         throw new CustomBadRequestException(CustomError.builder()
               .message("There are no session for the session id: " + id).code("400").build());
      }
      return bookingMapper.toViewBookingDTO(booking);
   }

   @Override
   public Booking findBookingByID(int id) throws CustomBadRequestException {
      Booking booking = bookingRepository.findById(id).get();
      if (booking == null) {
         throw new CustomBadRequestException(CustomError.builder()
               .message("There are no booking for the booking id: " + id).code("400").build());
      }
      return booking;
   }

   @Override
   public void deleteBookingByStatus(int id) throws CustomBadRequestException {
      Booking booking = bookingRepository.findById(id).get();
      if (booking != null) {
         if (booking.getStatus() == 0) {
            bookingRepository.delete(booking);
         } else {
            throw new CustomBadRequestException(
                  CustomError.builder().message("Cannot delete booking with status other than 0.").code("400").build());
         }
      } else {
         throw new CustomBadRequestException(
               CustomError.builder().message("Booking with ID not found: " + id).code("400").build());
      }
   }

   // @Override
   // public BookingDTO findEmailAndUsernameByBookingID(int id) {
   // Booking booking = bookingRepository.findById(id).get();
   // Times time = booking.getTime();
   // Session session = time.getSession();
   // Account account = accountRepository.findById(session.getMentor_id()).get();

   // BookingDTO bookingDTO = new BookingDTO();
   // bookingDTO.setUsername(account.getUsername());
   // bookingDTO.setEmail(account.getEmail());
   // bookingDTO.setScheduleDate(time.getStart_date());
   // bookingDTO.setScheduleTime(time.getStart_time().toString() + "-" +
   // time.getEnd_time().toString());
   // return bookingDTO;
   // }

   // @Override
   // public void deleteBookingByID(int id) {
   // bookingRepository.deleteById(id);
   // }

   // @Override
   // public void saveBooking(Booking r) {
   // bookingRepository.save(r);
   // }

}
