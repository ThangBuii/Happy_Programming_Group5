package com.hp.backend.model.account.mapper;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Favorite_Mentor;
import com.hp.backend.entity.Skills;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MenteeDTOResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTODetailResponse;
import com.hp.backend.model.account.dto.AdminSiteDTO.MentorDTOResponse;
import com.hp.backend.model.account.dto.FindMentorDTO.FindMentorResponseDTO;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOCreate;
import com.hp.backend.model.account.dto.LoginDTO.AccountDTOLoginResponse;
import com.hp.backend.model.account.dto.MenteeSiteDTO.MenteeDTODetailUpdateRequest;
import com.hp.backend.model.account.dto.MentorSiteDTO.MentorDTODetailUpdateRequest;
import com.hp.backend.model.favorite.dto.FavoriteListMenteeResponseDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.FavoriteRepository;
import com.hp.backend.repository.SkillRepository;
import com.hp.backend.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final BookingRepository bookingRepository;
    private final SkillRepository skillRepository;
    private final AccountRepository accountRepository;
    private final CommonUtils commonUtils;
    private final FavoriteRepository favoriteRepository;

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
        
        
        return MenteeDTOResponse.builder().avatar(commonUtils.imageToFrontEnd(account.getAvatar())).username(account.getUsername())
                .created_date(account.getCreated_date()).account_id(account.getAccount_id())
                .numberOfBooking(numberOfBookings).build();
    }

    public MentorDTOResponse toMentorDTOResponse(Account account) {
        int numberOfBookings = bookingRepository.getSessionCountByMentorId(account.getAccount_id());
        double earned = bookingRepository.sumPriceByTimeSessionMentorId(account.getAccount_id());

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(earned);


        return MentorDTOResponse.builder().avatar(commonUtils.imageToFrontEnd(account.getAvatar())).username(account.getUsername())
                .created_date(account.getCreated_date())
                .account_id(account.getAccount_id()).numberOfBooking(numberOfBookings).earned(formattedValue).build();
    }

    public MentorDTODetailResponse toMentorDTODetailResponse(Account account) {
        List<Skills> skills = skillRepository.findSkillsByMentorId(account.getAccount_id());
        
        return MentorDTODetailResponse.builder()
                .avatar(commonUtils.imageToFrontEnd(account.getAvatar())).email(account.getEmail()).username(account.getUsername())
                .created_date(account.getCreated_date()).gender(account.getGender()).dob(account.getDob())
                .country(account.getCountry()).city(account.getCity()).university(account.getUniversity())
                .major(account.getMajor()).degree(account.getDegree()).description(account.getDescription())
                .short_description(account.getShort_description()).role(account.getRole()).skills(skills).build();
    }

    public MenteeDTODetailResponse toMenteeDTODetailResponse(Account account) {
        
        return MenteeDTODetailResponse.builder()
                .avatar(commonUtils.imageToFrontEnd(account.getAvatar())).email(account.getEmail()).username(account.getUsername())
                .created_date(account.getCreated_date()).gender(account.getGender()).dob(account.getDob())
                .country(account.getCountry()).city(account.getCity()).description(account.getDescription()).build();
    }

    public Account toUpdatedMenteeAccount(MentorDTODetailUpdateRequest mentee, int account_id)
            throws CustomBadRequestException {
        Account account = accountRepository.findById(account_id).get();

        if (account == null) {
            throw new CustomBadRequestException(
                    CustomError.builder().message("Account is not exist").code("400").build());
        } else if (!account.getUsername().equalsIgnoreCase(mentee.getUsername())
                && accountRepository.existsByUsername(mentee.getUsername())) {
            throw new CustomBadRequestException(
                    CustomError.builder().message("Username has already existed").code("400").build());
        }
        
        return Account.builder().account_id(account_id).avatar(commonUtils.imageToDatabase(mentee.getAvatar())).email(account.getEmail())
                .username(mentee.getUsername()).gender(mentee.getGender()).dob(mentee.getDob())
                .country(mentee.getCountry()).city(mentee.getCity()).description(mentee.getDescription())
                .created_date(account.getCreated_date()).role(account.getRole()).password(account.getPassword())
                .build();
    }

    public Account toUpdatedMentorAccount(MentorDTODetailUpdateRequest mentor, int account_id)
            throws CustomBadRequestException {
        Account account = accountRepository.findById(account_id).get();

        if (account == null) {
            throw new CustomBadRequestException(
                    CustomError.builder().message("Account is not exist").code("400").build());
        } else if (!account.getUsername().equalsIgnoreCase(mentor.getUsername())
                && accountRepository.existsByUsername(mentor.getUsername())) {
            throw new CustomBadRequestException(
                    CustomError.builder().message("Username has already existed").code("400").build());
        }
        
        return Account.builder().account_id(account_id).avatar(commonUtils.imageToDatabase(mentor.getAvatar())).email(account.getEmail())
                .username(mentor.getUsername()).gender(mentor.getGender()).dob(mentor.getDob())
                .country(mentor.getCountry()).city(mentor.getCity()).description(mentor.getDescription())
                .created_date(account.getCreated_date()).role(account.getRole()).university(mentor.getUniversity())
                .major(mentor.getMajor()).degree(mentor.getDegree())
                .password(account.getPassword())
                .short_description(mentor.getShort_description()).build();
    }

    public FavoriteListMenteeResponseDTO toFavoriteListResponseDTO(Favorite_Mentor favorite)
            throws CustomBadRequestException {
        Optional<Account> mentor = accountRepository.findById(favorite.getMentor_id());

        if (!mentor.isPresent()) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("Account not exist").build());
        }
        List<Skills> skills = skillRepository.findSkillsByMentorId(mentor.get().getAccount_id());
        Account mentor1 = mentor.get();
        
        return FavoriteListMenteeResponseDTO.builder().avatar(commonUtils.imageToFrontEnd(mentor1.getAvatar())).username(mentor1.getUsername())
                .description(mentor1.getDescription()).short_description(mentor1.getShort_description())
                .skills(skills).mentor_id(mentor1.getAccount_id()).favorite_id(favorite.getFavorite_id()).build();
    }

    public FindMentorResponseDTO toFindMentorResponse(Account account, int account_id) {
        List<Skills> skills = skillRepository.findSkillsByMentorId(account.getAccount_id());
        boolean isFavorite = false;
        if(account_id != 0){
            isFavorite = favoriteRepository.existsByMentorIdAndMenteeId(account.getAccount_id(), account_id);
        }

        return FindMentorResponseDTO.builder().avatar(commonUtils.imageToFrontEnd(account.getAvatar()))
            .username(account.getUsername()).short_description(account.getShort_description())
            .description(account.getDescription()).skills(skills).mentor_id(account_id).isFavorite(isFavorite).build();
    }
}
