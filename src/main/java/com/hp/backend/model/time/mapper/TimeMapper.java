package com.hp.backend.model.time.mapper;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Booking;
import com.hp.backend.entity.Session;
import com.hp.backend.entity.Times;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.time.dto.GetListTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseDTO;
import com.hp.backend.repository.TimeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TimeMapper {
    private final TimeRepository timeRepository;

    public static GetListTimeResponseDTO toGetListTimeResponseDTO(Times time) {
        return GetListTimeResponseDTO.builder()
                .start_time(time.getStart_time().toString() + "-" + time.getEnd_time().toString())
                .time_id(time.getTime_id())
                .build();
    }

    // public GetListTimeRequestDTO findSessionIdAndStartDate(int id) {
    //     Times time = timeRepository.findById(id).get();
    //     return GetListTimeRequestDTO.builder().session_id(time.getSession().getSession_id()).start_date(time.getStart_date())
    //             .build();
    // }

}
