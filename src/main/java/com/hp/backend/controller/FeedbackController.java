package com.hp.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.model.feedback.dto.FeedbackAddRequestDTO;
import com.hp.backend.model.feedback.dto.FeedbackListAdminResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMenteeResponseDTO;
import com.hp.backend.model.feedback.dto.FeedbackListMentorResponseDTO;
import com.hp.backend.service.feedback.FeedbackService;
import com.hp.backend.utils.JwtTokenUtil;

import antlr.Token;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/admin/feedbacks")
    public List<FeedbackListAdminResponseDTO> getFeedbacksAdmin() throws CustomInternalServerException{
        return feedbackService.getFeedbacks();
    }

    @DeleteMapping("/feedback/{id}")
    public void deleteFeedback(@PathVariable int id) throws CustomBadRequestException{
        feedbackService.deleteFeedback(id);
    }

    @GetMapping("/mentee/feedbacks")
    public List<FeedbackListMenteeResponseDTO> getFeedbacksMentee(HttpServletRequest request) throws CustomInternalServerException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return feedbackService.getFeedbacksMentee(tokenPayload.getAccount_id());
    }

    @GetMapping("mentor/feedbacks")
    public List<FeedbackListMentorResponseDTO> getFeedbacksMentor(HttpServletRequest request) throws CustomInternalServerException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return feedbackService.getFeedbacksMentor(tokenPayload.getAccount_id());
    }

    @PostMapping("mentor/feedback")
    public void addFeedbackForMentor(@RequestBody FeedbackAddRequestDTO feedback,HttpServletRequest request){
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        feedbackService.addFeedback(feedback,tokenPayload.getAccount_id());
    }

    

    
}
