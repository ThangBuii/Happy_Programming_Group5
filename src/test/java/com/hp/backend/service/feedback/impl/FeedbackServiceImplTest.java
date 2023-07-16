package com.hp.backend.service.feedback.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Feedback;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.feedback.dto.FeedbackAddRequestDTO;
import com.hp.backend.model.feedback.dto.FeedbackListAdminResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMenteeResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMentorResponseDTO;
import com.hp.backend.model.feedback.mapper.FeedbackMapper;
import com.hp.backend.repository.FeedbackRepository;

public class FeedbackServiceImplTest {
    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private FeedbackMapper feedbackMapper;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @Test
    void testAddFeedback() throws CustomBadRequestException {
         MockitoAnnotations.openMocks(this); // Initialize mocks

        FeedbackAddRequestDTO feedbackAddRequestDTO = FeedbackAddRequestDTO.builder().build();
        int menteeId = 1;

        Feedback feedback = new Feedback();

        when(feedbackMapper.toFeedback(feedbackAddRequestDTO, menteeId)).thenReturn(feedback);

        // Act
        feedbackService.addFeedback(feedbackAddRequestDTO, menteeId);

        // Assert
        verify(feedbackRepository).save(feedback);
    }

    @Test
    void testDeleteFeedback_FeedbackExists() throws CustomBadRequestException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int feedbackId = 1;

