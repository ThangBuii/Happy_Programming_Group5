package com.hp.backend.service.Time;

import java.util.List;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.time.dto.AddTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseDTO;

public interface TimeService {

    List<GetListTimeResponseDTO> getAllTime(GetListTimeRequestDTO requestDTO);

    void addTime(int account_id, AddTimeRequestDTO addTimeRequestDTO) throws CustomBadRequestException;

}
