package com.hp.backend.service.Booking.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Booking;
import com.hp.backend.model.booking.dto.BookingListAdminDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.repository.TimeRepository;
import com.hp.backend.service.Booking.BookingListAdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingListAdminServiceImpl implements BookingListAdminService {

   private final BookingRepository bookingRepository;

   private final TimeRepository timeRepository;

   private final AccountRepository accountRepository;

   private final SessionRepository sessionRepository;

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

   // @Override
   // public void addBooking(Booking booking) {
   // bookingRepository.save(booking);
   // }

   // @Override
   // public Booking findBookingByID(int id) {

   // return bookingRepository.findById(id).get();
   // }

   // @Override
   // public BookingAdminDTO findEmailAndUsernameByAdminBookingID(int id) {
   // Booking booking = bookingRepository.findById(id).get();
   // Times time = booking.getTime();
   // Session session = time.getSession();
   // Account account = accountRepository.findById(session.getMentor_id()).get();
   // Account account2 = accountRepository.findById(booking.getMentee_id()).get();

   // BookingAdminDTO bookingAdminDTO = new BookingAdminDTO();
   // bookingAdminDTO.setBookingID(booking.getBooking_id());
   // bookingAdminDTO.setEmail(account2.getEmail());
   // bookingAdminDTO.setScheduleDate(time.getStart_date().toString());
   // bookingAdminDTO.setScheduleTime(time.getStart_time().toString() + "-" +
   // time.getEnd_time().toString());
   // bookingAdminDTO.setMenteeUsername(account2.getUsername());
   // bookingAdminDTO.setMentorUsername(account.getUsername());
   // bookingAdminDTO.setStatus(booking.getStatus());

   // return bookingAdminDTO;
   // }

   // @Override
   // public void deleteBookingByID(int id) throws Exception{

   // Optional<Booking> bookingOptional = bookingRepository.findById(id);
   // if (bookingOptional.isEmpty()) {
   // throw new Exception("Not found");
   // }
   // bookingRepository.deleteById(id);

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