        Feedback feedback = new Feedback();

        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.of(feedback));

        // Act
        feedbackService.deleteFeedback(feedbackId);

        // Assert
        verify(feedbackRepository, times(1)).deleteById(feedbackId);
    }

    @Test
    void testDeleteFeedback_FeedbackNotExists() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int feedbackId = 1;

        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.empty());

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            feedbackService.deleteFeedback(feedbackId);
        });

        assertEquals("404", exception.getErrors().get("errors").getCode());
        assertEquals("Feedback not exist", exception.getErrors().get("errors").getMessage());

        verify(feedbackRepository, never()).deleteById(anyInt());
    }

    @Test
    void testGetFeedbackByID_FeedbackExists() throws CustomBadRequestException, CustomInternalServerException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int feedbackId = 1;

        Feedback feedback = new Feedback();
        FeedbackListAdminResponseDTO expectedResponseDTO = FeedbackListAdminResponseDTO.builder().build();

        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.of(feedback));
        when(feedbackMapper.toFeedbackListResponseDTO(feedback)).thenReturn(expectedResponseDTO);

        // Act
        FeedbackListAdminResponseDTO actualResponseDTO = feedbackService.getFeedbackByID(feedbackId);

        // Assert
        assertEquals(expectedResponseDTO, actualResponseDTO);
    }

    @Test
    void testGetFeedbackByID_FeedbackNotExists() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int feedbackId = 1;

        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.empty());

        // Act & Assert
        CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () -> {
            feedbackService.getFeedbackByID(feedbackId);
        });

        assertEquals("404", exception.getErrors().get("errors").getCode());
        assertEquals("Feedback not exist", exception.getErrors().get("errors").getMessage());
    }

    @Test
    void testGetFeedbacks() throws CustomInternalServerException {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        List<Feedback> feedbacks = new ArrayList<>();
        Feedback feedback1 = new Feedback();
        Feedback feedback2 = new Feedback();
        feedbacks.add(feedback1);
        feedbacks.add(feedback2);

        List<FeedbackListAdminResponseDTO> expectedResponseDTOs = new ArrayList<>();
        FeedbackListAdminResponseDTO responseDTO1 = FeedbackListAdminResponseDTO.builder().build();
        FeedbackListAdminResponseDTO responseDTO2 = FeedbackListAdminResponseDTO.builder().build();
        expectedResponseDTOs.add(responseDTO1);
        expectedResponseDTOs.add(responseDTO2);

        when(feedbackRepository.findAll()).thenReturn(feedbacks);
        when(feedbackMapper.toFeedbackListResponseDTO(feedback1)).thenReturn(responseDTO1);
        when(feedbackMapper.toFeedbackListResponseDTO(feedback2)).thenReturn(responseDTO2);

        // Act
        List<FeedbackListAdminResponseDTO> actualResponseDTOs = feedbackService.getFeedbacks();

        // Assert
        assertEquals(expectedResponseDTOs.size(), actualResponseDTOs.size());
        assertEquals(expectedResponseDTOs.get(0), actualResponseDTOs.get(0));
        assertEquals(expectedResponseDTOs.get(1), actualResponseDTOs.get(1));
    }

    @Test
    void testGetFeedbacksMentee() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int menteeId = 1;

        List<Feedback> feedbacks = new ArrayList<>();
        Feedback feedback1 = new Feedback();
        Feedback feedback2 = new Feedback();
        feedbacks.add(feedback1);
        feedbacks.add(feedback2);

        List<FeedbackListMenteeResponseDTO> expectedResponseDTOs = new ArrayList<>();
        FeedbackListMenteeResponseDTO responseDTO1 = FeedbackListMenteeResponseDTO.builder().build();
        FeedbackListMenteeResponseDTO responseDTO2 = FeedbackListMenteeResponseDTO.builder().build();
        expectedResponseDTOs.add(responseDTO1);
        expectedResponseDTOs.add(responseDTO2);

        try {
            when(feedbackRepository.findByMenteeId(menteeId)).thenReturn(feedbacks);
            when(feedbackMapper.toFeedbackListMenteeResponseDTO(feedback1)).thenReturn(responseDTO1);
            when(feedbackMapper.toFeedbackListMenteeResponseDTO(feedback2)).thenReturn(responseDTO2);

            // Act
            List<FeedbackListMenteeResponseDTO> actualResponseDTOs = feedbackService.getFeedbacksMentee(menteeId);

            // Assert
            assertEquals(expectedResponseDTOs.size(), actualResponseDTOs.size());
            assertEquals(expectedResponseDTOs.get(0), actualResponseDTOs.get(0));
            assertEquals(expectedResponseDTOs.get(1), actualResponseDTOs.get(1));
        } catch (CustomInternalServerException e) {
            // Handle or fail the test based on your requirements
            fail("CustomInternalServerException occurred: " + e.getMessage());
        }
    }

    @Test
    void testGetFeedbacksMentor() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        int mentorId = 1;

        List<Feedback> feedbacks = new ArrayList<>();
        Feedback feedback1 = new Feedback();
        Feedback feedback2 = new Feedback();
        feedbacks.add(feedback1);
        feedbacks.add(feedback2);

        List<FeedbackListMentorResponseDTO> expectedResponseDTOs = new ArrayList<>();
        FeedbackListMentorResponseDTO responseDTO1 = FeedbackListMentorResponseDTO.builder().build();
        FeedbackListMentorResponseDTO responseDTO2 = FeedbackListMentorResponseDTO.builder().build();
        expectedResponseDTOs.add(responseDTO1);
        expectedResponseDTOs.add(responseDTO2);

        try {
            when(feedbackRepository.findByMentorId(mentorId)).thenReturn(feedbacks);
            when(feedbackMapper.toFeedbackListMentorResponseDTO(feedback1)).thenReturn(responseDTO1);
            when(feedbackMapper.toFeedbackListMentorResponseDTO(feedback2)).thenReturn(responseDTO2);

            // Act
            List<FeedbackListMentorResponseDTO> actualResponseDTOs = feedbackService.getFeedbacksMentor(mentorId);

            // Assert
            assertEquals(expectedResponseDTOs.size(), actualResponseDTOs.size());
            assertEquals(expectedResponseDTOs.get(0), actualResponseDTOs.get(0));
            assertEquals(expectedResponseDTOs.get(1), actualResponseDTOs.get(1));
        } catch (CustomInternalServerException e) {
            // Handle or fail the test based on your requirements
            fail("CustomInternalServerException occurred: " + e.getMessage());
        }
    }
}
