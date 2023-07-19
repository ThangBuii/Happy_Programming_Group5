package com.hp.backend.model.feedback.mapper;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Feedback;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.feedback.dto.FeedbackAddRequestDTO;
import com.hp.backend.model.feedback.dto.FeedbackListAdminResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMenteeResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMentorResponseDTO;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.service.feedback.FeedbackService;
import com.hp.backend.utils.CommonUtils;

class FeedbackMapperTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CommonUtils commonUtils;

    @InjectMocks
    private FeedbackMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToFeedback() throws CustomBadRequestException {
        int mentorId = 1;
        int menteeId = 2;

        FeedbackAddRequestDTO feedbackRequest = FeedbackAddRequestDTO.builder().mentor_id(mentorId)
        .content("feedbackContent").rating(4.5).build();
        // Set other properties if necessary

        Account mentorAccount = new Account();
        mentorAccount.setAccount_id(mentorId);
        // Set other mentor account properties if necessary

        Date currentDate = new Date(System.currentTimeMillis());

        when(accountRepository.findById(mentorId)).thenReturn(Optional.of(mentorAccount));
        when(commonUtils.getCurrentDate()).thenReturn(currentDate);

        // Act
        Feedback feedback = mapper.toFeedback(feedbackRequest, menteeId);

        // Assert
        Assertions.assertEquals(feedbackRequest.getContent(), feedback.getContent());
        Assertions.assertEquals(mentorId, feedback.getMentor_id());
        Assertions.assertEquals(feedbackRequest.getRating(), feedback.getRating());
        Assertions.assertEquals(currentDate, feedback.getTime());
        Assertions.assertEquals(menteeId, feedback.getMentee_id());
    }

    @Test
