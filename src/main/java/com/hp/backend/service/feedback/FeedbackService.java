package com.hp.backend.service.feedback;

import java.util.List;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.feedback.dto.FeedbackListAdminResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMenteeResponseDTO;




public interface FeedbackService {

    public List<FeedbackListAdminResponseDTO> getFeedbacks() throws CustomInternalServerException;

    public void deleteFeedback(int id) throws CustomBadRequestException;

    public List<FeedbackListMenteeResponseDTO> getFeedbacksMentee(int id) throws CustomInternalServerException;

    

    
}
