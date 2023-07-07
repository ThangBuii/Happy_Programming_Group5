package com.hp.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.model.revenue.dto.RevenueDTO;
import com.hp.backend.service.Revenue.RevenueListService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
@RequiredArgsConstructor
public class RevenueController {
    private final RevenueListService revenueListService;
    @GetMapping("admin/revenue")
    public List<RevenueDTO> getListRevenue() {
        return revenueListService.getRevenueList();
    }
}
