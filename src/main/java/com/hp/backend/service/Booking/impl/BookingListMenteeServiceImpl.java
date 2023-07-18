package com.hp.backend.service.Booking.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Booking;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.booking.dto.DashboardMenteeDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.model.booking.mapper.DashboardMapper;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.service.Booking.BookingListMenteeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingListMenteeServiceImpl implements BookingListMenteeService {

   private final BookingRepository bookingRepository;
   private final BookingMapper bookingMapper;
   private final DashboardMapper dashboardMapper;

   @Override
   public List<BookingListMenteeDTO> getAllMenteeBooking(int id) throws CustomNotFoundException {
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
   public ViewBookingDTO findMenteeBookingDetailByID(int id) throws CustomBadRequestException {
      Optional<Booking> booking = bookingRepository.findById(id);
      if (!booking.isPresent()) {
         throw new CustomBadRequestException(CustomError.builder()
               .message("There are no session for the session id: " + id).code("400").build());
      }
      return bookingMapper.toViewBookingDTO(booking.get());
   }

   @Override
   public Booking findBookingByID(int id) throws CustomBadRequestException {
      Optional<Booking> booking = bookingRepository.findById(id);
      if (!booking.isPresent()) {
         throw new CustomBadRequestException(CustomError.builder()
               .message("There are no booking for the booking id: " + id).code("400").build());
      }
      return booking.get();
   }

   @Override
   public void deleteMenteeBookingByStatus(int id) throws CustomBadRequestException {
      Optional<Booking> booking = bookingRepository.findById(id);
      if (!booking.isPresent()) {
         if (booking.get().getStatus() == 0) {
            bookingRepository.delete(booking.get());
         } else {
            throw new CustomBadRequestException(
                  CustomError.builder().message("Cannot delete booking with status other than 0.").code("400").build());
         }
      } else {
         throw new CustomBadRequestException(
               CustomError.builder().message("Booking with ID not found: " + id).code("400").build());
      }
   }

   @Override
   public List<DashboardMenteeDTO> getDashboardMenteeBooking(int id) throws CustomNotFoundException {
      List<DashboardMenteeDTO> dashboardDTOs = new ArrayList<>();
      List<Booking> bookings = bookingRepository.findAllByMentee_Id(id);
      if (bookings.isEmpty()) {
         CustomError error = new CustomError("BookingsNotFound", "No bookings found for the mentee with ID " + id, "");
         throw new CustomNotFoundException(error);
      }
      for (Booking booking : bookings) {

         dashboardDTOs.add(dashboardMapper.findEmailAndUsernameByBookingID(booking.getBooking_id()));

      }

      return dashboardDTOs;
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
