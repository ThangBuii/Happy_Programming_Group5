package com.hp.backend.service.feedback.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.hp.backend.entity.Feedback;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.feedback.dto.FeedbackListAdminResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMenteeResponseDTO;
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
        Feedback feedback = feedbackRepository.findById(id).get();

        if(feedback == null){
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

    


    
}
