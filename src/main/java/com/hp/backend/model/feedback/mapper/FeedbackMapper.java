package com.hp.backend.model.feedback.mapper;

import java.sql.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Feedback;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.feedback.dto.FeedbackAddRequestDTO;
import com.hp.backend.model.feedback.dto.FeedbackListAdminResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMenteeResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMentorResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackMenteeFeedbackMentorListDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FeedbackMapper {
        private final AccountRepository accountRepository;
        private final CommonUtils commonUtils;
        private static final String REPORT_SENDER_NOT_EXIST_MSG = "Report sender does not exist";

        public FeedbackListAdminResponseDTO toFeedbackListResponseDTO(Feedback feedback)
                        throws CustomInternalServerException {
                Optional<Account> mentor = accountRepository.findById(feedback.getMentor_id());
                Optional<Account> mentee = accountRepository.findById(feedback.getMentee_id());

                if (!mentor.isPresent() || !mentee.isPresent()) {
                        throw new CustomInternalServerException(
                                        CustomError.builder().message(REPORT_SENDER_NOT_EXIST_MSG).code("500")
                                                        .build());
                }
                

                return FeedbackListAdminResponseDTO.builder().mentee_avatar(commonUtils.imageToFrontEnd(mentee.get().getAvatar()))
                                .mentee_email(mentee.get().getEmail())
                                .mentee_username(mentee.get().getUsername())
                                .mentor_avatar(commonUtils.imageToFrontEnd(mentor.get().getAvatar())).mentor_email(mentor.get().getEmail())
                                .mentor_username(mentor.get().getUsername())
                                .created_date(feedback.getTime()).content(feedback.getContent())
                                .feedback_id(feedback.getFeedback_id())
                                .rating(feedback.getRating()).build();
        }

        public FeedbackListMenteeResponseDTO toFeedbackListMenteeResponseDTO(Feedback feedback)
                        throws CustomInternalServerException {
                Optional<Account> mentor = accountRepository.findById(feedback.getMentor_id());

                if (!mentor.isPresent()) {
                        throw new CustomInternalServerException(
                                        CustomError.builder().message(REPORT_SENDER_NOT_EXIST_MSG).code("500")
                                                        .build());
                }
                
        

                return FeedbackListMenteeResponseDTO.builder().avatar(commonUtils.imageToFrontEnd(mentor.get().getAvatar())).email(mentor.get().getEmail())
                                .username(mentor.get().getUsername())
                                .content(feedback.getContent()).created_date(feedback.getTime())
                                .feedback_id(feedback.getFeedback_id())
                                .rating(feedback.getRating()).build();
        }

        public FeedbackListMentorResponseDTO toFeedbackListMentorResponseDTO(Feedback feedback)
                        throws CustomInternalServerException {
                Optional<Account> mentee = accountRepository.findById(feedback.getMentee_id());

                if (!mentee.isPresent()) {
                        throw new CustomInternalServerException(
                                        CustomError.builder().message(REPORT_SENDER_NOT_EXIST_MSG).code("500")
                                                        .build());
                }

               
                return FeedbackListMentorResponseDTO.builder().avatar(commonUtils.imageToFrontEnd(mentee.get().getAvatar())).email(mentee.get().getEmail())
                                .username(mentee.get().getUsername())
                                .content(feedback.getContent()).created_date(feedback.getTime())
                                .feedback_id(feedback.getFeedback_id())
                                .rating(feedback.getRating()).build();
        }

        public Feedback toFeedback(FeedbackAddRequestDTO feedback, int mentee_id) throws CustomBadRequestException {
                if(!accountRepository.findById(feedback.getMentor_id()).isPresent()){
                        throw new CustomBadRequestException(CustomError.builder().message("Mentor is not exist").code("400")
                                                        .build());
                }

                Date currentDate = commonUtils.getCurrentDate();

                return Feedback.builder().content(feedback.getContent()).mentor_id(feedback.getMentor_id())
                                .rating(feedback.getRating()).time(currentDate)
                                .mentee_id(mentee_id).build();
        }

        public FeedbackMenteeFeedbackMentorListDTO toFeedbackMenteeFeedbackMentorListDTO(Account account) {
            return FeedbackMenteeFeedbackMentorListDTO.builder().mentor_id(account.getAccount_id()).mentor_name(account.getUsername()).build();
        }
}
