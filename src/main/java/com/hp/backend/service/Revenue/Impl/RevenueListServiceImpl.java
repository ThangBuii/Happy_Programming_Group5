package com.hp.backend.service.Revenue.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.System_Revenue;
import com.hp.backend.model.revenue.dto.RevenueDTO;
import com.hp.backend.model.revenue.mapper.RevenueMapper;
import com.hp.backend.repository.SystemRevenueRepository;
import com.hp.backend.service.Revenue.RevenueListService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevenueListServiceImpl implements RevenueListService {
    private final RevenueMapper revenueMapper;

    private final SystemRevenueRepository systemRevenueRepository;

    @Override
    public List<RevenueDTO> getRevenueList() {
        List<RevenueDTO> revenueDTOs = new ArrayList<>();
        List<System_Revenue> revenues = systemRevenueRepository.findAll();
        for (System_Revenue revenue : revenues) {

            revenueDTOs.add(revenueMapper.toRevenueDTO(revenue));

        }

        return revenueDTOs;
    }

}
