package com.hp.backend.model.feedback.mapper;

import java.sql.Date;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Feedback;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.feedback.dto.FeedbackAddRequestDTO;
import com.hp.backend.model.feedback.dto.FeedbackListAdminResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMenteeResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMentorResponseDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FeedbackMapper {
        private final AccountRepository accountRepository;
        private final DateUtil dateUtil;

        public FeedbackListAdminResponseDTO toFeedbackListResponseDTO(Feedback feedback)
                        throws CustomInternalServerException {
                Account mentor = accountRepository.findById(feedback.getMentor_id()).get();
                Account mentee = accountRepository.findById(feedback.getMentee_id()).get();

                if (mentor == null || mentee == null) {
                        throw new CustomInternalServerException(
                                        CustomError.builder().message("Report sender is not exist").code("500")
                                                        .build());
                }
                String mentee_avatar = Base64.getEncoder().encodeToString(mentee.getAvatar());
                String mentor_avatar = Base64.getEncoder().encodeToString(mentor.getAvatar());

                return FeedbackListAdminResponseDTO.builder().mentee_avatar(mentee_avatar)
                                .mentee_email(mentee.getEmail())
                                .mentee_username(mentee.getUsername())
                                .mentor_avatar(mentor_avatar).mentor_email(mentor.getEmail())
                                .mentor_username(mentor.getUsername())
                                .created_date(feedback.getTime()).content(feedback.getContent())
                                .feedback_id(feedback.getFeedback_id())
                                .rating(feedback.getRating()).build();
        }

        public FeedbackListMenteeResponseDTO toFeedbackListMenteeResponseDTO(Feedback feedback)
                        throws CustomInternalServerException {
                Account mentor = accountRepository.findById(feedback.getMentor_id()).get();

                
                if (mentor == null) {
                        throw new CustomInternalServerException(
                                        CustomError.builder().message("Report sender is not exist").code("500")
                                                        .build());
                }

                String mentor_avatar = Base64.getEncoder().encodeToString(mentor.getAvatar());
                return FeedbackListMenteeResponseDTO.builder().avatar(mentor_avatar).email(mentor.getEmail())
                                .username(mentor.getUsername())
                                .content(feedback.getContent()).created_date(feedback.getTime())
                                .feedback_id(feedback.getFeedback_id())
                                .rating(feedback.getRating()).build();
        }

        public FeedbackListMentorResponseDTO toFeedbackListMentorResponseDTO(Feedback feedback)
                        throws CustomInternalServerException {
                Account mentee = accountRepository.findById(feedback.getMentee_id()).get();

                if (mentee == null) {
                        throw new CustomInternalServerException(
                                        CustomError.builder().message("Report sender is not exist").code("500")
                                                        .build());
                }

                String mentee_avatar = Base64.getEncoder().encodeToString(mentee.getAvatar());
                return FeedbackListMentorResponseDTO.builder().avatar(mentee_avatar).email(mentee.getEmail())
                                .username(mentee.getUsername())
                                .content(feedback.getContent()).created_date(feedback.getTime())
                                .feedback_id(feedback.getFeedback_id())
                                .rating(feedback.getRating()).build();
        }

        public Feedback toFeedback(FeedbackAddRequestDTO feedback, int mentee_id) {
                Date currentDate = dateUtil.getCurrentDate();

                return Feedback.builder().content(feedback.getContent()).mentor_id(feedback.getMentor_id())
                                .rating(feedback.getRating()).time(currentDate)
                                .mentee_id(mentee_id).build();
        }
}
