package com.hp.backend.model.time.mapper;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Times;
import com.hp.backend.model.time.dto.GetListTimeResponseDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseFindMentorDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TimeMapper {

    public GetListTimeResponseDTO toGetListTimeResponseDTO(Times time) {
        return GetListTimeResponseDTO.builder()
                .start_time(time.getStart_time().toString() + "-" + time.getEnd_time().toString())
                .time_id(time.getTime_id())
                .build();
    }

    public GetListTimeResponseFindMentorDTO toGetListTimeResponseFindMentorDTO(Date start_date,List<GetListTimeResponseDTO> times) {
        return GetListTimeResponseFindMentorDTO.builder().schedule_date(start_date).times(times).build();
    }

}
