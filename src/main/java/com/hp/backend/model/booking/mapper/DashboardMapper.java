package com.hp.backend.model.booking.mapper;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Session;
import com.hp.backend.entity.Times;
import com.hp.backend.model.booking.dto.DashboardMenteeDTO;
import com.hp.backend.model.booking.dto.DashboardMentorDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DashboardMapper {
    private final BookingRepository bookingRepository;
    private final AccountRepository accountRepository;
    private final CommonUtils commonUtils;

    public static DashboardMenteeDTO toBookingDTO(DashboardMenteeDTO book1) {
        return DashboardMenteeDTO.builder().avatar(book1.getAvatar()).username(book1.getUsername()).email(book1.getEmail())
                .created_Date(book1.getCreated_Date())
                .build();
    }

    public static DashboardMentorDTO toBookingMentorDTO(DashboardMentorDTO book1) {
        return DashboardMentorDTO.builder().avatar(book1.getAvatar()).username(book1.getUsername()).email(book1.getEmail())
                .created_Date(book1.getCreated_Date())
                .build();
    }

    public DashboardMenteeDTO findEmailAndUsernameByBookingID(int id) {
        Booking booking = bookingRepository.findById(id).get();
        Times time = booking.getTime();
        Session session = time.getSession();
        Account account = accountRepository.findById(session.getMentor_id()).get();
        // Lấy scheduleTime từ start_time và end_time
        return DashboardMenteeDTO.builder().avatar(commonUtils.imageToFrontEnd(account.getAvatar())).username(account.getUsername()).email(account.getEmail())
                .created_Date(booking.getCreated_date()).status(booking.getStatus())
                .build();
    }

}
