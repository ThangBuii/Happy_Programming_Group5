package com.hp.backend.service.feedback.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

import com.hp.backend.entity.Feedback;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.feedback.dto.FeedbackAddRequestDTO;
import com.hp.backend.model.feedback.dto.FeedbackListAdminResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMenteeResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMentorResponseDTO;
import com.hp.backend.model.feedback.mapper.FeedbackMapper;
import com.hp.backend.repository.FeedbackRepository;
import com.hp.backend.service.feedback.FeedbackService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public List<FeedbackListAdminResponseDTO> getFeedbacks() throws CustomInternalServerException {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        List<FeedbackListAdminResponseDTO> feedbackListResponseDTOs = new ArrayList<>();

        for(Feedback feedback : feedbacks) {
            feedbackListResponseDTOs.add(feedbackMapper.toFeedbackListResponseDTO(feedback));
        }
        return feedbackListResponseDTOs;
    }

    @Override
    public void deleteFeedback(int id) throws CustomBadRequestException {
        Optional<Feedback> feedback = feedbackRepository.findById(id);

        if(!feedback.isPresent()){
            throw new CustomBadRequestException(CustomError.builder().message("Report not exist").code("404").build());
        }

        feedbackRepository.deleteById(id);
    }

    @Override
    public List<FeedbackListMenteeResponseDTO> getFeedbacksMentee(int mentee_id) throws CustomInternalServerException {
        List<Feedback> feedbacks = feedbackRepository.findByMenteeId(mentee_id);
        List<FeedbackListMenteeResponseDTO> feedbackListResponseDTOs = new ArrayList<>();

        for(Feedback feedback : feedbacks){
            feedbackListResponseDTOs.add(feedbackMapper.toFeedbackListMenteeResponseDTO(feedback));
        }
        return feedbackListResponseDTOs;
    }

    @Override
    public List<FeedbackListMentorResponseDTO> getFeedbacksMentor(int mentor_id) throws CustomInternalServerException {
        List<Feedback> feedbacks = feedbackRepository.findByMentorId(mentor_id);
        List<FeedbackListMentorResponseDTO> feedbackListResponseDTOs = new ArrayList<>();

        for(Feedback feedback : feedbacks){
            feedbackListResponseDTOs.add(feedbackMapper.toFeedbackListMentorResponseDTO(feedback));
        }
        return feedbackListResponseDTOs;
    }

    @Override
    public void addFeedback(FeedbackAddRequestDTO feedback, int mentee_id) {
        Feedback feedback1 = feedbackMapper.toFeedback(feedback,mentee_id);
        feedbackRepository.save(feedback1);
    }

    


    
}
