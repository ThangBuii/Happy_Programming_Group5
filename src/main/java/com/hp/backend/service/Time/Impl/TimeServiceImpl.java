package com.hp.backend.service.Time.Impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Times;
import com.hp.backend.model.time.dto.GetListTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseDTO;
import com.hp.backend.model.time.mapper.TimeMapper;
import com.hp.backend.repository.TimeRepository;
import com.hp.backend.service.Time.TimeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {
    private final TimeRepository timeRepository;
    private final TimeMapper timeMapper;

    @Override
    public List<GetListTimeResponseDTO> getAllTime(GetListTimeRequestDTO requestDTO) {
        List<GetListTimeResponseDTO> listTimeResponseDTOs = new ArrayList<>();
        List<Times> times = timeRepository.findStartTimeAndEndTime(requestDTO.getSession_id(),
                requestDTO.getStart_date());
        for (Times time : times) {
            listTimeResponseDTOs.add(timeMapper.toGetListTimeResponseDTO(time));
        }
        return listTimeResponseDTOs;
    }

}
