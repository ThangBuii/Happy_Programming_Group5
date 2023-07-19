package com.hp.backend.service.Revenue.Impl;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.System_Revenue;
import com.hp.backend.model.revenue.dto.RevenueDTO;
import com.hp.backend.model.revenue.mapper.RevenueMapper;
import com.hp.backend.repository.SystemRevenueRepository;

class RevenueListServiceImplTest {
    @Mock
    private SystemRevenueRepository systemRevenueRepository;

    @Mock
    private RevenueMapper revenueMapper;

    @InjectMocks
    private RevenueListServiceImpl revenueListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRevenueList() {
        // Arrange
        List<System_Revenue> revenues = new ArrayList<>();
        System_Revenue revenue = new System_Revenue();
        revenues.add(revenue);

        RevenueDTO revenueDTO = new RevenueDTO();
        when(systemRevenueRepository.findAll()).thenReturn(revenues);
        when(revenueMapper.toRevenueDTO(revenue)).thenReturn(revenueDTO);

        // Act
        List<RevenueDTO> result = revenueListService.getRevenueList();

        // Assert
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(revenueDTO, result.get(0));
        verify(systemRevenueRepository, times(1)).findAll();
        verify(revenueMapper, times(1)).toRevenueDTO(revenue);
    }
}