void toFeedback_MentorAccountNotFound_ThrowsCustomBadRequestException() {
    // Arrange
    int mentorId = 1;
    int menteeId = 2;

    FeedbackAddRequestDTO feedbackRequest = FeedbackAddRequestDTO.builder().mentor_id(mentorId)
        .content("feedbackContent").rating(4.5).build();
    // Set other properties if necessary

    when(accountRepository.findById(mentorId)).thenReturn(Optional.empty());

    // Act and Assert
    Assertions.assertThrows(CustomBadRequestException.class, () -> {
        mapper.toFeedback(feedbackRequest, menteeId);
    });
}

    @Test
    void testToFeedbackListMenteeResponseDTO() throws CustomInternalServerException {
        int mentorId = 1;
        int feedbackId = 3;

        Feedback feedback = new Feedback();
        feedback.setMentor_id(mentorId);
        feedback.setFeedback_id(feedbackId);
        feedback.setContent("feedbackContent");
        feedback.setRating(4.5);
        // Set other properties if necessary

        Account mentorAccount = new Account();
        mentorAccount.setAccount_id(mentorId);
        mentorAccount.setAvatar("mentorAvatar".getBytes());
        mentorAccount.setEmail("mentorEmail");
        mentorAccount.setUsername("mentorUsername");
        // Set other mentor account properties if necessary

        when(accountRepository.findById(mentorId)).thenReturn(Optional.of(mentorAccount));
        when(commonUtils.imageToFrontEnd(mentorAccount.getAvatar())).thenReturn("convertedMentorAvatar");

        // Act
        FeedbackListMenteeResponseDTO response = mapper.toFeedbackListMenteeResponseDTO(feedback);

        // Assert
        Assertions.assertEquals("convertedMentorAvatar", response.getAvatar());
        Assertions.assertEquals("mentorEmail", response.getEmail());
        Assertions.assertEquals("mentorUsername", response.getUsername());
        Assertions.assertEquals(feedback.getContent(), response.getContent());
        Assertions.assertEquals(feedback.getTime(), response.getCreated_date());
        Assertions.assertEquals(feedbackId, response.getFeedback_id());
        Assertions.assertEquals(feedback.getRating(), response.getRating());
    }

    @Test
    void toFeedbackListMenteeResponseDTO_MentorAccountNotFound_ThrowsCustomInternalServerException() {
        // Arrange
        int mentorId = 1;
        int feedbackId = 3;

        Feedback feedback = new Feedback();
        feedback.setMentor_id(mentorId);
        feedback.setFeedback_id(feedbackId);
        feedback.setContent("feedbackContent");
        feedback.setRating(4.5);
        // Set other properties if necessary

        when(accountRepository.findById(mentorId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(CustomInternalServerException.class, () -> {
            mapper.toFeedbackListMenteeResponseDTO(feedback);
        });
    }

    @Test
    void testToFeedbackListMentorResponseDTO() throws CustomInternalServerException {
        int menteeId = 1;
        int feedbackId = 3;

        Feedback feedback = new Feedback();
        feedback.setMentee_id(menteeId);
        feedback.setFeedback_id(feedbackId);
        feedback.setContent("feedbackContent");
        feedback.setRating(4.5);
        // Set other properties if necessary

        Account menteeAccount = new Account();
        menteeAccount.setAccount_id(menteeId);
        menteeAccount.setAvatar("menteeAvatar".getBytes());
        menteeAccount.setEmail("menteeEmail");
        menteeAccount.setUsername("menteeUsername");
        // Set other mentee account properties if necessary

        when(accountRepository.findById(menteeId)).thenReturn(Optional.of(menteeAccount));
        when(commonUtils.imageToFrontEnd(menteeAccount.getAvatar())).thenReturn("convertedMenteeAvatar");

        // Act
        FeedbackListMentorResponseDTO response = mapper.toFeedbackListMentorResponseDTO(feedback);

        // Assert
        Assertions.assertEquals("convertedMenteeAvatar", response.getAvatar());
        Assertions.assertEquals("menteeEmail", response.getEmail());
        Assertions.assertEquals("menteeUsername", response.getUsername());
        Assertions.assertEquals(feedback.getContent(), response.getContent());
        Assertions.assertEquals(feedback.getTime(), response.getCreated_date());
        Assertions.assertEquals(feedbackId, response.getFeedback_id());
        Assertions.assertEquals(feedback.getRating(), response.getRating());
    }

    @Test
    void toFeedbackListMentorResponseDTO_MenteeAccountNotFound_ThrowsCustomInternalServerException() {
        // Arrange
        int menteeId = 1;
        int feedbackId = 3;

        Feedback feedback = new Feedback();
        feedback.setMentee_id(menteeId);
        feedback.setFeedback_id(feedbackId);
        feedback.setContent("feedbackContent");
        feedback.setRating(4.5);
        // Set other properties if necessary

        when(accountRepository.findById(menteeId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(CustomInternalServerException.class, () -> {
            mapper.toFeedbackListMentorResponseDTO(feedback);
        });
    }

    @Test
    void testToFeedbackListResponseDTO() throws CustomInternalServerException {
        int mentorId = 1;
        int menteeId = 2;
        int feedbackId = 3;

        Feedback feedback = new Feedback();
        feedback.setMentor_id(mentorId);
        feedback.setMentee_id(menteeId);
        feedback.setFeedback_id(feedbackId);
        feedback.setContent("feedbackContent");
        feedback.setRating(4.5);
        // Set other properties if necessary

        Account mentorAccount = new Account();
        mentorAccount.setAccount_id(mentorId);
        mentorAccount.setAvatar("mentorAvatar".getBytes());
        mentorAccount.setEmail("mentorEmail");
        mentorAccount.setUsername("mentorUsername");
        // Set other mentor account properties if necessary

        Account menteeAccount = new Account();
        menteeAccount.setAccount_id(menteeId);
        menteeAccount.setAvatar("menteeAvatar".getBytes());
        menteeAccount.setEmail("menteeEmail");
        menteeAccount.setUsername("menteeUsername");
        // Set other mentee account properties if necessary

        when(accountRepository.findById(mentorId)).thenReturn(Optional.of(mentorAccount));
        when(accountRepository.findById(menteeId)).thenReturn(Optional.of(menteeAccount));
        when(commonUtils.imageToFrontEnd(mentorAccount.getAvatar())).thenReturn("convertedMentorAvatar");
        when(commonUtils.imageToFrontEnd(menteeAccount.getAvatar())).thenReturn("convertedMenteeAvatar");

        // Act
        FeedbackListAdminResponseDTO response = mapper.toFeedbackListResponseDTO(feedback);

        // Assert
        Assertions.assertEquals("convertedMenteeAvatar", response.getMentee_avatar());
        Assertions.assertEquals("menteeEmail", response.getMentee_email());
        Assertions.assertEquals("menteeUsername", response.getMentee_username());
        Assertions.assertEquals("convertedMentorAvatar", response.getMentor_avatar());
        Assertions.assertEquals("mentorEmail", response.getMentor_email());
        Assertions.assertEquals("mentorUsername", response.getMentor_username());
        Assertions.assertEquals(feedback.getTime(), response.getCreated_date());
        Assertions.assertEquals("feedbackContent", response.getContent());
        Assertions.assertEquals(feedbackId, response.getFeedback_id());
        Assertions.assertEquals(4.5, response.getRating());
    }

    @Test
    void toFeedbackListResponseDTO_MentorOrMenteeAccountNotFound_ThrowsCustomInternalServerException() {
        // Arrange
        int mentorId = 1;
        int menteeId = 2;
        int feedbackId = 3;

        Feedback feedback = new Feedback();
        feedback.setMentor_id(mentorId);
        feedback.setMentee_id(menteeId);
        feedback.setFeedback_id(feedbackId);
        feedback.setContent("feedbackContent");
        feedback.setRating(4.5);
        // Set other properties if necessary

        when(accountRepository.findById(mentorId)).thenReturn(Optional.empty());
        when(accountRepository.findById(menteeId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(CustomInternalServerException.class, () -> {
            mapper.toFeedbackListResponseDTO(feedback);
        });
    }
}
