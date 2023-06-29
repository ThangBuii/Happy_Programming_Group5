package com.hp.backend.model.feedback.mapper;


import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Feedback;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.feedback.dto.FeedbackListAdminResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMenteeResponseDTO;
import com.hp.backend.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FeedbackMapper {
    private final AccountRepository accountRepository;

    public FeedbackListAdminResponseDTO toFeedbackListResponseDTO(Feedback feedback) throws CustomInternalServerException {
        Account mentor = accountRepository.findById(feedback.getMentor_id()).get();
        Account mentee = accountRepository.findById(feedback.getMentee_id()).get();

        if (mentor == null || mentee == null) {
            throw new CustomInternalServerException(
                    CustomError.builder().message("Report sender is not exist").code("500").build());
        }

        return FeedbackListAdminResponseDTO.builder().mentee_ava(mentee.getAvatar()).mentee_email(mentee.getEmail()).mentee_username(mentee.getUsername())
                            .mentor_ava(mentor.getAvatar()).mentor_email(mentor.getEmail()).mentor_username(mentor.getUsername())
                            .created_date(feedback.getTime()).content(feedback.getContent()).feedback_id(feedback.getFeedback_id()).rating(feedback.getRating()).build();
    }

    public FeedbackListMenteeResponseDTO toFeedbackListMenteeResponseDTO(Feedback feedback) throws CustomInternalServerException {
        Account mentor = accountRepository.findById(feedback.getMentor_id()).get();

        if (mentor == null) {
            throw new CustomInternalServerException(
                    CustomError.builder().message("Report sender is not exist").code("500").build());
        }

        return FeedbackListMenteeResponseDTO.builder().ava(mentor.getAvatar()).email(mentor.getEmail()).username(mentor.getUsername())
                    .content(feedback.getContent()).created_date(feedback.getTime()).feedback_id(feedback.getFeedback_id()).rating(feedback.getRating()).build();
    }
}
