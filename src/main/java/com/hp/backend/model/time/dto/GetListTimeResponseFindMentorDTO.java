package com.hp.backend.model.time.dto;

import java.sql.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetListTimeResponseFindMentorDTO {
    private Date schedule_date;
    private List<GetListTimeResponseDTO> times;
}
