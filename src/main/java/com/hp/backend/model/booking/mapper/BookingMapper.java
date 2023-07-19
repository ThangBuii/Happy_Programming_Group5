package com.hp.backend.model.booking.mapper;

import java.sql.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Session;
import com.hp.backend.entity.Times;
import com.hp.backend.model.booking.dto.AddBookingRequestDTO;
import com.hp.backend.model.booking.dto.BookingListAdminDTO;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.booking.dto.BookingListMentorDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.TimeRepository;
import com.hp.backend.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final BookingRepository bookingRepository;
    private final AccountRepository accountRepository;
    private final TimeRepository timeRepository;
    private final CommonUtils commonUtils;

    public BookingListMenteeDTO toBookingDTO(Booking book1) {
        Optional<Account> account = accountRepository.findById(book1.getMentee_id());
        Times time = book1.getTime();
        return BookingListMenteeDTO.builder().bookingID(book1.getBooking_id()).avatar(commonUtils.imageToFrontEnd(account.get().getAvatar()))
                .username(account.get().getUsername()).email(account.get().getEmail())
                .scheduleDate(book1.getCreated_date())
                .scheduleTime(time.getStart_time().toString() + "-" + time.getEnd_time().toString())
                .build();
    }

    public static BookingListAdminDTO toBookingDTO(BookingListAdminDTO book1) {
        return BookingListAdminDTO.builder().bookingID(book1.getBookingID()).avatarMentee(book1.getAvatarMentee())
                .avatarMentor(book1.getAvatarMentor())
                .scheduleDate(book1.getScheduleDate()).scheduleTime(book1.getScheduleTime()).created_date(book1.getCreated_date())
                .menteeUsername(book1.getMenteeUsername()).mentorUsername(book1.getMenteeUsername()).session_id(book1.getSession_id())
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
        Optional<Booking> booking = bookingRepository.findById(id);
        Times time = booking.get().getTime();
        Session session = time.getSession();
        Optional<Account> account = accountRepository.findById(session.getMentor_id());
        return BookingListMenteeDTO.builder().username(account.get().getUsername()).email(account.get().getEmail())
                .scheduleDate(time.getStart_date()).avatar(commonUtils.imageToFrontEnd(account.get().getAvatar()))
                .scheduleTime(time.getStart_time().toString() + "-" + time.getEnd_time().toString()).bookingID(id)
                .build();

    }

    public BookingListAdminDTO findEmailAndUsernameByAdminBookingID(int id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        Times time = booking.get().getTime();
        Session session = time.getSession();
        Optional<Account> account = accountRepository.findById(session.getMentor_id());
        Optional<Account> account2 = accountRepository.findById(booking.get().getMentee_id());

        return BookingListAdminDTO.builder().bookingID(booking.get().getBooking_id())
                .avatarMentor(commonUtils.imageToFrontEnd(account.get().getAvatar()))
                .avatarMentee(commonUtils.imageToFrontEnd(account2.get().getAvatar()))
                .scheduleDate(time.getStart_date().toString()).created_date(booking.get().getCreated_date())
                .scheduleTime(time.getStart_time().toString() + "-" + time.getEnd_time().toString())
                .menteeUsername(account2.get().getUsername()).mentorUsername(account.get().getUsername())
                .status(booking.get().getStatus()).session_id(session.getSession_id())
                .build();
    }

    public ViewBookingDTO toViewBookingDTO(Booking booking) {
        Optional<Account> account = accountRepository.findById(booking.getMentee_id());
        Times time = booking.getTime();
        Session session = time.getSession();
        Optional<Account> account2 = accountRepository.findById(session.getMentor_id());
        return ViewBookingDTO.builder().menteeUsername(account.get().getUsername()).menteeEmail(account.get().getEmail())
                .mentorUsername(account2.get().getUsername()).mentorEmail(account2.get().getEmail())
                .scheduleDate(time.getStart_date())
                .scheduleTime(time.getStart_time().toString() + "-" + time.getEnd_time().toString())
                .menteeAvatar(commonUtils.imageToFrontEnd(account.get().getAvatar()))
                .mentorAvatar(commonUtils.imageToFrontEnd(account2.get().getAvatar())).status(booking.getStatus())
                .menteeAvatar(commonUtils.imageToFrontEnd(account.get().getAvatar()))
                .mentorAvatar(commonUtils.imageToFrontEnd(account2.get().getAvatar())).status(booking.getStatus())
                .createdDate(booking.getCreated_date())
                .build();
    }

    public Booking toBooking(AddBookingRequestDTO addBookingRequestDTO, int mentee_id) {
        Date currentDate = commonUtils.getCurrentDate();
        Optional<Times> time = timeRepository.findById(addBookingRequestDTO.getTime_id());
        return Booking.builder().mentee_id(mentee_id).time(time.get())
                .created_date(currentDate).status(0).build();
    }

}
