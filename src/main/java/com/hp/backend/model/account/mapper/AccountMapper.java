package com.hp.backend.model.account.mapper;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTOResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTOResponse;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOCreate;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginResponse;
import com.hp.backend.repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final BookingRepository bookingRepository;

    public static AccountDTOLoginResponse toAccountDTOResponse(Account account) {
        return AccountDTOLoginResponse.builder().role(account.getRole()).build();
    }

    public static Account toUser(AccountDTOCreate accountDTOCreate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        String formattedDate = dateFormat.format(currentDate);

        java.sql.Date sqlDate;
        try {
            java.util.Date parsedDate = dateFormat.parse(formattedDate);
            sqlDate = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing error
            return null; // or throw an exception
        }

        return Account.builder().username(accountDTOCreate.getUsername())
                .email(accountDTOCreate.getEmail())
                .password(accountDTOCreate.getPassword())
                .created_date(sqlDate).role(accountDTOCreate.getRole()).build();
    }

    public MenteeDTOResponse toMenteeDTOResponse(Account account) {
        int numberOfBookings = bookingRepository.countBookingsByMenteeId(account.getAccount_id());

        return MenteeDTOResponse.builder().avatar(account.getAvatar()).username(account.getUsername())
                .created_date(account.getCreated_date()).account_id(account.getAccount_id())
                .numberOfBooking(numberOfBookings).build();
    }

    public MentorDTOResponse toMentorDTOResponse(Account account) {
        int numberOfBookings = bookingRepository.getSessionCountByMentorId(account.getAccount_id());
        double earned = bookingRepository.sumPriceByTimeSessionMentorId(account.getAccount_id());

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(earned);

        return MentorDTOResponse.builder().avatar(account.getAvatar()).username(account.getUsername())
                .created_date(account.getCreated_date())
                .account_id(account.getAccount_id()).numberOfBooking(numberOfBookings).earned(formattedValue).build();
    }

    public static MentorDTODetailResponse toMentorDTODetailResponse(Account account) {
        return MentorDTODetailResponse.builder().account_id(account.getAccount_id())
                .avatar(account.getAvatar()).email(account.getEmail()).username(account.getUsername())
                .created_date(account.getCreated_date()).gender(account.getGender()).dob(account.getDob())
                .country(account.getCountry()).city(account.getCity()).university(account.getUniversity())
                .major(account.getMajor()).degree(account.getDegree()).description(account.getDescription())
                .jobtitle(account.getJobtitle()).workplace(account.getWorkplace())
                .short_description(account.getShort_description()).build();
    }

    public static MenteeDTODetailResponse toMenteeDTODetailResponse(Account account) {
        return MenteeDTODetailResponse.builder().account_id(account.getAccount_id())
                .avatar(account.getAvatar()).email(account.getEmail()).username(account.getUsername())
                .created_date(account.getCreated_date()).gender(account.getGender()).dob(account.getDob())
                .country(account.getCountry()).city(account.getCity()).description(account.getDescription()).build();
    }
}
