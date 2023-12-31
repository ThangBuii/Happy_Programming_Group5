package com.hp.backend.service.feedback;

import java.util.List;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.feedback.dto.FeedbackAddRequestDTO;
import com.hp.backend.model.feedback.dto.FeedbackListAdminResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMenteeResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMentorResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackMenteeFeedbackMentorListDTO;




public interface FeedbackService {

    public List<FeedbackListAdminResponseDTO> getFeedbacks() throws CustomInternalServerException;

    public void deleteFeedback(int id) throws CustomBadRequestException;

    public List<FeedbackListMenteeResponseDTO> getFeedbacksMentee(int id) throws CustomInternalServerException;

    public List<FeedbackListMentorResponseDTO> getFeedbacksMentor(int account_id) throws CustomInternalServerException;

    public void addFeedback(FeedbackAddRequestDTO feedback, int account_id) throws CustomBadRequestException;

    public FeedbackListAdminResponseDTO getFeedbackByID(int id) throws CustomBadRequestException, CustomInternalServerException;

    public List<FeedbackMenteeFeedbackMentorListDTO> getMenteeFeedbackMentorList(int account_id) throws CustomInternalServerException;

    

    
}
