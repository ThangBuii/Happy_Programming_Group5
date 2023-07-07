package com.hp.backend.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.model.time.dto.GetListTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseDTO;
import com.hp.backend.service.Time.TimeService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
@RequiredArgsConstructor
public class TimeController {
    private final TimeService timeService;

    @GetMapping("/time")
    List<GetListTimeResponseDTO> getListTime(@RequestBody GetListTimeRequestDTO getListTimeRequestDTO) {
        return timeService.getAllTime(getListTimeRequestDTO);
    }
}
