package com.hp.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.model.time.dto.AddTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseFindMentorDTO;
import com.hp.backend.service.Time.TimeService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
@RequiredArgsConstructor
public class TimeController {
    private final TimeService timeService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/time")
    List<GetListTimeResponseDTO> getListTime(@RequestBody GetListTimeRequestDTO getListTimeRequestDTO) {
        return timeService.getAllTime(getListTimeRequestDTO);
    }

    @PostMapping("mentor/time")
    public void addTime(@RequestBody AddTimeRequestDTO addTimeRequestDTO, HttpServletRequest request)
            throws CustomBadRequestException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        // Chuyển đổi chuỗi thời gian thành LocalTime

        timeService.addTime(tokenPayload.getAccount_id(), addTimeRequestDTO);
    }

    @GetMapping("/times")
    public List<GetListTimeResponseFindMentorDTO> getListTimeResponseFindMentorDTOs(@RequestParam int sessionId) {
        return timeService.getListTimeResponseFindMentorDTOs(sessionId);
    }
}
