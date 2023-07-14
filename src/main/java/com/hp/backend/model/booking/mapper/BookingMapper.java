package com.hp.backend.model.booking.mapper;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Report;
import com.hp.backend.entity.Session;
import com.hp.backend.entity.Times;
import com.hp.backend.model.booking.dto.AddBookingRequestDTO;
import com.hp.backend.model.booking.dto.BookingListAdminDTO;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.booking.dto.BookingListMentorDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;
import com.hp.backend.model.report.dto.ReportAddRequestDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final BookingRepository bookingRepository;
    private final AccountRepository accountRepository;
    private final CommonUtils commonUtils;

    public BookingListMenteeDTO toBookingDTO(Booking book1) {
        Account account = accountRepository.findById(book1.getMentee_id()).get();
        Times time = book1.getTime();
        return BookingListMenteeDTO.builder().bookingID(book1.getBooking_id()).avatar(commonUtils.imageToFrontEnd(account.getAvatar()))
                .username(account.getUsername()).email(account.getEmail())
                .scheduleDate(book1.getCreated_date())
                .scheduleTime(time.getStart_time().toString() + "-" + time.getEnd_time().toString())
                .build();
    }

    public static BookingListAdminDTO toBookingDTO(BookingListAdminDTO book1) {
        return BookingListAdminDTO.builder().bookingID(book1.getBookingID()).avatarMentee(book1.getAvatarMentee())
                .avatarMentor(book1.getAvatarMentor()).email(book1.getEmail())
                .scheduleDate(book1.getScheduleDate()).scheduleTime(book1.getScheduleTime())
                .menteeUsername(book1.getMenteeUsername()).mentorUsername(book1.getMenteeUsername())
                .build();
    }

    public static BookingListMentorDTO toBookingDTO(BookingListMentorDTO book1) {
        return BookingListMentorDTO.builder().bookingID(book1.getBookingID()).avatar(book1.getAvatar())
                .username(book1.getUsername()).email(book1.getEmail())
                .scheduleDate(book1.getScheduleDate())
                .scheduleTime(book1.getScheduleTime())
                .build();
    }

    public BookingListMenteeDTO findEmailAndUsernameByBookingID(int id) {
        Booking booking = bookingRepository.findById(id).get();
        Times time = booking.getTime();
        Session session = time.getSession();
        Account account = accountRepository.findById(session.getMentor_id()).get();
        return BookingListMenteeDTO.builder().username(account.getUsername()).email(account.getEmail())
                .scheduleDate(time.getStart_date())
                .scheduleTime(time.getStart_time().toString() + "-" + time.getEnd_time().toString())
                .build();

    }

    public BookingListAdminDTO findEmailAndUsernameByAdminBookingID(int id) {
        Booking booking = bookingRepository.findById(id).get();
        Times time = booking.getTime();
        Session session = time.getSession();
        Account account = accountRepository.findById(session.getMentor_id()).get();
        Account account2 = accountRepository.findById(booking.getMentee_id()).get();

        // BookingAdminDTO bookingAdminDTO = new BookingAdminDTO();
        // bookingAdminDTO.setBookingID(booking.getBooking_id());
        // bookingAdminDTO.setEmail(account2.getEmail());
        // bookingAdminDTO.setScheduleDate(time.getStart_date().toString());
        // bookingAdminDTO.setScheduleTime(time.getStart_time().toString() + "-" +
        // time.getEnd_time().toString());
        // bookingAdminDTO.setMenteeUsername(account2.getUsername());
        // bookingAdminDTO.setMentorUsername(account.getUsername());
        // bookingAdminDTO.setStatus(booking.getStatus());

        return BookingListAdminDTO.builder().bookingID(booking.getBooking_id())
                .avatarMentor(commonUtils.imageToFrontEnd(account.getAvatar()))
                .avatarMentee(commonUtils.imageToFrontEnd(account2.getAvatar())).email(account2.getEmail())
                .scheduleDate(time.getStart_date().toString())
                .scheduleTime(time.getStart_time().toString() + "-" + time.getEnd_time().toString())
                .menteeUsername(account2.getUsername()).mentorUsername(account.getUsername())
                .status(booking.getStatus())
                .build();
    }

    public ViewBookingDTO toViewBookingDTO(Booking booking) {
        Account account = accountRepository.findById(booking.getMentee_id()).get();
        Times time = booking.getTime();
        Session session = time.getSession();
        Account account2 = accountRepository.findById(session.getMentor_id()).get();
        return ViewBookingDTO.builder().menteeUsername(account.getUsername()).menteeEmail(account.getEmail())
                .mentorUsername(account2.getUsername()).mentorEmail(account2.getEmail())
                .scheduleDate(time.getStart_date())
                .scheduleTime(time.getStart_time().toString() + "-" + time.getEnd_time().toString())
                .menteeAvatar(commonUtils.imageToFrontEnd(account.getAvatar()))
                .mentorAvatar(commonUtils.imageToFrontEnd(account2.getAvatar())).status(booking.getStatus())
                .createdDate(booking.getCreated_date())
                .build();
    }

    public Booking toBooking(AddBookingRequestDTO addBookingRequestDTO, int mentee_id) {
        Date currentDate = commonUtils.getCurrentDate();
        return Booking.builder().mentee_id(mentee_id).time(Times.builder().time_id(addBookingRequestDTO.getTime_id()).build())
                .created_date(currentDate).status(0).build();
    }

}