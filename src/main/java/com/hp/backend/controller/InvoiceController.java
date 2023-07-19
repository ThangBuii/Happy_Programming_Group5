package com.hp.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.model.receipt.dto.InvoiceAdminDTO;
import com.hp.backend.model.receipt.dto.InvoiceDTO;
import com.hp.backend.model.receipt.dto.ViewInvoiceDTO;
import com.hp.backend.service.Invoice.InvoiceService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/mentor/invoice")
    List<InvoiceDTO> getInvoiceMentor(HttpServletRequest request) throws CustomNotFoundException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return invoiceService.getInvoiceMentor(tokenPayload.getAccount_id());
    }

    @GetMapping("/mentee/invoice")
    List<InvoiceDTO> getInvoiceMentee(HttpServletRequest request) throws CustomNotFoundException {
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        return invoiceService.getInvoiceMentee(tokenPayload.getAccount_id());
    }

    @GetMapping("/admin/invoice")
    List<InvoiceAdminDTO> getInvoiceAdmin() throws CustomNotFoundException {
        return invoiceService.getInvoiceAdmin();
    }

    @GetMapping("/invoice/{id}")
    ViewInvoiceDTO getInvoiceById(@PathVariable int id) throws CustomBadRequestException {
        return invoiceService.findInvoiceById(id);
    }

}
