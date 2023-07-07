package com.hp.backend.service.Time;

import java.util.List;

import com.hp.backend.model.time.dto.GetListTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseDTO;

public interface TimeService {

    List<GetListTimeResponseDTO> getAllTime(GetListTimeRequestDTO requestDTO);

}
