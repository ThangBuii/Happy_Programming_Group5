package com.hp.backend.service.Booking.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Times;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.booking.dto.BookingListMentorDTO;
import com.hp.backend.model.booking.dto.DashboardMentorDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;
import com.hp.backend.model.booking.mapper.BookingMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.service.Booking.BookingListMentorService;
import com.hp.backend.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingListMentorServiceImpl implements BookingListMentorService {

    private final BookingRepository bookingRepository;
    private final AccountRepository accountRepository;
    private final BookingMapper bookingMapper;
    private final CommonUtils commonUtils;

    @Override
    public List<BookingListMentorDTO> getAllMentorBooking(int id) throws CustomNotFoundException {
        List<Integer> menteeIds = bookingRepository.findMenteeIdsByMentorId(id);
        List<BookingListMentorDTO> mentorBookings = new ArrayList<>();

        for (Integer menteeId : menteeIds) {
            List<Booking> bookings = bookingRepository.findAllByMentee_Id(menteeId);

            for (Booking booking : bookings) {
                Account mentee = accountRepository.findById(menteeId).orElse(null);
                Times time = booking.getTime();

                if (mentee == null || time == null) {
                    CustomError error = new CustomError("DataNotFound", "Data for mentee or time not found", "");
                    throw new CustomNotFoundException(error);
                }

                BookingListMentorDTO bookingMentorDTO = new BookingListMentorDTO();
                bookingMentorDTO.setAvatar(commonUtils.imageToFrontEnd(mentee.getAvatar()));
                bookingMentorDTO.setMenteeID(menteeId);
                bookingMentorDTO.setBookingID(booking.getBooking_id());
                bookingMentorDTO.setUsername(mentee.getUsername());
                bookingMentorDTO.setEmail(mentee.getEmail());
                bookingMentorDTO.setScheduleDate(time.getStart_date());
                bookingMentorDTO.setScheduleTime(time.getStart_time().toString() + "-" + time.getEnd_time().toString());

                mentorBookings.add(bookingMentorDTO);
            }
        }

        return mentorBookings;
    }

    @Override
    public ViewBookingDTO findBookingDetailByID(int id) throws CustomBadRequestException {
        Booking booking = bookingRepository.findById(id).get();
        if (booking == null) {
            throw new CustomBadRequestException(CustomError.builder()
                    .message("There are no booking for the booking id: " + id).code("400").build());
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
    public void saveMentorBooking(Booking b) {
        bookingRepository.save(b);
    }

    @Override
    public List<DashboardMentorDTO> getDashboardMentorBooking(int id) throws CustomNotFoundException {
        List<Integer> menteeIds = bookingRepository.findMenteeIdsByMentorId(id);
        List<DashboardMentorDTO> mentorBookings = new ArrayList<>();

        for (Integer menteeId : menteeIds) {
            List<Booking> bookings = bookingRepository.findAllByMentee_Id(menteeId);

            for (Booking booking : bookings) {
                Account mentee = accountRepository.findById(menteeId).get();
                Times time = booking.getTime();

                if (mentee == null || time == null) {
                    CustomError error = new CustomError("DataNotFound", "Data for mentee or time not found", "");
                    throw new CustomNotFoundException(error);
                }

                DashboardMentorDTO dashboardMentorDTO = new DashboardMentorDTO();
                dashboardMentorDTO.setAvatar(commonUtils.imageToFrontEnd(mentee.getAvatar()));
                dashboardMentorDTO.setBookingID(booking.getBooking_id());
                dashboardMentorDTO.setUsername(mentee.getUsername());
                dashboardMentorDTO.setEmail(mentee.getEmail());
                dashboardMentorDTO.setCreated_Date(booking.getCreated_date());
                dashboardMentorDTO.setStatus(booking.getStatus());

                mentorBookings.add(dashboardMentorDTO);
            }
        }
        return mentorBookings;
    }

    

}
